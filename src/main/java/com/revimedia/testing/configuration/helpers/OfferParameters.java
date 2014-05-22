package com.revimedia.testing.configuration.helpers;

/**
 * Created by dstoianov on 5/21/2014, 7:54 PM.
 */
public class OfferParameters {
/*
    offer_id=24&
    aff_id=1068&
    aff_sub=1&
    aff_sub2=12345&
    source=test&
    transaction_id=67890

    offer_id >> only digits can be 2-3 digits
    aff_id >> only digits, can be 1-4 digits
    aff_sub >> can be both digits/letters, no limit
    source >> can be both digits/letters, no limit
    aff_sub2 >> can be both digits/letters, no limit
    transaction_id >>  can be both digits/letters, either 30 characters like (102b68ed13a5d2031c14d3e2a6ad19)
    or the 30 characters with "-up1"s after it (102b68ed13a5d2031c14d3e2a6ad19-up1 or like 102b68ed13a5d2031c14d3e2a6ad19-up1-up1)
    or a combination of letters/digits in this format 8-4-4-4-12 (ex: 19766D43-07A3-427B-A5AE-9140A437B7DE)

*/

    private Integer offer_id;
    private Integer aff_id;
    private String aff_sub;
    private String aff_sub2;
    private String source;
    private String transaction_id;

    public OfferParameters() {
        this.offer_id = DataHelper.randInt(10, 999);
        //this.aff_id = DataHelper.randInt(1, 9999);
        //this.aff_id = RandomStringUtils.random(4,true, true);

    }

    public String getParameters() {

        return null;
    }

}
