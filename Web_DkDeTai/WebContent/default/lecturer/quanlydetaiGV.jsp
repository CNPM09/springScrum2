<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
 <% String context = request.getContextPath(); request.setCharacterEncoding("utf-8"); %>
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
    <link rel="stylesheet" href="../asset/css/base.css">
    <link rel="stylesheet" href="../asset/css/main.css">
    <!-- icon -->
    <script src="https://kit.fontawesome.com/18ce1a4b11.js" crossorigin="anonymous"></script>
    <!-- Add html khác -->
    <script src="https://unpkg.com/htmlincludejs"></script>
</head>
<style>
.topic_registration {
    display: flex;
    align-items: center;
    height: 48px;
    background-color: var(--login-background-color);
    border: 1px solid var(--border-color);
}

.topic_registration ion-icon {
    font-size: 2rem;
    color: var(--text-blue-color);
    padding: 0 8px;
}

.topic_registration h3 {
    color: var(--text-blue-color);
    font-size: 1.6rem;
    font-weight: 600;
}

.topic_registration-filter {
    display: flex;
    height: 40px;
    align-items: center;
    background-color: var(--login-background-color);
    margin-top: 12px;
    border: 1px solid var(--border-color);
    padding-left: 8px;
}

.topic_registration-filter h3 {
    color: var(--text-blue-color);
    font-size: 1.6rem;
    font-weight: 600;
    border: 1px solid var(--text-white-color);
    border-radius: 6px;
    padding: 2px 8px;
    cursor: pointer;
    margin: 0;
}

.topic_registration-filter-active {
    background-color: #ECF8FC;
}

.topic_registration-filter h3 {
    margin-right: 24px;
    margin-top: 0;
}

/* avai */
.group_topic_registration-to-manage {
    border: 1px solid var(--nav-background-color);
    margin-top: 12px;
    padding-left: 8px;
}

.group_topic_registration-to-manage:first-child {
    background-color: var(--nav-border-color);
    border-radius: 4px;
}

.group_topic_registration-to-manage table tr th {
    text-align: start;
}

.group_topic_registration-to-manage:first-child table tr th {
    color: var(--blue-color);
    font-weight: 500;
}

table {
    width: 100%;
}

table tr th {
    color: var(--text-color);
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
                <li>
                    <a href="<%=context%>/lecturer/profile" >
                        <i class="fa-solid fa-circle-plus"></i>
                        Thông tin cá nhân
                    </a>
                </li>
                <li>
                    <a href="<%=context%>/lecturer/manage-topic">
                        <i class="fa-solid fa-users"></i>
                        Quản lý đề tài
                    </a>
                </li>
                <li>
                    <a href="">
                        <i class="fa-solid fa-circle-info"></i>
                        Chấm điểm đề tài
                    </a>
                </li>
            </ul>
        </div>
        <div class="content__main">
            <div class="content__main__breadcumb">Trang chủ</div>
            <div class="content__main__notify">
                <div class="content__main__notify-header">
                    <i class="layer-group-icons fa-solid fa-layer-group"></i>
                    Quản lý đề tài
                </div>
                <div class="topic_registration-detail">
                <div class="topic_registration-filter d-flex">
								<div class="mx-2">
									<h3 class="topic_registration-filter-active">
										<a href="<%=context%>/lecturer/manage-topic?select=0">Chưa
											được đăng ký</a>
									</h3 class="topic_registration-filter-active">
								</div>
								<div class="mx-2">
									<h3 class="">
										<a href="<%=context%>/lecturer/manage-topic?select=1">Đã
											được đăng ký</a>
									</h3>
								</div>

								<div class="mx-2">
									<h3 class="">
										<a href="<%=context%>/lecturer/manage-topic?status=0">Chưa
											được duyệt </a>
									</h3>
								</div>

								<div class="mx-2">
									<h3 class="">
										<a href="<%=context%>/lecturer/manage-topic/add">Thêm đề
											tài</a>
									</h3>
								</div>
							</div>
								<div class="">
									<table>
										<tr>
											<th width="30%">Tên đề tài</th>
											<th width="25%">Thời gian</th>
											<th width="25%">Năm học</th>
											<th class="hide_element" width="10%">Xem chi tiết</th>
											<th class="hide_element" width="10%">Xem chi tiết</th>
										</tr>
									</table>
								</div>
								<c:forEach var="topic" items="${topics}">
									<div class="group_topic_registration-to-manage">
										<table>
											<tr>
												<th width="30%" class="highlight_content">${topic.topicName}</th>
												<th width="25%">${topic.registrationperiod.registrationPeriodName}</th>
												<th width="25%">${topic.registrationperiod.schoolYear}</th>
												<th width="10%"><a
													href="<%=context%>/lecturer/manage-topic/delete?topic=${topic.getTopicId()}"
													class="highlight_content">Xóa</a></th>
											</tr>
										</table>
									</div>
								</c:forEach>
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