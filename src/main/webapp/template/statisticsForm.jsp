<%--
  Created by IntelliJ IDEA.
  User: kiric
  Date: 21.07.2025
  Time: 01:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Статистика</title>
</head>
<body>
<table>
    <tr>
        <td>Общее количество пользователей: </td>
        <td>${usersCount}</td>
    </tr>
    <tr>
        <td>Общее количество сообщений: </td>
        <td>${messagesCount}</td>
    </tr>
    <tr>
        <td>Количество активных пользователей: </td>
        <td>${activeUsersCount}</td>
    </tr>
</table>
</body>
</html>
