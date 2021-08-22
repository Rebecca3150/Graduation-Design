package pro.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import pro.dao.LogDao;
import pro.dao.UserDao;
import pro.util.MD5;
import pro.util.StringUtil;

@Controller
@RequestMapping(value="/UserController") 
public class UserController {
	
	
	// spring应用，工厂方法，bean类改造
		// 只要用到bean即...
		private static ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");

		
/**
 * 注册
 */
@RequestMapping(value = "/register", method = RequestMethod.POST)
private String register(HttpServletRequest req, HttpServletResponse resp, ModelMap model)
		throws ServletException, IOException {
			
	req.setCharacterEncoding("utf-8");	
	String writelog="";	
	System.out.println("跳转至UserController！");
	String username = req.getParameter("username");
	String job=req.getParameter("job");
	String role=req.getParameter("role");
	String gender=req.getParameter("gender");
	String email=req.getParameter("email");
	System.out.println("username:"+username);
	String phone=req.getParameter("phone");
	String age=req.getParameter("age");
	String address=req.getParameter("address");
	String number=req.getParameter("number");
	String birth=req.getParameter("birth");
	String password=req.getParameter("password");
	//获取随机字符串——用于对密码进行更复杂的加密
	String salt=StringUtil.getRandomString(10);
	System.out.println("密码："+password+"   盐值："+salt);
	//带有盐值的MD5秘密
	String md5Pwd=MD5.GetMD5Code(MD5.GetMD5Code(password)+salt);
	System.out.println("带有盐值的加密："+md5Pwd);
	User user=context.getBean("user",User.class);// 用到Spring工厂方法
	UserDao userDao = new UserDao();

	SimpleDateFormat createDate=new SimpleDateFormat("yyyyMMddHHmmss");
	String userid=createDate.format(new Date());
	user.setUserid(userid);
	user.setUsername(username);
	user.setPassword(md5Pwd);
	user.setSalt(salt);
	user.setGender(gender);
	user.setJob(job);
	user.setRole(role);
	user.setEmail(email);
	user.setPhone(phone);
	user.setAge(age);
	user.setAddress(address);
	user.setNumber(number);
	user.setBirth(birth);
	SimpleDateFormat createDate1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	user.setCreate_date(createDate1.format(new Date()));
		
	int status = 0;
	boolean flag = userDao.checkReg(username);
	if (flag) {// 不存在，注册成功
		userDao.save(user);
		status = 0;
		writelog=username+"注册用户成功；";
	} else {// 存在，注册失败
		status = 1;
		writelog=username+"注册用户失败；";
	}
	
	model.addAttribute("status", status);
	return "/pages/basic/register";
		}

/**
 * 登录
 */
@RequestMapping(value = "/login", method = RequestMethod.POST)
public String login(HttpServletRequest req, HttpServletResponse resp, ModelMap model) {
	String email = req.getParameter("email");
	String password=MD5.GetMD5Code(req.getParameter("password"));
	System.out.println("登录邮箱：" + email + " ，  密码：" + password);

	User user=context.getBean("user",User.class);// 用到Spring工厂方法
	UserDao userDao = new UserDao();
	int status = 0;// 密码是否正确
	String url = "pages/basic/login";

	if(email==""){
		status=0;//输入用户名
		model.addAttribute("status", status);
	}
	else if(password==""){
		status=1;//输入密码
		model.addAttribute("status", status);
	}
	else{
	boolean flag = userDao.checkRegE(email);
	if (flag) {// 登录失败，不存在
		status = 2;// 用户名不存在
		url = "pages/basic/login";
		model.addAttribute("status", status);
	} else {
		// 存在
		user = userDao.check(email, password);
		if (user != null) {// 密码正确
			System.out.println("密码正确！");
			/*if (user.getStatus() == 0) {// 如果是管理员
				url = "redirect:/first/index.jsp";// 重定向
				req.getSession().setAttribute("admin", admin);
			} else {// 如果非管理员
				url = "/index";
				req.getSession().setAttribute("admin", admin);
			}*/
			url="redirect:/pages/main/index.jsp";
			req.getSession().setAttribute("user", user);
			
			SimpleDateFormat createDate1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Log log=context.getBean("log", Log.class);
			log.setUserid(user.getUserid());
			log.setWritelog(user.getUsername()+"登录；");
			log.setCreate_date(createDate1.format(new Date()));
			LogDao logDao=new LogDao();
			logDao.save(log);
			
		} else {// 密码错误
			System.out.println("密码错误！");
			status = 3;
			url = "pages/basic/login";
			model.addAttribute("status", status);
		}
	}
	}
	
	
	
	return url;
}

}
