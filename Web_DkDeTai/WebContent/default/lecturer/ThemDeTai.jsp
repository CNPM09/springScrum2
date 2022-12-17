<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="DAO.TopicDAO"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Quản lý đề tài | Giảng viên</title>

<!-- logo title -->
<link rel="icon" href="../asset/img/fit-logo.png" type="image/icon type">
<!-- CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/asset/css/base.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/asset/css/main.css">
<!-- icon -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/18ce1a4b11.js"
	crossorigin="anonymous"></script>
<!-- Add html khác -->
<script src="https://unpkg.com/htmlincludejs"></script>
</head>
<%
String context = request.getContextPath();
TopicDAO td = new TopicDAO();
String check = (String) request.getAttribute("isAdded");
%>
<%
if (check != null) {
%>
<script type="text/javascript">
	$(document).ready(function() {
		$("#addTopicSuccess").modal('show');
	});
</script>
<%
}
%>
<style>
.form-label {
	color: var(- -text-blue-color);
}

.topic_registration {
	display: flex;
	align-items: center;
	height: 48px;
	background-color: var(- -login-background-color);
	border: 1px solid var(- -border-color);
}

.topic_registration ion-icon {
	font-size: 2rem;
	color: var(- -text-blue-color);
	padding: 0 8px;
}

.topic_registration h3 {
	color: var(- -text-blue-color);
	font-size: 1.6rem;
	font-weight: 600;
}

#btn {
	margin-top: 12px;
	border-radius: 6px;
	background-color: #059862;
	color: var(- -white-color)
}

.topic_registration-filter {
	display: flex;
	height: 40px;
	align-items: center;
	background-color: var(- -login-background-color);
	margin-top: 12px;
	border: 1px solid var(- -border-color);
	padding-left: 8px;
}

.topic_registration-filter h3 {
	color: var(- -text-blue-color);
	font-size: 1.6rem;
	font-weight: 600;
	border: 1px solid var(- -text-blue-color);
	border-radius: 6px;
	padding: 2px 8px;
	cursor: pointer;
	margin: 0;
}

.topic_registration-filter-active {
	background-color: var(- -white-color);
}

.topic_registration-filter h3 {
	margin-right: 24px;
	margin-top: 0;
}

/* avai */
.group_topic_registration-to-manage {
	border: 1px solid var(- -nav-background-color);
	margin-top: 12px;
	padding-left: 8px;
}

.group_topic_registration-to-manage:first-child {
	background-color: var(- -nav-border-color);
	border-radius: 4px;
}

.group_topic_registration-to-manage table tr th {
	text-align: start;
}

.group_topic_registration-to-manage:first-child table tr th {
	color: var(- -white-color);
	font-weight: 500;
}

table {
	width: 100%;
}

table tr th {
	color: var(- -text-color);
	font-size: 1.6rem;
	font-weight: 400;
	height: 32px;
	padding-right: 10px;
}

table tr th a {
	text-decoration: none;
	font-size: 1.6rem;
	font-weight: 400;
}

.highlight_content {
	color: #A555EE;
}

.hide_element {
	visibility: hidden;
}
</style>

<body>
	<!-- Header -->
	<!--<div id="header"></div>
    script src="./asset/script/header-import.js"></script>-->
	<jsp:include page="../header/header.jsp" />

	<!-- Content -->
	<div class="content">
		<!-- Menu -->
		<div class="content__menu">
			<div class="menu__header">
				<h3>Danh mục</h3>
			</div>
			<ul class="menu__nav">
				<li><a href="<%=context%>/lecturer/profile"> <i
						class="fa-solid fa-circle-plus"></i> Thông tin cá nhân
				</a></li>
				<li><a href="<%=context%>/lecturer/manage-topic"> <i
						class="fa-solid fa-users"></i> Quản lý đề tài
				</a></li>
				<li><a href=""> <i class="fa-solid fa-circle-info"></i>
						Chấm điểm đề tài
				</a></li>
			</ul>
		</div>
		<div class="content__main">
			<div class="content__main__breadcumb">Trang chủ</div>
			<div class="content__main__notify">
				<div class="content__main__notify-header">
					<i class="layer-group-icons fa-solid fa-layer-group"></i> Quản lý
					đề tài			
				</div>
				<div class="form-horizontal mt-3 d-flex justify-content-center ">
					<div class="col col-lg-6">
						<form class="" href=<%=context%>/lecturer/manage-topic/add" method="post" style="margin-top:2rem">
							<label for="site-title" class="form-label form_label">Mã
								đề tài</label> <input type="text" name="topicId" class="form-control"
								value="<%=td.randomId()%>" hidden /> 
								<label for="site-title"
								class="form-label form_label">Mã Giảng viên</label> 
								<input
								type="text" name="lecturerId" class="form-control"
								value="${lecturer.lecturerId}" hidden />
							<div class="mb-3">
								<label for="site-title" class="form-label form_label">Thời gian </label> <input type="text" name="registrationPeriodId"
									class="form-control form-control-lg"
									value="${period.registrationPeriodId}" disabled />
							</div>
							<div class="mb-3">
								<label for="site-title" class="form-label">Chuyên ngành
								</label> <input type="text" name="registrationPeriodName"
									class="form-control form-control-lg"
									value="${lecturer.major.majorId}" disabled />
							</div>

							<div class="mb-3">
								<label for="site-title" class="form-label">Tên đề tài </label> <input
									type="text" name="topicName"
									class="form-control form-control-lg" />
							</div>

							<div class="mb-3">
								<label for="site-title" class="form-label">Số lượng
									thành viên </label> <input type="number" min="2" max="3"
									name="numberOfMember" class="form-control form-control-lg" />
							</div>
							<div class="mb-3">
								<label for="site-title" class="form-label">Mô tả</label>
								<textarea class="form-control" name="topicDescription" rows="4"></textarea>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button id="btn" type="submit" class="btn btn-default">Thêm
										đề tài</button>
								</div>
							</div>
							<script language="javascript">
								var button = document.getElementById("btn");
								button.onclick = function() {
									alert("Thêm thành công");
								}
							</script>
							</form>
					</div>

				</div>
			</div>
		</div>
	</div>


	<jsp:include page="../footer/footer.jsp" />
	<!-- Footer -->
	<!--<div id="footer"></div>
    <script src="./asset/script/footer-import.js"></script>-->
</body>
</html>