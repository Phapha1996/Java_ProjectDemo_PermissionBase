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
	
	//����Ȩ��
	public void addPrivilege(Privilege privilege) throws SQLException{
		
		privilegeDao.add(privilege);
		
	}
	
	//ͨ��id�ҵ�Ȩ��
	public Privilege findPrivilege(String id) throws SQLException{
		
		Privilege p = privilegeDao.find(id);
		return p;
		
	}
	
	//��������Ȩ��
	public List getAllPrivilege() throws SQLException{
		return privilegeDao.getAll();
	}
	
	
	//����һ����ɫ
	public void addRole(Role role) throws SQLException{
		roleDao.add(role);
	}
	
	
	//����һ����ɫͨ��id
	public Role findRoleForID(String id) throws SQLException{
		return roleDao.find(id);
	}
	
	
	//�õ����еĽ�ɫ
	public List<Role> getallRole() throws SQLException{
		return roleDao.getAllRole();
	}
	 
	/*�õ�ĳ����ɫ��ӵ��Ȩ��,δ���
	public Role getRolePrivilege(String Role_id) throws SQLException{
		Role role = roleDao.find(Role_id);
		List<Privilege> privileges = roleDao.getRolePrivilege(Role_id);
		role.setPrivileges((Set)privileges);
		return role;
	}
	  **/
	
	
	//ͨ��id�õ�roleӵ�е�Privilege
	public List<Privilege> getRolePrivilege(String role_id) throws SQLException{
		return roleDao.getRolePrivilege(role_id);
	}

	
	
	//Ϊĳ����ɫ��Ӹ���Ȩ��
	public void updateRolePrivilege(String role_id, String[] privilege_ids) throws SQLException {
		Role role = roleDao.find(role_id);
		List list = new LinkedList();
		for(String id:privilege_ids){
			Privilege p = privilegeDao.find(id);
			list.add(p);
		}
		roleDao.updateRolePrivilege(role, list);
		
	}
	
	
	//ɾ��ĳ����ɫ��Privilege
	public void removeRolePrivilege(String privilege_id, String role_id) throws SQLException{
		roleDao.DeleteRolePri(privilege_id,role_id);
	}

		//�����û�
	public void addUser(User user) throws SQLException{
		String password = ServiceUtils.getMd5Password(user.getPassword());
		user.setPassword(password);
		userDao.add(user);
	}
	
	
	//ͨ��id�ҵ��û�
	public User findUserForID(String id) throws SQLException{
		return userDao.find(id);
	}
	
	
	//ͨ���˺������������û���ע�⣬���returnΪnull��˵���û����������벻����
	public User findUserForPassword(String username,String password) throws SQLException{
		password = ServiceUtils.getMd5Password(password);
		return userDao.find(username, password);
	}
	
	//�õ����е��û�
	public List<User> getAllUser() throws SQLException{
		return userDao.getAll();
	}
	
	
	//�õ��û������н�ɫ
	public List<Role> getUserRole(String user_id) throws SQLException{
		return userDao.getUserRoles(user_id);
	}
	
	
	//���µ�ǰ�û�ӵ�еĽ�ɫ
	public void updateUserRoles(String user_id, String[] role_ids) throws SQLException{
		User user = userDao.find(user_id);
		List<Role> list = new LinkedList<Role>();
		for(String role_id:role_ids){
			Role role = roleDao.find(role_id);
			list.add(role);
		}
		
		userDao.updateUserRole(user, list);
	}
	
	//ɾ���û�ӵ�е�ĳ����ɫ
	public void deleteUserRole(String user_id,String role_id) throws SQLException{
		userDao.DeleteUserRole(user_id, role_id);
	}

	
	public void addResource(Resource resource) throws SQLException{
		resourceDao.add(resource);
	}
	
	
	//ͨ��id������Դ
	public Resource findResourceForID(String id) throws SQLException{
		return resourceDao.find(id);
	}

	//ͨ��uri����
	public Resource findSourceForURI(String uri) throws SQLException{
		return resourceDao.findForUri(uri);
	}
	
	
	//�õ����е���Դ
	public List<Resource> getAllResource() throws SQLException{
		return resourceDao.getAll();
	}
	
	
	//�õ���ǰ��Դ����Ҫ��Ȩ��
	public List<Privilege> getResourceNeedPri(String resource_id) throws SQLException{
		return resourceDao.getResourceNeedPri(resource_id);
	}
	
	
	//���ӵ�ǰ��Դ����Ҫ��Ȩ��
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

	
	//�õ���ǰ�û���ӵ�е�����Ȩ��
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
