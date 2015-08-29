package org.annotation.test.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class StudentCollegeDataBuilder {

	public static Collection<Student> buildStudentList(int nonNullCount, int nullCount) {
		List<Student> students = new ArrayList<Student>();
		for (int i = 0; i < nonNullCount; i++) {
			students.add(new Student("Name" + i, i, i % 2 == 0));
		}
		for (int i = 0; i < nullCount; i++) {
			students.add(null);
		}
		Collections.shuffle(students);

		return students;
	}

}
