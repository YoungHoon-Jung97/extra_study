package com.test.mybatis;

import java.util.ArrayList;

public interface IStudentDAO
{
	public int count();
	public ArrayList<StudentDTO> list();
	public int add(StudentDTO s);
	public StudentDTO search(String sid);
	public int modify(StudentDTO s);
	public int remove(String sid);
}
