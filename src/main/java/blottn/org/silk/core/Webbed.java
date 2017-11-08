package blottn.org.silk.core;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Nick on 18/10/2017.
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface Webbed {
    boolean enabled() default true;
}
