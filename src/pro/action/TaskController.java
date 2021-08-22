package pro.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import pro.dao.GroupDao;
import pro.dao.LogDao;
import pro.dao.PlanDao;
import pro.dao.ProjectDao;
import pro.dao.TaskDao;
import pro.dao.TeamDao;
import pro.dao.UserDao;
import pro.util.DateUtil;
import pro.util.StringUtil;

@Controller
@RequestMapping(value = "/TaskController")
public class TaskController {

	private static ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");

	/**
	 * addSingleBefore
	 */
	@RequestMapping(value = "/addSingleBefore", method = RequestMethod.GET)
	public String addSingleBefore(HttpServletRequest req, HttpServletResponse resp, ModelMap model) {
		//System.out.println("到TaskController-addSingleBefore");

		ProjectDao projectDao = new ProjectDao();
		List<Project> projects = projectDao.getProjectListOther(1);
		req.getSession().setAttribute("projects", projects);
		return "pages/task/add_task";
	}

	/**
	 * 添加add单个任务
	 */
	@RequestMapping(value = "/add/{userid}", method = RequestMethod.POST)
	public String add(@PathVariable("userid") String userid, HttpServletRequest req, HttpServletResponse resp,
			ModelMap model) throws ServletException, IOException, SQLException, FileUploadException {
		int status = 1;
		req.setCharacterEncoding("utf-8");
		Task task = new Task();

		SimpleDateFormat createDate = new SimpleDateFormat("yyyyMMddHHmmss");
		String taskid = createDate.format(new Date());

		SimpleDateFormat createDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String create_date = createDate1.format(new Date());
		String proid = req.getParameter("proid");
		String planid = req.getParameter("planid");
		String ztaskid = req.getParameter("taskid");// 子任务的taskid
		String taskid2 = req.getParameter("taskid2"); //前置任务
		int type = Integer.parseInt(req.getParameter("type"));
		String begindate = req.getParameter("begindate");
		String enddate = req.getParameter("enddate");
		int level = Integer.parseInt(req.getParameter("level"));
		String info = req.getParameter("editorValue");
		String taskname = req.getParameter("taskname");
		String useridlist = req.getParameter("userid");

		task.setTaskid(taskid);
		task.setPlanid(planid);
		if (ztaskid == null)
			ztaskid = "0";
		task.setExtaskid(ztaskid);
		task.setTaskname(taskname);
		if (useridlist.equals("0")) {
			task.setTeamis(1);// 团队
			task.setUserid(type + proid);
			//System.out.println(type + proid);
		} else {
			task.setTeamis(0);// 个人
			task.setUserid(useridlist);
			//System.out.println(useridlist);
		}
		task.setBegindate(begindate);
		task.setEnddate(enddate);
		if (taskid2 == null)
			taskid2 = "0";
		task.setTaskid2(taskid2);
		task.setLevel(level);
		task.setInfo(info);
		task.setCreate_date(create_date);

		TaskDao taskDao = new TaskDao();
		status = taskDao.check(planid, taskname);
		if (status == 0) {// 不存在，添加成功
			taskDao.save(task);
		}

		Log log = context.getBean("log", Log.class);
		log.setUserid(userid);
		UserDao userDao = new UserDao();
		String username = userDao.getUserNameByUserid(userid);
		log.setWritelog(username + "新增" + task.getTaskname() + "任务；");
		log.setCreate_date(createDate1.format(new Date()));
		LogDao logDao = new LogDao();
		logDao.save(log);

		ProjectDao projectDao = new ProjectDao();
		List<Project> projects = projectDao.getProjectListOther(1);

		req.getSession().setAttribute("projects", projects);
		model.addAttribute("status", status);// 需要在页面上显示返回信息
		model.addAttribute("taskid", taskid);

		return "/pages/task/add_task";

	}

	/**
	 * getTaskByPlanid 未开始的
	 */
	@RequestMapping(value = "/getTaskByPlanid", method = RequestMethod.POST)
	private void getTaskByPlanid(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String planid = req.getParameter("planid");
		TaskDao taskDao = new TaskDao();
		//System.out.println("转到getTaskByPlanid");
		List<Task> taskList = taskDao.getListFresh(planid);
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.print(JSON.toJSONString(taskList));
		out.flush();
		out.close();

	}
	
	@RequestMapping(value = "/getTaskByEXTaskid", method = RequestMethod.POST)
	private void getTaskByEXTaskid(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String extaskid = req.getParameter("taskid");
		TaskDao taskDao = new TaskDao();
		//System.out.println("转到getTaskByEXTaskid");
		List<Task> taskList = taskDao.getListFreshByEx(extaskid);
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.print(JSON.toJSONString(taskList));
		out.flush();
		out.close();

	}
	
	
	/**
	 * 批量创建
	 */
	@RequestMapping(value = "/addAllBefore", method = RequestMethod.GET)
	public String addAllBefore(HttpServletRequest req, HttpServletResponse resp, ModelMap model) {
		//System.out.println("到TaskController-addAllBefore");

		ProjectDao projectDao = new ProjectDao();
		List<Project> projects = projectDao.getProjectListOther(1);
		req.getSession().setAttribute("projects", projects);
		return "pages/task/add_all";
	}
}
