/**
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.model.entity;

import java.util.Date;

/**
 * 购物车对象.
 */
public class Basket implements java.io.Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2428049829073308967L;

	/** The total. */
	private Double total;//=单价*数量
    
    /** The carriage. */
    private Double carriage;//运费
    
    /** The attribute. */
    private String attribute; //产品属性
    // Fields

    /** The basket id. */
    private Long basketId;

    /** The prod id. */
    private Long prodId;
    
    /** The pic. */
    private String pic;

    /** The user name. */
    private String userName;

    /** The basket count. */
    private Integer basketCount;

    /** The basket check. */
    private String basketCheck; //是否已经生成订单, false: 只是存在于购物车， true： 已经生成了订单，saveto时设置为true

    /** 产品名称. */
    private String prodName;

    /** The price. */
    private Double price;

    /** The cash. */
    private Double cash;

    /** The sub number. */
    private String subNumber;

    /** The basket date. */
    private Date basketDate;
    
    /** The last update date. */
    private Date lastUpdateDate;

    /** The shop name. */
    private String shopName;
    
	/**
	 * Instantiates a new basket.
	 */
	public Basket() {
    }

    /**
	 * Gets the basket id.
	 * 
	 * @return the basket id
	 */
    public Long getBasketId() {
        return this.basketId;
    }

    /**
	 * Sets the basket id.
	 * 
	 * @param basketId
	 *            the new basket id
	 */
    public void setBasketId(Long basketId) {
        this.basketId = basketId;
    }

    /**
	 * Gets the prod id.
	 * 
	 * @return the prod id
	 */
    public Long getProdId() {
        return this.prodId;
    }

    /**
	 * Sets the prod id.
	 * 
	 * @param prodId
	 *            the new prod id
	 */
    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }

    /**
	 * Gets the user name.
	 * 
	 * @return the user name
	 */
    public String getUserName() {
        return this.userName;
    }

    /**
	 * Sets the user name.
	 * 
	 * @param userName
	 *            the new user name
	 */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
	 * Gets the basket count.
	 * 
	 * @return the basket count
	 */
    public Integer getBasketCount() {
        return this.basketCount;
    }

    /**
	 * Sets the basket count.
	 * 
	 * @param basketCount
	 *            the new basket count
	 */
    public void setBasketCount(Integer basketCount) {
        this.basketCount = basketCount;
    }

    /**
	 * Gets the basket check.
	 * 
	 * @return the basket check
	 */
    public String getBasketCheck() {
        return this.basketCheck;
    }

    /**
	 * Sets the basket check.
	 * 
	 * @param basketCheck
	 *            the new basket check
	 */
    public void setBasketCheck(String basketCheck) {
        this.basketCheck = basketCheck;
    }

    /**
	 * Gets the prod name.
	 * 
	 * @return the prod name
	 */
    public String getProdName() {
        return this.prodName;
    }

    /**
	 * Sets the prod name.
	 * 
	 * @param prodName
	 *            the new prod name
	 */
    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    /**
	 * Gets the price.
	 * 
	 * @return the price
	 */
    public Double getPrice() {
        return this.price;
    }

    /**
	 * Sets the price.
	 * 
	 * @param price
	 *            the new price
	 */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
	 * Gets the cash.
	 * 
	 * @return the cash
	 */
    public Double getCash() {
        return this.cash;
    }

    /**
	 * Sets the cash.
	 * 
	 * @param cash
	 *            the new cash
	 */
    public void setCash(Double cash) {
        this.cash = cash;
    }

    /**
	 * Gets the sub number.
	 * 
	 * @return the sub number
	 */
    public String getSubNumber() {
        return this.subNumber;
    }

    /**
	 * Sets the sub number.
	 * 
	 * @param subNumber
	 *            the new sub number
	 */
    public void setSubNumber(String subNumber) {
        this.subNumber = subNumber;
    }

    /**
	 * Gets the basket date.
	 * 
	 * @return the basket date
	 */
    public Date getBasketDate() {
        return this.basketDate;
    }

    /**
	 * Sets the basket date.
	 * 
	 * @param basketDate
	 *            the new basket date
	 */
    public void setBasketDate(Date basketDate) {
        this.basketDate = basketDate;
    }

    /**
	 * Gets the total.
	 * 
	 * @return the total
	 */
    public Double getTotal() {
        return total;
    }

    /**
	 * Sets the total.
	 * 
	 * @param total
	 *            the new total
	 */
    public void setTotal(Double total) {
        this.total = total;
    }

    /**
	 * Gets the shop name.
	 * 
	 * @return the shop name
	 */
    public String getShopName() {
        return shopName;
    }

    /**
	 * Sets the shop name.
	 * 
	 * @param shopName
	 *            the new shop name
	 */
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

	/**
	 * Gets the carriage.
	 * 
	 * @return the carriage
	 */
	public Double getCarriage() {
		return carriage;
	}

	/**
	 * Sets the carriage.
	 * 
	 * @param carriage
	 *            the new carriage
	 */
	public void setCarriage(Double carriage) {
		this.carriage = carriage;
	}

	/**
	 * Gets the attribute.
	 * 
	 * @return the attribute
	 */
	public String getAttribute() {
		return attribute;
	}

	/**
	 * Sets the attribute.
	 * 
	 * @param attribute
	 *            the new attribute
	 */
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	/**
	 * Gets the pic.
	 * 
	 * @return the pic
	 */
	public String getPic() {
		return pic;
	}

	/**
	 * Sets the pic.
	 * 
	 * @param pic
	 *            the new pic
	 */
	public void setPic(String pic) {
		this.pic = pic;
	}

	/**
	 * Gets the last update date.
	 * 
	 * @return the last update date
	 */
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	/**
	 * Sets the last update date.
	 * 
	 * @param lastUpdateDate
	 *            the new last update date
	 */
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

}