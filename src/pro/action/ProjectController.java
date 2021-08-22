package pro.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
import pro.dao.TeamDao;
import pro.dao.UserDao;
import pro.util.DateUtil;
import pro.util.StringUtil;

@Controller
@RequestMapping(value = "/ProjectController")
public class ProjectController {

	private static ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");

	/**
	 * addBefore
	 */
	@RequestMapping(value = "/addBefore", method = RequestMethod.GET)
	public String addBefore(HttpServletRequest req, HttpServletResponse resp, ModelMap model) {
		System.out.println("到ProjectController-addBefore");

		UserDao userDao = new UserDao();
		List<User> userpros1 = userDao.getUserListByRole("项目经理");
		req.getSession().setAttribute("userpros1", userpros1);
		return "pages/project/add_project";
	}

	/**
	 * showall
	 */
	@RequestMapping(value = "/showall", method = RequestMethod.GET)
	public String showall(HttpServletRequest req) {
		System.out.println("到ProjectController-showall");
		/**
		 * 分页显示
		 */
		ProjectDao projectDao = new ProjectDao();
		int currentPage = StringUtil.StringToInt(req.getParameter("currentPage"));
		int countSize = projectDao.getCount();
		PagingBean pagingBeanProject = new PagingBean(currentPage, countSize, 10);
		List<Project> projects = projectDao.getAllListByPage(currentPage * 10, 10);
		pagingBeanProject.setPrefixUrl(req.getContextPath() + "/ProjectController/showall.action");
		pagingBeanProject.setAnd(true); // true的时候是&，否是？

		// List<Log> logs = logDao.getLogByUserid(userid);
		req.getSession().setAttribute("projects", projects);
		req.getSession().setAttribute("projectNumber", countSize);
		req.getSession().setAttribute("pagingBeanProject", pagingBeanProject);
		return "pages/project/show_all";
	}

	/**
	 * 0showclose,1showon
	 */
	@RequestMapping(value = "/showother/{type}", method = RequestMethod.GET)
	public String showother(@PathVariable("type") int type, HttpServletRequest req) {
		System.out.println("到ProjectController-other");
		String url = "";
		/**
		 * 分页显示
		 */
		ProjectDao projectDao = new ProjectDao();
		int currentPage = StringUtil.StringToInt(req.getParameter("currentPage"));
		int countSize = projectDao.getCount(type);
		PagingBean pagingBeanProject = new PagingBean(currentPage, countSize, 10);
		List<Project> projects = projectDao.getOtherListByPage(currentPage * 10, 10, type);
		pagingBeanProject.setPrefixUrl(req.getContextPath() + "/ProjectController/showother/" + type + ".action");
		pagingBeanProject.setAnd(true); // true的时候是&，否是？

		req.getSession().setAttribute("projects", projects);
		req.getSession().setAttribute("projectNumber", countSize);
		if (type == 1) {
			req.getSession().setAttribute("pagingBeanProjectOther1", pagingBeanProject);
			url = "pages/project/show_on";
		} else if (type == 0) {
			req.getSession().setAttribute("pagingBeanProjectOther0", pagingBeanProject);
			url = "pages/project/show_close";
		}
		return url;
	}

	
	
	/**
	 * detail
	 */
	@RequestMapping(value = "/detail/{proid}", method = RequestMethod.GET)
	public void detail(@PathVariable("proid") String proid, HttpServletRequest req, HttpServletResponse resp,
			ModelMap model) throws SQLException, IOException {
		System.out.println("到ProjectController-detail");
		// System.out.println("项目编号：" + ductid);
		ProjectDao projectDao = new ProjectDao();
		UserDao userDao = new UserDao();

		Project project = projectDao.getProjectByProid(proid);
		// System.out.println("1=" + project.getUserid1() + " 2=" +
		// project.getUserid2() + " 3=" + project.getUserid3());
		String userid1 = userDao.getUserNameByUserid(project.getUserid1());
		String userid2 = userDao.getUserNameByUserid(project.getUserid2());
		
		JSONObject json = new JSONObject();
		json.put("proid", project.getProid());
		json.put("proname", project.getProname());
		json.put("info", project.getInfo());
		json.put("begindate", project.getBegindate());
		json.put("enddate", project.getEnddate());
		json.put("userid1", userid1);
		json.put("day", project.getDay());
		json.put("userid2", project.getUserid2());
		json.put("create_date", project.getCreate_date());
		json.put("state", project.getState());
		
		// System.out.println(json);

		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.print(JSON.toJSONString(json));
		out.flush();
		out.close();
	}

	
	/**
	 * update
	 */
	@RequestMapping(value = "/update/{proid}/{userid}", method = RequestMethod.GET)
	public String update(@PathVariable("proid") String proid, @PathVariable("userid") String userid,
			HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		System.out.println("到ProjectController-update");

		// 日志记录
		SimpleDateFormat createDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Log log = context.getBean("log", Log.class);
		log.setUserid(userid);
		// String user=context.getBean("user",User.class);// 用到Spring工厂方法
		UserDao userDao = new UserDao();
		String username = userDao.getUserNameByUserid(userid);
		Project project = context.getBean("project", Project.class);// 用到Spring工厂方法
		ProjectDao projectDao = new ProjectDao();
		project = projectDao.getProjectByProid(proid);
		log.setWritelog(username + "关闭" + project.getProname() + "项目；");
		log.setCreate_date(createDate1.format(new Date()));
		LogDao logDao = new LogDao();
		logDao.save(log);

		projectDao.update(proid);// 关闭产品

		List<Project> projects = projectDao.getProjectList();
		req.getSession().setAttribute("projects", projects);
		return "pages/project/show_all";
	}

