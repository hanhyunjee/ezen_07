<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  request.setCharacterEncoding("UTF-8");
%> 
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  /> 

</script>
<style>
.bg {
     background-color: rgb(243, 243, 243);
     width: 80%;
     margin: 0 auto;
     margin-top: 3rem;
}
.form_bg {
  width: 90%;
  background-color: aqua;
  margin: 0 auto;
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
.table {
  display: flex;
  flex: 1;
  margin: 0 auto;
  text-align: center;
  background-color: rgb(233, 233, 233);
  line-height: 4;
}
input, textarea {
  font-size: ;
}
.btn {
  display: inline-block;
  cursor: pointer;
  border: 1px solid #fff;
  border-radius: 5px;
  padding: 1rem;
  margin: 3% 4% 10% 4%;
  width: 10%;
  text-align: center;
  color: #fff;
  background-color: #337ab7;
  transition: .5s;
  width: 40%;
  overflow: hidden;
}

.btn:hover {
  background-color: #1f6dff;
  border-radius: 20px;
}

input, textarea {
  display: flex;
  flex: 1;
}
</style>
 <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기창</title>
<link rel="stylesheet" href="./newwrite.css" >
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
  function readURL(input) {
      if (input.files && input.files[0]) {
         var reader = new FileReader();
         reader.onload = function (e) {
           $('#preview').attr('src', e.target.result);
          }
         reader.readAsDataURL(input.files[0]);
      }
  }
  function backToList(obj){
    obj.action="${contextPath}/board/listArticles.do";
    obj.submit();
  }

</script>
 <title>새글 쓰기 창</title>
</head>

<body>
  <div class="bg">
    <h1>새글 쓰기</h1>
    <form class="form_bg" name="articleForm" method="post"   action="${contextPath}/board/addArticle.do"   enctype="multipart/form-data">
      <table class="table">
        <tr>
          <td>글제목</td>
          <td colspan="2"><input class="flex" type="text" size="67"  maxlength="500" name="title" /></td>
        </tr>
        <tr>
          <td valign="top"><br>글내용</td>
          <td colspan=2><textarea  class="flex" name="content" rows="10" cols="65" maxlength="4000"></textarea> </td>
        </tr>
        <tr>
            <td text-align="right">이미지파일 첨부</td>
            <td> <input type="file" name="imageFileName"  onchange="readURL(this);" /></td>
            <td><img  id="preview" src="#"   width=200 height=200/></td>
        </tr>
        <tr>
          <td text-align="right"> </td>
          <td colspan="2">
              <input class="btn" type="submit" value="저장하기" />
              <input class="btn" type=button value="목록보기"onClick="backToList(this.form)" />
          </td>
        </tr>
      </table>
    </form>
  </div> 
</body>
</html>