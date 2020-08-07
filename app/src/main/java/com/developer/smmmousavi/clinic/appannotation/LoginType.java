package com.developer.smmmousavi.clinic.appannotation;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.StringDef;



@Retention(RetentionPolicy.SOURCE)
@StringDef({
    LoginType.LoggedOut,
    LoginType.LoggedIn,
    LoginType.NoUserName
})

public @interface LoginType {
    String LoggedOut = "LoggedOut";
    String LoggedIn = "LoggedIn";
    String NoUserName = "NoUserName";
}

