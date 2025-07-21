<%--
  Created by IntelliJ IDEA.
  User: kiric
  Date: 21.07.2025
  Time: 02:32
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
<p>Добро пожаловать ${name}</p>
<p><b>Ваши сообщения</b></p>
<c:forEach items="${messageList}" var="message">
    <table>
        <tr>
            <td>Дата и время отправки</td>
            <td>${message.getTime()}</td>
        </tr>
        <tr>
            <td>Отправитель</td>
            <td>${message.getAuthor()}</td>
        </tr>
        <tr>
            <td>Текст сообщения</td>
            <td>${message.getText()}</td>
        </tr>
    </table>
</c:forEach>
<form action="${pageContext.request.contextPath}/api/message" method="get">
    <button>Написать сообщение</button>
</form>
<form action="${pageContext.request.contextPath}/" method="get">
    <button>Вернуться на главную страницу</button>
</form>
</body>
</html>
