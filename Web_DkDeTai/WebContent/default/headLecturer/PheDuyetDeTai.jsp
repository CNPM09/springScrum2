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
    <title>Quản lý đề tài | Trưởng bộ môn</title>

    <!-- logo title -->
    <link rel="icon" href="./asset/img/fit-logo.png" type="image/icon type">
    <!-- CSS -->
    <link rel="stylesheet" href="../asset/css/base.css">
    <link rel="stylesheet" href="../asset/css/main.css">
    <!-- icon -->
    <script src="https://kit.fontawesome.com/18ce1a4b11.js" crossorigin="anonymous"></script>
    <!-- Add html khác -->
    <script src="https://unpkg.com/htmlincludejs"></script>
</head>

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
                    <a href="<%=context%>/head-lecturer/profile">
                        <i class="fa-solid fa-circle-plus"></i>
                        Thông tin cá nhân
                    </a>
                </li>
                <li>
                    <a href="">
                        <i class="fa-solid fa-users"></i>
                        Tạo đề tài
                    </a>
                </li>
                <li>
                    <a href="">
                        <i class="fa-solid fa-circle-info"></i>
                        Duyệt đề tài
                    </a>
                </li>
                <li>
                    <a href="">
                        <i class="fa-solid fa-circle-info"></i>
                        Phân công phản biện
                    </a>
                </li>
                <li>
                    <a href="">
                        <i class="fa-solid fa-circle-info"></i>
                        Phân công hội đồng
                    </a>
                </li>
                <li>
                    <a href="">
                        <i class="fa-solid fa-circle-info"></i>
                        Xem báo cáo
                    </a>
                </li>
            </ul>
        </div>
        <div class="content__main">
            <div class="content__main__breadcumb">Trang chủ</div>
            <div class="content__main__notify">
                <div class="content__main__notify-header">
                    <i class="bell-icon fa-solid fa-bell"></i>
                    Xét duyệt đề tài
                </div>
                <div class="content__main__notify-list">
                <div class="topic_registration-detail">
								<div class="group_topic_registration-to-manage">
									<table>
										<tr>
											<th width="30%">Tên đề tài</th>
											<th width="20%">Người đề xuất</th>
											<th width="20%">Chuyên ngành</th>
											<th width="10%">Thành viên</th>
											<th width="10%"></th>
											<th width="10%"></th>
										</tr>
									</table>
								</div>
								<c:forEach var="item" items="${topics}">
									<div class="group_topic_registration-to-manage">
										<table>
											<tr>
												<th width="30%" class="highlight_content bold_content">${item.getTopicName()}</th>
												<th width="20%" class="bold_content">${item.getLecturer().getPerson().getFullName()}</th>
												<th width="20%" class="bold_content">${item.getMajor().getMajorName()}</th>
												<th width="10%">4</th>
												<th width="10%"><a
													href="<%=context%>/head-lecturer/approve/detail?topic_id=${item.getTopicId()}">Xem
														chi tiết</a></th>
												<th width="10%"><a
													href="<%=context%>/head-lecturer/approve/accept?topic_id=${item.getTopicId()}"><buton  id="btn" >Duyệt</buton></a></th>
											</tr>
											<script language="javascript">
            var button = document.getElementById("btn");
            button.onclick = function(){
                alert("Duyệt thành công");
            }
        </script>
     
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
    <script>
		if ($('#acceptApprovalTopicStatus').val() == 'success') {
			Swal.fire('Thông báo', 'Duyệt đề tài thành công', 'success');
		}
	</script>
</body>

</html>