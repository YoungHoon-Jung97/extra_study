/*================
   IDAO.java
   - �������̽�
=================*/

package com.test.spr;

import java.sql.SQLException;
import java.util.List;

public interface IDAO
{
	// �޼ҵ� ���� �� �߻� �޼ҵ�
	public List<MemberDTO> list() throws ClassNotFoundException, SQLException;
	
}