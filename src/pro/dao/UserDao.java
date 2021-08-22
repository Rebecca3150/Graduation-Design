package pro.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pro.action.User;
import pro.util.DBUtil;
import pro.util.MD5;

public class UserDao {

	/**
	 * 检查是否存在此用户
	 * 
	 * @param name
	 * @return
	 */
	public boolean checkReg(String name) {
		System.out.println("跳转至UserDao_checkReg！");
		// 假设一开始允许注册，将标记值记为true
		boolean flag = true;
		// 查询用户是否已存在
		Connection connection = DBUtil.getConn();
		Statement statement = null;
		ResultSet rs = null;
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select username from [user]");
			while (rs.next()) {
				if (name.equals(rs.getString("username"))) {
					// admin数据表中已存在此账号则说明不允许注册，将标记值记为false
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

	// 检查是否存在此用户_email
	public boolean checkRegE(String email) {
		System.out.println("跳转至UserDao_checkRegE！");
		// 假设一开始允许注册，将标记值记为true
		boolean flag = true;
		// 查询用户是否已存在
		Connection connection = DBUtil.getConn();
		Statement statement = null;
		ResultSet rs = null;
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select email from [user]");
			while (rs.next()) {
				if (email.equals(rs.getString("email"))) {
					// admin数据表中已存在此账号则说明不允许注册，将标记值记为false
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
	 * 注册
	 * 
	 * @param user
	 */
	public void save(User user) {
		// 获取数据库连接
		System.out.println("跳转至UserDao_save！");
		String sql = "insert into [user](userid,username,password,salt,gender,job,role,email,phone,age,address,number,birth,create_date) values('"
				+ user.getUserid() + "','" + user.getUsername() + "','" + user.getPassword() + "','" + user.getSalt()
				+ "','" + user.getGender() + "','" + user.getJob() + "','" + user.getRole() + "','" + user.getEmail()
				+ "','" + user.getPhone() + "','" + user.getAge() + "','" + user.getAddress() + "','" + user.getNumber()
				+ "','" + user.getBirth() + "','" + user.getCreate_date() + "')";
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
	 * 登录验证
	 */
	public User check(String email, String password) {
		Connection conn = DBUtil.getConn();
		User user = null;
		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery("select * from [user] where email='" + email + "'");
			if (rs.next()) {
				if (rs.getString("password").equals(MD5.GetMD5Code(password + rs.getString("salt")))) {
					user = new User();
					user.setUserid(rs.getString("userid"));
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setSalt(rs.getString("salt"));
					user.setGender(rs.getString("gender"));
					user.setJob(rs.getString("job"));
					user.setRole(rs.getString("role"));
					user.setEmail(rs.getString("email"));
					user.setPhone(rs.getString("phone"));
					user.setAge(rs.getString("age"));
					user.setAddress(rs.getString("address"));
					user.setNumber(rs.getString("number"));
					user.setBirth(rs.getString("birth"));
					user.setCreate_date(rs.getString("create_date"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	/**
	 * 获取某一角色的全部用户信息
	 * 
	 * @return
	 */
	public List<User> getUserListByRole(String role) {
		String sql = "select * from [user] where role='" + role + "'";
		Connection connection = DBUtil.getConn();
		Statement statement = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<User>();
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			User user;
			while (rs.next()) {
				user = new User();
				user.setUserid(rs.getString("userid"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setSalt(rs.getString("salt"));
				user.setGender(rs.getString("gender"));
				user.setJob(rs.getString("job"));
				user.setRole(rs.getString("role"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setAge(rs.getString("age"));
				user.setAddress(rs.getString("address"));
				user.setNumber(rs.getString("number"));
				user.setBirth(rs.getString("birth"));
				user.setCreate_date(rs.getString("create_date"));
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, statement, connection);
		}
		return users;
	}
	
	
	/**
	 * 除去管理员和项目经理的所有用户
	 * @param role
	 * @return
	 */
	public List<User> getUserListOutRole() {
		String sql = "select * from [user] where role in('开发人员','数据库人员','制图人员','测试人员')";
		Connection connection = DBUtil.getConn();
		Statement statement = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<User>();
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			User user;
			while (rs.next()) {
				user = new User();
				user.setUserid(rs.getString("userid"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setSalt(rs.getString("salt"));
				user.setGender(rs.getString("gender"));
				user.setJob(rs.getString("job"));
				user.setRole(rs.getString("role"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setAge(rs.getString("age"));
				user.setAddress(rs.getString("address"));
				user.setNumber(rs.getString("number"));
				user.setBirth(rs.getString("birth"));
				user.setCreate_date(rs.getString("create_date"));
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, statement, connection);
		}
		return users;
	}
	
	
	
	/**
	 * 由ID的Name
	 * @throws SQLException 
	 */
	public String getUserNameByUserid(String userid) throws SQLException {
		Connection conn = DBUtil.getConn();
		String name=null;
	
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery("select * from [user] where userid='" + userid + "'");
			if (rs.next()) {
					name=rs.getString("username");
				}
		return name;
	}
	
	
	/**
	 * 由ID得到User信息
	 */
	public User getUser(String userid) {
		Connection conn = DBUtil.getConn();
		User user = null;
		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery("select * from [user] where userid='" + userid + "'");
			if (rs.next()) {
					user = new User();
					user.setUserid(rs.getString("userid"));
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setSalt(rs.getString("salt"));
					user.setGender(rs.getString("gender"));
					user.setJob(rs.getString("job"));
					user.setRole(rs.getString("role"));
					user.setEmail(rs.getString("email"));
					user.setPhone(rs.getString("phone"));
					user.setAge(rs.getString("age"));
					user.setAddress(rs.getString("address"));
					user.setNumber(rs.getString("number"));
					user.setBirth(rs.getString("birth"));
					user.setCreate_date(rs.getString("create_date"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	

}
