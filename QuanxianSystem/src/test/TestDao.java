package test;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import utils.Daofactory;
import dao.itf.PrivilegeDao;
import domain.Privilege;

public class TestDao {

	@Test
	public void testAdd(){
		try{
		Privilege p = new Privilege();
		p.setId("1");
		p.setName("����");
		p.setDiscription("����������");
		PrivilegeDao dao = (PrivilegeDao) Daofactory.getPrivilegeDao();
		dao.add(p);
		System.out.println("��ӳɹ�");
		}catch(Exception e){
			
			e.printStackTrace();
			System.out.println("���ʧ�ܣ�");
		}
	}
	
	
	
	
	@Test
	public void testFind(){
		try{
			PrivilegeDao dao = Daofactory.getPrivilegeDao();
			
			Privilege p = dao.find("1");
			System.out.println(p+"���ҳɹ�");
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("����ʧ�ܣ�");
		}
			
		
	}
	
	@Test
	public void testgetAll(){
		try{
			PrivilegeDao dao = Daofactory.getPrivilegeDao();
			List<Privilege> list = dao.getAll();
			System.out.println("���ҳɹ�"+list.size());
		}catch(Exception e){
			System.out.println("����ʧ�ܣ�����");
			e.printStackTrace();
		}
	}
	
}
