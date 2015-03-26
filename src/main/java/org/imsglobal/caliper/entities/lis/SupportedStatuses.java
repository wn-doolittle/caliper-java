package org.imsglobal.caliper.entities.lis;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SupportedStatuses {
    Status[] value() default {};
}