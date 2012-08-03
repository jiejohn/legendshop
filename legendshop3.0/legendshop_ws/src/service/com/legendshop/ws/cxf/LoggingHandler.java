/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.ws.cxf;

import java.io.PrintStream;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

/*
 * This simple SOAPHandler will output the contents of incoming
 * and outgoing messages.
 */
/**
 * The Class LoggingHandler.
 */
public class LoggingHandler implements SOAPHandler<SOAPMessageContext> {

    /** The out. */
    private PrintStream out;

    /**
	 * Instantiates a new logging handler.
	 */
    public LoggingHandler() {
        setLogStream(System.out);
    }

    /**
	 * Sets the log stream.
	 * 
	 * @param ps
	 *            the new log stream
	 */
    protected final void setLogStream(PrintStream ps) {
        out = ps;
    }

    /**
	 * Inits the.
	 * 
	 * @param c
	 *            the c
	 */
    public void init(Map c) {
        System.out.println("LoggingHandler : init() Called....");
    }

    /* (non-Javadoc)
     * @see javax.xml.ws.handler.soap.SOAPHandler#getHeaders()
     */
    public Set<QName> getHeaders() {
        return null;
    }

    /* (non-Javadoc)
     * @see javax.xml.ws.handler.Handler#handleMessage(javax.xml.ws.handler.MessageContext)
     */
    public boolean handleMessage(SOAPMessageContext smc) {
        System.out.println("LoggingHandler : handleMessage Called....");
        logToSystemOut(smc);
        return true;
    }

    /* (non-Javadoc)
     * @see javax.xml.ws.handler.Handler#handleFault(javax.xml.ws.handler.MessageContext)
     */
    public boolean handleFault(SOAPMessageContext smc) {
        System.out.println("LoggingHandler : handleFault Called....");
        logToSystemOut(smc);
        return true;
    }

    // nothing to clean up
    /* (non-Javadoc)
     * @see javax.xml.ws.handler.Handler#close(javax.xml.ws.handler.MessageContext)
     */
    public void close(MessageContext messageContext) {
        System.out.println("LoggingHandler : close() Called....");
    }

    // nothing to clean up
    /**
	 * Destroy.
	 */
    public void destroy() {
        System.out.println("LoggingHandler : destroy() Called....");
    }

    /*
     * Check the MESSAGE_OUTBOUND_PROPERTY in the context
     * to see if this is an outgoing or incoming message.
     * Write a brief message to the print stream and
     * output the message. The writeTo() method can throw
     * SOAPException or IOException
     */
    /**
	 * Log to system out.
	 * 
	 * @param smc
	 *            the smc
	 */
    protected void logToSystemOut(SOAPMessageContext smc) {
        Boolean outboundProperty = (Boolean)
            smc.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        if (outboundProperty.booleanValue()) {
            out.println("\nOutbound message:");
        } else {
            out.println("\nInbound message:");
        }   

         SOAPMessage message = smc.getMessage();
        try {
        	System.out.println("tst");
            message.writeTo(out);
            out.println();
        } catch (Exception e) {
            out.println("Exception in handler: " + e);
        }
    }
}
