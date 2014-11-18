/*globals Bq, $ */
/*jslint  browser: true, forin: true, nomen: true, plusplus: true, regexp: true,
 sloppy: true, white: true */
/**
 * @class Bq
 */
var Bq = Bq || window.Bq || {};

/*
 * Config object
 * @returns {Object} object with public properties
 */
Bq.Config = (function () {
  return {
    debug: false,
    Phone2Address: '/ajax/Phone2Address/Phone2AddressCheck',
    // will be passed to xml builder
    isMultiple: ['Pet.Pets.Pet', 'Petfullquote.Pets.Pet', 'Auto.Vehicles.Vehicle'],
    cloning: {
      showTopButtons: true,
      removeButtonText: 'Remove'
    },
    upsellHeaders: {
      auto: {
        auto: "",
        health: "Good News! We have matched you to multiple Health Providers. You may be eligible to save on health insurance!",
        home: "By combining Home Insurance with your Auto Insurance, you&#146;re eligible to SAVE up to 25%!",
        life: "By combining Life Insurance with your insurance policy, you&#146;re eligible to SAVE up to 25%!",
        pet: "Good News! We have matched you to multiple Pet Health Providers. Get Free quotes for Pet Insurance!",
        automotive: "",
        homesecurity: ""
      },
      health: {
        auto: "By combining Auto Insurance with your insurance policy, you&#146;re eligible to SAVE up to 25%!",
        health: "",
        home: "By combining Home Insurance with your insurance policy, you&#146;re eligible to SAVE up to 25%!",
        life: "By combining Life Insurance with your insurance policy, you&#146;re eligible to SAVE up to 25%!",
        pet: "Good News! We have matched you to multiple Pet Health Providers. Get Free quotes for Pet Insurance!",
        automotive: "",
        homesecurity: ""
      },
      home: {
        auto: "By combining Home Insurance with your Auto Insurance, you're eligible to SAVE up to 25%!",
        health: "By combining Home Insurance with your insurance policy, youтАЩre eligible to SAVE up to 25%!",
        home: "",
        life: "By combining Home Insurance with your insurance policy, youтАЩre eligible to SAVE up to 25%!",
        pet: "Good News! We have matched you to multiple Pet Health Providers. Get Free quotes for Pet Insurance!",
        automotive: "",
        homesecurity: "Good News! We have matched you to multiple Insurance Providers. You may be eligible to save on Home Insurance!",
        standard: "Good News! We have matched you to multiple Insurance Providers. You may be eligible to save on Home Insurance!"
      },
      life: {
        auto: "By combining Auto Insurance with your insurance policy, you&#146;re eligible to SAVE up to 25%!",
        health: "Good News! We have matched you to multiple Health Providers. You may be eligible to save on health insurance!",
        home: "By combining Home Insurance with your insurance policy, you&#146;re eligible to SAVE up to 25%!",
        life: "",
        pet: "Good News! We have matched you to multiple Pet Health Providers. Get Free quotes for Pet Insurance!",
        automotive: "",
        homesecurity: ""
      },
      pet: {
        auto: "{html@ContactData.City} Drivers Qualify for Auto Insurance Savings! Just 3 Quick Steps to Get Your FREE, no obligation Quotes &mdash; Only takes a Minute",
        health: "Good News! We have matched you to multiple Health Providers. You may be eligible to save on health insurance!",
        home: "{html@ContactData.City} Homeowners and Renters Qualify for Home Insurance Savings! Just 3 Quick Steps to Get Your FREE, no obligation Quotes &mdash; Only takes a Minute",
        life: "Good News! We have matched you to multiple Insurance Providers. You may be eligible to save on Life Insurance!",
        pet: "",
        automotive: "",
        homesecurity: ""
      },
      automotive: {
        auto: "",
        health: "",
        home: "",
        life: "",
        pet: "",
        automotive: "",
        homesecurity: ""
      },
      homesecurity: {
        auto: "",
        health: "",
        home: "",
        life: "",
        pet: "",
        automotive: "",
        homesecurity: ""
      }
    },
    upsellText: {
      auto: {
        auto: "",
        health: "Looking for More opportunities to&nbsp;SAVE? I&rsquo;m interested in&nbsp;comparing and requesting FREE Health Insurance quotes.",
        home: "Save Even More! I&rsquo;m interested in&nbsp;saving up&nbsp;to&nbsp;25% more by&nbsp;adding home insurance to&nbsp;my&nbsp;policy.",
        life: "Bundle to&nbsp;Save! I&rsquo;m interested in saving up&nbsp;to&nbsp;25% by&nbsp;adding life insurance to&nbsp;my policy.",
        pet: "Did you know&nbsp;&mdash; Pets need protection too! I&nbsp;often take my&nbsp;pet our for rides; I&rsquo;m interested in&nbsp;comparing rates for Pet Insurance.",
        automotive: "Looking to&nbsp;purchase a&nbsp;new car? Yes, I&rsquo;m interested in&nbsp;comparing clearance Car Prices from my&nbsp;local dealers!",
        homesecurity: "",
        petfullquote: "Save Even More! I own a pet and am interested in saving money on pet medical bills. I want to learn more about PetPremium Pet Health Insurance and compare rates for my dog/cat."
      },
      health: {
        auto: "Looking for More opportunities to&nbsp;SAVE? I&rsquo;m interested in&nbsp;comparing and requesting FREE Auto Insurance quotes.",
        health: "",
        home: "Looking for More opportunities to&nbsp;SAVE? I&rsquo;m interested in&nbsp;comparing and requesting FREE Home Insurance quotes.",
        life: "Looking for More opportunities to&nbsp;SAVE? I&rsquo;m interested in&nbsp;comparing and requesting FREE Life Insurance quotes.",
        pet: "",
        automotive: "",
        homesecurity: "",
        petfullquote: "Save Even More! I own a pet and am interested in saving money on pet medical bills. I want to learn more about PetPremium Pet Health Insurance and compare rates for my dog/cat."
      },
      home: {
        auto: "Save Even More! I&rsquo;m interested in saving up to 25% more by adding auto insurance to my policy.",
        health: "Looking for More opportunities to SAVE? I&rsquo;m interested in comparing and requesting FREE Health Insurance quotes.",
        home: "",
        life: "Bundle to Save! I&rsquo;m interested in saving up to 25% by adding life insurance to my policy.",
        pet: "Yes, I would like to compare and SAVE on pet insurance for my pet.",
        automotive: "",
        homesecurity: "Save Even More! I&rsquo;m interested in saving up to 20% more by getting a home security system.",
        petfullquote: "Save Even More! I own a pet and am interested in saving money on pet medical bills. I want to learn more about PetPremium Pet Health Insurance and compare rates for my dog/cat."
      },
      life: {
        auto: "Bundle to Save! I&rsquo;m interested in saving up to 25% by adding auto insurance to my policy.",
        health: "Save Even More! Get Health Insurance and stay healthy. You can Save Hundreds on your Life Insurance policy.",
        home: "Bundle to Save! I&rsquo;m interested in saving up to 25% by adding home insurance to my policy.",
        life: "",
        pet: "",
        automotive: "",
        homesecurity: "",
        petfullquote: "Save Even More! I own a pet and am interested in saving money on pet medical bills. I want to learn more about PetPremium Pet Health Insurance and compare rates for my dog/cat."
      },
      pet: {
        auto: "My pet often rides with me in my car. I would like to compare and SAVE on my auto insurance.",
        health: "Yes! I&rsquo;m interested in comparing affordable rates for my health insurance too.",
        home: "My pet often stays at home. I&rsquo;m interested in finding affordable rates for Home Insurance.",
        life: "",
        pet: "",
        automotive: "",
        homesecurity: ""
      },
      automotive: {
        auto: "Yes! I&rsquo;m interested in getting affordable auto insurance rates for my vehicle.",
        health: "Looking for More opportunities to&nbsp;SAVE? I&rsquo;m interested in&nbsp;comparing and requesting FREE Health Insurance quotes.",
        home: "",
        life: "",
        pet: "",
        automotive: "",
        homesecurity: "",
        petfullquote: "Save Even More! I own a pet and am interested in saving money on pet medical bills. I want to learn more about PetPremium Pet Health Insurance and compare rates for my dog/cat."
      },
      homesecurity: {
        auto: "Save Even More! IтАЩm interested in saving on my auto insurance.",
        health: "Save Even More! IтАЩm interested in saving on my health insurance.",
        home: "Yes! I&rsquo;m interested in saving up to 20% on Home-owners &amp; Renter&rsquo;s insurance with the addition of my home security system.",
        life: "Save Even More! IтАЩm interested in saving on my life insurance.",
        pet: "",
        automotive: "",
        homesecurity: "",
        petfullquote: "Save Even More! I own a pet and am interested in saving money on pet medical bills. I want to learn more about PetPremium Pet Health Insurance and compare rates for my dog/cat."
      }
    },
    disclaimerText: {
      auto: {
        //tcpa: "By submitting this form, I authorize <a href='http://www.bestquotes.com/auto/auto-companies.html' target='_blank'> insurance companies, their agents and partners </a> to contact me by phone calls and text messages to the number I provided. I agree to receive telemarketing calls and pre-recorded messages via an autodialed phone system. I understand that my consent is not a condition of purchase.",
        //tcpa: "By submitting this form, I authorize <a href='http://www.bestquotes.com/auto/auto-companies.html' target='_blank'> insurance companies, their agents and partners </a>to contact me by phone calls and text messages to the number I provided. I agree to receive telemarketing calls and pre-recorded messages via an automatic telephone dialing system phone system. I understand that my consent is not a condition of purchase.",
        tcpa: "By submitting this form, I authorize <a href='http://www.bestquotes.com/auto/auto-companies.html' target='_blank'> insurance companies, their agents and partners </a>to contact me by phone calls and text messages to the number I provided. I agree to receive telemarketing calls and pre-recorded messages via an automatic telephone dialing system. I understand that my consent is not a condition of purchase.",
        bestq: "By submitting my information I agree to the <a href='http://www.bestquotes.com/privacy-policy/' target='_blank'>Privacy Policy</a> and <a href='http://www.bestquotes.com/terms-and-conditions/' target='_blank'>Terms and Conditions</a> of this website and agree to be bound by them. I acknowledge that I am seeking a quote request, which I authorize and agree that up to eight insurance companies, their agents and partner companies may use the information I provided to contact me or to obtain additional information. I authorize this website, insurance companies or their agents that receive my insurance quote request from this website, or its partner companies, to confirm my information through the use of a consumer report, including my driving record. None of the listed companies sponsor, endorse or are in any way affiliated to this website."
      },
      autofinance: {
        tcpa: "By submitting this form and requesting quotes, I authorize that dealers, lenders, their agents and partner companies may contact me via telephone including, calls and/or text messages to the phone number I provided above. I certify that all of the information provided in this application is true and authorize dealers, lenders, their agents and partner companies to obtain a credit report about you in order to evaluate your credit application. I authorize that these marketing or telemarketing communications may be delivered to me using an automatic telephone dialing system or by pre-recorded message. I understand that consent is not a condition of purchase.",
        bestq: "By submitting my information I agree to the <a href='http://www.bestquotes.com/privacy-policy/' target='_blank'>Privacy Policy</a> and <a href='http://www.bestquotes.com/terms-and-conditions/' target='_blank'>Terms and Conditions</a> of this website and agree to be bound by them. I acknowledge that I am seeking a quote request, which I authorize and agree that up to eight dealers, lenders, their agents and partner companies may use the information I provided to contact me or to obtain additional information. I authorize this website, dealers, lenders or their agents that receive my quote request from this website, or its partner companies, to confirm my information through the use of a consumer report. None of the listed companies sponsor, endorse or are in any way affiliated to this website."
      },
      health: {
        tcpa: "By submitting this form, I authorize <a href='http://www.bestquotes.com/health/health-companies.html' target='_blank'>insurance companies, their agents and partners</a> to contact me by phone calls and text messages to the number I provided. I agree to receive telemarketing calls and pre-recorded messages via an autodialed phone system. I understand that my consent is not a condition of purchase.",
        bestq: "By submitting my information I agree to the <a href='http://www.bestquotes.com/privacy-policy/' target='_blank'>Privacy Policy</a> and <a href='http://www.bestquotes.com/terms-and-conditions/' target='_blank'>Terms and Conditions</a> of this website and agree to be bound by them. I acknowledge that I am seeking a quote request, which I authorize and agree that up to eight insurance companies, their agents and partner companies may use the information I provided to contact me or to obtain additional information. I authorize this website, insurance companies or their agents that receive my insurance quote request from this website, or its partner companies, to confirm my information through the use of a consumer report. None of the listed companies sponsor, endorse or are in any way affiliated to this website."
      },
      home: {
        tcpa: "By submitting this form, I authorize <a href='http://www.bestquotes.com/home/home-companies.html' target='_blank'>insurance companies, their agents and partners</a> to contact me by phone calls and text messages to the number I provided. I agree to receive telemarketing calls and pre-recorded messages via an autodialed phone system. I understand that my consent is not a condition of purchase.",
        bestq: "By submitting my information I agree to the <a href='http://www.bestquotes.com/privacy-policy/' target='_blank'>Privacy Policy</a> and <a href='http://www.bestquotes.com/terms-and-conditions/'target='_blank'>Terms and Conditions</a> of this website and agree to be bound by them. I acknowledge that I am seeking a quote request, which I authorize and agree that up to eight insurance companies, their agents and partner companies may use the information I provided to contact me or to obtain additional information. I authorize this website, insurance companies or their agents that receive my insurance quote request from this website, or its partner companies, to confirm my information through the use of a consumer report. None of the listed companies sponsor, endorse or are in any way affiliated to this website."
      },
      homesecurity: {
        //tcpa: "By submitting this form, I authorize <a href='https://www.homesafety.com/home-security/home-security-companies.html' target='_blank'>security companies, their dealers, ADT Authorized Premier Provider and partners</a> to contact me by phone calls and text messages to the number I provided. I authorize that these marketing communications may be delivered to me using an automatic telephone dialing system or by pre-recorded message, or by email marketing to the email address provided. I understand that my consent is not a condition of purchase.",
        tcpa: "By clicking Get My Quotes, I authorize <a href='https://www.homesafety.com/home-security/home-security-companies.html' target='_blank'>security companies</a>, including ADT, Alliance, FrontPoint, Gaylord, LifeShield, Monitronics, Protect America, Protect Your Home тАУ ADT Authorized Premier Provider, Protection1, Vivint, their dealers and partner companies to contact me by phone calls and text messages to the number I provided. I authorize that these marketing communications may be delivered to me using an automatic telephone dialing system or by prerecorded message. I understand that my consent is not a condition of purchase.",
        tcpaWithCheckBox: "By checking the box, I authorize that <a href='https://www.homesafety.com/home-security/home-security-companies.html' target='_blank'>home alarm companies, their dealers and partner companies</a> may contact me regarding home security using automated technology, including calls to the (wireless) phone number I provided above. I understand that consent is not a condition of purchase.",
        /* Can be overridden in _bqOptions.TCPACheckBoxErrorMsg */
        tcpaCheckboxErrorMsg: "Our home security providers require your consent to contact you. Please consent to submit the form.",
        bestq: '<div class="bq-footnote-discl"><span class="bq-footnote">1</span> http://www.prnewswire.com/news-releases/survey-finds-insurance-carriers-offer-major-discounts-for-home-alarm-systems-116654399.html</div>By submitting my information I agree to the <a href="https://www.homesafety.com/privacy-policy/" target="_blank">Privacy Policy</a> and <a href="https://www.homesafety.com/terms-and-conditions/" target="_blank">Terms and Conditions</a> of this website and agree to be bound by them. I acknowledge that I am seeking a quote request, which I authorize and agree that up to four security companies, their dealers and partner companies may use the information I provided to contact me or to obtain additional information. None of the listed companies sponsor, endorse or are in any way affiliated to this website.'
      },

      life: {
        tcpa: "By submitting this form, I authorize <a href='http://www.bestquotes.com/life/life-companies.html' target='_blank'>insurance companies, their agents and partners</a> to contact me by phone calls and text messages to the number I provided. I agree to receive telemarketing calls and pre-recorded messages via an autodialed phone system. I understand that my consent is not a condition of purchase.",
        bestq: "By submitting my information I agree to the <a href='http://www.bestquotes.com/privacy-policy/' target='_blank'>Privacy Policy</a> and <a href='http://www.bestquotes.com/terms-and-conditions/' target='_blank'>Terms and Conditions</a> of this website and agree to be bound by them. I acknowledge that I am seeking a quote request, which I authorize and agree that up to eight insurance companies, their agents and partner companies may use the information I provided to contact me or to obtain additional information. I authorize this website, insurance companies or their agents that receive my insurance quote request from this website, or its partner companies, to confirm my information through the use of a consumer report. None of the listed companies sponsor, endorse or are in any way affiliated to this website."
      },
      automotive: {
        tcpa: "By submitting this form and requesting quotes, I authorize that car manufacturers, their dealers and partner companies may contact me via telephone including, calls and/or text messages to the phone number I provided above. I authorize that these marketing or telemarketing communications may be delivered to me using an automatic telephone dialing system or by pre-recorded message. I understand that consent is not a condition of purchase.",
        bestq: "By submitting my information I agree to the <a href='http://www.autocomparison.com/privacy-policy/' target='_blank'>Privacy Policy</a> and <a href='http://www.autocomparison.com/terms-of-use/' target='_blank'>Terms and Conditions</a> of this website and agree to be bound by them. I acknowledge that I am seeking a quote request, which I authorize and agree that up to eight dealers, their agents and partner companies may use the information I provided to contact me or to obtain additional information. I authorize this website, dealers or their agents that receive my quote request from this website, or its partner companies, to confirm my information through the use of a consumer report. None of the listed companies sponsor, endorse or are in any way affiliated to this website."
      },
      pet: {
        tcpa: "By submitting this form, I authorize that PetPremium may contact me via telephone including calls and/or text messages to the phone number I provided above. I authorize that these marketing and telemarketing communications may be delivered to me using an automatic telephone dialing system or by pre-recorded message. I understand that consent is not a condition of purchase.",
        bestq: "By submitting my information I agree to the <a href='http://www.petpremium.com/privacy-policy/' target='_blank'>Privacy Policy</a> and <a href='http://www.bestquotes.com/terms-and-conditions/' target='_blank'>Terms and Conditions</a> of this website and agree to be bound by them. I acknowledge that I am seeking a quote request, which I authorize and agree that PetPremium may use the information I provided to contact me or to obtain additional information. I authorize this website, PetPremium or their agents that receive my insurance quote request from this website, or its partner companies, to confirm my information through the use of a consumer report."
      },
      petfullquote: {
        tcpa: "By submitting this form, I authorize that PetPremium may contact me via telephone including calls to the phone number I provided above. I authorize that these marketing or telemarketing communications may be delivered to me using an automatic telephone dialing system or by pre-recorded message.",
        bestq: "By submitting my information I agree to the <a href='http://www.petpremium.com/privacy-policy/'  target='_blank'>Privacy Policy and <a href='http://www.bestquotes.com/terms-and-conditions/' target='_blank'>Terms and Conditions</a> of this website and agree to be bound by them. I acknowledge that I am purchasing pet insurance, which I authorize and agree that PetPremium may use the information I provided to contact me or to obtain additional information. I authorize this website, PetPremium or their agents that receive my purchase from this website, or its partner companies, to confirm my information through the use of a consumer report."
      }
    },

    exitPopUpText: {
      auto: {
        h1: "Wait, don't leave! We are here to help you!",
        phone: "Call: <img style='vertical-align: middle;margin: 0 3px 0 8px;' src='http://bestquotes.com/cdsimgs/call-icon.gif' /> <a class='exit-number' style='text-decoration: none;color: #374A4C;' href='tel:8887591914'>(888) 759-1914</a>",
        content: "<ul style='font-weight: bold;margin: 0 auto;margin-top: -15px;font-size: 15px;font-family: Arial;width: 390px;color: #374A4C;'><li style='padding: 5px;'>Speak with a knowledgeable specialist directly</li><li style='padding: 5px;'>FREE, No Obligation Auto Insurance Quotes</li><li style='padding: 5px;'>We have plans and policies for everyone</li></ul><img style='position: absolute;top: 145px;left: 489px;' src='http://bestquotes.com/cdsimgs/callcenter-1.gif' />",
        button: "Stay and receive FREE Quotes"
      },
      autofinance: {

      },
      health: {
        h1: "Wait, don't leave! We are here to help you!",
        phone: "Call: <img style='vertical-align: middle;margin: 0 3px 0 8px;' src='http://bestquotes.com/cdsimgs/call-icon.gif' /> <a class='exit-number' style='text-decoration: none;color: #374A4C;' href='tel:8667391752'>(866) 739-1752</a>",
        content: "<ul style='font-weight: bold;margin: 0 auto;margin-top: -15px;font-size: 15px;font-family: Arial;width: 390px;color: #374A4C;'><li style='padding: 5px;'>Speak with a knowledgeable specialist directly</li><li style='padding: 5px;'>FREE, No Obligation Health Insurance Quotes</li><li style='padding: 5px;'>We have plans and policies for everyone</li></ul><img style='position: absolute;top: 145px;left: 489px;' src='http://bestquotes.com/cdsimgs/callcenter-1.gif' />",
        button: "Stay and receive FREE Quotes"
      },
      home: {
        h1: "WAIT!",
        phone: "Are you sure you want to leave this page?",
        content: "<p style='text-align: center;color: #374A4C;'>You are only a few clicks away from receiving FREE home insurance quotes. Protecting your home has never been easier.</p>",
        button: "Compare Rates & Save Now!"
      },
      homesecurity: {
        h1: "Wait, don't leave! We are here to help you!",
        phone: "Call: <img style='vertical-align: middle;margin: 0 3px 0 8px;' src='http://bestquotes.com/cdsimgs/call-icon.gif' /> <a class='exit-number' style='text-decoration: none;color: #374A4C;' href='tel:8666798303'>(866) 679-8303</a>",
        content: "<ul style='font-weight: bold;margin: 0 auto;margin-top: -15px;font-size: 15px;font-family: Arial;width: 390px;color: #374A4C;'><li style='padding: 5px;'>Speak with a knowledgeable specialist directly</li><li style='padding: 5px;'>FREE, No Obligation Home Security Quotes</li><li style='padding: 5px;'>We have plans and policies for everyone</li></ul><img style='position: absolute;top: 145px;left: 489px;' src='http://bestquotes.com/cdsimgs/callcenter-1.gif' />",
        button: "Stay and receive FREE Quotes"
      },
      life: {
        h1: "Wait, don't leave! We are here to help you!",
        phone: "Call: <img style='vertical-align: middle;margin: 0 3px 0 8px;' src='http://bestquotes.com/cdsimgs/call-icon.gif' /> <a class='exit-number' style='text-decoration: none;color: #374A4C;' href='tel:8777719628'>(877) 771-9628</a>",
        content: "<ul style='font-weight: bold;margin: 0 auto;margin-top: -15px;font-size: 15px;font-family: Arial;width: 390px;color: #374A4C;'><li style='padding: 5px;'>Speak with a knowledgeable specialist directly</li><li style='padding: 5px;'>FREE, No Obligation Life Insurance Quotes</li><li style='padding: 5px;'>We have plans and policies for everyone</li></ul><img style='position: absolute;top: 145px;left: 489px;' src='http://bestquotes.com/cdsimgs/callcenter-1.gif' />",
        button: "Stay and receive FREE Quotes"
      },
      automotive: {
        h1: "WAIT!",
        phone: "Are you sure you want to leave this page?",
        content: "<p style='text-align: center;color: #374A4C;'>You are only a few clicks away from receiving your FREE New Car Price Quotes. Find the lowest prices from your local dealers.</p>",
        button: "Compare Rates & Save Now!"
      },
      pet: {

      },
      petfullquote: {

      }
    },

    LeadIdScript: {
      auto: '4ff25439-c05c-4658-8f34-6c6b0a1c4317',
      autocall: '42ed988a-a2f6-55a4-4f46-af8bb590cc76',
      automotive:  '65107435-fc66-ae96-05d7-c00540f3b822',
      automotivecall: 'e0514883-e9bc-8c91-463a-2a261871b59f',
      health: 'feb30aac-1544-9be1-fe93-f590c0151e67',
      healthcall: 'c93b266d-cb8b-0d7a-306b-339c04534dd9',
      home: 'ecb7fd87-6216-8994-e581-00f3dcaec7af',
      homecall: 'f94bac84-91b8-c082-064e-30b6b8615ef5',
      homesecurity: 'a2138705-124d-c17d-92f9-4c35f63fedae',
      homesecuritycall: 'd44fffab-a75c-b1dd-189b-231bad57e597',
      life: '1b0ec839-4b22-eede-fef8-56330c5ef15f',
      lifecall: '18bcdfd5-7c5d-1013-ecfb-c081a26fa3b9',
      medicalcall: '9513c3fb-6d1f-82b8-56b3-8d87127a82dd',
      medical: '3849d28f-1904-bd77-c42e-4454c6d5b96a',
      petfullquote: 'f353f6c9-9900-a106-dfbb-9039c908ad91',
      petfullquotecall: '2565adc2-7b6e-ddd3-ebd2-ed11d4d406ac'
    }
  };
})();
