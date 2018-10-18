package com.split.osiris.splitchores.utils;

import android.util.Patterns;

public class CommonUtils {
    public static boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
