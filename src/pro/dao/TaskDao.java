package pro.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pro.action.Plan;
import pro.action.Task;
import pro.util.DBUtil;

public class TaskDao {

	public List<Task> getListFresh(String planid) {
		String sql = "select * from task where planid='" + planid + "' and state=0 and extaskid='0'";
		// System.out.println(sql);
		Connection connection = DBUtil.getConn();
		Statement statement = null;
		ResultSet rs = null;
		List<Task> tasks = new ArrayList<Task>();
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			Task task;
			while (rs.next()) {
				task = new Task();
				task.setTaskid(rs.getString("taskid"));
				task.setPlanid(rs.getString("planid"));
				task.setExtaskid(rs.getString("extaskid"));
				task.setTaskname(rs.getString("taskname"));
				task.setState(rs.getInt("state"));
				task.setUserid(rs.getString("userid"));
				task.setTeamis(rs.getInt("teamis"));
				task.setBegindate(rs.getString("begindate"));
				task.setEnddate(rs.getString("enddate"));
				task.setDay(rs.getInt("day"));
				task.setTime(rs.getInt("time"));
				task.setTaskid2(rs.getString("taskid2"));
				task.setLevel(rs.getInt("level"));
				task.setInfo(rs.getString("info"));
				task.setCreate_date(rs.getString("create_date"));
				tasks.add(task);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, statement, connection);
		}
		return tasks;
	}

	
	public List<Task> getListFreshByEx(String taskid) {
		String sql = "select * from task where extaskid='" +taskid + "' and state=0";
		// System.out.println(sql);
		Connection connection = DBUtil.getConn();
		Statement statement = null;
		ResultSet rs = null;
		List<Task> tasks = new ArrayList<Task>();
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			Task task;
			while (rs.next()) {
				task = new Task();
				task.setTaskid(rs.getString("taskid"));
				task.setPlanid(rs.getString("planid"));
				task.setExtaskid(rs.getString("extaskid"));
				task.setTaskname(rs.getString("taskname"));
				task.setState(rs.getInt("state"));
				task.setUserid(rs.getString("userid"));
				task.setTeamis(rs.getInt("teamis"));
				task.setBegindate(rs.getString("begindate"));
				task.setEnddate(rs.getString("enddate"));
				task.setDay(rs.getInt("day"));
				task.setTime(rs.getInt("time"));
				task.setTaskid2(rs.getString("taskid2"));
				task.setLevel(rs.getInt("level"));
				task.setInfo(rs.getString("info"));
				task.setCreate_date(rs.getString("create_date"));
				tasks.add(task);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, statement, connection);
		}
		return tasks;
	}

	
	
	public int check(String planid, String taskname) {
		int flag = 0;
		// 查询用户是否已存在
		Connection connection = DBUtil.getConn();
		Statement statement = null;
		ResultSet rs = null;
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select taskname from task where planid='" + planid + "'");
			while (rs.next()) {
				if (taskname.equals(rs.getString("taskname"))) {
					// admin数据表中已存在此账号则说明不允许注册，将标记值记为false
					flag = 1;
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
	 * 保存部分数据
	 * 
	 * @param task
	 */
	public void save(Task task) {
		// 获取数据库连接
		System.out.println("跳转至TaskDao_save！");
		String sql = "insert into task(taskid,planid,extaskid,taskname,userid,teamis,begindate,enddate,taskid2,level,info,create_date) values('"
				+ task.getTaskid() + "','" + task.getPlanid() + "','" + task.getExtaskid() + "','" + task.getTaskname()
				+ "','" + task.getUserid() + "'," + task.getTeamis() + ",'" + task.getBegindate() + "','"
				+ task.getEnddate() + "','" + task.getTaskid2() + "'," + task.getLevel() + ",'" + task.getInfo() + "','"
				+ task.getCreate_date() + "')";
	//	System.out.println("Task-add中的sql语句：" + sql);
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

}
