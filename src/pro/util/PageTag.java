package pro.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import pro.action.PagingBean;






public class PageTag extends SimpleTagSupport {
	private PagingBean pagingBean;
	private HttpServletRequest request;

	@Override
	public void doTag() throws JspException,IOException{
		StringBuffer sb=new StringBuffer();
		
		if(pagingBean!=null){
			//System.out.println("PagTag显示路径："+pagingBean.getPrefixUrl()+"?currentPage="+(pagingBean.getCountPerPage()-1));
//			定义导航链接的部分呢——如果文档中有“前后”按钮，则应该把它放到 <nav> 元素中。
			sb.append("<nav><ul class='pagination'>");
			
			sb.append("<li>");
			sb.append("<span class='text-primary'>");
			sb.append(pagingBean.getCurrentPage() + 1);
			sb.append("/");
			sb.append(pagingBean.getPageCount());
			sb.append("</span>");
			sb.append("</li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
			
			//处理上一页和首页
			if(pagingBean.getCurrentPage()<=0){
				//当前页为第一页
				//则首页按钮和上一页 按钮为不可用状态——点击无效
				sb.append("<li class='disabled text-dark'>首页</li>");
				sb.append("&nbsp;&nbsp;&nbsp;&nbsp;<li class='disabled text-dark'>上一页</li>");
			}else{
				//当前页不是第一页，则首页和上一页为可用状态——点击有效
				sb.append("<li><a href='").append(pagingBean.getPrefixUrl())
				.append("' aria-label='Previous' class='text-primary'><span aria-hidden='true'>首页</span></a></li>");
				sb.append("&nbsp;&nbsp;&nbsp;&nbsp;<li><a href='").append(pagingBean.getPrefixUrl()).append(pagingBean.isAnd()?"?":"?")
				.append("currentPage=").append(pagingBean.getCurrentPage()-1).append("' class='text-primary'><span aria-hidden='true'>上一页</span></a></li>");
			}
				//处理下一页和尾页	
				if(pagingBean.getCurrentPage()>=(pagingBean.getPageCount()-1)){
					//当前页是最后一页，下一页和尾页按钮不可用状态——点击无效
					sb.append("&nbsp;&nbsp;&nbsp;&nbsp;<li class='disabled text-dark'><a>下一页</a></li>");
					sb.append("&nbsp;&nbsp;&nbsp;&nbsp;<li class='disabled text-dark'><a>尾页</a></li>");
				}else{
					//当前页不是最后一页
					sb.append("&nbsp;&nbsp;&nbsp;&nbsp;<li><a class='text-primary' href='").append(pagingBean.getPrefixUrl()).append(pagingBean.isAnd() ? "?" : "?")
					.append("currentPage=").append(pagingBean.getCurrentPage() + 1).append("'><span aria-hidden='true'>下一页</span></a></li>");
					
					sb.append("&nbsp;&nbsp;&nbsp;&nbsp;<li><a class='text-primary' href='").append(pagingBean.getPrefixUrl()).append(pagingBean.isAnd() ? "?" : "?")
					.append("currentPage=").append(pagingBean.getPageCount() - 1)
					.append("' aria-label='Previous'><span aria-hidden='true'>尾页</span></a></li>");
				}
			
				sb.append("</ul></nav>"); 
				getJspContext().getOut().write(sb.toString());
			
		}
	}

	public PagingBean getPagingBean(){
		return pagingBean;
	}
	
	public void setPagingBean(PagingBean pagingBean){
		this.pagingBean=pagingBean;
	}
	
	public HttpServletRequest getRequest(){
		return request;
	}

	public void setRequest(HttpServletRequest request)
	{
		this.request=request;
	}
}
