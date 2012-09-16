package com.legendshop.cas.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasig.cas.services.RegisteredService;
import org.jasig.cas.services.RegisteredServiceImpl;
import org.jasig.cas.services.ServiceRegistryDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.legendshop.cas.CasConstant;
import com.legendshop.cas.model.Application;

/**
 * To implement the Service Registry.
 * 
 * @author George Guo
 * 
 */
public class ServiceRegistryDaoImpl implements ServiceRegistryDao {

	private static Log logger = LogFactory.getLog(ServiceRegistryDaoImpl.class);

	private JdbcTemplate jdbcTemplate;

	private String[] allowedAttributes = new String[0];

	@Override
	public boolean delete(RegisteredService registeredService) {
		try {
			jdbcTemplate
					.update("delete from ls_cas_application where id=?", new Object[] { registeredService.getId() });
		} catch (Exception e) {
			logger.error("Error when delete the service registry", e);
			return false;
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegisteredService> load() {
		List<RegisteredService> registeredServiceList = new ArrayList<RegisteredService>();
		try {
			List<Application> applications = (List<Application>) jdbcTemplate.query(
					"select * from ls_cas_application where status=?", new Object[] { CasConstant.APP_STATUS_ACTIVE },
					new ApplicationRowMapper());
			if (applications != null && !applications.isEmpty()) {
				for (Application app : applications) {
					RegisteredServiceImpl registeredServiceImpl = new RegisteredServiceImpl();

					registeredServiceImpl.setId(app.getId());
					registeredServiceImpl.setServiceId(app.getUrlExpression() != null ? app.getUrlExpression() : app
							.getHomePage());
					registeredServiceImpl.setName(app.getApplicationName());
					registeredServiceImpl.setDescription(app.getDescription());
					registeredServiceImpl.setAllowedAttributes(this.allowedAttributes);

					registeredServiceList.add(registeredServiceImpl);
				}
			}
		} catch (Exception e) {
			logger.error("Error when load the registered services", e);
		}
		return registeredServiceList;
	}

	class ApplicationRowMapper implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int index) throws SQLException {
			Application application = new Application();
			application.setId(rs.getLong("id"));
			application.setApplicationName(rs.getString("app_name"));
			application.setApplicationId(rs.getString("app_no"));
			application.setHomePage(rs.getString("service_url"));
			application.setUrlExpression(rs.getString("service_url_expression"));
			application.setDescription(rs.getString("descriptions"));

			return application;
		}
	}

	@Override
	public RegisteredService save(final RegisteredService registeredService) {
		if (registeredService == null) {
			return registeredService;
		}

		try {
			if (registeredService.getId() <= 0) {
				addRegisterService(registeredService);
			} else {
				String sql = "update ls_cas_application set app_no=?, app_name=?, descriptions=?, service_url=? where id=?";
				jdbcTemplate.update(
						sql,
						new Object[] {
								registeredService.getName(),
								registeredService.getName(),
								registeredService.getDescription() == null ? registeredService.getName()
										: registeredService.getDescription(), registeredService.getServiceId(),
								registeredService.getId() });
			}

		} catch (Exception e) {
			logger.error("Error when save the registered service", e);
		}
		return registeredService;
	}

	/**
	 * e.g. 'http://test.com' will generate a expression like
	 * 'http://test.com/**', following the Ant path pattern.
	 * 
	 * @param url
	 * @return
	 */
	private String generateUrlExpression(String url) {
		if (url == null) {
			return null;
		}
		if (url.endsWith("/**")) {
			return url;
		}
		if (url.endsWith("/")) {
			return url + "**";
		}
		return url + "/**";
	}

	private void addRegisterService(final RegisteredService registeredService) {
		PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				String sql = "insert into ls_cas_application(app_no,app_name,descriptions,service_url,service_url_expression) values(?,?,?,?,?)";
				PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

				preparedStatement.setString(1, registeredService.getName());
				preparedStatement.setString(2, registeredService.getName());
				preparedStatement.setString(3, registeredService.getDescription() == null ? registeredService.getName()
						: registeredService.getDescription());
				preparedStatement.setString(4, registeredService.getServiceId());
				preparedStatement.setString(5, generateUrlExpression(registeredService.getServiceId()));

				return preparedStatement;
			}
		};
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(preparedStatementCreator, keyHolder);

		// set the auto generated id value
		if (registeredService instanceof RegisteredServiceImpl) {
			((RegisteredServiceImpl) registeredService).setId(keyHolder.getKey().longValue());

			if (logger.isDebugEnabled()) {
				logger.debug("Set registered service id (app id) to: [" + registeredService.getId()
						+ "] successfully when save entity");
			}
		}
	}

	@Override
	public RegisteredService findServiceById(long id) {
		if (id <= 0) {
			return null;
		}

		try {
			Application app = (Application) jdbcTemplate.queryForObject("select * from ls_cas_application where id=?",
					new Object[] { id }, new ApplicationRowMapper());
			if (app == null) {
				return null;
			}
			RegisteredServiceImpl registeredServiceImpl = new RegisteredServiceImpl();
			registeredServiceImpl.setId(app.getId());
			registeredServiceImpl.setServiceId(app.getUrlExpression());
			registeredServiceImpl.setName(app.getApplicationName());
			registeredServiceImpl.setDescription(app.getDescription());
			registeredServiceImpl.setAllowedAttributes(this.allowedAttributes);

			return registeredServiceImpl;
		} catch (Exception e) {
			logger.error("Error when find service by id: " + id, e);
		}
		return null;
	}

	/**
	 * @param jdbcTemplate
	 *            the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * @param allowedAttributes
	 *            the allowedAttributes to set
	 */
	public void setAllowedAttributes(String[] allowedAttributes) {
		this.allowedAttributes = allowedAttributes;
	}
}
