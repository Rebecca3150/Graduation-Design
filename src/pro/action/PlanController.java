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
import pro.dao.TeamDao;
import pro.dao.UserDao;
import pro.util.DateUtil;
import pro.util.StringUtil;

@Controller
@RequestMapping(value = "/PlanController")
public class PlanController {

	private static ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");

	/**
	 * addBefore
	 */
	@RequestMapping(value = "/addBefore", method = RequestMethod.GET)
	public String addBefore(HttpServletRequest req, HttpServletResponse resp, ModelMap model) {
		System.out.println("到PlanController-addBefore");

		ProjectDao projectDao = new ProjectDao();
		List<Project> projects = projectDao.getProjectListOther(1);
		req.getSession().setAttribute("projects", projects);
		return "pages/plan/add_plan";
	}

	/**
	 * 添加计划 add
	 */
	@RequestMapping(value = "/add/{userid}", method = RequestMethod.POST)
	public String add(@PathVariable("userid") String userid, HttpServletRequest req, HttpServletResponse resp,
			ModelMap model) throws ServletException, IOException, SQLException, FileUploadException {
		int status = 1;
		req.setCharacterEncoding("utf-8");
		Plan plan = new Plan();

		SimpleDateFormat createDate = new SimpleDateFormat("yyyyMMddHHmmss");
		String planid = createDate.format(new Date());

		SimpleDateFormat createDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String create_date = createDate1.format(new Date());
		String proid = req.getParameter("proid");
		String planname = req.getParameter("planname");
		String begindate = req.getParameter("begindate");
		String enddate = req.getParameter("enddate");
		String info = req.getParameter("editorValue");

		plan.setPlanid(planid);
		plan.setCreate_date(create_date);
		plan.setProtid(proid);
		plan.setPlanname(planname);
		plan.setBegindate(begindate);
		plan.setEnddate(enddate);
		plan.setInfo(info);

		PlanDao planDao = new PlanDao();
		status = planDao.check(proid, planname);
		if (status == 0) {// 不存在，添加成功
			planDao.save(plan);
		}

		Log log = context.getBean("log", Log.class);
		log.setUserid(userid);
		UserDao userDao = new UserDao();
		String username = userDao.getUserNameByUserid(userid);
		log.setWritelog(username + "新增" + plan.getPlanname() + "计划；");
		log.setCreate_date(createDate1.format(new Date()));
		LogDao logDao = new LogDao();
		logDao.save(log);

		model.addAttribute("status", status);// 需要在页面上显示返回信息
		model.addAttribute("planid", planid);

		return "/pages/plan/add_plan";

	}

	/**
	 * showBefore
	 */
	@RequestMapping(value = "/showBefore", method = RequestMethod.GET)
	public String showBefore(HttpServletRequest req, HttpServletResponse resp, ModelMap model) throws IOException {
		System.out.println("到PlanController-showBefore");
		ProjectDao projectDao = new ProjectDao();
		List<Project> projects = projectDao.getProjectListOther(1);
		req.getSession().setAttribute("projects", projects);
		return "pages/plan/show_all";
	}

	/**
	 * updateBefore
	 */
	@RequestMapping(value = "/updateBefore", method = RequestMethod.GET)
	public String updateBefore(HttpServletRequest req, HttpServletResponse resp, ModelMap model) throws IOException {
		System.out.println("到PlanController-updateBefore");
		ProjectDao projectDao = new ProjectDao();
		List<Project> projects = projectDao.getProjectListOther(1);
		req.getSession().setAttribute("projects", projects);
		return "pages/plan/show_update";
	}

	/**
	 * showall
	 * 
	 * @throws ParseException
	 */
	@RequestMapping(value = "/showall", method = RequestMethod.POST)
	private void showall(HttpServletRequest req, HttpServletResponse resp, ModelMap model)
			throws IOException, ParseException {
		String proid = req.getParameter("proid");
		System.out.println("转到showall");
		PlanDao planDao = new PlanDao();
		List<Plan> planlist = planDao.getList(proid);

		// 与现在的时间匹配，更改计划的状态
		matchTimeInPlanList(planlist);

		planlist = planDao.getList(proid);
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.print(JSON.toJSONString(planlist));
		out.flush();
		out.close();

	}

