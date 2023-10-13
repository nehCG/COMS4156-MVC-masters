package com.mvcmasters.ems.utils;

import com.mvcmasters.ems.exceptions.ParamsException;

public class AssertUtil {

    public static void isTrue(Boolean flag, String msg) {
        if (flag) {
            throw new ParamsException(msg);
        }
    }

}
