<%--
  Created by IntelliJ IDEA.
  User: kiric
  Date: 20.07.2025
  Time: 23:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Написать сообщение</title>
</head>
<body>
<h1>Написать сообщение</h1>
<form action="/JD2HW4-1.0-SNAPSHOT/sendMessage" method="post">
    <p><b>Текст сообщения</b></p>
    <textarea name="text"></textarea>
    <p><b>Адресат</b></p>
    <p><input type="text" name="destination" /></p>
    <input type="submit" value="Отправить" />
</form>
</body>
</html>
