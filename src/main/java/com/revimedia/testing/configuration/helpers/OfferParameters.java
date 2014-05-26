package com.revimedia.testing.configuration.helpers;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Created by dstoianov on 5/21/2014, 7:54 PM.
 */
public class OfferParameters {
/*
    offer_id >> only digits can be 2-3 digits
    aff_id >> only digits, can be 1-4 digits
    aff_sub >> can be both digits/letters, no limit
    source >> can be both digits/letters, no limit
    aff_sub2 >> can be both digits/letters, no limit
    transaction_id >>  can be both digits/letters, either 30 characters like (102b68ed13a5d2031c14d3e2a6ad19)
    or the 30 characters with "-up1"s after it (102b68ed13a5d2031c14d3e2a6ad19-up1 or like 102b68ed13a5d2031c14d3e2a6ad19-up1-up1)
    or a combination of letters/digits in this format 8-4-4-4-12 (ex: 19766D43-07A3-427B-A5AE-9140A437B7DE)

*/

    private Integer aff_id;
    private Integer offer_id;
    private String aff_sub;
    private String aff_sub2;
    private String source;
    private String update_survey; //SurveyPath can update only on
    private String transaction_id;

    public Integer getAff_id() {
        return aff_id;
    }

    public void setAff_id(Integer aff_id) {
        this.aff_id = aff_id;
    }

    public Integer getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(Integer offer_id) {
        this.offer_id = offer_id;
    }

    public String getAff_sub() {
        return aff_sub;
    }

    public void setAff_sub(String aff_sub) {
        this.aff_sub = aff_sub;
    }

    public String getAff_sub2() {
        return aff_sub2;
    }

    public void setAff_sub2(String aff_sub2) {
        this.aff_sub2 = aff_sub2;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUpdate_survey() {
        return update_survey;
    }

    public void setUpdate_survey(String update_survey) {
        this.update_survey = update_survey;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public OfferParameters() {
        this.offer_id = DataHelper.randInt(10, 999);
        this.aff_id = DataHelper.randInt(1, 9999);
        this.aff_sub = "test.Aff_Sub-" + RandomStringUtils.random(10, true, true);
        this.aff_sub2 = "test.Aff_sub2-" + RandomStringUtils.random(13, true, true);
        this.source = "test.Source-" + RandomStringUtils.random(15, true, true);
        this.transaction_id = "test.TransID-" + RandomStringUtils.random(30, true, true);
        this.update_survey = "test.SurveyPath-" + RandomStringUtils.random(15, true, true);
    }


    public String toURLString() {
        return "?aff_id=" + aff_id +
                "&offer_id=" + offer_id +
                "&aff_sub=" + aff_sub +
                "&aff_sub2=" + aff_sub2 +
                "&source=" + source +
                "&update_survey=" + update_survey +
                "&transaction_id=" + transaction_id;
    }


}
