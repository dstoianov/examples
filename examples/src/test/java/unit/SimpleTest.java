package unit;

/**
 * Created by dstoianov on 2015-01-20.
 */
public class SimpleTest {


    public static void main(final String[] argv) {
        String str;

        str = "hello\r\n\tjava\r\nbook";

        replaceSym(str);
        replaceSym(message);
    }


    static void replaceSym(String str) {
        System.out.println("Length " + str.length());
        System.out.println(str);
//        str = str.replaceAll("(\\r|\\n|\\t)", "");
        str = str.replaceAll("(\\r|\\t)", "");
        System.out.println("Length " + str.length());
        System.out.println(str);
    }
//Length 1712
//Length 1639 Length 1689

    static String message = "Content-Type: multipart/related;\n" +
            " boundary=\"----=_Part_9_17372168.1421767709647\"\n" +
            "\n" +
            "------=_Part_9_17372168.1421767709647\n" +
            "Content-Type: text/html;charset=UTF-8\n" +
            "Content-Transfer-Encoding: 7bit\n" +
            "\n" +
            "<html>\n" +
            "    <head>\n" +
            "        <title></title>\n" +
            "        <link rel=\"stylesheet\" type=\"text/css\" href=\"http://www.plspro.com/email/emailStylesheetHTML.css\"> </link>\n" +
            "        <style>\n" +
            "            td.noPadding {\n" +
            "                padding: 0 !important;\n" +
            "            }\n" +
            "            td { padding: 0px 25px; }\n" +
            "            #footer p\n" +
            "            {\n" +
            "                margin: 0;\n" +
            "            }\n" +
            "        </style>\n" +
            "    </head>\n" +
            "    <body>\n" +
            "        <div id=\"header\">\n" +
            "        </div>\n" +
            "        <div id=\"main\"><span>\n" +
            "            You have a new invoice from <b>PLS Logistics Services</b> attached to this email.\n" +
            "\t\t\t\tFor your convenience required paperwork can be downloaded by clicking <a href=\"http://exp-qa.plslogistics.com/my-freight/#/document/22038\">HERE</a>.\n" +
            "</span>\n" +
            "<br/>\n" +
            "<br/>\n" +
            "<hr/>\n" +
            "        </div>\n" +
            "        <br/><br/>\n" +
            "        <div id=\"footer\">\n" +
            "            <div>\n" +
            "\t\t\t\t\t\t\t\t\t<p>PLS Contact: LTL Customer Service</p>\n" +
            "\t\t\t\t\t<p>Email: <a href=\"mailto:plsfreight1@plslogistics.com\">plsfreight1@plslogistics.com</a></p>\n" +
            "\t\t\t\t\t<p>Phone: +1(888)757 8261</p>\n" +
            "                <p>Thank you for using <a href=\"http://exp-qa.plslogistics.com/my-freight/index.html#\">PLS Logistics Services</a></p>\n" +
            "            </div>\n" +
            "            <div id=\"logo\" class=\"left\"></div>\n" +
            "            <div id=\"printLogo\" class=\"left\">\n" +
            "                <img align=\"center\" width=\"100\" height=\"40\" src=\"http://www.plspro.com/email/pls_logo_new.jpg\"/>\n" +
            "            </div>\n" +
            "            <div id=\"headerImage\" class=\"right\"></div>\n" +
            "        </div>\n" +
            "    </body>\n" +
            "</html>\n" +
            "------=_Part_9_17372168.1421767709647--\n";

    static String ss = "<html>\n" +
            "    <head>\n" +
            "        <title></title>\n" +
            "        <link rel=\"stylesheet\" type=\"text/css\" href=\"http://www.plspro.com/email/emailStylesheetHTML.css\"> </link>\n" +
            "        <style>\n" +
            "            td.noPadding {\n" +
            "                padding: 0 !important;\n" +
            "            }\n" +
            "            td { padding: 0px 25px; }\n" +
            "            #footer p\n" +
            "            {\n" +
            "                margin: 0;\n" +
            "            }\n" +
            "        </style>\n" +
            "    </head>\n" +
            "    <body>\n" +
            "        <div id=\"header\">\n" +
            "        </div>\n" +
            "        <div id=\"main\"><span>\n" +
            "            You have a new invoice from <b>PLS Logistics Services</b> attached to this email.\n" +
            "For your convenience required paperwork can be downloaded by clicking <a href=\"http://exp-qa.plslogistics.com/my-freight/#/document/22038\">HERE</a>.\n" +
            "</span>\n" +
            "<br/>\n" +
            "<br/>\n" +
            "<hr/>\n" +
            "        </div>\n" +
            "        <br/><br/>\n" +
            "        <div id=\"footer\">\n" +
            "            <div>\n" +
            "<p>PLS Contact: LTL Customer Service</p>\n" +
            "<p>Email: <a href=\"mailto:plsfreight1@plslogistics.com\">plsfreight1@plslogistics.com</a></p>\n" +
            "<p>Phone: +1(888)757 8261</p>\n" +
            "                <p>Thank you for using <a href=\"http://exp-qa.plslogistics.com/my-freight/index.html#\">PLS Logistics Services</a></p>\n" +
            "            </div>\n" +
            "            <div id=\"logo\" class=\"left\"></div>\n" +
            "            <div id=\"printLogo\" class=\"left\">\n" +
            "                <img align=\"center\" width=\"100\" height=\"40\" src=\"http://www.plspro.com/email/pls_logo_new.jpg\"/>\n" +
            "            </div>\n" +
            "            <div id=\"headerImage\" class=\"right\"></div>\n" +
            "        </div>\n" +
            "    </body>\n" +
            "</html>";

}
