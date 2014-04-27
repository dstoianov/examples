package xmltoobject;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Funker on 27.04.14.
 */

@XmlRootElement(name = "ROW")
@XmlAccessorType(XmlAccessType.FIELD)
public class Row {

    @XmlElement(name = "PARTY_NAME")
    private String partyName;

    @XmlElement(name = "COUNTRY_NAME")
    private String countryName;

    @XmlElement(name = "STATE_NAME")
    private String stateName;

    @XmlElement(name = "STATE_STDT_CODE")
    private String stateCode;

    @XmlElement(name = "CITY_NAME")
    private String city;

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlElement(name = "POSTCODE")
    private String zipCode;

    @XmlElement(name = "LINE1")
    private String address;

    @XmlElement(name = "LINE2")
    private String address2;

    @XmlElement(name = "WEB_URL_ADDRESS")
    private String email;


    @Override
    public String toString() {
        return "Row{\n" +
                "partyName=" + partyName +
                "\n, countryName=" + countryName +
                "\n, stateName=" + stateName +
                "\n, stateCode=" + stateCode +
                "\n, city=" + city +
                "\n, zipCode=" + zipCode +
                "\n, address=" + address +
                "\n, address2=" + address2 +
                "\n, email=" + email +
                '}';
    }
}
