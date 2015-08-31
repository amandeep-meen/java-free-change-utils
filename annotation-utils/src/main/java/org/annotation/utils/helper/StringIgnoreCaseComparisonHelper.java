package org.annotation.utils.helper;

import org.annotation.utils.ObserveChanges.ComparisonHelper;

public class StringIgnoreCaseComparisonHelper implements ComparisonHelper<String> {

	private static String BLANK = "";

	@Override
	public boolean isSame(String arg1, String arg2) {

		return trimToEmpty(arg1).equalsIgnoreCase(trimToEmpty(arg2));
	}

	private String trimToEmpty(String val) {
		return val == null ? BLANK : val.trim();
	}

}
