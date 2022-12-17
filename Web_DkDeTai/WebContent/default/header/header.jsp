<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<% String context = request.getContextPath(); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Header</title>

    <!-- logo title -->
    <link rel="icon" href="${pageContext.request.contextPath}/asset/img/fit-logo.png" type="image/icon type">
    <!-- CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/asset/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/asset/css/header-styles.css">
    <!-- icon -->
    <script src="https://kit.fontawesome.com/18ce1a4b11.js" crossorigin="anonymous"></script>
</head>
<style>
.header_login-container {
    display: flex;
    align-items: center;
    justify-content: flex-end;
}
.header_login-container div {
    font-size: 1.5rem;
    font-weight: 400;
    margin-right: 24px;
    color: #FFFF00;
}

.login-name__container {
	display: flex;
	justify-content: flex-end;
}
</style>
<body>
    <header class="header">
        <div class="header__container">
            <div class="header__container--left">
                <img src="${pageContext.request.contextPath}/asset/img/UTE.png" alt="HCMUTE logo" class="header__container-logo">
                <div class="header__container-content">
                    <h1 class="header__container-content-VNName">
                        TRƯỜNG ĐẠI HỌC SƯ PHẠM KỸ THUẬT TP.HCM
                    </h1>
                    <h2 class="header__container-content-EnglishName">
                        HCM University of Teachnology and Education
                    </h2>
                    <h2 class="header__container-content-line">
                        ____________________________________
                    </h2>
                    <h1 class="header__container-content-departmentName">
                        Khoa Công nghệ Thông tin
                    </h1>
                </div>
            </div>
            <div class="header__container--right">
                <img src="../asset/img/Banner_1.png" alt="">
                <img src="${pageContext.request.contextPath}/asset/img/Banner_1.png" alt="">
            </div>
        </div>
        <div class="header__nav">
            <div class="header__nav-home">
                <i class="header__nav-icon fa-solid fa-house"></i>
                <p class="header__nav-text mr-left-1rem">Trang chủ</p>
            </div>
            <div class="header__nav-login">	  
			         <c:if test="${not empty sessionScope.person}">
			          <i class="header__nav-icon fa-solid fa-right-to-bracket"></i>
			         <a id="header-login" href="<%=context%>/logout">
			            <p class="header__nav-text mr-left-1rem">Đăng xuất</p>
			              </a>
			        </c:if>
			 
			      <c:if test="${empty sessionScope.person}">
			       <i class="header__nav-icon fa-solid fa-right-to-bracket"></i>
			           <a id="header-login" href="<%=context%>/login">
                			<p class="header__nav-text mr-left-1rem">Đăng nhập</p>
                			 </a>
                			 </c:if>
               			
               			 
            </div>
        </div>
    </header>
</body>
</html>