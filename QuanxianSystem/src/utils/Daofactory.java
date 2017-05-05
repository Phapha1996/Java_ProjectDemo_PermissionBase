package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import dao.itf.PrivilegeDao;
import dao.itf.ResourceDao;
import dao.itf.RoleDao;
import dao.itf.UserDao;

public class Daofactory {
	
	public static String privilegeDaoName;
	public static String roleDaoName;
	public static String userDaoName;
	public static String resourceDaoName;
	static {
		try {
			InputStream in = Daofactory.class.getClassLoader().getResourceAsStream("dao.properties");
			Properties pro = new Properties();
			pro.load(in);
			privilegeDaoName = pro.getProperty("privilegeDaoName");
			roleDaoName = pro.getProperty("roleDaoName");
			userDaoName = pro.getProperty("userDaoName");
			resourceDaoName = pro.getProperty("resourceDaoName");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//工厂设计模式面向接口编程篇
	public static PrivilegeDao getPrivilegeDao(){
		try {
			return (PrivilegeDao) Class.forName(privilegeDaoName).newInstance();
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	//得到RoleDao
	public static RoleDao getRoleDao(){
		try {
			return (RoleDao)Class.forName(roleDaoName).newInstance();
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	
	public static UserDao getUserDao(){
		try {
			return (UserDao)Class.forName(userDaoName).newInstance();
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	
	public static ResourceDao getResourceDao(){
		try {
			return (ResourceDao)Class.forName(resourceDaoName).newInstance();
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}
