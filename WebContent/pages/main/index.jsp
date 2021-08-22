<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
</head>
<body>
	<div class="container-scroller">
		<!-- partial:partials/_navbar.html -->
		<nav
			class="navbar default-layout-navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
		<div
			class="text-center navbar-brand-wrapper d-flex align-items-center justify-content-center">
			<a class="navbar-brand brand-logo"
				href="${pageContext.request.contextPath}/pages/main/index.jsp"><img
				src="${pageContext.request.contextPath}/images/logo.svg" alt="logo" /></a>
			<a class="navbar-brand brand-logo-mini"
				href="${pageContext.request.contextPath}/pages/main/index.jsp"><img
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
						<input type="text" id="" name=""
							class="form-control bg-transparent border-0"
							placeholder="查询产品/项目/文档...">
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
						<a class="dropdown-item"
							href="/project_management/LogController/showlog/${user.userid }.action">
							<i class="mdi mdi-cached mr-2 text-success"></i> 活动日志
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
					href="#ui-project" aria-expanded="false" aria-controls="ui-basic">
						<span class="menu-title">项目信息管理</span> <i class="menu-arrow"></i>
						<i class="mdi mdi-crosshairs-gps menu-icon"></i>
				</a>
					<div class="collapse" id="ui-project">
						<ul class="nav flex-column sub-menu">
							<li class="nav-item"><a class="nav-link"
								href="/project_management/ProjectController/addBefore.action">项目立项</a></li>
							<li class="nav-item"><a class="nav-link"
								href="/project_management/ProjectController/showother/1.action">未关闭项目</a></li>
							<li class="nav-item"><a class="nav-link"
								href="/project_management/ProjectController/showother/0.action">结束项目</a></li>
							<li class="nav-item"><a class="nav-link"
								href="/project_management/ProjectController/showall.action">所有项目</a></li>
						</ul>
					</div></li>
				<li class="nav-item"><a class="nav-link" data-toggle="collapse"
					href="#ui-plan" aria-expanded="false" aria-controls="ui-basic">
						<span class="menu-title">项目计划管理</span><i class="menu-arrow"></i> <i
						class="mdi mdi-contacts menu-icon"></i>
				</a>
					<div class="collapse" id="ui-plan">
						<ul class="nav flex-column sub-menu">
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/pages/ui-features/buttons.html">计划模板管理</a></li>
							<li class="nav-item"><a class="nav-link"
								href="/project_management/PlanController/addBefore.action">计划编制</a></li>
							<li class="nav-item"><a class="nav-link"
								href="/project_management/PlanController/showBefore.action">计划执行</a></li>
							<li class="nav-item"><a class="nav-link"
								href="/project_management/PlanController/updateBefore.action">计划变更</a></li>
						</ul>
					</div></li>
				<li class="nav-item"><a class="nav-link" data-toggle="collapse"
					href="#ui-task" aria-expanded="false" aria-controls="ui-basic">
						<span class="menu-title">项目任务管理</span><i class="menu-arrow"></i> <i
						class="mdi mdi-contacts menu-icon"></i>
				</a>
					<div class="collapse" id="ui-task">
						<ul class="nav flex-column sub-menu">
							<li class="nav-item"><a class="nav-link"
								href="/project_management/TaskController/addSingleBefore.action">新建任务</a></li>
							<li class="nav-item"><a class="nav-link"
								href="/project_management/TaskController/addAllBefore.action">批量创建</a></li>
							<li class="nav-item"><a class="nav-link"
								href="/project_management/TaskController/showBefore.action">任务执行</a></li>
						</ul>
					</div></li>
				<li class="nav-item"><a class="nav-link" data-toggle="collapse"
					href="#ui-talk" aria-expanded="false" aria-controls="ui-basic">
						<span class="menu-title">项目沟通管理</span><i class="menu-arrow"></i> <i
						class="mdi mdi-format-list-bulleted menu-icon"></i>
				</a>
					<div class="collapse" id="ui-talk">
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
								href="/project_management/TeamController/teamBefore.action">团队管理</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/pages/ui-features/typography.html">人员管理</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/pages/ui-features/typography.html">权限管理</a></li>
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
								<i class="mdi mdi-home"></i>
							</span>首页
						</h3>
						<nav aria-label="breadcrumb">
						<ul class="breadcrumb">
							<li class="breadcrumb-item active" aria-current="page"><span></span>Overview
								<i
								class="mdi mdi-alert-circle-outline icon-sm text-primary align-middle"></i>
							</li>
						</ul>
						</nav>
					</div>
					<%-- 				<div class="row">
						<div class="col-md-4 stretch-card grid-margin">
							<div class="card bg-gradient-danger card-img-holder text-white">
								<div class="card-body">
									<img
										src="${pageContext.request.contextPath}/images/dashboard/circle.svg"
										class="card-img-absolute" alt="circle-image" />
									<h4 class="font-weight-normal mb-3">
										待完成<i class="mdi mdi-chart-line mdi-24px float-right"></i>
									</h4>
									<h2 class="mb-5">$ 15,0000</h2>
									<h6 class="card-text">Increased by 60%</h6>
								</div>
							</div>
						</div>
						<div class="col-md-4 stretch-card grid-margin">
							<div class="card bg-gradient-info card-img-holder text-white">
								<div class="card-body">
									<img
										src="${pageContext.request.contextPath}/images/dashboard/circle.svg"
										class="card-img-absolute" alt="circle-image" />
									<h4 class="font-weight-normal mb-3">
										已完成 <i class="mdi mdi-bookmark-outline mdi-24px float-right"></i>
									</h4>
									<h2 class="mb-5">45,6334</h2>
									<h6 class="card-text">Decreased by 10%</h6>
								</div>
							</div>
						</div>
						<div class="col-md-4 stretch-card grid-margin">
							<div class="card bg-gradient-success card-img-holder text-white">
								<div class="card-body">
									<img
										src="${pageContext.request.contextPath}/images/dashboard/circle.svg"
										class="card-img-absolute" alt="circle-image" />
									<h4 class="font-weight-normal mb-3">
										交付 <i class="mdi mdi-diamond mdi-24px float-right"></i>
									</h4>
									<h2 class="mb-5">95,5741</h2>
									<h6 class="card-text">Increased by 5%</h6>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-7 grid-margin stretch-card">
							<div class="card">
								<div class="card-body">
									<div class="clearfix">
										<h4 class="card-title float-left">Visit And Sales
											Statistics</h4>
										<div id="visit-sale-chart-legend"
											class="rounded-legend legend-horizontal legend-top-right float-right"></div>
									</div>
									<canvas id="visit-sale-chart" class="mt-4"></canvas>
								</div>
							</div>
						</div>
						<div class="col-md-5 grid-margin stretch-card">
							<div class="card">
								<div class="card-body">
									<h4 class="card-title">Traffic Sources</h4>
									<canvas id="traffic-chart"></canvas>
									<div id="traffic-chart-legend"
										class="rounded-legend legend-vertical legend-bottom-left pt-4"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-12 grid-margin">
							<div class="card">
								<div class="card-body">
									<h4 class="card-title">Recent Tickets</h4>
									<div class="table-responsive">
										<table class="table">
											<thead>
												<tr>
													<th>Assignee</th>
													<th>Subject</th>
													<th>Status</th>
													<th>Last Update</th>
													<th>Tracking ID</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td><img
														src="${pageContext.request.contextPath}/images/faces/face1.jpg"
														class="mr-2" alt="image"> David Grey</td>
													<td>Fund is not recieved</td>
													<td><label class="badge badge-gradient-success">DONE</label>
													</td>
													<td>Dec 5, 2017</td>
													<td>WD-12345</td>
												</tr>
												<tr>
													<td><img
														src="${pageContext.request.contextPath}/images/faces/face2.jpg"
														class="mr-2" alt="image"> Stella Johnson</td>
													<td>High loading time</td>
													<td><label class="badge badge-gradient-warning">PROGRESS</label>
													</td>
													<td>Dec 12, 2017</td>
													<td>WD-12346</td>
												</tr>
												<tr>
													<td><img
														src="${pageContext.request.contextPath}/images/faces/face3.jpg"
														class="mr-2" alt="image"> Marina Michel</td>
													<td>Website down for one week</td>
													<td><label class="badge badge-gradient-info">ON
															HOLD</label></td>
													<td>Dec 16, 2017</td>
													<td>WD-12347</td>
												</tr>
												<tr>
													<td><img
														src="${pageContext.request.contextPath}/images/faces/face4.jpg"
														class="mr-2" alt="image"> John Doe</td>
													<td>Loosing control on server</td>
													<td><label class="badge badge-gradient-danger">REJECTED</label>
													</td>
													<td>Dec 3, 2017</td>
													<td>WD-12348</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-12 grid-margin stretch-card">
							<div class="card">
								<div class="card-body">
									<h4 class="card-title">Recent Updates</h4>
									<div class="d-flex">
										<div
											class="d-flex align-items-center mr-4 text-muted font-weight-light">
											<i class="mdi mdi-account-outline icon-sm mr-2"></i> <span>jack
												Menqu</span>
										</div>
										<div
											class="d-flex align-items-center text-muted font-weight-light">
											<i class="mdi mdi-clock icon-sm mr-2"></i> <span>October
												3rd, 2018</span>
										</div>
									</div>
									<div class="row mt-3">
										<div class="col-6 pr-1">
											<img
												src="${pageContext.request.contextPath}/images/dashboard/img_1.jpg"
												class="mb-2 mw-100 w-100 rounded" alt="image"> <img
												src="${pageContext.request.contextPath}/images/dashboard/img_4.jpg"
												class="mw-100 w-100 rounded" alt="image">
										</div>
										<div class="col-6 pl-1">
											<img
												src="${pageContext.request.contextPath}/images/dashboard/img_2.jpg"
												class="mb-2 mw-100 w-100 rounded" alt="image"> <img
												src="${pageContext.request.contextPath}/images/dashboard/img_3.jpg"
												class="mw-100 w-100 rounded" alt="image">
										</div>
									</div>
									<div class="d-flex mt-5 align-items-top">
										<img
											src="${pageContext.request.contextPath}/images/faces/face3.jpg"
											class="img-sm rounded-circle mr-3" alt="image">
										<div class="mb-0 flex-grow">
											<h5 class="mr-2 mb-2">School Website - Authentication
												Module.</h5>
											<p class="mb-0 font-weight-light">It is a long
												established fact that a reader will be distracted by the
												readable content of a page.</p>
										</div>
										<div class="ml-auto">
											<i class="mdi mdi-heart-outline text-muted"></i>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 grid-margin stretch-card">
							<div class="card">
								<div class="card-body">
									<h4 class="card-title">Project Status</h4>
									<div class="table-responsive">
										<table class="table">
											<thead>
												<tr>
													<th>#</th>
													<th>Name</th>
													<th>Due Date</th>
													<th>Progress</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>1</td>
													<td>Herman Beck</td>
													<td>May 15, 2015</td>
													<td>
														<div class="progress">
															<div class="progress-bar bg-gradient-success"
																role="progressbar" style="width: 25%" aria-valuenow="25"
																aria-valuemin="0" aria-valuemax="100"></div>
														</div>
													</td>
												</tr>
												<tr>
													<td>2</td>
													<td>Messsy Adam</td>
													<td>Jul 01, 2015</td>
													<td>
														<div class="progress">
															<div class="progress-bar bg-gradient-danger"
																role="progressbar" style="width: 75%" aria-valuenow="75"
																aria-valuemin="0" aria-valuemax="100"></div>
														</div>
													</td>
												</tr>
												<tr>
													<td>3</td>
													<td>John Richards</td>
													<td>Apr 12, 2015</td>
													<td>
														<div class="progress">
															<div class="progress-bar bg-gradient-warning"
																role="progressbar" style="width: 90%" aria-valuenow="90"
																aria-valuemin="0" aria-valuemax="100"></div>
														</div>
													</td>
												</tr>
												<tr>
													<td>4</td>
													<td>Peter Meggik</td>
													<td>May 15, 2015</td>
													<td>
														<div class="progress">
															<div class="progress-bar bg-gradient-primary"
																role="progressbar" style="width: 50%" aria-valuenow="50"
																aria-valuemin="0" aria-valuemax="100"></div>
														</div>
													</td>
												</tr>
												<tr>
													<td>5</td>
													<td>Edward</td>
													<td>May 03, 2015</td>
													<td>
														<div class="progress">
															<div class="progress-bar bg-gradient-danger"
																role="progressbar" style="width: 35%" aria-valuenow="35"
																aria-valuemin="0" aria-valuemax="100"></div>
														</div>
													</td>
												</tr>
												<tr>
													<td>5</td>
													<td>Ronald</td>
													<td>Jun 05, 2015</td>
													<td>
														<div class="progress">
															<div class="progress-bar bg-gradient-info"
																role="progressbar" style="width: 65%" aria-valuenow="65"
																aria-valuemin="0" aria-valuemax="100"></div>
														</div>
													</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div> --%>
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
