
package com.legendshop.ws.service.product;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.legendshop.ws.service.product package. 
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

    private final static QName _ImportProductService_QNAME = new QName("http://product.service.ws.legendshop.com", "importProductService");
    private final static QName _ImportProductServiceResponse_QNAME = new QName("http://product.service.ws.legendshop.com", "importProductServiceResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.legendshop.ws.service.product
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ImportProductService }
     * 
     */
    public ImportProductService createImportProductService() {
        return new ImportProductService();
    }

    /**
     * Create an instance of {@link ImportProductServiceResponse }
     * 
     */
    public ImportProductServiceResponse createImportProductServiceResponse() {
        return new ImportProductServiceResponse();
    }

    /**
     * Create an instance of {@link Product }
     * 
     */
    public Product createProduct() {
        return new Product();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ImportProductService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://product.service.ws.legendshop.com", name = "importProductService")
    public JAXBElement<ImportProductService> createImportProductService(ImportProductService value) {
        return new JAXBElement<ImportProductService>(_ImportProductService_QNAME, ImportProductService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ImportProductServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://product.service.ws.legendshop.com", name = "importProductServiceResponse")
    public JAXBElement<ImportProductServiceResponse> createImportProductServiceResponse(ImportProductServiceResponse value) {
        return new JAXBElement<ImportProductServiceResponse>(_ImportProductServiceResponse_QNAME, ImportProductServiceResponse.class, null, value);
    }

}
