<%--<%@ page language="java" contentType="text/html; charset=UTF-8"--%>
<%--		 pageEncoding="UTF-8"%>--%>
<%--<%@include file="/common/taglib.jsp"%>--%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--<html>--%>
<%--<head>--%>
<%--	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>
<%--	<title>Đăng nhập</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="container">--%>
<%--	<!-- <h1 class="form-heading">login Form</h1> -->--%>
<%--	<div class="login-form">--%>
<%--		<div class="main-div">--%>
<%--			<c:if test="${param.incorrectAccount != null}">--%>
<%--				<div class="alert alert-danger">--%>
<%--					Username or password incorrect--%>
<%--				</div>--%>
<%--			</c:if>--%>
<%--			<c:if test="${param.accessDenied != null}">--%>
<%--				<div class="alert alert-danger">--%>
<%--					you Not authorize--%>
<%--				</div>--%>
<%--			</c:if>--%>
<%--			<c:if test="${param.sessionTimeout != null}">--%>
<%--				<div class="alert alert-danger">--%>
<%--					session timeout--%>
<%--				</div>--%>
<%--			</c:if>--%>
<%--			<form action="j_spring_security_check" id="formLogin" method="post">--%>
<%--				<div class="form-group">--%>
<%--					<input type="text" class="form-control" id="userName" name="j_username" placeholder="Tên đăng nhập">--%>
<%--				</div>--%>

<%--				<div class="form-group">--%>
<%--					<input type="password" class="form-control" id="password" name="j_password" placeholder="Mật khẩu">--%>
<%--				</div>--%>
<%--				<button type="submit" class="btn btn-primary" >Đăng nhập</button>--%>
<%--			</form>--%>
<%--		</div>--%>
<%--	</div>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Đăng nhập</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<style>
		body {
			background-color: #f8f9fa;
			font-family: Arial, sans-serif;
		}
		.container {
			margin-top: 100px;
		}
		.login-form {
			max-width: 400px;
			margin: auto;
			padding: 20px;
			background-color: #fff;
			border-radius: 5px;
			box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);
		}
		.form-heading {
			text-align: center;
			margin-bottom: 30px;
		}
		.form-group {
			margin-bottom: 20px;
		}
		.form-control {
			border-radius: 5px;
		}
		.btn-primary {
			border-radius: 5px;
			width: 100%;
		}
		.alert {
			margin-bottom: 20px;
			border-radius: 5px;
		}
		.alert-danger {
			background-color: #f8d7da;
			color: #721c24;
			border: 1px solid #f5c6cb;
		}
	</style>
</head>
<body>
<div class="container">
	<div class="login-form">
		<!-- <h1 class="form-heading">login Form</h1> -->
		<div class="main-div">
			<c:if test="${param.incorrectAccount != null}">
				<div class="alert alert-danger">
					Tên đăng nhập hoặc mật khẩu không chính xác
				</div>
			</c:if>
			<c:if test="${param.accessDenied != null}">
				<div class="alert alert-danger">
					Bạn không được phép truy cập
				</div>
			</c:if>
			<c:if test="${param.sessionTimeout != null}">
				<div class="alert alert-danger">
					Hết thời gian phiên làm việc
				</div>
			</c:if>
			<form action="j_spring_security_check" id="formLogin" method="post">
				<div class="form-group">
					<input type="text" class="form-control" id="userName" name="j_username" placeholder="Tên đăng nhập">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" id="password" name="j_password" placeholder="Mật khẩu">
				</div>
				<button type="submit" class="btn btn-primary">Đăng nhập</button>
			</form>
		</div>
	</div>
</div>
</body>
</html>
