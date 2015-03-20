package org.imsglobal.caliper.events;

import org.imsglobal.caliper.profiles.Profile.Action;

import java.lang.annotation.*;

/**
 * Created by pgray on 3/20/15.
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SupportedActions {

    Action[] value() default {};

}
