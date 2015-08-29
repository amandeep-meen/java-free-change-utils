package org.annotation.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.FIELD, ElementType.TYPE })
public @interface Changed {

	Class<? extends ComparisonHelper<?>> helper() default DefaultHelper.class;

	public static interface ComparisonHelper<T> {
		public boolean isSame(T arg1, T arg2);
	}

	public static class DefaultHelper implements ComparisonHelper<Object> {

		@Override
		public boolean isSame(Object arg1, Object arg2) {

			return DetectChanges.isSame(arg1, arg2);
		}

	}

}
