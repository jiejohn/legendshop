
package com.legendshop.ws.service.sub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>exportOrderService complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="exportOrderService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sub" type="{http://sub.service.ws.legendshop.com}sub" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "exportOrderService", propOrder = {
    "sub"
})
public class ExportOrderService {

    protected Sub sub;

    /**
     * 获取sub属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Sub }
     *     
     */
    public Sub getSub() {
        return sub;
    }

    /**
     * 设置sub属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Sub }
     *     
     */
    public void setSub(Sub value) {
        this.sub = value;
    }

}
