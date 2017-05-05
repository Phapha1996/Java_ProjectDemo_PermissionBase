package dao.itf;

import java.sql.SQLException;
import java.util.List;

import domain.Role;
import domain.User;

public interface UserDao {

	//增加一个User
	public void add(User user) throws SQLException;

	//得到指定ID的User
	public User find(String id) throws SQLException;

	//得到指定username，password的User
	public User find(String username, String password) throws SQLException;

	//得到所有的User
	public List<User> getAll() throws SQLException;

	//为某个角色从新授予角色
	public void updateUserRole(User user, List<Role> roles) throws SQLException;

	//得到当前user所拥有的角色
	public List<Role> getUserRoles(String user_id) throws SQLException;

	//删除某个用户所拥有的某个角色
	public void DeleteUserRole(String user_id, String role_id)
			throws SQLException;
}