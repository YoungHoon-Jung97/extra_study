/*=================================
   MemberList.java
   - DAO Ŭ������ �����ϴ� Ŭ����
   - ���� ���� ����
   - print() �޼ҵ� ����
================================== */

package com.test.spr;

public class MemberList
{
	// IOC/DI ���� ��
	/*
	// ó�� �����ϴ� Ŭ���� ����
	private OracleDAO dao;
	
	public MemberList()
	{
		//MemberList ���忡���� ������ü OracleDAO
		dao = new OracleDAO();
	}
	*/
	// �ι�° �����ϴ� Ŭ���� ����
	/*
	private MssqlDAO dao;
	
	public MemberList()
	{
		//MemberList ���忡���� ������ü MssqlDAO
		dao = new MssqlDAO();
	}
	*/

	// �� ������ IoC/DI ���� ����
	
	//private ����� �����;
	//private ��ȯ�� ȯ��;
	//             ��
	//private ��� �����콺Ʈ�ι�;
	
	// check
	// ��� ������ �߰��ϴ� �������� ������ Ÿ���� �������̽� ����
	// �� DI �� ���� �غ� �۾�
	private IDAO dao;
	
	// check!!
	// setter �޼ҵ� �߰�
	public void setDao(IDAO dao)
	{
		this.dao = dao;
	}
	
	// print() �޼ҵ� ���� �� ���� ���� ����. �״�� ���.
	public void print()
	{
		try
		{
			for (MemberDTO dto : dao.list())
			{
				System.out.printf("%14s %4s %15s %15s%n"
							, dto.getId(), dto.getName()
							, dto.getTel(), dto.getEmail());
			}
		} 
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
}