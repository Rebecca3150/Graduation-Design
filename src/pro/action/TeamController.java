package pro.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import pro.dao.GroupDao;
import pro.dao.LogDao;
import pro.dao.ProjectDao;
import pro.dao.TaskDao;
import pro.dao.TeamDao;
import pro.dao.UserDao;
import pro.util.DateUtil;
import pro.util.StringUtil;

@Controller
@RequestMapping(value = "/TeamController")
public class TeamController {

	private static ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");

	/**
	 * teamBefore
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/teamBefore", method = RequestMethod.GET)
	public String teamBefore(HttpServletRequest req, HttpServletResponse resp, ModelMap model) throws IOException {
		System.out.println("到TeamController-teamBefore");

		UserDao userDao = new UserDao();
		ProjectDao projectDao = new ProjectDao();

		List<User> perusers = userDao.getUserListOutRole();
		List<Project> projects = projectDao.getProjectListOther(1);

		req.getSession().setAttribute("perusers", perusers);
		req.getSession().setAttribute("projects", projects);
		return "pages/basic/team";
	}

	@RequestMapping(value = "/showTeam", method = RequestMethod.POST)
	private void getCity(HttpServletRequest req, HttpServletResponse resp, ModelMap model) throws IOException {
		String proid = req.getParameter("proid");
		System.out.println("转到showTeam");
		List<User> userlist = ChangeToList(proid);
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.print(JSON.toJSONString(userlist));
		out.flush();
		out.close();

	}

	/**
	 * showTeam
	 *//*
		 * @RequestMapping(value = "/showTeam/{proid}", method =
		 * RequestMethod.GET) public void detail(@PathVariable("proid") String
		 * proid, HttpServletRequest req, HttpServletResponse resp, ModelMap
		 * model) throws SQLException, IOException {
		 * System.out.println("到TeamController-showTeam"); TeamDao teamDao = new
		 * TeamDao(); UserDao userDao = new UserDao(); GroupDao groupDao=new
		 * GroupDao();
		 * 
		 * Team team = teamDao.getTeamByProid(proid); //List<User>
		 * groupid1=groupDao.getUserByGroupid(team.getGroupid1()); JSONObject
		 * json = new JSONObject(); //json.put("", project.getProid());
		 * //json.put("", project.getProname());
		 * 
		 * // System.out.println(json);
		 * 
		 * resp.setCharacterEncoding("utf-8"); PrintWriter out =
		 * resp.getWriter(); out.print(JSON.toJSONString(json)); out.flush();
		 * out.close(); }
		 */

