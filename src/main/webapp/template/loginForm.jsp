<%--
  Created by IntelliJ IDEA.
  User: kiric
  Date: 20.07.2025
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
  <title>Вход</title>
</head>
<body>
<h1>Вход</h1>
<form action="/JD2HW4-1.0-SNAPSHOT/login" method="post">
  <table>
    <tr>
      <td>Введите логин</td>
      <td><input type="text" name="login" /></td>
    </tr>
    <tr>
      <td>Введите пароль</td>
      <td><input type="password" name="password" /></td>
    </tr>
  </table>
  <input type="submit" value="Войти" />
</form>
</body>
</html>

