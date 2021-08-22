package pro.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pro.action.Plan;
import pro.action.Project;
import pro.action.User;
import pro.util.DBUtil;

public class PlanDao {

	public void save(Plan plan) {
		// 获取数据库连接
		System.out.println("跳转至PlanDao_save！");
		String sql = "insert into [plan](planid,proid,planname,state,begindate,enddate,info,create_date) values('"
				+ plan.getPlanid() + "','" + plan.getProtid() + "','" + plan.getPlanname() + "','"
				+ plan.getState()+ "','" + plan.getBegindate() + "','" 
				+ plan.getEnddate() + "','" + plan.getInfo()+ "','" + plan.getCreate_date() + "')";
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
	
	public int check(String proid,String planname) {
		int flag = 0;
		// 查询用户是否已存在
		Connection connection = DBUtil.getConn();
		Statement statement = null;
		ResultSet rs = null;
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select planname from [plan] where proid='"+proid+"'");
			while (rs.next()) {
				if (planname.equals(rs.getString("planname"))) {
					// admin数据表中已存在此账号则说明不允许注册，将标记值记为false
					flag=1;
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
			rs = state.executeQuery("select count(*) count from [plan]");

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

	/**
	 * all 获取每一个分页的数据
	 */
	public List<Plan> getAllListByPage(int start, int size) {
		String sql = "select top " + size + " * from [plan] where(planid not in(select top " + start
				+ " planid from [plan]))";
		// System.out.println("获取分页数据sql语句： "+sql);
		Connection connection = DBUtil.getConn();
		Statement statement = null;
		ResultSet rs = null;
		List<Plan> plans = new ArrayList<Plan>();
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			Plan plan;
			while (rs.next()) {
				plan = new Plan();
				plan.setPlanid(rs.getString("planid"));
				plan.setProtid(rs.getString("proid"));
				plan.setPlanname(rs.getString("planname"));
				plan.setState(rs.getInt("state"));
				plan.setBegindate(rs.getString("begindate"));
				plan.setEnddate(rs.getString("enddate"));
				plan.setInfo(rs.getString("info"));
				plan.setCreate_date(rs.getString("create_date"));
				plans.add(plan);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, statement, connection);
		}
		return plans;
	}

	public List<Plan> getList(String proid) {
		String sql = "select * from [plan] where proid='" + proid + "'";
		//System.out.println(sql);
		Connection connection = DBUtil.getConn();
		Statement statement = null;
		ResultSet rs = null;
		List<Plan> plans = new ArrayList<Plan>();
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			Plan plan;
			while (rs.next()) {
				plan = new Plan();
				plan.setPlanid(rs.getString("planid"));
				plan.setProtid(rs.getString("proid"));
				plan.setPlanname(rs.getString("planname"));
				plan.setState(rs.getInt("state"));
				plan.setBegindate(rs.getString("begindate"));
				plan.setEnddate(rs.getString("enddate"));
				plan.setInfo(rs.getString("info"));
				plan.setCreate_date(rs.getString("create_date"));
				plans.add(plan);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, statement, connection);
		}
		return plans;
	}
	
	
	public List<Plan> getListFresh(String proid) {
		String sql = "select * from [plan] where proid='" + proid + "' and state=0";
		//System.out.println(sql);
		Connection connection = DBUtil.getConn();
		Statement statement = null;
		ResultSet rs = null;
		List<Plan> plans = new ArrayList<Plan>();
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			Plan plan;
			while (rs.next()) {
				plan = new Plan();
				plan.setPlanid(rs.getString("planid"));
				plan.setProtid(rs.getString("proid"));
				plan.setPlanname(rs.getString("planname"));
				plan.setState(rs.getInt("state"));
				plan.setBegindate(rs.getString("begindate"));
				plan.setEnddate(rs.getString("enddate"));
				plan.setInfo(rs.getString("info"));
				plan.setCreate_date(rs.getString("create_date"));
				plans.add(plan);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, statement, connection);
		}
		return plans;
	}

	/**
	 * 根据计划ID得到计划的详细信息
	 */
	public Plan getPlanByPlanid(String planid) {
		Connection conn = DBUtil.getConn();
		Plan plan = null;
		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery("select * from [plan] where planid='" + planid + "'");
			if (rs.next()) {
				plan = new Plan();
				plan.setPlanid(rs.getString("planid"));
				plan.setProtid(rs.getString("proid"));
				plan.setPlanname(rs.getString("planname"));
				plan.setState(rs.getInt("state"));
				plan.setBegindate(rs.getString("begindate"));
				plan.setEnddate(rs.getString("enddate"));
				plan.setInfo(rs.getString("info"));
				plan.setCreate_date(rs.getString("create_date"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return plan;
	}

	/**
	 * 删除计划
	 * @param planid
	 * @return
	 */
	public int update(String planid) {
		int flag = 1;
		String sql = "update [plan] set state='" + 3 + "' where planid='" + planid + "'";
		Connection connection = DBUtil.getConn();
		Statement statement = null;
		int a = 0;
		try {
			statement = connection.createStatement();
			a = statement.executeUpdate(sql);
			flag = 0;// 代表成功
		} catch (Exception e) {
			flag = 1;
			e.printStackTrace();
		} finally {
			DBUtil.close(statement, connection);
		}
		return flag;
	}
	
	
	/**
	 * 修改工期
	 */
	public int updateTime(String planid,String enddate) {
		int flag = 1;
		String sql = "update [plan] set enddate='" + enddate + "' where planid='" + planid + "'";
		Connection connection = DBUtil.getConn();
		Statement statement = null;
		int a = 0;
		try {
			statement = connection.createStatement();
			a = statement.executeUpdate(sql);
			flag = 0;// 代表成功
		} catch (Exception e) {
			flag = 1;
			e.printStackTrace();
		} finally {
			DBUtil.close(statement, connection);
		}
		return flag;
	}

	
	public int updateState(Plan plan) {
		int flag = 1;
		String sql = "update [plan] set state='" + plan.getState() + "' where planid='" + plan.getPlanid() + "'";
		Connection connection = DBUtil.getConn();
		Statement statement = null;
		int a = 0;
		try {
			statement = connection.createStatement();
			a = statement.executeUpdate(sql);
			flag = 0;// 代表成功
		} catch (Exception e) {
			flag = 1;
			e.printStackTrace();
		} finally {
			DBUtil.close(statement, connection);
		}
		return flag;
	}

}
