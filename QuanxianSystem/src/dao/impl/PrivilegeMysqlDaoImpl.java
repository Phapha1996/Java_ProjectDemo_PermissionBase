package dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import utils.JdbcUtils;
import dao.itf.PrivilegeDao;
import domain.Privilege;

public class PrivilegeMysqlDaoImpl implements PrivilegeDao {

	/* (non-Javadoc)
	 * @see dao.impl.PrivilegeDao#add(domain.Privilege)
	 */
	public void add(Privilege p) throws SQLException{
		
		QueryRunner qr = new QueryRunner(JdbcUtils.getPools());
		String sql = "insert into privilege(id,name,discription) values(?,?,?)";
		Object[] params = {p.getId(),p.getName(),p.getDiscription()};
		qr.update(sql, params);
		
	}
	
	
	/* (non-Javadoc)
	 * @see dao.impl.PrivilegeDao#find(java.lang.String)
	 */
	public Privilege find(String id) throws SQLException{
		QueryRunner qr = new QueryRunner(JdbcUtils.getPools());
		String sql = "select * from Privilege where id=?";
		Object param = id;
		Privilege p = qr.query(sql, param, new BeanHandler(Privilege.class));
		return p;
	}
	
	
	/* (non-Javadoc)
	 * @see dao.impl.PrivilegeDao#getAll()
	 */
	 	
	public List<Privilege> getAll() throws SQLException{
		
		QueryRunner qr = new QueryRunner(JdbcUtils.getPools());
		String sql = "select * from Privilege";
		List<Privilege> list = qr.query(sql,new BeanListHandler(Privilege.class));
		return list;
		
	}		
}
