package com._6core.lib.hexagonal.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @DomainService describes business logic for specific domain,
 * can be part of some complex business logic (see {@link UseCase @UseCase}).
 * <p>
 * Applicable for annotating an interface/class.
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface DomainService {
}
