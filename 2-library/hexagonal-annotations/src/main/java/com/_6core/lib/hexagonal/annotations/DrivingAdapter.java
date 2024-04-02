package com._6core.lib.hexagonal.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @DrivingAdapter - an implementation of how our application can be used by others.
 * <p>
 * Applicable for annotating a class that implements incoming interaction of
 * {@link PortIn @PortIn} interface,
 * e.g. Controller class.
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface DrivingAdapter {
}
