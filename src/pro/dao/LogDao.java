package pro.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import pro.action.Log;
import pro.util.DBUtil;

public class LogDao {

	/**
	 * 记录日志
	 * @param user
	 */
	public void save(Log log) {

		System.out.println("跳转至LogDao_save！");
		String sql = "insert into log(userid,writelog,create_date) values('" +log.getUserid() + "','" + log.getWritelog() + "','" +log.getCreate_date() + "')";
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
	 * 展示某用户的操作日志
	 * @return
	 */
	public List<Log> getLogByUserid(String userid) {
		String sql = "select * from log where userid='"+userid+"'";
		Connection connection = DBUtil.getConn();
		Statement statement = null;
		ResultSet rs = null;
		List<Log> logs = new ArrayList<Log>();
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			Log log;
			while (rs.next()) {
				log = new Log();
				log.setLogid(rs.getInt("logid"));
				log.setUserid(rs.getString("userid"));
				log.setWritelog(rs.getString("writelog"));
				log.setCreate_date(rs.getString("create_date"));
				
				logs.add(log);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, statement, connection);
		}
		return logs;
	}
	
	/**
	 * 获取数据表总量By userid
	 * @return
	 */
	public int getCount(String userid){
		ResultSet rs=null;
		Statement state=null;
		Connection conn=null;
		int size=0;
		try{
			conn=DBUtil.getConn();
			state=conn.createStatement();
			rs=state.executeQuery("select count(*) count from log where userid='"+userid+"'");
			
			if(rs.next()){
				size=rs.getInt("count");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, state, conn);
		}
		return size;
	}
	

	/**
	 * 获取每一个分页的数据
	 */
	public List<Log> getListByPage(int start,int size,String userid){
		String sql = "select top " + size + " * from log where(logid not in(select top " + start + " logid from log)) and userid='"+userid+"'";
		System.out.println("获取分页数据sql语句： "+sql);
		Connection connection=DBUtil.getConn();
		Statement statement=null;
		ResultSet rs=null;
		List<Log> logs=new ArrayList<Log>();
		try{
			statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			Log log;
			while(rs.next()){
				log = new Log();
				log.setLogid(rs.getInt("logid"));
				log.setUserid(rs.getString("userid"));
				log.setWritelog(rs.getString("writelog"));
				log.setCreate_date(rs.getString("create_date"));
				logs.add(log);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(rs,statement, connection);
		}
		return logs;
	}
}
