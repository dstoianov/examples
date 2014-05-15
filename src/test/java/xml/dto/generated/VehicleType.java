//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.05.14 at 06:15:02 PM EEST 
//


package xml.dto.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for VehicleType complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="VehicleType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AnnualMiles" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CollisionDeductible" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ComphrensiveDeductible" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Garage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OneWayDistance" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PrimaryUse" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Ownership" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WeeklyCommuteDays" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Year" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Make" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Model" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VehicleType", propOrder = {
        "annualMiles",
        "collisionDeductible",
        "comphrensiveDeductible",
        "garage",
        "oneWayDistance",
        "primaryUse",
        "ownership",
        "weeklyCommuteDays",
        "year",
        "make",
        "model"
})
public class VehicleType {

    @XmlElement(name = "AnnualMiles", required = true)
    protected String annualMiles;
    @XmlElement(name = "CollisionDeductible", required = true)
    protected String collisionDeductible;
    @XmlElement(name = "ComphrensiveDeductible", required = true)
    protected String comphrensiveDeductible;
    @XmlElement(name = "Garage", required = true)
    protected String garage;
    @XmlElement(name = "OneWayDistance", required = true)
    protected String oneWayDistance;
    @XmlElement(name = "PrimaryUse", required = true)
    protected String primaryUse;
    @XmlElement(name = "Ownership", required = true)
    protected String ownership;
    @XmlElement(name = "WeeklyCommuteDays", required = true)
    protected String weeklyCommuteDays;
    @XmlElement(name = "Year", required = true)
    protected String year;
    @XmlElement(name = "Make", required = true)
    protected String make;
    @XmlElement(name = "Model", required = true)
    protected String model;

    /**
     * Gets the value of the annualMiles property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getAnnualMiles() {
        return annualMiles;
    }

    /**
     * Sets the value of the annualMiles property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAnnualMiles(String value) {
        this.annualMiles = value;
    }

    /**
     * Gets the value of the collisionDeductible property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCollisionDeductible() {
        return collisionDeductible;
    }

    /**
     * Sets the value of the collisionDeductible property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCollisionDeductible(String value) {
        this.collisionDeductible = value;
    }

    /**
     * Gets the value of the comphrensiveDeductible property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getComphrensiveDeductible() {
        return comphrensiveDeductible;
    }

    /**
     * Sets the value of the comphrensiveDeductible property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setComphrensiveDeductible(String value) {
        this.comphrensiveDeductible = value;
    }

    /**
     * Gets the value of the garage property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getGarage() {
        return garage;
    }

    /**
     * Sets the value of the garage property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setGarage(String value) {
        this.garage = value;
    }

    /**
     * Gets the value of the oneWayDistance property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getOneWayDistance() {
        return oneWayDistance;
    }

    /**
     * Sets the value of the oneWayDistance property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setOneWayDistance(String value) {
        this.oneWayDistance = value;
    }

    /**
     * Gets the value of the primaryUse property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPrimaryUse() {
        return primaryUse;
    }

    /**
     * Sets the value of the primaryUse property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPrimaryUse(String value) {
        this.primaryUse = value;
    }

    /**
     * Gets the value of the ownership property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getOwnership() {
        return ownership;
    }

    /**
     * Sets the value of the ownership property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setOwnership(String value) {
        this.ownership = value;
    }

    /**
     * Gets the value of the weeklyCommuteDays property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getWeeklyCommuteDays() {
        return weeklyCommuteDays;
    }

    /**
     * Sets the value of the weeklyCommuteDays property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setWeeklyCommuteDays(String value) {
        this.weeklyCommuteDays = value;
    }

    /**
     * Gets the value of the year property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getYear() {
        return year;
    }

    /**
     * Sets the value of the year property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setYear(String value) {
        this.year = value;
    }

    /**
     * Gets the value of the make property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMake() {
        return make;
    }

    /**
     * Sets the value of the make property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMake(String value) {
        this.make = value;
    }

    /**
     * Gets the value of the model property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the value of the model property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setModel(String value) {
        this.model = value;
    }

}
