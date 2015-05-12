package com.revimedia.testing.cds.prepop;

import com.revimedia.testing.configuration.dto.Contact;

/**
 * Created by dstoianov on 5/7/2014, 6:16 PM.
 */
public class PrePop {
    private Contact contact;

    public PrePop(Contact contact) {
        this.contact = contact;
    }

    public String offer_id;
    public String aff_id;
    public String aff_sub;
    public String source;
    public String firstname = contact.getFirstName();
    public String lastname = contact.getLastName();
    public String address1 = contact.getAddress();
    public String address2;
    public String city = contact.getCity();
    public String state = contact.getState();
    public String zipcode = contact.getZipCode();
    public String phone = contact.getPhoneNumber();
    public String email = contact.getEmailAddress();
    public String birthdate = contact.getBirthDate();
    public String gender = contact.getBirthDate();
    public String dailymileage;
    public String annualmiles;
    public String insurancecompany;
    public String return_url;
    public String redirect_url;
    public String transaction_id;
    public String prepopip;
    public String up;
    public String update_survey;
    public String ppcpn;
    public String style;
    public String wlcss;
    public String wlpcss;
    public String Custom;
    public String mid;
    public String aff_sub2;
    public String GoGadget;
    public String popppc;
    public String exit = "exit=true";

}