	/**
	 * 添加项目 add
	 * userid 创建者
	 */
	@RequestMapping(value = "/add/{userid}", method = RequestMethod.POST)
	public void add(@PathVariable("userid") String userid, HttpServletRequest req, HttpServletResponse resp,
			ModelMap model) throws ServletException, IOException, SQLException {
		int status = 1;
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
		Project project = new Project();

		
		try {
			List<FileItem> fileItems = upload.parseRequest(req);
			PrintWriter pw = resp.getWriter();
			// System.out.println("个数："+fileItems.size());
			SimpleDateFormat createDate = new SimpleDateFormat("yyyyMMddHHmmss");
			String proid = createDate.format(new Date());
			project.setProid(proid);
			SimpleDateFormat createDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String create_date=createDate1.format(new Date());
			SimpleDateFormat createDate2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			project.setCreate_date(create_date);

			for (FileItem item : fileItems) {
				item.getString("utf-8");
				if (item.isFormField()) {
					// 处理表单内容
					System.out.println("这里处理表单内容：" + item.getFieldName());
					status = processFormField(item, pw, project);
				} else {
					// 处理上传文件
					System.out.println("文件");
					status = processUploadFile(item, pw, project);
				}
			}
			ProjectDao projectDao = new ProjectDao();
			if (status == 0) {// 不存在，添加成功
				projectDao.save(project);
				
				//新建团队+新建小组
				TeamDao teamDao=new TeamDao();
				GroupDao groupDao=new GroupDao();
				Team team=context.getBean("team",Team.class);// 用到Spring工厂方法
				team.setProid(proid);
				team.setGroupid1("1"+proid);
				team.setGroupid2("2"+proid);
				team.setGroupid3("3"+proid);
				team.setGroupid4("4"+proid);
				for(int i=1;i<=4;++i){
					groupDao.save(i+proid,i, create_date);
				}
				team.setCreate_date(create_date);
				teamDao.save(team);
				
			}

			System.out.println("上传文件结束！");
			// 日志记录
			// SimpleDateFormat createDate1 = new SimpleDateFormat("yyyy-MM-dd
			// HH:mm:ss");
			Log log = context.getBean("log", Log.class);
			log.setUserid(userid);
			UserDao userDao = new UserDao();
			String username = userDao.getUserNameByUserid(userid);
			log.setWritelog(username + "新增" + project.getProname() + "项目；");
			log.setCreate_date(createDate2.format(new Date()));
			LogDao logDao = new LogDao();
			logDao.save(log);

			if (status == 0) {
				pw.println("<script language='javascript'>alert('新增项目成功！生成项目编号：" + project.getProid()
						+ "');window.location.href='/project_management/pages/project/add_project.jsp';</script>");
			} else {
				pw.println(
						"<script language='javascript'>alert('新增项目失败！');window.location.href='/project_management/pages/project/add_project.jsp';</script>");

			}
			pw.flush();
			pw.close();
			// return "/pages/project/add_project";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 处理上传的文件
	 */
	private int processUploadFile(FileItem item, PrintWriter pw, Project project) {
		// System.out.println("处理上传的文件！");
		String UPLOAD_PATH = "C:/Users/a/workspace/project_management/WebContent/WEB-INF/uploads/";
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
			project.setAnnex(picPath);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	/**
	 * 处理表单内容
	 */
	private int processFormField(FileItem item, PrintWriter pw, Project project) throws UnsupportedEncodingException {
		// System.out.println("处理表单内容！");
		boolean flag;
		int status = 0;
		String name = item.getFieldName();
		String value = new String(item.getString("utf-8"));
		switch (name) {
		case "proname":
			ProjectDao projectDao = new ProjectDao();
			flag = projectDao.checkReg(value);
			if (flag) {// 不存在，添加成功
				status = 0;
			} else {
				status = 1;
			}
			project.setProname(value);
			break;
		case "editorValue":
			System.out.println("富文本编辑器！");
			project.setInfo(value);
			break;
		case "begindate":
			project.setBegindate(value);
			break;
		case "enddate":
			project.setEnddate(value);
			break;
		case "userid1":
			project.setUserid1(value);
			break;
		case "userid2":
			project.setUserid2(value);
			break;
		case "day":
			project.setDay(StringUtil.StringToInt(value));
			break;
		default:
			break;
		}
		return status;
	}

	/**
	 * download
	 */
	@RequestMapping(value = "/download/{proid}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> download(@PathVariable("proid") String proid, HttpServletRequest req,
			String filename, ModelMap model) throws IOException {
		System.out.println("到ProjectController-download");
		Project project = context.getBean("project", Project.class);
		ProjectDao projectDao = new ProjectDao();
		project = projectDao.getProjectByProid(proid);
		String path = project.getAnnex();
		File file = new File(path);
		HttpHeaders headers = new HttpHeaders();
		String fileName = new String("项目信息.doc".getBytes("UTF-8"), "iso-8859-1");
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
	}

	/**
	 * update修改项目详情
	 */
	@RequestMapping(value = "/updateTo/{userid}", method = RequestMethod.POST)
	public void updateTo(@PathVariable("userid") String userid, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		int status = 1;
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		try {
			DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
			List<FileItem> fileItems = upload.parseRequest(req);
			String proid = null;
			for (FileItem item : fileItems) {
				item.getString("utf-8");
				if (item.isFormField()) {
					// 处理表单内容
					String name = item.getFieldName();
					if (name.equals("proid")) {
						proid = new String(item.getString("utf-8"));
						// System.out.println("得到产品编号："+ductid);
					}
				}
			}

			Project project = context.getBean("project", Project.class);
			ProjectDao projectDao = new ProjectDao();
			project = projectDao.getProjectByProid(proid);

			PrintWriter pw = resp.getWriter();
			System.out.println("个数：" + fileItems.size());

			for (FileItem item : fileItems) {
				item.getString("utf-8");
				if (item.isFormField()) {
					// 处理表单内容
					//System.out.println("处理表单内容：" + item.getFieldName());
					processFormFieldUpdate(item, pw, project);
				} else {
					// 处理上传文件
					System.out.println("处理上传文件：" + item.getName());
					status = processUploadFileUpdate(item, pw, project);
				}
			}

			status = projectDao.updateTo(project);

			// 日志记录
			SimpleDateFormat createDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Log log = context.getBean("log", Log.class);
			log.setUserid(userid);
			UserDao userDao = new UserDao();
			String username = userDao.getUserNameByUserid(userid);
			log.setWritelog(username + "修改" + project.getProname() + "项目信息；");
			log.setCreate_date(createDate1.format(new Date()));
			LogDao logDao = new LogDao();
			logDao.save(log);

		//	System.out.println("是否修改成功：" + project.getDuctinfo());
			if (status == 0) {
				pw.println(
						"<script language='javascript'>alert('修改项目信息成功！');window.location.href='/project_management/pages/project/show_all.jsp';</script>");
			} else {
				pw.println(
						"<script language='javascript'>alert('修改项目信息失败！');window.location.href='/project_management/pages/project/show_all.jsp';</script>");
			}

			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 修改上传文件
	 */
	private int processUploadFileUpdate(FileItem item, PrintWriter pw, Project project) {
		int status = 0;
		// 首先要删除原来的文件
		boolean success = (new File(project.getAnnex())).delete();
		if (success) {
			System.out.println("删除成功！");
		} else {
			System.out.println("删除失败！");
			status = 1;
		}
		if (success) {
			System.out.println("修改上传文件");
			String UPLOAD_PATH = "C:/Users/a/workspace/project_management/WebContent/WEB-INF/uploads/";
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
			File uploadFile = new File(
					UPLOAD_PATH + DateUtil.getDateStr() + "/" + DateUtil.getTimeStr() + "." + filename);
			System.out.println("uploadFile:" + uploadFile);
			try {
				item.write(uploadFile);
				project.setAnnex(picPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return status;
	}

	/**
	 * 修改表单内容
	 */
	private void processFormFieldUpdate(FileItem item, PrintWriter pw, Project project)
			throws UnsupportedEncodingException {
		String name = item.getFieldName();
		String value = new String(item.getString("utf-8"));
		switch (name) {
		case "info":
			System.out.println("富文本编辑器！");
			project.setInfo(value);
			//System.out.println("修改前："+value+",后台修改后：" + project.getInfo());
			break;
		default:
			break;
		}
	}

}
