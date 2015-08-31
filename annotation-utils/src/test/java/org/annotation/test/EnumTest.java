package org.annotation.test;

import org.annotation.utils.DetectChangesUtils;
import org.junit.Assert;
import org.junit.Test;

public class EnumTest {

	@Test
	public void testNullChanges() {
		boolean actual = DetectChangesUtils.isSame(null, null);
		Assert.assertTrue("Expected True ", new Boolean(actual));
	}

	@Test
	public void testNullArg1Changes() {
		boolean actual = DetectChangesUtils.isSame(null, new String());
		Assert.assertFalse("Expected False ", new Boolean(actual));
	}
	
	@Test
	public void testNullArg2Changes() {
		boolean actual = DetectChangesUtils.isSame(new String(),null);
		Assert.assertFalse("Expected False ", new Boolean(actual));
	}

	@Test
	public void testEnumChanges() {
		boolean actual = DetectChangesUtils.isSame(HeaderEnum.H1, HeaderEnum.H1);
		Assert.assertTrue("Expected True ", new Boolean(actual));
	}

	public static enum HeaderEnum {
		H1, H2, H3
	}

}
