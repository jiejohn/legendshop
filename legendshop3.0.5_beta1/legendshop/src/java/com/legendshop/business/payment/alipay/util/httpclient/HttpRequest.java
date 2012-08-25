/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.payment.alipay.util.httpclient;

import org.apache.commons.httpclient.NameValuePair;

/* *
 *类名：HttpRequest
 *功能：Http请求对象的封装
 *详细：封装Http请求
 *版本：3.2
 *日期：2011-03-17
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

/**
 * The Class HttpRequest.
 */
public class HttpRequest {

    /** HTTP GET method. */
    public static final String METHOD_GET        = "GET";

    /** HTTP POST method. */
    public static final String METHOD_POST       = "POST";

    /** 待请求的url. */
    private String             url               = null;

    /** 默认的请求方式. */
    private String             method            = METHOD_POST;

    /** The timeout. */
    private int                timeout           = 0;

    /** The connection timeout. */
    private int                connectionTimeout = 0;

    /** Post方式请求时组装好的参数值对. */
    private NameValuePair[]    parameters        = null;

    /** Get方式请求时对应的参数. */
    private String             queryString       = null;

    /** 默认的请求编码方式. */
    private String             charset           = "utf-8";

    /** 请求发起方的ip地址. */
    private String             clientIp;

    /** 请求返回的方式. */
    private HttpResultType     resultType        = HttpResultType.BYTES;

    /**
	 * Instantiates a new http request.
	 * 
	 * @param resultType
	 *            the result type
	 */
    public HttpRequest(HttpResultType resultType) {
        super();
        this.resultType = resultType;
    }

    /**
	 * Gets the client ip.
	 * 
	 * @return Returns the clientIp.
	 */
    public String getClientIp() {
        return clientIp;
    }

    /**
	 * Sets the client ip.
	 * 
	 * @param clientIp
	 *            The clientIp to set.
	 */
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    /**
	 * Gets the parameters.
	 * 
	 * @return the parameters
	 */
    public NameValuePair[] getParameters() {
        return parameters;
    }

    /**
	 * Sets the parameters.
	 * 
	 * @param parameters
	 *            the new parameters
	 */
    public void setParameters(NameValuePair[] parameters) {
        this.parameters = parameters;
    }

    /**
	 * Gets the query string.
	 * 
	 * @return the query string
	 */
    public String getQueryString() {
        return queryString;
    }

    /**
	 * Sets the query string.
	 * 
	 * @param queryString
	 *            the new query string
	 */
    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    /**
	 * Gets the url.
	 * 
	 * @return the url
	 */
    public String getUrl() {
        return url;
    }

    /**
	 * Sets the url.
	 * 
	 * @param url
	 *            the new url
	 */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
	 * Gets the method.
	 * 
	 * @return the method
	 */
    public String getMethod() {
        return method;
    }

    /**
	 * Sets the method.
	 * 
	 * @param method
	 *            the new method
	 */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
	 * Gets the connection timeout.
	 * 
	 * @return the connection timeout
	 */
    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    /**
	 * Sets the connection timeout.
	 * 
	 * @param connectionTimeout
	 *            the new connection timeout
	 */
    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    /**
	 * Gets the timeout.
	 * 
	 * @return the timeout
	 */
    public int getTimeout() {
        return timeout;
    }

    /**
	 * Sets the timeout.
	 * 
	 * @param timeout
	 *            the new timeout
	 */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    /**
	 * Gets the charset.
	 * 
	 * @return Returns the charset.
	 */
    public String getCharset() {
        return charset;
    }

    /**
	 * Sets the charset.
	 * 
	 * @param charset
	 *            The charset to set.
	 */
    public void setCharset(String charset) {
        this.charset = charset;
    }

    /**
	 * Gets the result type.
	 * 
	 * @return the result type
	 */
    public HttpResultType getResultType() {
        return resultType;
    }

    /**
	 * Sets the result type.
	 * 
	 * @param resultType
	 *            the new result type
	 */
    public void setResultType(HttpResultType resultType) {
        this.resultType = resultType;
    }

}
