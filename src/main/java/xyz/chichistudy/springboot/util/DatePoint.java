package xyz.chichistudy.springboot.util;

import java.lang.annotation.Target;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import static xyz.chichistudy.springboot.util.DatePoint.ComparePattern.*;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = DatePoint.DatePointValidator.class)
public @interface DatePoint {
	
	ComparePattern comparePattern() default PastOrPresent;
	
	String message() default "Date out of range";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

	class DatePointValidator implements ConstraintValidator<DatePoint, Object> {
		
		private DatePoint datePoint;

		@Override
		public void initialize(DatePoint datePoint) {
			this.datePoint = datePoint;
		}

		@Override
		public boolean isValid(Object value, ConstraintValidatorContext context) {
			if (value == null) {
				return true;
			}
			LocalDate valueDate = ((Date) value).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			
			int result = LocalDate.now().compareTo(valueDate);
			switch(datePoint.comparePattern()) {
				case Future:
					return result < 0;
				case FutureOrPresent:
					return result <= 0;
				case Past:
					return result > 0;
				case PastOrPresent:
					return result >= 0;
				default:
					return false;
			}
		}
		
	}
	
	enum ComparePattern {
		Future,
		Past,
		FutureOrPresent,
		PastOrPresent;
	}
}
