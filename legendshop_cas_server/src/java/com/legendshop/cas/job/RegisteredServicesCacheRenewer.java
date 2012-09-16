package com.legendshop.cas.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasig.cas.services.ReloadableServicesManager;
import org.jasig.cas.services.ServicesManager;

/**
 * To renew the registered services, which is kept in the memory.
 * 
 * @author George Guo
 * 
 */
public class RegisteredServicesCacheRenewer {

	private static Log logger = LogFactory.getLog(RegisteredServicesCacheRenewer.class);
	private ServicesManager servicesManager;

	public void refresh() {
		if (servicesManager == null) {
			return;
		}

		if (servicesManager instanceof ReloadableServicesManager) {
			if (logger.isDebugEnabled()) {
				logger.debug("Start to refresh the registered service...");
			}
			ReloadableServicesManager reloadableServicesManager = (ReloadableServicesManager) servicesManager;
			reloadableServicesManager.reload();

			if (logger.isDebugEnabled()) {
				logger.debug("Finished to refresh the registered service...");
			}
		} else {
			logger.warn("No need to refresh since servicesManager class is: " + servicesManager.getClass().getName());
		}

	}

	/**
	 * @param servicesManager
	 *            the servicesManager to set
	 */
	public void setServicesManager(ServicesManager servicesManager) {
		this.servicesManager = servicesManager;
	}

}
