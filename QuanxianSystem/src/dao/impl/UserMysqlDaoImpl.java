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

	//增加一个User
	/* (non-Javadoc)
	 * @see dao.impl.UserDao#add(domain.User)
	 */
	public void add(User user) throws SQLException{
		QueryRunner qr = new QueryRunner(JdbcUtils.getPools());
		String sql = "insert into user(id,username,password) values(?,?,?) ";
		Object params[] = {user.getId(),user.getUsername(),user.getPassword()};
		qr.update(sql, params);
	}
	
	
	//得到指定ID的User
	/* (non-Javadoc)
	 * @see dao.impl.UserDao#find(java.lang.String)
	 */
	public User find(String id) throws SQLException{
		QueryRunner qr = new QueryRunner(JdbcUtils.getPools());
		String sql = "select * from user where id=?";
		User user = qr.query(sql, id, new BeanHandler(User.class));
		
		return user;
	}
	
	
	

	
	
	
	//得到指定username，password的User
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
	
	//得到所有的User
	/* (non-Javadoc)
	 * @see dao.impl.UserDao#getAll()
	 */
	public List<User> getAll() throws SQLException{
		QueryRunner qr = new QueryRunner(JdbcUtils.getPools());
		String sql = "select * from user";
		List<User> list = qr.query(sql, new BeanListHandler(User.class));
		return list;
	}
	
	
	
	//为某个角色从新授予角色
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
	
	
	//得到当前user所拥有的角色
	/* (non-Javadoc)
	 * @see dao.impl.UserDao#getUserRoles(java.lang.String)
	 */
	public List<Role> getUserRoles(String user_id) throws SQLException{
		
		QueryRunner qr = new QueryRunner(JdbcUtils.getPools());
		String sql = "select role.* from role,user_role where user_role.user_id=? and role.id=user_role.role_id";
		List<Role> list = qr.query(sql, user_id, new BeanListHandler(Role.class));
		
		return list;
	}
	
	
	
	//删除某个用户所拥有的某个角色
	public void DeleteUserRole(String user_id, String role_id)
			throws SQLException {
		QueryRunner qr = new QueryRunner(JdbcUtils.getPools());
		String sql = "delete from user_role where user_id=? and role_id=?";
		Object[] params = {user_id,role_id};
		qr.update(sql,params);
	}
	
}
