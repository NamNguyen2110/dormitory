package com.web.assgiment.dormitory.common.validator.group;

public class RegexContant {

    public static final String USERNAME_REGEX= "[a-zA-Z0-9]";

    public static final String CODE_REGEX = "([A-Z0-9_]+)$";

    public static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*@\" + \"[A-Za-z0-9-]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$";
}
