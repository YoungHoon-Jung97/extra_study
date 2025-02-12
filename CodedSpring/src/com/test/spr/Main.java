/*============================================================
  Main.java
  - main() �޼ҵ带 �����ϰ� �ִ� �׽�Ʈ Ŭ����
 ================================================================*/
package com.test.spr;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main
{
   public static void main(String[] args)
   {     
      //MemberList member = new MemberList();
      //-- ��ü ���� ���� �� ������ �������� ����ϰ� �ǹǷ�
      //   �ν��Ͻ� ���� ���� ����~!!!
      
      // ������ ȯ�� ���� ���Ϸ� ���� applicationContext.xml ����
      // MemberList Ŭ���� ����� ��ü�� ���� �� �ֵ��� �ڵ� ����
      ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
      
      MemberList member = context.getBean("member",MemberList.class);
      
      // �����ؾ��ϴ� ����
      member.print();
   }
}