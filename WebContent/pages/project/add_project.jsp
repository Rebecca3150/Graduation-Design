<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>XX软件公司产品开发项目管理系统</title>
<!-- plugins:css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/vendors/iconfonts/mdi/css/materialdesignicons.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/vendors/css/vendor.bundle.base.css">
<!-- endinject -->
<!-- inject:css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<!-- endinject -->
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/images/favicon.png" />

<!-- 富文本编辑器 -->
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/utf8-jsp/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/utf8-jsp/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/utf8-jsp/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
	//实例化编辑器
	//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，
	//直接调用UE.getEditor('editor')就能拿到相关的实例
	var ue = UE.getEditor('editor');
</script>

</head>
<body>
	<div class="container-scroller">
		<!-- partial:partials/_navbar.html -->
		<nav
			class="navbar default-layout-navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
		<div
			class="text-center navbar-brand-wrapper d-flex align-items-center justify-content-center">
			<a class="navbar-brand brand-logo"
				href="${pageContext.request.contextPath}/main/index.jsp"><img
				src="${pageContext.request.contextPath}/images/logo.svg" alt="logo" /></a>
			<a class="navbar-brand brand-logo-mini"
				href="${pageContext.request.contextPath}/main/index.jsp"><img
				src="${pageContext.request.contextPath}/images/logo-mini.svg"
				alt="logo" /></a>
		</div>
		<div class="navbar-menu-wrapper d-flex align-items-stretch">
			<div class="search-field d-none d-md-block">
				<form class="d-flex align-items-center h-100" action="#">
					<div class="input-group">
						<div class="input-group-prepend bg-transparent">
							<i class="input-group-text border-0 mdi mdi-magnify"></i>
						</div>
						<input type="text" class="form-control bg-transparent border-0"
							placeholder="Search projects">
					</div>
				</form>
			</div>
			<ul class="navbar-nav navbar-nav-right">
				<li class="nav-item nav-profile dropdown"><a
					class="nav-link dropdown-toggle" id="profileDropdown" href="#"
					data-toggle="dropdown" aria-expanded="false">
						<div class="nav-profile-img">
							<img
								src="${pageContext.request.contextPath}/images/faces-clipart/pic-2.png"
								alt="image"> <span class="availability-status online"></span>
						</div>
						<div class="nav-profile-text">
							<p class="mb-1 text-black">${user.username }</p>
						</div>
				</a>
					<div class="dropdown-menu navbar-dropdown"
						aria-labelledby="profileDropdown">
						<a class="dropdown-item" href="#"> <i
							class="mdi mdi-cached mr-2 text-success"></i> 活动日志
						</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item"
							href="${pageContext.request.contextPath}/pages/basic/login.jsp">
							<i class="mdi mdi-logout mr-2 text-primary"></i> 退出
						</a>
					</div></li>
				<li class="nav-item d-none d-lg-block full-screen-link"><a
					class="nav-link"> <i class="mdi mdi-fullscreen"
						id="fullscreen-button"></i>
				</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link count-indicator dropdown-toggle"
					id="messageDropdown" href="#" data-toggle="dropdown"
					aria-expanded="false"> <i class="mdi mdi-email-outline"></i> <span
						class="count-symbol bg-warning"></span>
				</a>
					<div
						class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list"
						aria-labelledby="messageDropdown">
						<h6 class="p-3 mb-0">Messages</h6>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item preview-item">
							<div class="preview-thumbnail">
								<img
									src="${pageContext.request.contextPath}/images/faces/face4.jpg"
									alt="image" class="profile-pic">
							</div>
							<div
								class="preview-item-content d-flex align-items-start flex-column justify-content-center">
								<h6 class="preview-subject ellipsis mb-1 font-weight-normal">Mark
									send you a message</h6>
								<p class="text-gray mb-0">1 Minutes ago</p>
							</div>
						</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item preview-item">
							<div class="preview-thumbnail">
								<img
									src="${pageContext.request.contextPath}/images/faces/face2.jpg"
									alt="image" class="profile-pic">
							</div>
							<div
								class="preview-item-content d-flex align-items-start flex-column justify-content-center">
								<h6 class="preview-subject ellipsis mb-1 font-weight-normal">Cregh
									send you a message</h6>
								<p class="text-gray mb-0">15 Minutes ago</p>
							</div>
						</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item preview-item">
							<div class="preview-thumbnail">
								<img
									src="${pageContext.request.contextPath}/images/faces/face3.jpg"
									alt="image" class="profile-pic">
							</div>
							<div
								class="preview-item-content d-flex align-items-start flex-column justify-content-center">
								<h6 class="preview-subject ellipsis mb-1 font-weight-normal">Profile
									picture updated</h6>
								<p class="text-gray mb-0">18 Minutes ago</p>
							</div>
						</a>
						<div class="dropdown-divider"></div>
						<h6 class="p-3 mb-0 text-center">4 new messages</h6>
					</div></li>
				<li class="nav-item dropdown"><a
					class="nav-link count-indicator dropdown-toggle"
					id="notificationDropdown" href="#" data-toggle="dropdown"> <i
						class="mdi mdi-bell-outline"></i> <span
						class="count-symbol bg-danger"></span>
				</a>
					<div
						class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list"
						aria-labelledby="notificationDropdown">
						<h6 class="p-3 mb-0">Notifications</h6>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item preview-item">
							<div class="preview-thumbnail">
								<div class="preview-icon bg-success">
									<i class="mdi mdi-calendar"></i>
								</div>
							</div>
							<div
								class="preview-item-content d-flex align-items-start flex-column justify-content-center">
								<h6 class="preview-subject font-weight-normal mb-1">Event
									today</h6>
								<p class="text-gray ellipsis mb-0">Just a reminder that you
									have an event today</p>
							</div>
						</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item preview-item">
							<div class="preview-thumbnail">
								<div class="preview-icon bg-warning">
									<i class="mdi mdi-settings"></i>
								</div>
							</div>
							<div
								class="preview-item-content d-flex align-items-start flex-column justify-content-center">
								<h6 class="preview-subject font-weight-normal mb-1">Settings</h6>
								<p class="text-gray ellipsis mb-0">Update dashboard</p>
							</div>
						</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item preview-item">
							<div class="preview-thumbnail">
								<div class="preview-icon bg-info">
									<i class="mdi mdi-link-variant"></i>
								</div>
							</div>
							<div
								class="preview-item-content d-flex align-items-start flex-column justify-content-center">
								<h6 class="preview-subject font-weight-normal mb-1">Launch
									Admin</h6>
								<p class="text-gray ellipsis mb-0">New admin wow!</p>
							</div>
						</a>
						<div class="dropdown-divider"></div>
						<h6 class="p-3 mb-0 text-center">See all notifications</h6>
					</div></li>
				<li class="nav-item nav-logout d-none d-lg-block"><a
					class="nav-link"
					href="${pageContext.request.contextPath}/pages/basic/login.jsp">
						<i class="mdi mdi-power"></i>
				</a></li>
				<li class="nav-item nav-settings d-none d-lg-block"><a
					class="nav-link" href="#"> <i
						class="mdi mdi-format-line-spacing"></i>
				</a></li>
			</ul>
			<button
				class="navbar-toggler navbar-toggler-right d-lg-none align-self-center"
				type="button" data-toggle="offcanvas">
				<span class="mdi mdi-menu"></span>
			</button>
		</div>
		</nav>
		<!-- partial -->
		<div class="container-fluid page-body-wrapper">
			<!-- partial:partials/_sidebar.html -->
			<nav class="sidebar sidebar-offcanvas" id="sidebar">
			<ul class="nav">
				<li class="nav-item nav-profile"><a href="#" class="nav-link">
						<div class="nav-profile-image">
							<img
								src="${pageContext.request.contextPath}/images/faces-clipart/pic-2.png"
								alt="profile"> <span class="login-status online"></span>
							<!--change to offline or busy as needed-->
						</div>
						<div class="nav-profile-text d-flex flex-column">
							<span class="font-weight-bold mb-2">${user.username }</span> <span
								class="text-secondary text-small">${user.role }</span>
						</div> <i class="mdi mdi-bookmark-check text-success nav-profile-badge"></i>
				</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/pages/main/index.jsp">
						<span class="menu-title">首页</span> <i
						class="mdi mdi-home menu-icon"></i>
				</a></li>
				<li class="nav-item"><a class="nav-link" data-toggle="collapse"
					href="#ui-axinxi" aria-expanded="false" aria-controls="ui-basic">
						<span class="menu-title">项目信息管理</span> <i class="menu-arrow"></i>
						<i class="mdi mdi-crosshairs-gps menu-icon"></i>
				</a>
					<div class="collapse" id="ui-axinxi">
						<ul class="nav flex-column sub-menu">
							<li class="nav-item"><a class="nav-link"
								href="/project_management/ProjectController/addBefore.action">新增项目</a></li>
							<li class="nav-item"><a class="nav-link"
								href="/project_management/ProjectController/showother/1.action">未关闭项目</a></li>
							<li class="nav-item"><a class="nav-link"
								href="/project_management/ProjectController/showother/0.action">结束项目</a></li>
							<li class="nav-item"><a class="nav-link"
								href="/project_management/ProjectController/showall.action">所有项目</a></li>
						</ul>
					</div></li>
				<li class="nav-item"><a class="nav-link" data-toggle="collapse"
					href="#ui-xuqiu" aria-expanded="false" aria-controls="ui-basic">
						<span class="menu-title">产品需求管理</span> <i class="menu-arrow"></i>
						<i class="mdi mdi-crosshairs-gps menu-icon"></i>
				</a>
					<div class="collapse" id="ui-xuqiu">
						<ul class="nav flex-column sub-menu">
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/pages/ui-features/buttons.html">提需求</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/pages/ui-features/typography.html">草稿</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/pages/ui-features/typography.html">激活</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/pages/ui-features/typography.html">已变更</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/pages/ui-features/typography.html">已关闭</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/pages/ui-features/typography.html">所有需求</a></li>
						</ul>
					</div></li>
				<li class="nav-item"><a class="nav-link" data-toggle="collapse"
					href="#ui-xinxi" aria-expanded="false" aria-controls="ui-basic">
						<span class="menu-title">项目信息管理</span> <i class="menu-arrow"></i>
						<i class="mdi mdi-crosshairs-gps menu-icon"></i>
				</a>
					<div class="collapse" id="ui-xinxi">
						<ul class="nav flex-column sub-menu">
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/pages/ui-features/buttons.html">添加项目</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/pages/ui-features/typography.html">正常项目</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/pages/ui-features/typography.html">结束项目</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/pages/ui-features/typography.html">所有项目</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/pages/ui-features/typography.html">项目团队</a></li>
						</ul>
					</div></li>
				<li class="nav-item"><a class="nav-link" data-toggle="collapse"
					href="#ui-renwu" aria-expanded="false" aria-controls="ui-basic">
						<span class="menu-title">项目任务管理</span><i class="menu-arrow"></i> <i
						class="mdi mdi-contacts menu-icon"></i>
				</a>
					<div class="collapse" id="ui-renwu">
						<ul class="nav flex-column sub-menu">
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/pages/ui-features/buttons.html">计划模板管理</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/pages/ui-features/typography.html">计划编制</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/pages/ui-features/typography.html">计划执行</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/pages/ui-features/typography.html">计划变更</a></li>
						</ul>
					</div></li>
				<li class="nav-item"><a class="nav-link" data-toggle="collapse"
					href="#ui-goutong" aria-expanded="false" aria-controls="ui-basic">
						<span class="menu-title">项目沟通管理</span><i class="menu-arrow"></i> <i
						class="mdi mdi-format-list-bulleted menu-icon"></i>
				</a>
					<div class="collapse" id="ui-goutong">
						<ul class="nav flex-column sub-menu">
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/pages/ui-features/buttons.html">项目简报</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/pages/ui-features/typography.html">项目公告</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/pages/ui-features/typography.html">项目问题集</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/pages/ui-features/typography.html">项目讨论区</a></li>
						</ul>
					</div></li>
				<li class="nav-item"><a class="nav-link" data-toggle="collapse"
					href="#ui-kanban" aria-expanded="false" aria-controls="ui-basic">
						<span class="menu-title">项目看板</span><i class="menu-arrow"></i> <i
						class="mdi mdi-chart-bar menu-icon"></i>
				</a>
					<div class="collapse" id="ui-kanban">
						<ul class="nav flex-column sub-menu">
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/pages/ui-features/buttons.html">领导看板</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/pages/ui-features/typography.html">个人看板</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/pages/ui-features/typography.html">项目任务看板</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/pages/ui-features/typography.html">项目里程碑看板</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/pages/ui-features/typography.html">项目计划查询</a></li>
						</ul>
					</div></li>
				<li class="nav-item"><a class="nav-link" data-toggle="collapse"
					href="#ui-xitong" aria-expanded="false" aria-controls="ui-basic">
						<span class="menu-title">系统管理</span><i class="menu-arrow"></i> <i
						class="mdi mdi-table-large menu-icon"></i>
				</a>
					<div class="collapse" id="ui-xitong">
						<ul class="nav flex-column sub-menu">
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/pages/ui-features/buttons.html">基础设置</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/pages/ui-features/typography.html">组织管理</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/pages/ui-features/typography.html">人员管理</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/pages/ui-features/typography.html">角色管理</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/pages/ui-features/typography.html">权限定义</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/pages/ui-features/typography.html">门户配置</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/pages/ui-features/typography.html">日志管理</a></li>
						</ul>
					</div></li>
				<li class="nav-item"><a class="nav-link" data-toggle="collapse"
					href="#general-pages" aria-expanded="false"
					aria-controls="general-pages"> <span class="menu-title">Sample
							Pages</span> <i class="menu-arrow"></i> <i
						class="mdi mdi-medical-bag menu-icon"></i>
				</a>
					<div class="collapse" id="general-pages">
						<ul class="nav flex-column sub-menu">
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/pages/samples/blank-page.html">
									Blank Page </a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/pages/samples/login.html">
									Login </a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/pages/samples/register.html">
									Register </a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/pages/samples/error-404.html">
									404 </a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/pages/samples/error-500.html">
									500 </a></li>
						</ul>
					</div></li>
				<div class="tlinks">
					Collect from <a href="http://www.cssmoban.com/">企业网站模板</a>
				</div>
				<li class="nav-item sidebar-actions"><span class="nav-link">
						<div class="border-bottom">
							<h6 class="font-weight-normal mb-3">Projects</h6>
						</div> <a class="btn btn-block btn-lg btn-gradient-primary mt-4"
						href="${pageContext.request.contextPath}/pages/project/add_project.jsp">立项</a>
						<div class="mt-4">
							<div class="border-bottom">
								<p class="text-secondary">Categories</p>
							</div>
							<ul class="gradient-bullet-list mt-4">
								<li>Free</li>
								<li>Pro</li>
							</ul>
						</div>
				</span></li>
			</ul>
			</nav>


			<!-- partial -->
			<div class="main-panel">
				<div class="content-wrapper">
					<div class="row">
						<div class="col-12"></div>
					</div>
					<div class="page-header">
						<h3 class="page-title">
							<span class="page-title-icon bg-gradient-primary text-white mr-2">
								<i class="mdi mdi-star"></i>
							</span> <a href="#" class="text-primary">项目信息管理</a> /新增项目
						</h3>
						<nav aria-label="breadcrumb">
						<ul class="breadcrumb">
							<li class="breadcrumb-item active" aria-current="page"><span>add</span>
								<i
								class="mdi mdi-bookmark-plus-outline icon-sm text-primary align-middle"></i>

							</li>
						</ul>
						</nav>
					</div>
					<div class="row">
						<div class="col-12 grid-margin">
							<div class="card">
								<div class="card-body">
									<form enctype="multipart/form-data" class="form-sample"
										role="form" method="post"
										action="/project_management/ProjectController/add/${user.userid }.action">
										<div class="row">
											<div class="col-md-6">
												<div class="form-group row">
													<label class="col-sm-3 col-form-label">项目名称</label>
													<div class="col-sm-9">
														<input type="text" id="proname" name="proname"
															class="form-control" />
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group row">
													<label class="col-sm-3 col-form-label">项目经理</label>
													<div class="col-sm-9">
														<select class="form-control" id="userid1" name="userid1">
															<c:forEach items="${userpros1 }" var="item">
																<option value="${item.userid }">${item.username}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6">
												<div class="form-group row">
													<label class="col-sm-3 col-form-label">开始日期</label>
													<div class="col-sm-9">
														<input type="date" class="form-control form-control-lg"
										id="begindate" name="begindate" placeholder="yyyy/MM/dd">
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group row">
													<label class="col-sm-3 col-form-label">结束日期</label>
													<div class="col-sm-9">
														<input type="date" class="form-control form-control-lg"
										id="enddate" name="enddate" placeholder="yyyy/MM/dd">
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6">
												<div class="form-group row">
													<label class="col-sm-3 col-form-label">人月（人力）</label>
													<div class="col-sm-9">
														<input type="text" id="day" name="day" class="form-control" />
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group row">
													<label class="col-sm-3 col-form-label">创建者</label>
													<div class="col-sm-9">
														<input type="text" id="userid2" name="userid2" value="${user.username }"
															class="form-control" readonly/>
													</div>
												</div>
											</div>
										</div>
										<!-- <div class="row">
											<div class="col-md-6">
												<div class="form-group row">
													<label class="col-sm-3 col-form-label">附件</label>
													<div class="col-sm-9">
														<input type="file" id="annex" name="annex"
															class="form-control" />
													</div>
												</div>
											</div>
										</div> -->
										<div class="row">
											<div class="col-md-6">
												<div class="form-group row">
													<label class="col-sm-3 col-form-label">项目描述</label>
													<div class="col-sm-9">
														<script id="editor" type="text/plain"
															style="width:1080px;height:250px;"></script>
													</div>
												</div>
											</div>
										</div>

										<br>
										<br>
										<button type="submit"
											class="btn btn-lg btn-gradient-primary btn-lg mt-4 mr-2">保存</button>
										<button type="reset" class="btn btn-light btn-lg mt-4 mr-2">取消</button>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- main-panel ends -->
		</div>
		<!-- page-body-wrapper ends -->
	</div>
	<!-- container-scroller -->

	<!-- plugins:js -->
	<script
		src="${pageContext.request.contextPath}/vendors/js/vendor.bundle.base.js"></script>
	<script
		src="${pageContext.request.contextPath}/vendors/js/vendor.bundle.addons.js"></script>
	<!-- endinject -->
	<!-- Plugin js for this page-->
	<!-- End plugin js for this page-->
	<!-- inject:js -->
	<script src="${pageContext.request.contextPath}/js/off-canvas.js"></script>
	<script src="${pageContext.request.contextPath}/js/misc.js"></script>
	<!-- endinject -->
	<!-- Custom js for this page-->
	<script src="${pageContext.request.contextPath}/js/dashboard.js"></script>
	<!-- End custom js for this page-->
</body>

</html>
