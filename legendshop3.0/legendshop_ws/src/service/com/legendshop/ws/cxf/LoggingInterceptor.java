/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.ws.cxf;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.saaj.SAAJInInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.NodeList;

/**
 * The Class LoggingInterceptor.
 */
public class LoggingInterceptor extends AbstractPhaseInterceptor<SoapMessage> {  
    
    /** The logger. */
    Log logger = LogFactory.getLog(LoggingInterceptor.class);  
      
    /** The saaj in. */
    private final SAAJInInterceptor saajIn = new SAAJInInterceptor();  
      
    /**
	 * Instantiates a new logging interceptor.
	 */
    public LoggingInterceptor() {
        super(Phase.PRE_PROTOCOL);  
        getAfter().add(SAAJInInterceptor.class.getName());  
    }  
  
    /* (non-Javadoc)
     * @see org.apache.cxf.interceptor.Interceptor#handleMessage(org.apache.cxf.message.Message)
     */
    @Override
	public void handleMessage(SoapMessage message) throws Fault {  
        try {  
            logger.info("记录访问web services日志");  
            SOAPMessage doc = message.getContent(SOAPMessage.class);  
		    System.out.println("getHeaders = "+message.getHeaders());
            System.out.println("doc = "+doc);
            if (doc == null) {  
                saajIn.handleMessage(message);  
                doc = message.getContent(SOAPMessage.class);  
            }  
            SOAPHeader header = doc.getSOAPHeader();  
            if (header == null) {  
                return;  
            }  
  
            NodeList nodes = header.getElementsByTagName("proc:user");  
            for(int i=0; i<nodes.getLength(); i++) {  
                System.out.println(nodes.item(i).getLocalName()+"----"+nodes.item(i).getTextContent());  
            }  
              
        } catch (SOAPException e) {  
            e.printStackTrace();  
        }  
         
    }  
}  
