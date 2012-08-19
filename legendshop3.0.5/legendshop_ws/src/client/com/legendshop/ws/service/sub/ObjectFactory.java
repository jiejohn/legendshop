
package com.legendshop.ws.service.sub;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.legendshop.ws.service.sub package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ExportOrderService_QNAME = new QName("http://sub.service.ws.legendshop.com", "exportOrderService");
    private final static QName _ExportOrderServiceResponse_QNAME = new QName("http://sub.service.ws.legendshop.com", "exportOrderServiceResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.legendshop.ws.service.sub
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ExportOrderService }
     * 
     */
    public ExportOrderService createExportOrderService() {
        return new ExportOrderService();
    }

    /**
     * Create an instance of {@link ExportOrderServiceResponse }
     * 
     */
    public ExportOrderServiceResponse createExportOrderServiceResponse() {
        return new ExportOrderServiceResponse();
    }

    /**
     * Create an instance of {@link Sub }
     * 
     */
    public Sub createSub() {
        return new Sub();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExportOrderService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sub.service.ws.legendshop.com", name = "exportOrderService")
    public JAXBElement<ExportOrderService> createExportOrderService(ExportOrderService value) {
        return new JAXBElement<ExportOrderService>(_ExportOrderService_QNAME, ExportOrderService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExportOrderServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sub.service.ws.legendshop.com", name = "exportOrderServiceResponse")
    public JAXBElement<ExportOrderServiceResponse> createExportOrderServiceResponse(ExportOrderServiceResponse value) {
        return new JAXBElement<ExportOrderServiceResponse>(_ExportOrderServiceResponse_QNAME, ExportOrderServiceResponse.class, null, value);
    }

}
