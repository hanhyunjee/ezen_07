<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<c:set  var="articlesList"  value="${articlesMap.articlesList}" />
<c:set  var="totArticles"  value="${articlesMap.totArticles}" />
<c:set  var="section"  value="${articlesMap.section}" />
<c:set  var="pageNum"  value="${articlesMap.pageNum}" />

<%
  request.setCharacterEncoding("UTF-8");
%>  
<!DOCTYPE html>
<html>
<head>
 <style>
   .bg {
     background-color: rgb(243, 243, 243);
     width: 80%;
     margin: 0 auto;
     margin-top: 3rem;
   }
   a {
     text-decoration: none;
     color: inherit;
   }
   h1 {
     text-align: center;
     padding: 3rem 0 0 0;
     color: gray;
   }
   .home {
     width: 90%;
     margin: 0 auto;
     margin-bottom: .3rem;
   }
   .home_btn, .submit_btn  {
     display: block;
     cursor: pointer;
     border: 1px solid #fff;
     border-radius: 5px;
     padding: .3rem;
     width: 10%;
     text-align: center;
     color: #fff;
     background-color: #337ab7;
     transition: .5s;
    }
    .home_btn:hover,
    .submit_btn:hover {
      background-color: #1f6dff;
      border-radius: 20px;
   }
   .submit_btn {
     margin: 0 auto;
   }
   table {
     border-collapse: collapse;
     text-align: center;
     margin: 0 auto;
     width: 90%;
   }
   tr {
     border-bottom: 1px solid #f8f8f8;
     line-height: 1.6;
    }
    tr:last-of-type {
     border-bottom: 3px solid #f8f8f8;
    }
   .tr01 {
    line-height: 2.6;
    text-align: center;
    background-color: rgb(233, 233, 233);
    border-bottom: 3px solid #f8f8f8;
   }
   .sel-page{color:red;}
   .next {
     color: #337ab7;
     transition: .3s;
   }
   .next:hover {
     color: #1f6dff;
     font-size: 1.3rem;
   }
   .cls2{
     text-align:center;
     font-size: 1.2rem;
     cursor: pointer;
     color: gray;
   }
   
   table .title_style {
   	 text-align: left;
   }
  </style>
  <meta charset="UTF-8">
  <title>글목록창</title>
</head>
<body>
  <div class="bg">
    <h1>게시판</h1>
  <div class="home">
    <a  class="home_btn"  href="${contextPath}/main.jsp">HOME</a>
  </div>
<table>
  <tr class="tr01">
     <td >글번호</td>
     <td >작성자</td>              
     <td >제목</td>
     <td >작성일</td>
  </tr>
<c:choose>
  <c:when test="${articlesList ==null }" >
    <tr  height="10">
      <td colspan="4">
         <p>
            <b><span style="font-size:9pt;">등록된 글이 없습니다.</span></b>
        </p>
      </td>  
    </tr>
  </c:when>
  <c:when test="${articlesList !=null }" >
    <c:forEach  var="article" items="${articlesList }" varStatus="articleNum" >
     <tr>
   <td width="5%">${articleNum.count}</td>
   <td width="10%">${article.id }</td>
   <td class="title_style" width="35%">
       <span style="padding-right:30px"></span>    
      <c:choose>
         <c:when test='${article.level > 1 }'>  
            <c:forEach begin="1" end="${article.level }" step="1">
                <span style="padding-left:10px"></span> 
            </c:forEach>
            <span style="font-size:12px;">[답변]</span>
                   <a class='cls1' href="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}">${article.title}</a>
             </c:when>
             <c:otherwise>
               <a class='cls1' href="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}">${article.title }</a>
             </c:otherwise>
           </c:choose>
     </td>
     <td  width="10%"><fmt:formatDate value="${article.writeDate}" /></td> 
   </tr>
    </c:forEach>
     </c:when>
    </c:choose>
</table>

<div class="cls2">
 <c:if test="${totArticles != null }" >
      <c:choose>
        <c:when test="${totArticles >100 }">  <!-- 글 개수가 100 초과인경우 -->
         <c:forEach   var="page" begin="1" end="10" step="1" >
            <c:if test="${section >1 && page==1 }">
             <a  class="next" href="${contextPath }/board/listArticles.do?section=${section-1}&pageNum=${(section-1)*10 +1 }">&nbsp; pre &#60;&#60; </a>
            </c:if>
             <a  href="${contextPath }/board/listArticles.do?section=${section}&pageNum=${page}">${(section-1)*10 +page } </a>
            <c:if test="${page ==10 }">
             <a class="next" href="${contextPath }/board/listArticles.do?section=${section+1}&pageNum=${section*10+1}">&nbsp; >> next</a>
            </c:if>
         </c:forEach>
        </c:when>
        <c:when test="${totArticles ==100 }" >  <!--등록된 글 개수가 100개인경우  -->
         <c:forEach   var="page" begin="1" end="10" step="1" >
           <a   href="#">${page } </a>
         </c:forEach>
        </c:when>
        
        <c:when test="${totArticles< 100 }" >   <!--등록된 글 개수가 100개 미만인 경우  -->
         <c:forEach   var="page" begin="1" end="${totArticles/10 +1}" step="1" >
            <c:choose>
              <c:when test="${page==pageNum }">
               <a class="sel-page"  href="${contextPath }/board/listArticles.do?section=${section}&pageNum=${page}">${page } </a>
             </c:when>
             <c:otherwise>
               <a   href="${contextPath }/board/listArticles.do?section=${section}&pageNum=${page}">${page } </a>
             </c:otherwise>
           </c:choose>
         </c:forEach>
        </c:when>
      </c:choose>
    </c:if>
</div>    
<br><br>
<a  class="submit_btn" href="${contextPath}/board/articleForm.do">글쓰기</a><br>
</div> 
</body>
</html>