package org.annotation.test;

import org.annotation.utils.DetectChanges;
import org.junit.Assert;
import org.junit.Test;

public class EnumTest {

	@Test
	public void testNullChanges() {
		boolean actual = DetectChanges.isSame(null, null);
		Assert.assertTrue("Expected True ", new Boolean(actual));
	}

	@Test
	public void testNullArg1Changes() {
		boolean actual = DetectChanges.isSame(null, new String());
		Assert.assertFalse("Expected False ", new Boolean(actual));
	}
	
	@Test
	public void testNullArg2Changes() {
		boolean actual = DetectChanges.isSame(new String(),null);
		Assert.assertFalse("Expected False ", new Boolean(actual));
	}

	@Test
	public void testEnumChanges() {
		boolean actual = DetectChanges.isSame(HeaderEnum.H1, HeaderEnum.H1);
		Assert.assertTrue("Expected True ", new Boolean(actual));
	}

	public static enum HeaderEnum {
		H1, H2, H3
	}

}
