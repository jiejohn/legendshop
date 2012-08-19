/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.model.dynamic;
/**
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
import java.io.Serializable;

/**
 * The Class Item.
 */
public class Item implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7793296752968339250L;

	/** The key. */
	private String key = null;

    /** The value. */
    private String value = null;
    
    /**
	 * Instantiates a new item.
	 */
    public Item() {
	}

    /**
	 * Instantiates a new item.
	 * 
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
    public Item(String key, String value) {
		this.key = key;
		this.value = value;
	}
    
    /**
	 * Instantiates a new item.
	 * 
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
    public Item(Long key, String value) {
		this.key = String.valueOf(key);
		this.value = value;
	}

	/**
	 * Gets the key.
	 * 
	 * @return the key
	 */
	public String getKey() {
        return key;
    }

    /**
	 * Sets the key.
	 * 
	 * @param key
	 *            the new key
	 */
    public void setKey(String key) {
        this.key = key;
    }

    /**
	 * Gets the value.
	 * 
	 * @return the value
	 */
    public String getValue() {
        return value;
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
}
