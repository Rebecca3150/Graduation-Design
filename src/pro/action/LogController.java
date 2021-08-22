package pro.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import pro.dao.LogDao;
import pro.util.StringUtil;

@Controller
@RequestMapping(value = "/LogController")
public class LogController {

	private static ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");

	/**
	 * showlog
	 */
	@RequestMapping(value = "/showlog/{userid}", method = RequestMethod.GET)
	public String showall(@PathVariable("userid") String userid, HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("到LogController-showlog");

		/**
		 * 分页显示
		 */
		LogDao logDao = new LogDao();
		int currentPage=StringUtil.StringToInt(req.getParameter("currentPage"));
		//System.out.println("现在是第几页： "+currentPage);
		int countSize=logDao.getCount(userid);
		PagingBean pagingBeanLog=new PagingBean(currentPage,countSize,10);
		List<Log> logs=logDao.getListByPage(currentPage * 10,10,userid);
		pagingBeanLog.setPrefixUrl(req.getContextPath()+"/LogController/showlog/"+userid+".action");
		pagingBeanLog.setAnd(true); //true的时候是&，否是？
		
		//List<Log> logs = logDao.getLogByUserid(userid);
		req.getSession().setAttribute("logs", logs);
		req.getSession().setAttribute("logNumber", countSize);
		req.getSession().setAttribute("pagingBeanLog", pagingBeanLog);
		return "pages/basic/show_log";
	}

}
