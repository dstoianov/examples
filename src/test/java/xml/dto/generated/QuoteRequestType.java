//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.05.14 at 06:15:02 PM EEST 
//


package xml.dto.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QuoteRequestType complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="QuoteRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Drivers" type="{}DriversType"/>
 *         &lt;element name="Vehicles" type="{}VehiclesType"/>
 *         &lt;element name="Insurance" type="{}InsuranceType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="QuoteType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QuoteRequestType", propOrder = {
        "drivers",
        "vehicles",
        "insurance"
})
public class QuoteRequestType {

    @XmlElement(name = "Drivers", required = true)
    protected DriversType drivers;
    @XmlElement(name = "Vehicles", required = true)
    protected VehiclesType vehicles;
    @XmlElement(name = "Insurance", required = true)
    protected InsuranceType insurance;
    @XmlAttribute(name = "QuoteType")
    protected String quoteType;

    /**
     * Gets the value of the drivers property.
     *
     * @return possible object is
     * {@link DriversType }
     */
    public DriversType getDrivers() {
        return drivers;
    }

    /**
     * Sets the value of the drivers property.
     *
     * @param value allowed object is
     *              {@link DriversType }
     */
    public void setDrivers(DriversType value) {
        this.drivers = value;
    }

    /**
     * Gets the value of the vehicles property.
     *
     * @return possible object is
     * {@link VehiclesType }
     */
    public VehiclesType getVehicles() {
        return vehicles;
    }

    /**
     * Sets the value of the vehicles property.
     *
     * @param value allowed object is
     *              {@link VehiclesType }
     */
    public void setVehicles(VehiclesType value) {
        this.vehicles = value;
    }

    /**
     * Gets the value of the insurance property.
     *
     * @return possible object is
     * {@link InsuranceType }
     */
    public InsuranceType getInsurance() {
        return insurance;
    }

    /**
     * Sets the value of the insurance property.
     *
     * @param value allowed object is
     *              {@link InsuranceType }
     */
    public void setInsurance(InsuranceType value) {
        this.insurance = value;
    }

    /**
     * Gets the value of the quoteType property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getQuoteType() {
        return quoteType;
    }

    /**
     * Sets the value of the quoteType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setQuoteType(String value) {
        this.quoteType = value;
    }

}
