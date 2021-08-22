package pro.action;

public class Task {

	private String taskid;
	private String planid;
	private String extaskid;
	private String taskname;
	private int state;
	private String userid;
	private int teamis;
	private String begindate;
	private String enddate;
	private int day;
	private int time;
	private String taskid2;
	private int level;
	private String info;
	private String create_date;
	
	
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	public String getPlanid() {
		return planid;
	}
	public void setPlanid(String planid) {
		this.planid = planid;
	}
	public String getExtaskid() {
		return extaskid;
	}
	public void setExtaskid(String extaskid) {
		this.extaskid = extaskid;
	}
	public String getTaskname() {
		return taskname;
	}
	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getTeamis() {
		return teamis;
	}
	public void setTeamis(int teamis) {
		this.teamis = teamis;
	}
	public String getBegindate() {
		return begindate;
	}
	public void setBegindate(String begindate) {
		this.begindate = begindate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getTaskid2() {
		return taskid2;
	}
	public void setTaskid2(String taskid2) {
		this.taskid2 = taskid2;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
}
