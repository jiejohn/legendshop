/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.model.entity;

/**
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class SystemParameter implements java.io.Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3188172953619133451L;
	
	/** The name. */
	private String name;
	
	/** The value. */
	private String value;
	
	/** The memo. */
	private String memo;
	
	/** The type. */
	private String type;
	
	/** The optional. */
	private String optional;
	
	/** The change online. */
	private String changeOnline;//是否可以线上修改
	
	/** The display order. */
	private Integer  displayOrder;
	
	private String groupId;

	// Constructors

	/**
	 * default constructor.
	 */
	public SystemParameter() {
	}

	// Property accessors

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * Sets the value.
	 * 
	 * @param value
	 *            the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Gets the memo.
	 * 
	 * @return the memo
	 */
	public String getMemo() {
		return this.memo;
	}

	/**
	 * Sets the memo.
	 * 
	 * @param memo
	 *            the new memo
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 * 
	 * @param type
	 *            the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the optional.
	 * 
	 * @return the optional
	 */
	public String getOptional() {
		return optional;
	}

	/**
	 * Sets the optional.
	 * 
	 * @param optional
	 *            the new optional
	 */
	public void setOptional(String optional) {
		this.optional = optional;
	}

	/**
	 * Gets the display order.
	 * 
	 * @return the display order
	 */
	public Integer getDisplayOrder() {
		return displayOrder;
	}

	/**
	 * Sets the display order.
	 * 
	 * @param displayOrder
	 *            the new display order
	 */
	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	/**
	 * Gets the change online.
	 * 
	 * @return the change online
	 */
	public String getChangeOnline() {
		return changeOnline;
	}

	/**
	 * Sets the change online.
	 * 
	 * @param changeOnline
	 *            the new change online
	 */
	public void setChangeOnline(String changeOnline) {
		this.changeOnline = changeOnline;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

}