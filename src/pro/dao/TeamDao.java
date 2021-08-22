package pro.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import pro.action.Team;
import pro.util.DBUtil;

public class TeamDao {

	/**
	 * 根据项目ID得到团队信息
	 */
	public Team getTeamByProid(String proid) {
		Connection conn = DBUtil.getConn();
		Team team = null;
		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery("select * from team where proid='" + proid + "'");
			if (rs.next()) {
				team = new Team();
				team.setProid(rs.getString("proid"));
				team.setGroupid1(rs.getString("groupid1"));
				team.setGroupid1(rs.getString("groupid2"));
				team.setGroupid1(rs.getString("groupid3"));
				team.setGroupid1(rs.getString("groupid4"));
				team.setCreate_date(rs.getString("create_date"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return team;
	}


	/**
	 * 新建团队
	 */
	public void save(Team team) {
		// 获取数据库连接
		System.out.println("跳转至TeamDao_save！");
		String sql = "insert into team(proid,groupid1,groupid2,groupid3,groupid4,create_date) values('" + team.getProid() + "','" + team.getGroupid1() + "','" + team.getGroupid2()
				+ "','" + team.getGroupid3() + "','" + team.getGroupid4() + "','" + team.getCreate_date() + "')";
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
