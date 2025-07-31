<%--
  Created by IntelliJ IDEA.
  User: kiric
  Date: 21.07.2025
  Time: 02:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
  <title>Регистрация пользователя</title>
</head>
<body>
<h1>Регистрация пользователя</h1>
<form action="${pageContext.request.contextPath}/api/user" method="post">
  <p style="color:red">${error}</p>
  <table>
    <tr>
      <td>Введите логин</td>
      <td><input type="text" name="login" /></td>
    </tr>
    <tr>
      <td>Введите пароль</td>
      <td><input type="password" name="password" /></td>
    </tr>
    <tr>
      <td>Введите ФИО</td>
      <td><input type="text" name="name" /></td>
    </tr>
    <tr>
      <td>Введите дату рождения</td>
    </tr>
    <tr>
      <td>День</td>
      <td><input type="text" name="day" /></td>
    </tr>
    <tr>
      <td>Месяц</td>
      <td><input type="text" name="month" /></td>
    </tr>
    <tr>
      <td>Год</td>
      <td><input type="text" name="year" /></td>
    </tr>
  </table>
  <input type="submit" value="Зарегистрироваться" />
</form>
</body>
</html>
