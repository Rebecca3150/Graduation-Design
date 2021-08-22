<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>职员注册</title>
<!-- plugins:css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/vendors/iconfonts/mdi/css/materialdesignicons.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/vendors/css/vendor.bundle.base.css">
<!-- endinject -->
<!-- plugin css for this page -->
<!-- End plugin css for this page -->
<!-- inject:css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<!-- endinject -->
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/images/favicon.png" />
</head>

<body>
	<div class="container-scroller">
		<div class="container-fluid page-body-wrapper full-page-wrapper">
			<div class="content-wrapper d-flex align-items-center auth">
				<div class="row w-100">
					<div class="col-lg-7 mx-auto">
						<div class="auth-form-light text-left p-5">
							<div class="brand-logo">
								<img src="${pageContext.request.contextPath}/images/logo.svg">
							</div>
							<!-- 获取status-状态值 -->
							<c:if test="${status==0}">
								<div class='alert alert-success' role='alert'>注册成功</div>
							</c:if>
							<c:if test="${status==1}">
								<div class="alert alert-danger" role="alert">注册失败</div>
							</c:if>
							<h4>注册新用户</h4>
							<h6 class="font-weight-light">注册很简单，只需几步</h6>
							<form class="pt-3" role="form" method="post"
								action="/project_management/UserController/register.action">
								<div class="form-group">
									<input type="text" class="form-control form-control-lg"
										id="username" name="username" placeholder="真实姓名">
								</div>
								<div class="form-group">
									<input type="password" class="form-control form-control-lg"
										id="password" name="password" placeholder="密码">
								</div>
								<div class="form-group">
									<input type="password" class="form-control form-control-lg"
										id="password2" name="password2" placeholder="确认密码">
								</div>
								<div class="form-group">
									<select class="form-control form-control-lg" id="gender"
										name="gender">
										<option>性别</option>
										<option>男</option>
										<option>女</option>
									</select>
								</div>
								<div class="form-group">
									<input type="text" class="form-control form-control-lg"
										id="job" name="job" placeholder="职位">
								</div>
								<div class="form-group">
									<select class="form-control form-control-lg" id="role"
										name="role">
										<option>角色</option>
										<option>开发人员</option>
										<option>数据库人员</option>
										<option>制图人员</option>
										<option>测试人员</option>
										<option>项目经理</option>
									</select>
								</div>
								<div class="form-group">
									<input type="email" class="form-control form-control-lg"
										id="email" name="email" placeholder="邮件">
								</div>
								<div class="form-group">
									<input type="tel" class="form-control form-control-lg"
										id="phone" name="phone" placeholder="手机">
								</div>
								<div class="form-group">
									<input type="number" class="form-control form-control-lg"
										id="age" name="age" placeholder="年龄">
								</div>
								<div class="form-group">
									<input type="text" class="form-control form-control-lg"
										id="address" name="address" placeholder="住址">
								</div>
								<div class="form-group">
									<input type="number" class="form-control form-control-lg"
										id="number" name="number" placeholder="身份证号">
								</div>
								<div class="form-group">
									出生日期
								</div>
								<div class="form-group">
									<input type="date" class="form-control form-control-lg"
										id="birth" name="birth" placeholder="出生日期  yyyy/MM/dd">
								</div>
								<div class="mb-4">
									<div class="form-check">
										<label class="form-check-label text-muted"> <input
											type="checkbox" class="form-check-input">
										</label> &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;我同意所有条款和协议&nbsp;&nbsp;<a
											href="login.jsp" data-toggle="modal" data-target="#pro1"
											class="text-primary">查看</a>
										<!-- 模态框 -->
										<div class="modal fade" id="pro1" tabindex="-1" role="dialog"
											aria-labelledby="myModalLabel" aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="card">
														<div class="modal-header">
															条款和协议
															<button type="button" class="close" data-dismiss="modal"
																aria-hidden="true">×</button>
														</div>
														<div class="modal-body">
															<!-- 文字 -->
															<div class="form-group">
																<div class="input-group">协议内容.................</div>
															</div>

														</div>
													</div>
												</div>
												<!-- /.modal-content -->
											</div>
											<!-- /.modal-dialog -->
										</div>
										<!-- /.modal -->
									</div>
								</div>
								<div class="mt-3">
									<button type="submit"
										class="btn btn-block btn-gradient-primary btn-lg font-weight-medium auth-form-btn">确认注册</button>
								</div>
								<div class="text-center mt-4 font-weight-light">
									已经注册过？ <a href="${pageContext.request.contextPath}/pages/basic/login.jsp" class="text-primary">登录</a>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<!-- content-wrapper ends -->
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
	<!-- inject:js -->
	<script src="${pageContext.request.contextPath}/js/off-canvas.js"></script>
	<script src="${pageContext.request.contextPath}/js/misc.js"></script>
	<!-- endinject -->
</body>

</html>
