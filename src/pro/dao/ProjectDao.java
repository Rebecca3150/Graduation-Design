package pro.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pro.action.Log;
import pro.action.Project;
import pro.util.DBUtil;

public class ProjectDao {

	/**
	 * 检查是否存在此产品
	 * 
	 * @param name
	 * @return
	 */
	public boolean checkReg(String name) {
		System.out.println("跳转至ProjectDao_checkReg！");

		boolean flag = true;
		Connection connection = DBUtil.getConn();
		Statement statement = null;
		ResultSet rs = null;
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select proname from project");
			while (rs.next()) {
				if (name.equals(rs.getString("proname"))) {
					flag = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, statement, connection);
		}
		return flag;
	}

	/**
	 * 添加项目 字段不完整
	 */
	public void save(Project project) {
		// 获取数据库连接
		System.out.println("跳转至ProjectDao_save！");
		String sql = "insert into project(proid,proname,info,begindate,enddate,userid1,day,userid2,create_date) values('"
				+ project.getProid() + "','" + project.getProname() + "','" + project.getInfo() + "','"
				+ project.getBegindate() + "','" + project.getEnddate() + "','" + project.getUserid1() + "','"
				+ project.getDay() + "','" + project.getUserid2() + "','" + project.getCreate_date() + "')";
		Connection conn = DBUtil.getConn();
		// 创建数据库操作对象
		Statement state = null;
		try {
			state = conn.createStatement();
			state.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(state, conn);
		}

	}

