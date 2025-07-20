<%--
  Created by IntelliJ IDEA.
  User: kiric
  Date: 20.07.2025
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Сообщения</title>
</head>
<body>
<h1>Добро пожаловать ${name}</h1>
<p><b>Ваши сообщения</b></p>
<c:forEach items="${messageList}" var="message">
<table>
    <tr>
        <td>${message.getText()} </td>
        <td>            от ${message.getAuthor()}; ${message.getTime()}</td>
    </tr>
</table>
</c:forEach>
<form action="/JD2HW4-1.0-SNAPSHOT/sendMessage" method="get">
<button>Написать сообщение</button>
</form>
</body>
</html>
