
package app.co.jp.util.IF;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import app.co.jp.util.ByteSizeValidator;

@Documented
@Constraint(validatedBy = {ByteSizeValidator.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface ByteSize {
	String message() default "{validation.ByteSize.message}";


	  Class<?>[] groups() default {};

	  Class<? extends Payload>[] payload() default {};

	  String encoding() default "UTF-8";
	  //String encoding() default "SHIFT-JIS";

	  int size();

	  @Target({FIELD})
	  @Retention(RUNTIME)
	  @Documented
	  public @interface List {
	    ByteSize[] value();
	  }
}
