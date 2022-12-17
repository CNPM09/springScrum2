<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%
request.setCharacterEncoding("utf-8");
String context = request.getContextPath();
String baseUrl = request.getContextPath() + "/default/home";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang chủ</title>

    <!-- logo title -->
    <link rel="icon" href="./asset/img/fit-logo.png" type="image/icon type">
    <!-- CSS -->
    <link rel="stylesheet" href="./asset/css/base.css">
    <link rel="stylesheet" href="./asset/css/main.css">
    <!-- icon -->
    <script src="https://kit.fontawesome.com/18ce1a4b11.js" crossorigin="anonymous"></script>
    <!-- Add html khác -->
    <script src="https://unpkg.com/htmlincludejs"></script>
</head>
<body>
<div id="app">
    <jsp:include page="./header/header.jsp" />
    <div class="content">
        <div class="content__menu">
            <div class="menu__header"><h3>Danh mục</h3></div>          
            <ul class="menu__nav">
                <li>
                    <a href="">
                        <i class="fa-solid fa-list-ul"></i>
                        Danh sách
                    </a>
                </li>
                <li>
                    <a href="">
                        <i class="fa-solid fa-user-tie"></i>
                        Thông tin giảng viên
                    </a>
                </li>
                <li>
                    <a href="">
                        <i class="fa-solid fa-user-graduate"></i>
                        Thông tin sinh viên
                    </a>
                </li>
                <li>
                    <a href="">
                        <i class="fa-solid fa-circle-info"></i>
                        Hướng dẫn đăng ký
                    </a>
                </li>
                <li>
                    <a href="">
                        <i class="fa-solid fa-chart-line"></i>
                        Thống kê
                    </a>
                </li>
                <li>
                    <a href="">
                        <i class="fa-solid fa-magnifying-glass"></i>
                        Tìm kiếm
                    </a>
                </li>
                <li>
                    <a href="">
                        <i class="fa-regular fa-address-book"></i>
                        Liên hệ
                    </a>
                </li>
            </ul>

        </div>
        <div class="content__main">
            <div class="content__main__breadcumb">Trang chủ</div>
            <div class="content__main__notify">
                <div class="content__main__notify-header">
                    <i class="bell-icon fa-solid fa-bell"></i>
                    Thông báo
                </div>
                <div class="content__main__notify-list">

                </div>
            </div>
        </div>
    </div>
    <jsp:include page="./footer/footer.jsp" />
    </div>
</body>
</html>