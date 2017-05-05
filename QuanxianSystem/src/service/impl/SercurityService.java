package service.impl;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import dao.itf.PrivilegeDao;
import dao.itf.ResourceDao;
import dao.itf.RoleDao;
import dao.itf.UserDao;
import domain.Privilege;
import domain.Resource;
import domain.Role;
import domain.User;
import utils.Daofactory;
import utils.ServiceUtils;
import utils.WebUtils;

public class SercurityService {

	PrivilegeDao privilegeDao = Daofactory.getPrivilegeDao();
	RoleDao roleDao = Daofactory.getRoleDao();
	UserDao userDao = Daofactory.getUserDao();
	ResourceDao resourceDao = Daofactory.getResourceDao();
	
	//增加权限
	public void addPrivilege(Privilege privilege) throws SQLException{
		
		privilegeDao.add(privilege);
		
	}
	
	//通过id找到权限
	public Privilege findPrivilege(String id) throws SQLException{
		
		Privilege p = privilegeDao.find(id);
		return p;
		
	}
	
	//查找所有权限
	public List getAllPrivilege() throws SQLException{
		return privilegeDao.getAll();
	}
	
	
	//增加一名角色
	public void addRole(Role role) throws SQLException{
		roleDao.add(role);
	}
	
	
	//查找一名角色通过id
	public Role findRoleForID(String id) throws SQLException{
		return roleDao.find(id);
	}
	
	
	//得到所有的角色
	public List<Role> getallRole() throws SQLException{
		return roleDao.getAllRole();
	}
	 
	/*得到某个角色所拥有权限,未完成
	public Role getRolePrivilege(String Role_id) throws SQLException{
		Role role = roleDao.find(Role_id);
		List<Privilege> privileges = roleDao.getRolePrivilege(Role_id);
		role.setPrivileges((Set)privileges);
		return role;
	}
	  **/
	
	
	//通过id得到role拥有的Privilege
	public List<Privilege> getRolePrivilege(String role_id) throws SQLException{
		return roleDao.getRolePrivilege(role_id);
	}

	
	
	//为某个角色添加更新权限
	public void updateRolePrivilege(String role_id, String[] privilege_ids) throws SQLException {
		Role role = roleDao.find(role_id);
		List list = new LinkedList();
		for(String id:privilege_ids){
			Privilege p = privilegeDao.find(id);
			list.add(p);
		}
		roleDao.updateRolePrivilege(role, list);
		
	}
	
	
	//删除某个角色的Privilege
	public void removeRolePrivilege(String privilege_id, String role_id) throws SQLException{
		roleDao.DeleteRolePri(privilege_id,role_id);
	}

		//增加用户
	public void addUser(User user) throws SQLException{
		String password = ServiceUtils.getMd5Password(user.getPassword());
		user.setPassword(password);
		userDao.add(user);
	}
	
	
	//通过id找到用户
	public User findUserForID(String id) throws SQLException{
		return userDao.find(id);
	}
	
	
	//通过账号密码给其查找用户，注意，如果return为null，说明用户名或者密码不存在
	public User findUserForPassword(String username,String password) throws SQLException{
		password = ServiceUtils.getMd5Password(password);
		return userDao.find(username, password);
	}
	
	//得到所有的用户
	public List<User> getAllUser() throws SQLException{
		return userDao.getAll();
	}
	
	
	//得到用户的所有角色
	public List<Role> getUserRole(String user_id) throws SQLException{
		return userDao.getUserRoles(user_id);
	}
	
	
	//更新当前用户拥有的角色
	public void updateUserRoles(String user_id, String[] role_ids) throws SQLException{
		User user = userDao.find(user_id);
		List<Role> list = new LinkedList<Role>();
		for(String role_id:role_ids){
			Role role = roleDao.find(role_id);
			list.add(role);
		}
		
		userDao.updateUserRole(user, list);
	}
	
	//删除用户拥有的某个角色
	public void deleteUserRole(String user_id,String role_id) throws SQLException{
		userDao.DeleteUserRole(user_id, role_id);
	}

	
	public void addResource(Resource resource) throws SQLException{
		resourceDao.add(resource);
	}
	
	
	//通过id查找资源
	public Resource findResourceForID(String id) throws SQLException{
		return resourceDao.find(id);
	}

	//通过uri查找
	public Resource findSourceForURI(String uri) throws SQLException{
		return resourceDao.findForUri(uri);
	}
	
	
	//得到所有的资源
	public List<Resource> getAllResource() throws SQLException{
		return resourceDao.getAll();
	}
	
	
	//得到当前资源所需要的权限
	public List<Privilege> getResourceNeedPri(String resource_id) throws SQLException{
		return resourceDao.getResourceNeedPri(resource_id);
	}
	
	
	//增加当前资源所需要的权限
	public void addResourceNeedPri(String resource_id,String[] privilege_ids) throws SQLException{
		Resource re = resourceDao.find(resource_id);
		List<Privilege> list = new LinkedList<Privilege>();
		for(String pri_id:privilege_ids){
			list.add(privilegeDao.find(pri_id));
		}
		
		resourceDao.updateResourceNeedPri(re, list);
	}
	
	
	public void deleteResourceNeedPri(String resource_id,String privilege_id) throws SQLException{
		resourceDao.DeleteResourceNeed(resource_id, privilege_id);
	}

	
	//得到当前用户所拥有的所有权限
	public List<Privilege> getUserPrivileges(User user) throws SQLException {
		
		List<Role> roles = userDao.getUserRoles(user.getId());
		List<Privilege> list = new LinkedList<Privilege>();
		for(Role role:roles){
			List<Privilege> role_pri_list = roleDao.getRolePrivilege(role.getId());
			list.addAll(role_pri_list);
		}
		
		return list;
	}
	
	
}
