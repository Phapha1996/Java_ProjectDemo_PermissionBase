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
		p.setName("梁媛");
		p.setDiscription("我是梁豆豆");
		PrivilegeDao dao = (PrivilegeDao) Daofactory.getPrivilegeDao();
		dao.add(p);
		System.out.println("添加成功");
		}catch(Exception e){
			
			e.printStackTrace();
			System.out.println("添加失败！");
		}
	}
	
	
	
	
	@Test
	public void testFind(){
		try{
			PrivilegeDao dao = Daofactory.getPrivilegeDao();
			
			Privilege p = dao.find("1");
			System.out.println(p+"查找成功");
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("查找失败！");
		}
			
		
	}
	
	@Test
	public void testgetAll(){
		try{
			PrivilegeDao dao = Daofactory.getPrivilegeDao();
			List<Privilege> list = dao.getAll();
			System.out.println("查找成功"+list.size());
		}catch(Exception e){
			System.out.println("查找失败！！！");
			e.printStackTrace();
		}
	}
	
}
