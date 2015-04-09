package org.imsglobal.caliper.entities;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SupportedEntityTypes {
    EntityType[] value() default {};
}