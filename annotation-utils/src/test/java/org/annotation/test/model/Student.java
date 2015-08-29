package org.annotation.test.model;

import java.util.Collection;

import org.annotation.utils.Changed;

@Changed
public class Student {
	private Integer rollNumber;
	private String name;
	private boolean pass;

	private Collection<Student> colleagues;

	public Student(String name) {
		this(name, null, false);
	}

	public Student(String name, Integer rollNumber) {
		this(name, rollNumber, false);
	}

	public Student(String name, Integer rollNumber, boolean pass) {
		this.name = name;
		this.rollNumber = rollNumber;
		this.pass = pass;
	}

	public Integer getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(Integer rollNumber) {
		this.rollNumber = rollNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isPass() {
		return pass;
	}

	public void setPass(boolean pass) {
		this.pass = pass;
	}

	public Collection<Student> getColleagues() {
		return colleagues;
	}

	public void setColleagues(Collection<Student> colleagues) {
		this.colleagues = colleagues;
	}
}