	/**
	 * 所有项目列表
	 * 
	 * @return
	 */
	public List<Project> getProjectList() {
		String sql = "select * from project";
		Connection connection = DBUtil.getConn();
		Statement statement = null;
		ResultSet rs = null;
		List<Project> projects = new ArrayList<Project>();
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			Project project;
			while (rs.next()) {
				project = new Project();
				project.setProid(rs.getString("proid"));
				project.setProname(rs.getString("proname"));
				project.setInfo(rs.getString("info"));
				project.setBegindate(rs.getString("begindate"));
				project.setEnddate(rs.getString("enddate"));
				project.setUserid1(rs.getString("userid1"));
				project.setDay(rs.getInt("day"));
				project.setUserid2(rs.getString("userid2"));
				project.setState(rs.getInt("state"));
				project.setCreate_date(rs.getString("create_date"));
				project.setAnnex(rs.getString("annex"));
				projects.add(project);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, statement, connection);
		}
		return projects;
	}

	
	/**
	 * 指定状态的项目列表
	 */
	public List<Project> getProjectListOther(int state) {
		String sql = "select * from project where state="+state;
		Connection connection = DBUtil.getConn();
		Statement statement = null;
		ResultSet rs = null;
		List<Project> projects = new ArrayList<Project>();
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			Project project;
			while (rs.next()) {
				project = new Project();
				project.setProid(rs.getString("proid"));
				project.setProname(rs.getString("proname"));
				project.setInfo(rs.getString("info"));
				project.setBegindate(rs.getString("begindate"));
				project.setEnddate(rs.getString("enddate"));
				project.setUserid1(rs.getString("userid1"));
				project.setDay(rs.getInt("day"));
				project.setUserid2(rs.getString("userid2"));
				project.setState(rs.getInt("state"));
				project.setCreate_date(rs.getString("create_date"));
				project.setAnnex(rs.getString("annex"));
				projects.add(project);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, statement, connection);
		}
		return projects;
	}

	
	/**
	 * all 获取每一个分页的数据
	 */
	public List<Project> getAllListByPage(int start, int size) {
		String sql = "select top " + size + " * from project where(proid not in(select top " + start
				+ " proid from project))";
		// System.out.println("获取分页数据sql语句： "+sql);
		Connection connection = DBUtil.getConn();
		Statement statement = null;
		ResultSet rs = null;
		List<Project> projects = new ArrayList<Project>();
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			Project project;
			while (rs.next()) {
				project = new Project();
				project.setProid(rs.getString("proid"));
				project.setProname(rs.getString("proname"));
				project.setInfo(rs.getString("info"));
				project.setBegindate(rs.getString("begindate"));
				project.setEnddate(rs.getString("enddate"));
				project.setUserid1(rs.getString("userid1"));
				project.setDay(rs.getInt("day"));
				project.setUserid2(rs.getString("userid2"));
				project.setState(rs.getInt("state"));
				project.setCreate_date(rs.getString("create_date"));
				project.setAnnex(rs.getString("annex"));
				projects.add(project);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, statement, connection);
		}
		return projects;
	}

	/**
	 * 特定状态的产品列表
	 * 
	 * @return
	 */

	public List<Project> getProjectListByState(int state) {
		String sql = "select * from project where state=" + state;
		Connection connection = DBUtil.getConn();
		Statement statement = null;
		ResultSet rs = null;
		List<Project> projects = new ArrayList<Project>();
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			Project project;
			while (rs.next()) {
				project = new Project();
				project.setProid(rs.getString("proid"));
				project.setProname(rs.getString("proname"));
				project.setInfo(rs.getString("info"));
				project.setBegindate(rs.getString("begindate"));
				project.setEnddate(rs.getString("enddate"));
				project.setUserid1(rs.getString("userid1"));
				project.setDay(rs.getInt("day"));
				project.setUserid2(rs.getString("userid2"));
				project.setState(rs.getInt("state"));
				project.setCreate_date(rs.getString("create_date"));
				project.setAnnex(rs.getString("annex"));
				projects.add(project);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, statement, connection);
		}
		return projects;
	}

	/**
	 * 特定状态 获取每一个分页的数据
	 */
	public List<Project> getOtherListByPage(int start, int size, int state) {
		String sql = "select top " + size + " * from project where(proid not in(select top " + start
				+ " proid from project)) and state=" + state;
		//System.out.println("sql语句：" + sql);
		Connection connection = DBUtil.getConn();
		Statement statement = null;
		ResultSet rs = null;
		List<Project> projects = new ArrayList<Project>();
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			Project project;
			while (rs.next()) {
				project = new Project();
				project.setProid(rs.getString("proid"));
				project.setProname(rs.getString("proname"));
				project.setInfo(rs.getString("info"));
				project.setBegindate(rs.getString("begindate"));
				project.setEnddate(rs.getString("enddate"));
				project.setUserid1(rs.getString("userid1"));
				project.setDay(rs.getInt("day"));
				project.setUserid2(rs.getString("userid2"));
				project.setState(rs.getInt("state"));
				project.setCreate_date(rs.getString("create_date"));
				project.setAnnex(rs.getString("annex"));
				projects.add(project);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, statement, connection);
		}
		return projects;
	}

	/**
	 * update
	 * 
	 * @param ductid
	 */
	public void update(String proid) {
		String sql = "update project set state=0 where proid='" + proid + "'";
		Connection connection = DBUtil.getConn();
		Statement statement = null;
		int a = 0;
		try {
			statement = connection.createStatement();
			a = statement.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(statement, connection);
		}
	}

	public Project getProjectByProid(String proid) {
		Connection conn = DBUtil.getConn();
		Project project = null;
		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery("select * from project where proid='" + proid + "'");
			if (rs.next()) {
				project = new Project();
				project.setProid(rs.getString("proid"));
				project.setProname(rs.getString("proname"));
				project.setInfo(rs.getString("info"));
				project.setBegindate(rs.getString("begindate"));
				project.setEnddate(rs.getString("enddate"));
				project.setUserid1(rs.getString("userid1"));
				project.setDay(rs.getInt("day"));
				project.setUserid2(rs.getString("userid2"));
				project.setState(rs.getInt("state"));
				project.setCreate_date(rs.getString("create_date"));
				project.setAnnex(rs.getString("annex"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return project;
	}

	/**
	 * 修改产品详情
	 * 
	 * @param ductid
	 */
	public int updateTo(Project project) {
		int flag = 1;
		String sql = "update project set info='" + project.getInfo() + "' where proid='" + project.getProid() + "'";
		Connection connection = DBUtil.getConn();
		Statement statement = null;
		int a = 0;
		try {
			statement = connection.createStatement();
			a = statement.executeUpdate(sql);
			flag = 0;
		} catch (Exception e) {
			flag = 1;
			e.printStackTrace();
		} finally {
			DBUtil.close(statement, connection);
		}
		return flag;
	}

	/**
	 * 获取数据表总量
	 * 
	 * @return
	 */
	public int getCount() {
		ResultSet rs = null;
		Statement state = null;
		Connection conn = null;
		int size = 0;
		try {
			conn = DBUtil.getConn();
			state = conn.createStatement();
			rs = state.executeQuery("select count(*) count from project");

			if (rs.next()) {
				size = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
		}
		return size;
	}

	public int getCount(int state) {
		ResultSet rs = null;
		Statement st = null;
		Connection conn = null;
		int size = 0;
		try {
			conn = DBUtil.getConn();
			st = conn.createStatement();
			rs = st.executeQuery("select count(*) count from project where state=" + state);

			if (rs.next()) {
				size = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, st, conn);
		}
		return size;
	}

}
