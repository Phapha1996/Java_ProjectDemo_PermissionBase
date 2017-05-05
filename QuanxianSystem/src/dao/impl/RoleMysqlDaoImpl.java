package dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import utils.JdbcUtils;
import utils.WebUtils;
import dao.itf.RoleDao;
import domain.Privilege;
import domain.Role;

public class RoleMysqlDaoImpl implements RoleDao {

	
	//����һ����ɫ
	public void add(Role role) throws SQLException {
		QueryRunner qr = new QueryRunner(JdbcUtils.getPools());
		String sql = "insert into role(id,name,discription) values(?,?,?)";
		Object[] params = {role.getId(),role.getName(),role.getDiscription()};
		qr.update(sql, params);
		
	}

	//����һ����ɫ
	public Role find(String id) throws SQLException {
		QueryRunner qr = new QueryRunner(JdbcUtils.getPools());
		String sql = "select * from role where id=?";
		return qr.query(sql, id, new BeanHandler(Role.class));
	}

	//�õ������ɫ��ӵ�е�Ȩ��
	public List<Privilege> getRolePrivilege(String role_id) throws SQLException{
		QueryRunner qr = new QueryRunner(JdbcUtils.getPools());
		String sql = "select privilege.* from privilege,role_privilege where role_privilege.role_id=? and role_privilege.privilege_id=privilege.id";
		return qr.query(sql, role_id, new BeanListHandler(Privilege.class));
	}
	
	//�õ����еĽ�ɫ
	public List<Role> getAllRole() throws SQLException {
		QueryRunner qr = new QueryRunner(JdbcUtils.getPools());
		String sql = "select * from role";
		return (List<Role>)qr.query(sql, new BeanListHandler(Role.class));
	}

	
	//Ϊĳ����ɫ��Ȩ�����м��
	public void updateRolePrivilege(Role role,List<Privilege> privilege) throws SQLException {
		QueryRunner qr = new QueryRunner(JdbcUtils.getPools());
		for(Privilege p:privilege){
			String sql = "insert into role_privilege(id,role_id,privilege_id) values(?,?,?)";
			Object[] params = {WebUtils.getId(),role.getId(),p.getId()};
			qr.update(sql, params);
		}
	}

	public void DeleteRolePri(String privilege_id, String role_id)
			throws SQLException {
		QueryRunner qr = new QueryRunner(JdbcUtils.getPools());
		String sql = "delete from role_privilege where role_id=? and privilege_id=?";
		Object[] params = {role_id,privilege_id};
		qr.update(sql,params);
	}
	
}
