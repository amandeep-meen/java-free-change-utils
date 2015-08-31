package org.annotation.test;

import java.util.Collection;
import java.util.HashSet;

import org.annotation.test.model.Student;
import org.annotation.test.model.StudentCollegeDataBuilder;
import org.annotation.utils.DetectChangesUtils;
import org.junit.Assert;
import org.junit.Test;

public class StudentColleagueTest {

	@Test
	public void testNameChanges() {

		Student s1 = new Student("S1");
		s1.setColleagues(build(400, 0));
		Student s2 = new Student("S1");
		s2.setColleagues(build(400, 0));

		boolean actual = DetectChangesUtils.isSame(s1, s2);
		Assert.assertEquals(Boolean.TRUE, new Boolean(actual));
	}

	@Test
	public void testColleageDifferentSizeChanges() {

		Student s1 = new Student("S1");
		s1.setColleagues(build(400, 0));
		Student s2 = new Student("S1");
		s2.setColleagues(build(300, 0));

		boolean actual = DetectChangesUtils.isSame(s1, s2);
		Assert.assertEquals(Boolean.FALSE, new Boolean(actual));
	}

	@Test
	public void testColleageSameSizeHavingNullChanges() {

		Student s1 = new Student("S1");
		s1.setColleagues(build(200, 2));
		Student s2 = new Student("s1");
		s2.setColleagues(build(200, 2));

		boolean actual = DetectChangesUtils.isSame(s1, s2);
		Assert.assertEquals(Boolean.TRUE, new Boolean(actual));
	}

	@Test
	public void testColleageDifferentSizeHavingNullChanges() {

		Student s1 = new Student("S1");
		s1.setColleagues(build(2, 2));
		Student s2 = new Student("S1");
		s2.setColleagues(build(2, 3));

		boolean actual = DetectChangesUtils.isSame(s1, s2);
		Assert.assertEquals(Boolean.FALSE, new Boolean(actual));
	}

	@Test
	public void testNullAndEmptyCollectionlChanges() {

		Student s1 = new Student("S1");
		s1.setColleagues(build(4, 0));
		Student s2 = new Student("S1");
		s2.setColleagues(new HashSet<Student>(build(4, 0)));

		boolean actual = DetectChangesUtils.isSame(s1, s2);
		Assert.assertEquals(Boolean.TRUE, new Boolean(actual));

		Assert.assertEquals(new Integer(4), new Integer(s1.getColleagues().size()));
		Assert.assertEquals(new Integer(4), new Integer(s2.getColleagues().size()));
	}

	@Test
	public void testArrayAndSetCollection() {

		Student s1 = new Student("S1");
		s1.setColleagues(build(0, 0));
		Student s2 = new Student("S1");

		boolean actual = DetectChangesUtils.isSame(s1, s2);
		Assert.assertEquals(Boolean.TRUE, new Boolean(actual));
	}

	public Collection<Student> build(int nonNullCount, int nullCount) {
		return StudentCollegeDataBuilder.buildStudentList(nonNullCount, nullCount);
	}
}
