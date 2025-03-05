package com.test.mybatis;

import java.util.ArrayList;

public interface IGradeDAO
{
	public ArrayList<GradeDTO> list();
	public int add(GradeDTO g);
	public int count();
	public GradeDTO search(String sid);
}
