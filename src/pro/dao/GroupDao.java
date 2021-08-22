package pro.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import pro.action.Group;
import pro.util.DBUtil;

public class GroupDao {

	/**
	 * 根据小组ID得到小组信息
	 * 
	 * @param groupid
	 * @return
	 */
	public Group get(String groupid) {
		Connection conn = DBUtil.getConn();
		Group group = null;
		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery("select * from [group] where groupid='" + groupid + "'");
			if (rs.next()) {
				group = new Group();
				group.setGroupid(groupid);
				group.setType(rs.getInt("type"));
				group.setUserid(rs.getString("userid"));
				group.setCreate_date(rs.getString("create_date"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return group;
	}

	/**
	 * 新建小组
	 */
	public void save(String groupid, int type, String create_date) {
		// 获取数据库连接
		System.out.println("跳转至GroupDao_save！");
		String sql = "insert into [group](groupid,type,userid,create_date) values('" + groupid + "'," + type + ",';','"
				+ create_date + "')";
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
	 * 增添用户
	 */
	public int update(String groupid, String userid) {
		int flag = 1;
		String sql = "update [group] set userid='" + userid + "' where groupid='" + groupid + "'";
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
