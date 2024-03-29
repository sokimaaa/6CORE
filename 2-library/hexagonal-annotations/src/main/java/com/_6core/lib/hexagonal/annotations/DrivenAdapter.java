package com._6core.lib.hexagonal.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @DrivenAdapter - an implementation of how our application can use other application/service.
 * <p>
 * Applicable for annotating a class that implements outgoing interaction of
 * {@link PortOut @PortOut} interface,
 * e.g. implementation of interaction with DB layer (repository).
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface DrivenAdapter {
}
