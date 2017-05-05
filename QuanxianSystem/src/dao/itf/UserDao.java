package dao.itf;

import java.sql.SQLException;
import java.util.List;

import domain.Role;
import domain.User;

public interface UserDao {

	//����һ��User
	public void add(User user) throws SQLException;

	//�õ�ָ��ID��User
	public User find(String id) throws SQLException;

	//�õ�ָ��username��password��User
	public User find(String username, String password) throws SQLException;

	//�õ����е�User
	public List<User> getAll() throws SQLException;

	//Ϊĳ����ɫ���������ɫ
	public void updateUserRole(User user, List<Role> roles) throws SQLException;

	//�õ���ǰuser��ӵ�еĽ�ɫ
	public List<Role> getUserRoles(String user_id) throws SQLException;

	//ɾ��ĳ���û���ӵ�е�ĳ����ɫ
	public void DeleteUserRole(String user_id, String role_id)
			throws SQLException;
}