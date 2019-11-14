package com.ruokapp.core.helper;

public class ErrorMessage {

    public static final String GENERAL_ERROR = "Something went wrong. Try Again";
    public static final String USERNAME_EMPTY_ERROR = "The field Username cannot be empty";
    public static final String USERNAME_SPECIAL_CHARS_ERROR = "The Username could not contain special characters";

    public static final String EMAIL_EMPTY_ERROR = "The field Email cannot be empty";
    public static final String EMAIL_INVALID_ERROR = "The Email is not valid";

    public static final String PASS_EMPTY_ERROR = "The field Password cannot be empty";
    public static final String PASS_LENGTH_ERROR = "The Password must be contain 8 characters";

    public static final String REPEAT_PASS_EMPTY_ERROR = "The field Repeat Password cannot be empty";
    public static final String REPEAT_PASS_DOESNT_MATCH_ERROR = "The passwords doesn't match";

    public static final String EMAIL_REGISTERED_ERROR = "This email was register";

    public static final String REGISTER_ERROR = "Could not register. Try again";

    public static final String LOGIN_ERROR = "Verify your email and/or password";

    public static final String MAX_REQUEST_AVAILABLE_ERROR = "You have already reached the max request limit by day";
}
