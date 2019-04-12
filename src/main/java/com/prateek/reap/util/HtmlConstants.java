package com.prateek.reap.util;


public class HtmlConstants {


    // Html Pages

    public static final String LOG_IN_HTML_PAGE = "/login";
    public static final String SIGN_UP_HTML_PAGE = "/signup";
    public static final String ITEM_HTML_PAGE = "/item";
    public static final String FORGET_PASSWORD_HTML_PAGE = "/forgetPassword";
    public static final String RESET_PASSWORD_HTML_PAGE = "/resetPassword";
    public static final String DASHBOARD_HTML_PAGE = "/dashboard";
    public static final String BADGE_HTML_PAGE = "/badges";
    public static final String HTML_404_PAGE = "404";
    public static final String HTML_500_PAGE = "500";
    public static final String HTML_ERROR_PAGE = "error";

    // Redirects

    public static final String REDIRECT_TO_LOGIN = "redirect:/login";
    public static final String REDIRECT_TO_SIGN_UP = "redirect:/signup";
    public static final String REDIRECT_TO_FORGET_PASSWORD = "redirect:/forget-password";
    public static final String REDIRECT_TO_DASHBOARD = "redirect:/dashboard";


    // Keys

    public static final String KEY_LOGGED_IN_USER = "loggedUser";
    public static final String KEY_BADGE = "badge";
    public static final String KEY_USERS = "users";
    public static final String KEY_RECEIVE_STARS = "recvstars";
    public static final String KEY_STAR_COUNT = "starCount";
    public static final String KEY_STAR_RECEIVED = "starReceived";
    public static final String KEY_WALL_OF_FAME = "wallOfFame";
    public static final String KEY_NEWERS_BOARD = "newersBoard";
    public static final String KEY_LINK_EXPIRE = "expire";
    public static final String KEY_EMAIL_ERROR ="emailError";
    public static final String KEY_SELF_ERROR ="selfRecoError";
    public static final String KEY_ERROR = "error";
    public static final String KEY_EMAIL_EXISTS_ERROR = "exist";
    public static final String KEY_SIGN_IN_SUCCESS = "signsuccess";
    public static final String KEY_RESET_PASSWORD_SUCCESS = "resetPassword";
    public static final String KEY_BINDING_RESULT = "binding";
    public static final String KEY_TOKEN = "token";
    public static final String KEY_USER = "user";
    public static final String KEY_PASSWORD_SUCCESS= "passwordsuccess";
    public static final String KEY_SUCCESS = "success";
    public static final String KEY_NEW_PASSWORD = "newPassword";
    public static final String KEY_RECOGNITION_FAILURE = "saveError";
    public static final String KEY_COMMENT_BLANK = "commentBlank";
    public static final String KEY_SET_HEADER_CSV = "Content-Disposition";
    public static final String KEY_ITEMS = "items";
    public static final String KEY_TOTAL_PRICE = "totalPrice";
    public static final String KEY_ITEM_URLS = "itemUrls";

    // Messages

    public static final String VALUE_LOGIN_ERROR = "Bad Credentials, Please Try Again";
    public static final String VALUE_EMAIL_EXISTS_ERROR = "Email Id Already Exists";
    public static final String VALUE_WRONG_EMAIL_ENTERED =
            "Please Enter Correct Email Id";
    public static final String VALUE_SIGN_UP_SUCCESS = "Registration Successfull,You can Login Now";
    public static final String VALUE_FORGET_PASSWORD_SUCCESS = "Email Sent Successfully";
    public static final String VALUE_RESET_PASSWORD_SUCCESS = "Password Resetted, Please Log in.";
    public static final String VALUE_RECOGNITION_SUCCESS = "Newer Has Been Successfully RECOGNIZED";
    public static final String VALUE_RECOGNITION_FAILURE = "Cannot Give StarType To Newer";
    public static final String VALUE_SET_HEADER_CSV = "attachment; file=BadgesGiven.csv";
    public static final String VALUE_RECOGNITION_RECIEVER_EMAIL = "User Doesn't exists";
    public static final String VALUE_SELF_RECOGNITION_ERROR =
            "Cannot Give To Recognition to yourself";
    public static final String VALUE_RESET_PASSWORD_ERROR = "Reset Password Link is expired";
    public static final String VALUE_BLANK_COMMENT =
            "Comment you entered is blank.Please enter correctly.";

    // Thymleaf Objects

    public static final String THYMELEAF_ALL_SHARED_RECOGNITION = "sharedRecognition";
    public static final String THYMELEAF_ALL_RECIEVED_RECOGNITION = "receivedRecognition";
    public static final String THYMELEAF_ALL_RECOGNITION = "allRecognition";
    public static final String THYMELEAF_STAR_RECEIVED = "starReceived";
    public static final String THYMELEAF_PURCHASE_HISTORY = "purchaseHistory";

    // Request Objects

    public static final String REQUEST_PARAM_PROFILE_IMAGE = "photo";
    public static final String REQUEST_PARAM_RECEIVER = "receiver";
    public static final String REQUEST_PARAM_COMMENT = "comment";
    public static final String REQUEST_PARAM_STAR = "star";
    public static final String REQUEST_PARAM_END_DATE = "endDate";
    public static final String REQUEST_PARAM_END = "end";
    public static final String REQUEST_PARAM_STARTDATE = "startDate";
    public static final String REQUEST_PARAM_START= "start";
    public static final String REQUEST_PARAM_EMAIL = "email";
    public static final String REQUEST_PARAM_POINT = "point";
    public static final String REQUEST_PARAM_USER_ID = "userId";
    public static final String REQUEST_PARAM_ROLE = "role";
    public static final String PATH_VARIABLE_ID = "id";

}
