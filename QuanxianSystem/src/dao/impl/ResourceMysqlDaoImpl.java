package dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import utils.JdbcUtils;
import dao.itf.ResourceDao;
import domain.Privilege;
import domain.Resource;

public class ResourceMysqlDaoImpl implements ResourceDao {

	//增加一个资源
	/* (non-Javadoc)
	 * @see dao.impl.ResourceDao#add(domain.Resource)
	 */
	public void add(Resource re) throws SQLException{
		QueryRunner qr = new QueryRunner(JdbcUtils.getPools());
		String sql = "insert into resource(id,uri) values(?,?)";
		Object[] params = {re.getId(),re.getUri()};
		qr.update(sql, params);
	}
	
	
	/* (non-Javadoc)
	 * @see dao.impl.ResourceDao#delete(java.lang.String)
	 */
	public void delete(String id) throws SQLException{
		QueryRunner qr = new QueryRunner(JdbcUtils.getPools());
		String sql = "delete from resource where id=?";
		Object param = id;
		qr.update(sql, param);
	}
	
	//查找某个资源通过id
	/* (non-Javadoc)
	 * @see dao.impl.ResourceDao#find(java.lang.String)
	 */
	public Resource find(String id) throws SQLException{
		QueryRunner qr = new QueryRunner(JdbcUtils.getPools());
		String sql = "select * from resource where id=?";
		Object param = id;
		return qr.query(sql, param, new BeanHandler(Resource.class));
	}
	
	
	public Resource findForUri(String uri) throws SQLException{
		QueryRunner qr = new QueryRunner(JdbcUtils.getPools());
		String sql = "select * from resource where uri=?";
		Object param = uri;
		return qr.query(sql, param, new BeanHandler(Resource.class));
	}
	
	
	//得到当前资源所需要的权限
	/* (non-Javadoc)
	 * @see dao.impl.ResourceDao#getResourceNeedPri(java.lang.String)
	 */
	public List<Privilege> getResourceNeedPri(String id) throws SQLException{
		QueryRunner qr = new QueryRunner(JdbcUtils.getPools());
		String sql = "select privilege.* from resource_privilege,privilege where resource_privilege.resource_id=? and resource_privilege.privilege_id=privilege.id";
		Object param = id;
		return qr.query(sql, new BeanListHandler(Privilege.class), param);
	}
	
	
	//得到当前所有的资源
	/* (non-Javadoc)
	 * @see dao.impl.ResourceDao#getAll()
	 */
	public List<Resource> getAll() throws SQLException{
		QueryRunner qr = new QueryRunner(JdbcUtils.getPools());
		String sql = "select * from resource";
		return qr.query(sql, new BeanListHandler(Resource.class));
	}
	
	
	//更新当前资源所需要的权限
	/* (non-Javadoc)
	 * @see dao.impl.ResourceDao#updateResourceNeedPri(domain.Resource, java.util.List)
	 */
	public void updateResourceNeedPri(Resource re,List<Privilege> pri) throws SQLException{
		QueryRunner qr = new QueryRunner(JdbcUtils.getPools());
		for(Privilege p:pri){
			String sql = "insert into resource_privilege(resource_id,privilege_id) values(?,?)";
			Object[] params = {re.getId(),p.getId()};
			qr.update(sql, params);
		}
	}
	
	
	
	//剔除当前资源所需要的某个权限
	/* (non-Javadoc)
	 * @see dao.impl.ResourceDao#DeleteResourceNeed(java.lang.String, java.lang.String)
	 */
	public void DeleteResourceNeed(String resource_id, String privilege_id)
			throws SQLException {
		QueryRunner qr = new QueryRunner(JdbcUtils.getPools());
		String sql = "delete from resource_privilege where resource_id=? and privilege_id=?";
		Object[] params = {resource_id,privilege_id};
		qr.update(sql,params);
	}
	
	
	
}
