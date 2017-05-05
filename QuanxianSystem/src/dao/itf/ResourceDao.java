package dao.itf;

import java.sql.SQLException;
import java.util.List;

import domain.Privilege;
import domain.Resource;

public interface ResourceDao {

	//����һ����Դ
	public void add(Resource re) throws SQLException;

	public void delete(String id) throws SQLException;

	//����ĳ����Դͨ��id
	public Resource find(String id) throws SQLException;

	//�õ���ǰ��Դ����Ҫ��Ȩ��
	public List<Privilege> getResourceNeedPri(String id) throws SQLException;

	//�õ���ǰ���е���Դ
	public List<Resource> getAll() throws SQLException;

	//���µ�ǰ��Դ����Ҫ��Ȩ��
	public void updateResourceNeedPri(Resource re, List<Privilege> pri)
			throws SQLException;

	//�޳���ǰ��Դ����Ҫ��ĳ��Ȩ��
	public void DeleteResourceNeed(String resource_id, String privilege_id)
			throws SQLException;
	
	public Resource findForUri(String uri) throws SQLException;

}