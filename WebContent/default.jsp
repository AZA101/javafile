<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>文件上传</title>
</head>
<body>
<!-- 文件上传方法方式要使用的是post方法
encytpe编码使用multipart/form-data 
使用form表单提交到servlet上  -->
<form action="/file_upload3/RegistServlet" method="post" enctype="multipart/form-data" >
   用户名:<input type="text" name="username"><br>
   密码：<input type="password" name="password"><br>
  选择文件:<input type="file" name="pic"><br>
  <input type="submit">
</form>
</body>
</html>