	/**
	 * 与现在的时间匹配，更改计划的状态
	 * 
	 * @param planlist
	 * @throws ParseException
	 */
	public void matchTimeInPlanList(List<Plan> planlist) throws ParseException {
		PlanDao planDao = new PlanDao();
		// 与现在的时间匹配，更改计划的状态
		SimpleDateFormat createDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now_date = createDate.format(new Date());
		Date date = createDate.parse(now_date);// 当前日期
		Date date2, date3;
		for (Plan plan : planlist) {
			if (plan.getState() != 3) {
				date2 = createDate.parse(plan.getBegindate());// 开始日期
				date3 = createDate.parse(plan.getEnddate());// 结束日期

				// 进行比较(0未开始，1已过期，2未过期，3被删除)
				if (date.before(date2)) {// 还未开始
					plan.setState(0);// 未开始
				} else if (date.after(date2) && date.before(date3)) {
					plan.setState(2);// 未过期，进行中
				} else if (date.after(date3)) {
					plan.setState(1);// 已过期
				}
				planDao.updateState(plan);
			}
		}
	}

	/**
	 * update
	 * 
	 * @throws ParseException
	 */
	@RequestMapping(value = "/update/{planid}", method = RequestMethod.GET)
	public String update(@PathVariable("planid") String planid, HttpServletRequest req, HttpServletResponse resp,
			ModelMap model) throws SQLException, ParseException {
		System.out.println("到PlanController-update");
		int status = 1;

		// 日志记录
		SimpleDateFormat createDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Log log = context.getBean("log", Log.class);
		log.setUserid("");
		// String user=context.getBean("user",User.class);// 用到Spring工厂方法
		UserDao userDao = new UserDao();
		// String username = userDao.getUserNameByUserid(userid);
		Plan plan = context.getBean("plan", Plan.class);// 用到Spring工厂方法
		PlanDao planDao = new PlanDao();
		plan = planDao.getPlanByPlanid(planid);
		log.setWritelog("删除了" + plan.getPlanname() + "项目计划;");
		log.setCreate_date(createDate1.format(new Date()));
		LogDao logDao = new LogDao();
		logDao.save(log);

		status = planDao.update(planid);// 0成功，1失败

		List<Plan> planlist = planDao.getList(plan.getProtid());
		// 与现在的时间匹配，更改计划的状态
		matchTimeInPlanList(planlist);

		model.addAttribute("status", status);// 需要在页面上显示返回信息
		model.addAttribute("planlist", planlist);
		return "pages/plan/show_all";
	}

	/**
	 * getPlan
	 * 
	 * @throws ParseException
	 */
	@RequestMapping(value = "/getPlan/{planid}", method = RequestMethod.GET)
	public void getPlan(@PathVariable("planid") String planid, HttpServletRequest req, HttpServletResponse resp,
			ModelMap model) throws SQLException, IOException, ParseException {
		System.out.println("到PlanController-getPlan");
		System.out.println("计划编号ID：" + planid);
		PlanDao planDao = new PlanDao();

		Plan plan = planDao.getPlanByPlanid(planid);

		// 计算当前工期
		String enddate = plan.getEnddate();
		String begindate = plan.getBegindate();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date edate = simpleDateFormat.parse(enddate);
		Date bdate = simpleDateFormat.parse(begindate);
		long diff = edate.getTime() - bdate.getTime();
		long nd = 1000 * 24 * 60 * 60;
		long day = diff / nd;

		JSONObject json = new JSONObject();
		json.put("planid", plan.getPlanid());
		json.put("proid", plan.getProtid());
		json.put("planname", plan.getPlanname());
		json.put("begindate", plan.getBegindate());
		json.put("enddate", plan.getEnddate());
		json.put("info", plan.getInfo());
		json.put("create_date", plan.getCreate_date());
		json.put("state", plan.getState());
		json.put("newdate", day);
		//System.out.println("day："+day);

		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.print(JSON.toJSONString(json));
		out.flush();
		out.close();
	}

