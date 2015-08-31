package org.annotation.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.annotation.utils.ObserveChanges.ComparisonHelper;

public class DetectChangesUtils {

	public static <T> boolean isChanged(T arg1, T arg2) {
		return isSame(arg1, arg2) == false;
	}

	public static <T> boolean isSame(T arg1, T arg2) {

		if (arg1 == null && arg2 == null) {
			return true;
		}

		if (isCollection(arg1) || isCollection(arg2)) {
			Class<?> clazz = arg1 == null ? arg2.getClass() : arg1.getClass();
			return isSameCollection(nonNullCollection(arg1, clazz), nonNullCollection(arg2, clazz));
		}
		if (arg1 == null || arg2 == null) {
			return false;
		}

		if (arg1.getClass().equals(arg2.getClass()) == false) {
			return false;
		}

		Class<?> clazz = arg1.getClass();
		if (clazz.isPrimitive()) {
			return (arg1 == arg2);
		}
		/*
		 * if (isCollection(clazz)) { System.out.println("This is Collection");
		 * return isSameCollection(arg1, arg2); }
		 */

		if (clazz.isAnnotationPresent(ObserveChanges.class)) {
			for (Field field : clazz.getDeclaredFields()) {
				if (field.isAnnotationPresent(IgnoreChanges.class)) {
					/* Ignore this field */
					continue;
				}

				try {

					boolean same = false;
					if (field.isAnnotationPresent(ObserveChanges.class)) {
						ObserveChanges annotation = field.getAnnotation(ObserveChanges.class);
						Method metthod = clazz.getMethod(annotation.method().isEmpty() ? getMetthodName(field) : annotation.method());
						@SuppressWarnings("unchecked")
						ComparisonHelper<? super Object> helper = (ComparisonHelper<? super Object>) annotation.helper().newInstance();
						same = helper.isSame(metthod.invoke(arg1), metthod.invoke(arg2));
					} else {
						Method metthod = clazz.getMethod(getMetthodName(field));
						ComparisonHelper<? super Object> helper = ObserveChanges.DefaultHelper.INSTANCE;
						same = helper.isSame(metthod.invoke(arg1), metthod.invoke(arg2));
					}
					if (same) {
						continue;
					}

					/* Wherever mismatch return */
					return false;
				} catch (Exception e) {
					throw new IllegalStateException(e);
				}
			}
			/* If compared successfully then return true */
			return true;
		}

		return arg1.equals(arg2);
	}

	private static <T> boolean isSameCollection(T arg1, T arg2) {
		Collection<?> col1 = (Collection<?>) arg1;
		Collection<?> col2 = (Collection<?>) arg2;

		boolean actualSize = col1.size() == col2.size();
		/* Actual Size Does not match */
		if (actualSize == false) {
			return false;
		}
		/* Remove Nulls */
		col1 = collectionAfterNullElimination(col1);
		col2 = collectionAfterNullElimination(col2);
		/* Check Size After removing nulls */
		actualSize = col1.size() == col2.size();
		/* Actual Size Does not match */
		if (actualSize == false) {
			return false;
		}
		/* Compare Lists */
		for (Object col1Object : col1) {
			boolean found = false;
			Iterator<?> col2Iterator = col2.iterator();
			while (col2Iterator.hasNext()) {
				Object col2Object = col2Iterator.next();
				found = isSame(col1Object, col2Object);
				if (found) {
					col2Iterator.remove();
					break;
				}
			}
			/* Outer loop: take decision to continue */
			if (found == false) {
				/* collections are not same */
				return false;
			}
		}
		return true;
	}

	private static boolean isCollection(Object arg) {
		if (arg == null) {
			return false;
		}
		return Collection.class.isAssignableFrom(arg.getClass());
	}

	private static Object nonNullCollection(Object arg, Class<?> clazz) {
		if (arg == null) {
			try {
				return clazz.newInstance();
			} catch (Exception e) {
				throw new IllegalStateException(e);
			}

		}
		return arg;
	}

	private static String getMetthodName(Field field) {
		if (field.getType().isPrimitive() && boolean.class.isAssignableFrom(field.getType())) {
			return "is" + firstCapitalLetter(field.getName());
		}
		return "get" + firstCapitalLetter(field.getName());
	}

	private static String firstCapitalLetter(String val) {

		char[] stringArray = val.trim().toCharArray();
		stringArray[0] = Character.toUpperCase(stringArray[0]);
		return new String(stringArray);

	}

	private static Collection<?> collectionAfterNullElimination(Collection<?> col) {
		List<Object> collList = new ArrayList<Object>();
		for (Object obj : col) {
			if (obj == null) {
				continue;
			}
			collList.add(obj);
		}
		return collList;

	}
}
