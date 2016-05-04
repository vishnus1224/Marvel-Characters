package com.vishnus1224.marvelcharacters.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Used for objects that should live as long as the activity.
 * Created by Vishnu on 4/29/2016.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
