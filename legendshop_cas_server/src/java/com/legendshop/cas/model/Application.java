package com.legendshop.cas.model;

/**
 * In most case, application means the same thing as the Service in CAS.
 * 
 * @author George Guo
 * 
 */
public class Application {
	/**
	 * The unique identity of the application, means the same as the
	 * RegisterService identify
	 */
	private long id;

	/**
	 * the application identity which should be recognized by human being. It's
	 * recommended that ensure the application id be unique.
	 */
	private String applicationId;

	/**
	 * the application name which contains the short description(e.g. the
	 * Chinese name of this application)
	 */
	private String applicationName;

	/**
	 * the standard index URL of a web application or any entry point URI of an
	 * application.
	 */
	private String homePage;

	/**
	 * the application URL expression
	 */
	private String urlExpression;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * short description of the application
	 */
	private String description;

	public String getUrlExpression() {
		return urlExpression;
	}

	public void setUrlExpression(String urlExpression) {
		this.urlExpression = urlExpression;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}
}
