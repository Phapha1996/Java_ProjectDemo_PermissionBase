package dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import utils.JdbcUtils;
import utils.WebUtils;
import dao.itf.UserDao;
import domain.Role;
import domain.User;

public class UserMysqlDaoImpl implements UserDao {

	//����һ��User
	/* (non-Javadoc)
	 * @see dao.impl.UserDao#add(domain.User)
	 */
	public void add(User user) throws SQLException{
		QueryRunner qr = new QueryRunner(JdbcUtils.getPools());
		String sql = "insert into user(id,username,password) values(?,?,?) ";
		Object params[] = {user.getId(),user.getUsername(),user.getPassword()};
		qr.update(sql, params);
	}
	
	
	//�õ�ָ��ID��User
	/* (non-Javadoc)
	 * @see dao.impl.UserDao#find(java.lang.String)
	 */
	public User find(String id) throws SQLException{
		QueryRunner qr = new QueryRunner(JdbcUtils.getPools());
		String sql = "select * from user where id=?";
		User user = qr.query(sql, id, new BeanHandler(User.class));
		
		return user;
	}
	
	
	

	
	
	
	//�õ�ָ��username��password��User
	/* (non-Javadoc)
	 * @see dao.impl.UserDao#find(java.lang.String, java.lang.String)
	 */
	public User find(String username,String password) throws SQLException{

		QueryRunner qr = new QueryRunner(JdbcUtils.getPools());
		String sql = "select * from user where username=? and password=?";
		Object[] params = {username,password};
		User user = qr.query(sql, params, new BeanHandler(User.class));
		return user;
	
	}
	
	//�õ����е�User
	/* (non-Javadoc)
	 * @see dao.impl.UserDao#getAll()
	 */
	public List<User> getAll() throws SQLException{
		QueryRunner qr = new QueryRunner(JdbcUtils.getPools());
		String sql = "select * from user";
		List<User> list = qr.query(sql, new BeanListHandler(User.class));
		return list;
	}
	
	
	
	//Ϊĳ����ɫ���������ɫ
	/* (non-Javadoc)
	 * @see dao.impl.UserDao#updateUserRole(domain.User, java.util.List)
	 */
	public void updateUserRole(User user,List<Role> roles) throws SQLException{
		QueryRunner qr = new QueryRunner(JdbcUtils.getPools());

		for(Role role:roles){
			String sql = "insert into user_role(id,user_id,role_id) values(?,?,?)";
			Object[] params = {WebUtils.getId(),user.getId(),role.getId()};
			qr.update(sql, params);
		}
	}
	
	
	//�õ���ǰuser��ӵ�еĽ�ɫ
	/* (non-Javadoc)
	 * @see dao.impl.UserDao#getUserRoles(java.lang.String)
	 */
	public List<Role> getUserRoles(String user_id) throws SQLException{
		
		QueryRunner qr = new QueryRunner(JdbcUtils.getPools());
		String sql = "select role.* from role,user_role where user_role.user_id=? and role.id=user_role.role_id";
		List<Role> list = qr.query(sql, user_id, new BeanListHandler(Role.class));
		
		return list;
	}
	
	
	
	//ɾ��ĳ���û���ӵ�е�ĳ����ɫ
	public void DeleteUserRole(String user_id, String role_id)
			throws SQLException {
		QueryRunner qr = new QueryRunner(JdbcUtils.getPools());
		String sql = "delete from user_role where user_id=? and role_id=?";
		Object[] params = {user_id,role_id};
		qr.update(sql,params);
	}
	
}
