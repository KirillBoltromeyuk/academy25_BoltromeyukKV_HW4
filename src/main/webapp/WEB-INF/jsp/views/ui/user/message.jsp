<%--
  Created by IntelliJ IDEA.
  User: kiric
  Date: 21.07.2025
  Time: 02:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Написать сообщение</title>
</head>
<body>
<h1>Написать сообщение</h1>
<form action="${pageContext.request.contextPath}/api/message" method="post">
    <p><b>Текст сообщения</b></p>
    <textarea name="text"></textarea>
    <p><b>Адресат</b></p>
    <p><input type="text" name="destination" /></p>
    <input type="submit" value="Отправить" />
</form>
<form action="${pageContext.request.contextPath}/" method="get">
    <button>Вернуться на главную страницу</button>
</form>
</body>
</html>
