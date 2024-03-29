package com._6core.lib.hexagonal.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @PortOut describes how our application can use other application/service.
 * <p>
 * Applicable for annotating an interface that describes outgoing interaction,
 * e.g. interface with DB layer (repository).
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface PortOut {
}
