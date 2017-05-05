package dao.itf;

import java.sql.SQLException;
import java.util.List;

import domain.Privilege;

public interface PrivilegeDao {

	public void add(Privilege p) throws SQLException;

	public Privilege find(String id) throws SQLException;

	public List<Privilege> getAll() throws SQLException;

}