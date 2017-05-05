package dao.itf;

import java.sql.SQLException;
import java.util.List;

import domain.Privilege;
import domain.Resource;

public interface ResourceDao {

	//增加一个资源
	public void add(Resource re) throws SQLException;

	public void delete(String id) throws SQLException;

	//查找某个资源通过id
	public Resource find(String id) throws SQLException;

	//得到当前资源所需要的权限
	public List<Privilege> getResourceNeedPri(String id) throws SQLException;

	//得到当前所有的资源
	public List<Resource> getAll() throws SQLException;

	//更新当前资源所需要的权限
	public void updateResourceNeedPri(Resource re, List<Privilege> pri)
			throws SQLException;

	//剔除当前资源所需要的某个权限
	public void DeleteResourceNeed(String resource_id, String privilege_id)
			throws SQLException;
	
	public Resource findForUri(String uri) throws SQLException;

}