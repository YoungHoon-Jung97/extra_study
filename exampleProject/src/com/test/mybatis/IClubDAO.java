package com.test.mybatis;

public interface IClubDAO {

	public int add(ClubDTO dto);
	
	public int search(String name);
}
