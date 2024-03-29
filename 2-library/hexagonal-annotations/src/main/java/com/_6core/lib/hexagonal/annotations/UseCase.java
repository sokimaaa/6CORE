package com._6core.lib.hexagonal.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @UseCase describes some complex business logic for specific use case,
 * can consist of several domain service logic (see {@link DomainService @DomainService})
 * <p>
 * Applicable for annotating an interface/class.
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface UseCase {
}