	/**
	 * getPlanByProid
	 * 得到未开始的计划
	 */
	@RequestMapping(value = "/getPlanByProid", method = RequestMethod.POST)
	private void getPlanByProid(HttpServletRequest req, HttpServletResponse resp) throws IOException { 
		String proid =req.getParameter("proid");
		PlanDao planDao = new PlanDao();
		System.out.println("转到getPlanByProid");
		List<Plan> planList = planDao.getListFresh(proid);
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.print(JSON.toJSONString(planList));
		out.flush();
		out.close();

		}
	
	
	/**
	 * updateTime
	 */
	@RequestMapping(value = "/updateTime", method = RequestMethod.POST)
	public String updateTime(HttpServletRequest req, HttpServletResponse resp,
			ModelMap model) throws SQLException, ParseException {
		System.out.println("到PlanController-updateTime");
		int status = 1;
		String planid=req.getParameter("planid");
		int day=Integer.parseInt(req.getParameter("newdate"));
		// 日志记录
		SimpleDateFormat createDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Log log = context.getBean("log", Log.class);
		log.setUserid("");
		// String user=context.getBean("user",User.class);// 用到Spring工厂方法
		UserDao userDao = new UserDao();
		// String username = userDao.getUserNameByUserid(userid);
		Plan plan = context.getBean("plan", Plan.class);// 用到Spring工厂方法
		PlanDao planDao = new PlanDao();
		plan = planDao.getPlanByPlanid(planid);
		log.setWritelog("修改了" + plan.getPlanname() + "项目计划的工期;");
		log.setCreate_date(createDate1.format(new Date()));
		LogDao logDao = new LogDao();
		logDao.save(log);

		//经修改过的工期修改结束日期
		String enddate;
		String begindate = plan.getBegindate();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = df.parse(begindate);     
		Calendar cal=Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.DATE, day);
		//System.out.println(df.format(cal.getTime()));
		String newenddate=df.format(cal.getTime());
		status = planDao.updateTime(planid,newenddate);// 0成功，1失败

		List<Plan> planlist = planDao.getList(plan.getProtid());
		// 与现在的时间匹配，更改计划的状态
		matchTimeInPlanList(planlist);
		
		ProjectDao projectDao = new ProjectDao();
		List<Project> projects = projectDao.getProjectListOther(1);
		req.getSession().setAttribute("projects", projects);
		model.addAttribute("status", status);// 需要在页面上显示返回信息
		model.addAttribute("planlist", planlist);
		
		return "pages/plan/show_update";
	}
	
	/**
	 * 处理上传的文件
	 */
	private int processUploadFile(FileItem item, PrintWriter pw, Plan plan) {
		// System.out.println("处理上传的文件！");
		String UPLOAD_PATH = "C:/Users/a/workspace/plan_management/WebContent/WEB-INF/uploads/";
		int status = 0;
		String filename = item.getName();
		int index = filename.lastIndexOf(".");
		filename = filename.substring(index + 1, filename.length());
		String picPath = UPLOAD_PATH + DateUtil.getDateStr() + "/" + DateUtil.getTimeStr() + "." + filename;
		// System.out.println("picPath:"+picPath);
		long fileSize = item.getSize();
		if ("".equals(filename) && fileSize == 0) {
			status = 1;
		}
		// 新建文件夹，日期为文件夹名，时间为文件名
		File file = new File(UPLOAD_PATH + DateUtil.getDateStr());
		// System.out.println("file："+file);
		file.mkdirs();
		File uploadFile = new File(UPLOAD_PATH + DateUtil.getDateStr() + "/" + DateUtil.getTimeStr() + "." + filename);
		System.out.println("uploadFile:" + uploadFile);
		try {
			item.write(uploadFile);
			// plan.setAnnex(picPath);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	/**
	 * 处理表单内容
	 */
	/*
	 * private int processFormField(FileItem item, PrintWriter pw, Plan plan)
	 * throws UnsupportedEncodingException { // System.out.println("处理表单内容！");
	 * boolean flag; int status = 0; String name = item.getFieldName(); String
	 * value = new String(item.getString("utf-8")); switch (name) { case
	 * "proid": PlanDao planDao = new PlanDao(); flag = planDao.checkReg(value);
	 * if (flag) {// 不存在，添加成功 status = 0; } else { status = 1; }
	 * plan.setProname(value); break; case "editorValue":
	 * System.out.println("富文本编辑器！"); plan.setInfo(value); break; case
	 * "begindate": plan.setBegindate(value); break; case "enddate":
	 * plan.setEnddate(value); break; case "userid1": plan.setUserid1(value);
	 * break; case "userid2": plan.setUserid2(value); break; case "day":
	 * plan.setDay(StringUtil.StringToInt(value)); break; default: break; }
	 * return status; }
	 */

}
