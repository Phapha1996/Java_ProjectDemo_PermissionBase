package dao.itf;

import java.sql.SQLException;
import java.util.List;

import domain.Privilege;
import domain.Role;

public interface RoleDao {

	public void add(Role role)throws SQLException;
	
	public Role find(String id)throws SQLException;
	
	public List<Role> getAllRole()throws SQLException;
	
	public List<Privilege> getRolePrivilege(String role_id) throws SQLException;
	
	public void updateRolePrivilege(Role role,List<Privilege> privilege) throws SQLException;

	public void DeleteRolePri(String privilege_id, String role_id)throws SQLException;
}
