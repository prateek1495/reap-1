package com.prateek.reap.util;

public class Constants {

    public static final String RESET_TOKEN_URL = "http://localhost:8080/reset-password?token=";

    // Mail Constants
    public static final String MAIL_SUBJECT_RECOGNITION_REVOKED = "Recognition revoked";
    public static final String MAIL_SUBJECT = "Reset Password";
    public static final String RECOGNITION_YOU_GAVE_TO = "The recognition you gave to ";
    public static final String REVOKED_BY_ADMIN = " has been revoked by Admin ";
    public static final String REASON_REVOKATION = " For the following reason  ";
    public static final String CONTACT_ADMIN = " For more information Please contact the admin";
    public static final String RECOGNITION_NEWER = "Newer Recognition";
    public static final String GAVE_STAR = "You gave a ";
    public static final String TO= " to ";
    public static final String YOU= " you ";
    public static final String CANNOTREVOKE= " cannot be revoked ";
    public static final String EXPIRE_REASON= " as receiver has already redeem the points ";
    public static final String STAR= " star ";
    public static final String CONGRATULATION = "Congratulations!!!!";
    public static final String HAVE_RECEIVED = " have received a ";
    public static final String STAR_FROM = " star from ";
    public static final String RECOGNITION_YOU_RECEIVED = "The recognition you received from ";
    public static final String UPDATED_AT = "updatedAt";
    public static final String DECIDER_DELETE = "DELETE";
    public static final String DECIDER_ADD = "ADD";
    public static final String HTML_TEMPLATE_BEFORE_BODY = "<table width=\"100%\" bgcolor=\"#FFFFFF\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:2px solid #999999\">\n" +
            "    <tbody><tr>\n" +
            "        <td style=\"border:none\">\n" +
            "            <table width=\"100%\" height=\"100%\" bgcolor=\"#1cc09f\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\n" +
            "                <tbody><tr><td height=\"10\"></td></tr>\n" +
            "                <tr>\n" +
            "                    <td width=\"2%\"></td>\n" +
            "                    <td style=\"color:#ffffff;font-family:open sans;line-height:20px;font-size:17px\" width=\"90%\">Reset Password Link </td>\n" +
            "                    <td width=\"5%\"></td>\n" +
            "                    <td><img src=\"https://ci5.googleusercontent.com/proxy/Er5Cqp3oDeOGVj7ubbmaMMPLd5R7Kmy3Nh8QrvNhXowL2XlimVsVTtHLplWKs4DDGhIm8iajMWmOhtOADa9z4sojP8U=s0-d-e1-ft#http://learning.tothenew.com/images/mail-logo.png\" class=\"CToWUd\"></td>\n" +
            "                    <td width=\"3%\"></td>\n" +
            "                </tr>\n" +
            "                <tr><td height=\"10\"></td></tr>\n" +
            "            </tbody></table>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "    <tr><td height=\"20\">Your password reset link is ";

    public static final String HTML_RECOGINITION_TEMPLATE_BEFORE_BODY = "<table width=\"100%\" bgcolor=\"#FFFFFF\" " +
            "border=\"0\" " +
            "cellpadding=\"0\" cellspacing=\"0\" style=\"border:2px solid #999999\">\n" +
            "    <tbody><tr>\n" +
            "        <td style=\"border:none\">\n" +
            "            <table width=\"100%\" height=\"100%\" bgcolor=\"#1cc09f\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\n" +
            "                <tbody><tr><td height=\"10\"></td></tr>\n" +
            "                <tr>\n" +
            "                    <td width=\"2%\"></td>\n" +
            "                    <td style=\"color:#ffffff;font-family:open sans;line-height:20px;font-size:17px\" " +
            "width=\"90%\">Newer Recognition </td>\n" +
            "                    <td width=\"5%\"></td>\n" +
            "                    <td><img src=\"https://ci5.googleusercontent.com/proxy/Er5Cqp3oDeOGVj7ubbmaMMPLd5R7Kmy3Nh8QrvNhXowL2XlimVsVTtHLplWKs4DDGhIm8iajMWmOhtOADa9z4sojP8U=s0-d-e1-ft#http://learning.tothenew.com/images/mail-logo.png\" class=\"CToWUd\"></td>\n" +
            "                    <td width=\"3%\"></td>\n" +
            "                </tr>\n" +
            "                <tr><td height=\"10\"></td></tr>\n" +
            "            </tbody></table>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "    <tr><td height=\"90\">";




    public static final String HTML_REVOCATION_TEMPLATE_BEFORE_BODY = "<table width=\"100%\" bgcolor=\"#FFFFFF\" " +
            "border=\"0\" " +
            "cellpadding=\"0\" cellspacing=\"0\" style=\"border:2px solid #999999\">\n" +
            "    <tbody><tr>\n" +
            "        <td style=\"border:none\">\n" +
            "            <table width=\"100%\" height=\"100%\" bgcolor=\"#1cc09f\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\n" +
            "                <tbody><tr><td height=\"10\"></td></tr>\n" +
            "                <tr>\n" +
            "                    <td width=\"2%\"></td>\n" +
            "                    <td style=\"color:#ffffff;font-family:open sans;line-height:20px;font-size:17px\" " +
            "width=\"90%\">Recognition Revocation </td>\n" +
            "                    <td width=\"5%\"></td>\n" +
            "                    <td><img src=\"https://ci5.googleusercontent.com/proxy/Er5Cqp3oDeOGVj7ubbmaMMPLd5R7Kmy3Nh8QrvNhXowL2XlimVsVTtHLplWKs4DDGhIm8iajMWmOhtOADa9z4sojP8U=s0-d-e1-ft#http://learning.tothenew.com/images/mail-logo.png\" class=\"CToWUd\"></td>\n" +
            "                    <td width=\"3%\"></td>\n" +
            "                </tr>\n" +
            "                <tr><td height=\"10\"></td></tr>\n" +
            "            </tbody></table>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "    <tr><td height=\"90\"> ";

