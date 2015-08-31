package org.annotation.test;

import org.annotation.test.model.Student;
import org.annotation.utils.DetectChangesUtils;
import org.junit.Assert;
import org.junit.Test;

public class StudentTest {

	@Test
	public void testNameChanges() {
		boolean actual = DetectChangesUtils.isSame(new Student("S1"), new Student("S2"));
		Assert.assertEquals(Boolean.FALSE, new Boolean(actual));
	}

	@Test
	public void testNameAndRollChanges() {
		boolean actual = DetectChangesUtils.isSame(new Student("S1", null), new Student("S1", 20));
		Assert.assertEquals(Boolean.FALSE, new Boolean(actual));
	}

	@Test
	public void testNameAndRollSameChanges() {
		boolean actual = DetectChangesUtils.isSame(new Student("S1", 20), new Student("S1", 20, true));
		Assert.assertEquals(Boolean.FALSE, new Boolean(actual));
	}

	@Test
	public void testNullChanges() {
		boolean actual = DetectChangesUtils.isSame(null, null);
		Assert.assertTrue("Expected True ", new Boolean(actual));
	}

	@Test
	public void testNullArg1Changes() {
		boolean actual = DetectChangesUtils.isSame(null, new Student("S1"));
		Assert.assertFalse("Expected False ", new Boolean(actual));
	}

	@Test
	public void testNullArg2Changes() {
		boolean actual = DetectChangesUtils.isSame(new Student("S1"), null);
		Assert.assertFalse("Expected False ", new Boolean(actual));
	}

}
