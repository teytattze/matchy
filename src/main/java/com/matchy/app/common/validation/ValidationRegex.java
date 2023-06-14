package com.matchy.app.common.validation;

public class ValidationRegex {
    public static final String FOCUS_AREA_NAME = "^[A-Z][a-z]+(?:[._\\- ][A-Za-z]+)*$";
    public static final String USER_EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public static final String USER_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=.\\-_*])([a-zA-Z0-9@#$%^&+=*.\\-_]){8,}$";
    public static final String USER_FIRST_NAME = "^[A-Z][a-z]+$";
    public static final String USER_LAST_NAME = "^[A-Z][a-z]+([- ][A-Z][a-z]+)*$";
}
