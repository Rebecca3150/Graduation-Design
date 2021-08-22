<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>职员登录</title>
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
					<div class="col-lg-4 mx-auto">
						<div class="auth-form-light text-left p-5">
							<div class="brand-logo">
								<img src="${pageContext.request.contextPath}/images/logo.svg">
							</div>
							<h4>你好！让我们开始吧</h4>
							<h6 class="font-weight-light">登录以继续......</h6>
							<form class="pt-3" role="form" method="post"
								action="/project_management/UserController/login.action">
								<!-- 获取status-状态值 -->
								<c:if test="${status==0}">
									<div class='alert alert-danger' role='alert'>用户名为空！</div>
								</c:if>
								<c:if test="${status==1}">
									<div class="alert alert-danger" role="alert">密码为空！</div>
								</c:if>
								<c:if test="${status==2}">
									<div class="alert alert-danger" role="alert">此用户不存在！</div>
								</c:if>
								<c:if test="${status==3}">
									<div class="alert alert-danger" role="alert">密码错误，请重新输入！</div>
								</c:if>
								<div class="form-group">
									<input type="email" class="form-control form-control-lg"
										id="email" name="email" placeholder="邮箱">
								</div>
								<div class="form-group">
									<input type="password" class="form-control form-control-lg"
										id="password" name="password" placeholder="密码">
								</div>
								<div class="mt-3">
									<button type="submit"
										class="btn btn-block btn-gradient-primary btn-lg font-weight-medium auth-form-btn">登录</button>
								</div>
								<div
									class="my-2 d-flex justify-content-between align-items-center">
									<div class="form-check">
										<label class="form-check-label text-muted"> <input
											type="checkbox" class="form-check-input"> 记住我
										</label>
									</div>
									<a href="#" class="auth-link text-black">忘记密码？</a>
								</div>
								<div class="mb-2">
									<button type="button"
										class="btn btn-block btn-facebook auth-form-btn">
										<i class="mdi mdi-cellphone-iphone"></i>手机直接登录
									</button>
								</div>
								<div class="text-center mt-4 font-weight-light">
									还没有账户? <a href="register.jsp" class="text-primary">注册</a>
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
	<script src="../../vendors/js/vendor.bundle.base.js"></script>
	<script src="../../vendors/js/vendor.bundle.addons.js"></script>
	<!-- endinject -->
	<!-- inject:js -->
	<script src="../../js/off-canvas.js"></script>
	<script src="../../js/misc.js"></script>
	<!-- endinject -->
</body>

</html>
