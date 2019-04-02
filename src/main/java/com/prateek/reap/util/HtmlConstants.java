package com.vagish.reap.util;


public class HtmlConstants {


    //Html Pages

    public static final String LOG_IN_HTML_PAGE = "auth/login";
    public static final String SIGN_IN_HTML_PAGE = "auth/signUp";
    public static final String FORGET_PASSWORD_HTML_PAGE = "auth/forgetPassword";
    public static final String RESET_PASSWORD_HTML_PAGE = "auth/resetPassword";
    public static final String DASHBOARD_HTML_PAGE = "dashboard/dashboard";
    public static final String ITEM_HTML_PAGES = "cart/warehouse";


    //Redirects

    public static final String REDIRECT_TO_LOGIN = "redirect:/login";
    public static final String REDIRECT_TO_SIGN_UP = "redirect:/signup";
    public static final String REDIRECT_TO_FORGET_PASSWORD = "redirect:/forget-password";
    public static final String REDIRECT_TO_DASHBOARD = "redirect:/dashboard";
    public static final String REDIRECT_TO_ITEM = "redirect:/item-warehouse";


    //Model Key
    public static final String MODEL_SIGN_SUCCESS_KEY = "sign-success";
    public static final String MODEL_FORGET_PASSWORD_SUCCESS_KEY = "forgetPassword";
    public static final String MODEL_RESET_PASSWORD_SUCCESS_KEY = "resetPassword";
    public static final String MODEL_RECOGNITION_SUCCESS_KEY = "success";
    public static final String MODEL_RECOGNITION_FAILURE_KEY = "saveError";
    public static final String MODEL_RECOGNITION_FAILURE_RECEIVER_EMAIL = "emailError";
    public static final String MODEL_SELF_RECOGNITION_ERROR = "selfRecoError";


    //Keys

    public static final String KEY_ERROR = "error";
    public static final String KEY_EMAIL_EXISTS_ERROR = "emailError";
    public static final String KEY_SIGN_IN_SUCCESS = "signSuccess";
    public static final String KEY_FORGET_PASSWORD_SUCCESS = "forgetPassword";
    public static final String KEY_RESET_PASSWORD_SUCCESS = "resetPassword";
    public static final String KEY_BINDING_RESULT = "binding";
    public static final String KEY_TOKEN = "token";
    public static final String KEY_USER = "user";
    public static final String KEY_SUCCESS = "success";
    public static final String KEY_NEW_PASSWORD = "newPassword";
    public static final String KEY_RECOGNITION_FAILURE = "saveError";
    public static final String KEY_SET_CONTENT_TYPE_CSV = "text/csv";
    public static final String KEY_SET_HEADER_CSV = "Content-Disposition";
    public static final String KEY_ITEM_EXISTS_ERROR = "itemError";
    public static final String KEY_ITEM = "item";
    public static final String KEY_ITEMS = "items";
    public static final String KEY_UPDATE_ITEM = "updateItem";
    public static final String KEY_UPDATE_SUCCESS = "updateSuccess";


    //Messages

    public static final String VALUE_LOGIN_ERROR = "Bad Credentials, Please Try Again";
    public static final String VALUE_EMAIL_EXISTS_ERROR = "Email Id Already Exists";
    public static final String VALUE_WRONG_EMAIL_ENTERED = "Email-id entered is wrong, Please enter correct id.";
    public static final String VALUE_SIGN_IN_SUCCESS = "Sign In Successful, Please Log in.";
    public static final String VALUE_FORGET_PASSWORD_SUCCESS = "Email Sent Successfully";
    public static final String VALUE_RESET_PASSWORD_SUCCESS = "Password Resetted, Please Log in.";
    public static final String VALUE_RECOGNITION_SUCCESS = "Newer Has Been Successfully RECOGNIZED";
    public static final String VALUE_RECOGNITION_FAILURE = "Cannot Give Star To Newer";
    public static final String VALUE_SET_HEADER_CSV = "attachment; file=customers.csv";
    public static final String VALUE_RECOGNITION_RECIEVER_EMAIL = "User Doesn't exists";
    public static final String VALUE_ITEM_EXISTS_ERROR = "Item Already Exists";
    public static final String VALUE_ITEM_ADD_SUCCESS = "Item Added Successfully";
    public static final String VALUE_ITEM_UPDATE_SUCCESS = "Item updated Successfully";
    public static final String VALUE_SELF_RECOGNITION_ERROR = "Cannot Give To Recognition to yourself";



    //Thymleaf Objects

    public static final String THYMELEAF_FIND_ALL_ACTIVE_RECOGNITION = "recog";
    public static final String THYMELEAF_ADD_BADGE_OBJECT = "badge";
    public static final String THYMELEAF_ADD_USERS_STAR_COUNT_OBJECT = "users";


}
