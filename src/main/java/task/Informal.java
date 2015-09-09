package task;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


import javax.inject.Qualifier;


@Retention(value = RetentionPolicy.RUNTIME)
@Qualifier
public @interface Informal {

}
