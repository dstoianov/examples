package com.revimedia.testing.cds.auto.staticdata;

/**
 * Created by dstoianov on 4/29/2014, 7:31 PM.
 */
public class ExtraDataAutoMFS implements Cloneable {

    private String insuranceCompany;
    private String insuredSince;
    private String residenceType;
    private String maritalStatus;
    private String creditRating;
    private String education;
    private String year;
    private String make;
    private String model;

    public ExtraDataAutoMFS() {
        this.insuranceCompany = CommonExtraData.getRandomInsuranceCompany();
        this.insuredSince = CommonExtraData.getRandomInsuredSince();
        this.residenceType = CommonExtraData.getRandomResidenceType();
        this.maritalStatus = CommonExtraData.getRandomMaritalStatus();
        this.creditRating = CommonExtraData.getRandomCreditRating();
        this.education = CommonExtraData.getRandomEducation();
        String[] car = CommonExtraData.getRandomCar();
        this.year = car[0];
        this.make = car[1];
        this.model = car[2];
    }

    public ExtraDataAutoMFS(String insuranceCompany, String insuredSince, String residenceType, String maritalStatus, String creditRating, String education, String year, String make, String model) {
        this.insuranceCompany = insuranceCompany;
        this.insuredSince = insuredSince;
        this.residenceType = residenceType;
        this.maritalStatus = maritalStatus;
        this.creditRating = creditRating;
        this.education = education;
        this.year = year;
        this.make = make;
        this.model = model;
    }

    public ExtraDataAutoMFS clone() throws CloneNotSupportedException {
        return (ExtraDataAutoMFS) super.clone();
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public String getInsuredSince() {
        return insuredSince;
    }

    public String getResidenceType() {
        return residenceType;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public String getCreditRating() {
        return creditRating;
    }

    public String getEducation() {
        return education;
    }

    public String getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    @Override
    public String toString() {
        return "\ninsuranceCompany='" + insuranceCompany + '\'' +
                ",\ninsuredSince='" + insuredSince + '\'' +
                ",\nresidenceType='" + residenceType + '\'' +
                ",\nmaritalStatus='" + maritalStatus + '\'' +
                ",\ncreditRating='" + creditRating + '\'' +
                ",\neducation='" + education + '\'' +
                ",\nyear='" + year + '\'' +
                ",\nmake='" + make + '\'' +
                "\nmodel='" + model + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExtraDataAutoMFS that = (ExtraDataAutoMFS) o;

        if (creditRating != null ? !creditRating.equals(that.creditRating) : that.creditRating != null)
            return false;
        if (education != null ? !education.equals(that.education) : that.education != null)
            return false;
        if (insuranceCompany != null ? !insuranceCompany.equals(that.insuranceCompany) : that.insuranceCompany != null)
            return false;
        if (insuredSince != null ? !insuredSince.equals(that.insuredSince) : that.insuredSince != null)
            return false;
        if (make != null ? !make.equals(that.make) : that.make != null) {
            System.out.println("make is different: \n 1) " + make + "\n2) " + that.make);
            return false;
        }
        if (maritalStatus != null ? !maritalStatus.equals(that.maritalStatus) : that.maritalStatus != null)
            return false;
        if (model != null ? !model.equals(that.model) : that.model != null) return false;
        if (residenceType != null ? !residenceType.equals(that.residenceType) : that.residenceType != null)
            return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;

        return true;
    }

}