	/**
	 * add
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(HttpServletRequest req, HttpServletResponse resp, ModelMap model)
			throws ServletException, IOException, SQLException {
		int status = 1;
		req.setCharacterEncoding("utf-8");
		// resp.setContentType("text/html;charset=utf-8");

		String userid = req.getParameter("userid");
		int role = Integer.parseInt(req.getParameter("role"));
		String proid = req.getParameter("proid");
		//System.out.println("得到userid:" + userid + " 得到角色role标签：" + role + " 还有最后的proid:" + proid);

		String groupid = "";
		// 若不空，则找到其编号，添加
		TeamDao teamDao = new TeamDao();
		Team team = teamDao.getTeamByProid(proid);
		GroupDao groupDao = new GroupDao();

		groupid = role + proid;
		Group group = groupDao.get(groupid);
		userid = group.getUserid() + userid + ";";
		status = groupDao.update(groupid, userid);// 0成功，1失败

		List<User> userlist = new ArrayList<User>();
		try {
			// 用于在页面显示的列表
			userlist = ChangeToList(proid);

			// 日志记录

			Log log = context.getBean("log", Log.class);
			SimpleDateFormat createDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			log.setCreate_date(createDate1.format(new Date()));
			log.setUserid("");
			UserDao userDao = new UserDao();
			String username = userDao.getUserNameByUserid(userid);
			Project project = context.getBean("project", Project.class);// 用到Spring工厂方法
			ProjectDao projectDao = new ProjectDao();
			project = projectDao.getProjectByProid(proid);
			log.setWritelog(project.getProname() + "项目新增了小组成员" + username + "；");
			LogDao logDao = new LogDao();
			logDao.save(log);
		} catch (Exception ex) {
			System.out.println("为空");
		}

		//如何将proid传入<select>中？？？？？？？？？？？？？？？？？？？？？？？？？？？？
		
		
		model.addAttribute("status", status);// 需要在页面上显示返回信息
		model.addAttribute("userlist", userlist);
		
		return "/pages/basic/team";

	}

	/**
	 * update
	 */
	@RequestMapping(value = "/update/{proid}/{role}/{userid}", method = RequestMethod.GET)
	public String update(@PathVariable("proid") String proid, @PathVariable("role") String role,
			@PathVariable("userid") String userid, HttpServletRequest req, HttpServletResponse resp, ModelMap model)
			throws SQLException {
		System.out.println("到TeamController-update");
		int state = 1;

		// 日志记录
		SimpleDateFormat createDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Log log = context.getBean("log", Log.class);
		log.setUserid("");
		// String user=context.getBean("user",User.class);// 用到Spring工厂方法
		UserDao userDao = new UserDao();
		String username = userDao.getUserNameByUserid(userid);
		Project project = context.getBean("project", Project.class);// 用到Spring工厂方法
		ProjectDao projectDao = new ProjectDao();
		project = projectDao.getProjectByProid(proid);
		log.setWritelog("删除了" + project.getProname() + "项目的小组成员" + username + ";");
		log.setCreate_date(createDate1.format(new Date()));
		LogDao logDao = new LogDao();
		logDao.save(log);

		// 删除小组成员
		// System.out.println("得到的:::::proid=" + proid + ":::::role=" + role +
		// ":::::userid=" + userid);
		int newrole = 1;
		if (role.equals("开发人员")) {
			newrole = 1;
		} else if (role.equals("数据库人员")) {
			newrole = 2;
		} else if (role.equals("制图人员")) {
			newrole = 3;
		} else if (role.equals("测试人员")) {
			newrole = 4;
		}
		TeamDao teamDao = new TeamDao();
		Team team = teamDao.getTeamByProid(proid);
		GroupDao groupDao = new GroupDao();
		String groupid = newrole + proid;
		Group group = groupDao.get(groupid);
		String exuserid = group.getUserid();

		String newuserid = exuserid.replace(userid + ";", "");
		// System.out.println("原来的userid:" + exuserid + "，需要删除的是：" + userid +
		// "，结果是：" + newuserid);
		state = groupDao.update(groupid, newuserid);// 0成功，1失败

		List<User> userlist = ChangeToList(proid);

		model.addAttribute("state", state);// 需要在页面上显示返回信息
		model.addAttribute("userlist", userlist);
		return "pages/basic/team";
	}

	/**
	 * split截取字符串
	 */
	public String[] SplitApply(String userid) {
		String[] userlist = userid.split(";");
		return userlist;
	}

	/**
	 * 将一系列字符串转换成用户列表
	 */
	public List<User> ChangeToList(String proid) {
		// System.out.println("ChangeToList函数中的proid" + proid);
		User user = null;
		UserDao userDao = new UserDao();
		GroupDao groupDao = new GroupDao();
		TeamDao teamDao = new TeamDao();
		List<User> userlist = new ArrayList<User>();
		String[] str;
		String userid1234;
		// groupid1,2,3,4
		for (int j = 1; j <= 4; ++j) {
			userid1234 = groupDao.get(j + proid).getUserid();
			str = SplitApply(userid1234);
			for (int i = 1; i < str.length; ++i) {
				user = userDao.getUser(str[i]);
				userlist.add(user);
			}
		}
		return userlist;
	}
	
	public List<User> ChangeToListSingle(String groupid) {
		User user = null;
		UserDao userDao = new UserDao();
		GroupDao groupDao = new GroupDao();
		List<User> userlist = new ArrayList<User>();
		String[] str;
		String userid;
		userid = groupDao.get(groupid).getUserid();
		str = SplitApply(userid);
		for (int i = 1; i < str.length; ++i) {
		user = userDao.getUser(str[i]);
		userlist.add(user);
			}
		return userlist;
	}
	
	/**
	 * 根据groupid得到小组成员
	 */
	@RequestMapping(value = "/getUserList", method = RequestMethod.POST)
	private void getUserList(HttpServletRequest req, HttpServletResponse resp) throws IOException { 
		String groupid =req.getParameter("groupid");
		System.out.println("转到getUserList");
		List<User> userList = ChangeToListSingle(groupid);
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.print(JSON.toJSONString(userList));
		out.flush();
		out.close();

		}
}