    public static final String HTML_EXPIRE_REVOCATION_TEMPLATE_BEFORE_BODY = "<table width=\"100%\" " +
            "bgcolor=\"#FFFFFF\" " +
            "border=\"0\" " +
            "cellpadding=\"0\" cellspacing=\"0\" style=\"border:2px solid #999999\">\n" +
            "    <tbody><tr>\n" +
            "        <td style=\"border:none\">\n" +
            "            <table width=\"100%\" height=\"100%\" bgcolor=\"#1cc09f\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\n" +
            "                <tbody><tr><td height=\"10\"></td></tr>\n" +
            "                <tr>\n" +
            "                    <td width=\"2%\"></td>\n" +
            "                    <td style=\"color:#ffffff;font-family:open sans;line-height:20px;font-size:17px\" " +
            "width=\"90%\">Recognition Revocation </td>\n" +
            "                    <td width=\"5%\"></td>\n" +
            "                    <td><img src=\"https://ci5.googleusercontent.com/proxy/Er5Cqp3oDeOGVj7ubbmaMMPLd5R7Kmy3Nh8QrvNhXowL2XlimVsVTtHLplWKs4DDGhIm8iajMWmOhtOADa9z4sojP8U=s0-d-e1-ft#http://learning.tothenew.com/images/mail-logo.png\" class=\"CToWUd\"></td>\n" +
            "                    <td width=\"3%\"></td>\n" +
            "                </tr>\n" +
            "                <tr><td height=\"10\"></td></tr>\n" +
            "            </tbody></table>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "    <tr><td height=\"90\"> ";



    public static final String HTML_TEMPLATE_AFTER_BODY="</td></tr>\n" +
            "    \n" +
            "    <tr><td height=\"20\"></td></tr>\n" +
            "    <tr>\n" +
            "    <td>\n" +
            "        <table border=\"0\" align=\"left\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#f3f3f4\" width=\"100%\">\n" +
            "            <tbody><tr><td height=\"10\"></td></tr>\n" +
            "            <tr>\n" +
            "                <td width=\"2%\"></td>\n" +
            "                <td style=\"color:#999999;font-family:open sans;line-height:20px;font-size:17px\">Thanks,<br>Reap Team</td>\n" +
            "            </tr>\n" +
            "            <tr><td height=\"10\"></td></tr>\n" +
            "        </tbody></table>\n" +
            "    </td>\n" +
            "</tr>\n" +
            "\n" +
            "</tbody></table>";


    public static final String HTML_RECOGNITION_TEMPLATE_AFTER_BODY="</td></tr>\n" +
            "    \n" +
            "    <tr><td height=\"20\"></td></tr>\n" +
            "    <tr>\n" +
            "    <td>\n" +
            "        <table border=\"0\" align=\"left\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#f3f3f4\" width=\"100%\">\n" +
            "            <tbody><tr><td height=\"10\"></td></tr>\n" +
            "            <tr>\n" +
            "                <td width=\"2%\"></td>\n" +
            "                <td style=\"color:#999999;font-family:open sans;line-height:20px;font-size:17px\">Thanks,<br>Reap Team</td>\n" +
            "            </tr>\n" +
            "            <tr><td height=\"10\"></td></tr>\n" +
            "        </tbody></table>\n" +
            "    </td>\n" +
            "</tr>\n" +
            "\n" +
            "</tbody></table>";


    public static final String HTML_REVOCATION_TEMPLATE_AFTER_BODY="</td></tr>\n" +
            "    \n" +
            "    <tr><td height=\"20\"></td></tr>\n" +
            "    <tr>\n" +
            "    <td>\n" +
            "        <table border=\"0\" align=\"left\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#f3f3f4\" width=\"100%\">\n" +
            "            <tbody><tr><td height=\"10\"></td></tr>\n" +
            "            <tr>\n" +
            "                <td width=\"2%\"></td>\n" +
            "                <td style=\"color:#999999;font-family:open sans;line-height:20px;font-size:17px\">Thanks,<br>Reap Team</td>\n" +
            "            </tr>\n" +
            "            <tr><td height=\"10\"></td></tr>\n" +
            "        </tbody></table>\n" +
            "    </td>\n" +
            "</tr>\n" +
            "\n" +
            "</tbody></table>";


    public static final String HTML_EXPIRE_REVOCATION_TEMPLATE_AFTER_BODY="</td></tr>\n" +
            "    \n" +
            "    <tr><td height=\"20\"></td></tr>\n" +
            "    <tr>\n" +
            "    <td>\n" +
            "        <table border=\"0\" align=\"left\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#f3f3f4\" width=\"100%\">\n" +
            "            <tbody><tr><td height=\"10\"></td></tr>\n" +
            "            <tr>\n" +
            "                <td width=\"2%\"></td>\n" +
            "                <td style=\"color:#999999;font-family:open sans;line-height:20px;font-size:17px\">Thanks,<br>Reap Team</td>\n" +
            "            </tr>\n" +
            "            <tr><td height=\"10\"></td></tr>\n" +
            "        </tbody></table>\n" +
            "    </td>\n" +
            "</tr>\n" +
            "\n" +
            "</tbody></table>";

}

