<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<mata name="viewport content=" width=device-width ", initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<title>여행지 게시판 웹사이트</title>
</head>


<body>

	<%
	String userID = null;
	if(session.getAttribute("userID")!=null){
		userID = (String) session.getAttribute("userID");
	}
	
		
	
	
	%>


	<nav class="navbor">

		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script src="js/bootstrap.js"></script>

		<nav class="navbar navbar-default" id="nav">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="main.jsp">여행지 게시판 웹사이트</a>
			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="main.jsp">메인</a></li>
					<li><a href="${contextPath}/pro17p/board/listArticles.do">게시판</a></li>		<!-- 게시판을 누르면 listArticles.do로 이동해서 게시판이 보임 -->
				</ul>	

				<%
					
					if(userID == null){%>



				<ul class="nav navbar-nav navbar-right">
					<li class="dropown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">접속하기<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="login.jsp">로그인</a></li>		<!-- 로그인을 누르면 login.jsp로 이동 -->
							<li><a href="join.jsp">회원가입</a></li>		<!-- 회원가입을 누르면 join.jsp로 이동 -->
						</ul></li>
				</ul>


				<% 
					} else{
						%>

				<ul class="nav navbar-nav navbar-right">
					<li class="dropown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">회원관리<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="logoutAction.jsp">로그아웃</a></li>	<!-- 로그아웃을 누르면 logoutAction.jsp로 이동 -->

						</ul></li>
				</ul>

				<% 
					}
				
				%>
			</div>
		</nav>
		<div class="container">
			<div class="jumbotron">
				<h2>여행정보 게시판</h2>
				<p style=font-size:13px;>
					본 게시판은 한숨[,]처럼 쉬었다 머물어 갈 수 있는 여행지정보 게시판입니다.<br>
					여러분들의 소중한 하나가 도움이 됩니다<br> 회원가입
					후 로그인을 하셔서 게시판 메뉴로 들어가신 후 글 작성을 하시면 됩니다!
				</p>		<!-- 메인에 보이는 글 -->

			</div>

			<div class="container">
				<div id="myCarousel" class="carousel slide" date-ride="Carousel">
					<ol class="carousel-indicators">
						<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
						<li data-target="#myCarousel" data-slide-to="1"></li>
						<li data-target="#myCarousel" data-slide-to="2"></li>
					</ol>
					<div width class="carousel-inner">
						<div class="item active">
							<img src="images/ddd.png">		<!-- 메인에 있는 사진들 -->
						</div>
						<div class="item">
							<img src="images/fff.png">
						</div>
						<div class="item">
							<img src="images/ggg.png">
						</div>
					</div>
					<a class="left carousel-control" href="#myCarousel"
						data-slide="prev"> <span
						class="glyphicon glyphicon-chevron-left"></span>
					</a> <a class="right carousel-control" href="#myCarousel"
						data-slide="next"> <span
						class="glyphicon glyphicon-chevron-right"></span>
					</a>			<!-- 사진 양쪽에 버튼을 만들어서 누르면 사진을 전, 후로 이동가능  -->

				</div>


			</div>
</body>
</html>