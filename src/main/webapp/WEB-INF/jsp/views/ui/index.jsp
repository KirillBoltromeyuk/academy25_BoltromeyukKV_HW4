<%--
  Created by IntelliJ IDEA.
  User: kiric
  Date: 21.07.2025
  Time: 02:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>${name}</title>
</head>
<body>
<c:set var="userName" value="${name}"/>
<c:set var="userRole" value="${role}"/>
<c:if test="${userName==null}">
    <h1>Добро пожаловать</h1>
    <p>Войдите или зарегистрируйтесь</p>
    <form action="${pageContext.request.contextPath}/api/user" method="get">
        <button>Регистрация</button>
    </form>
    <form action="${pageContext.request.contextPath}/api/login" method="get">
        <button>Вход</button>
    </form>
</c:if>
<c:if test="${userName!=null}">
    <h1>Добро пожаловать ${name}</h1>
    <form action="${pageContext.request.contextPath}/api/chats" method="get">
        <button>Мои сообщения</button>
    </form>
    <form action="${pageContext.request.contextPath}/api/message" method="get">
        <button>Написать сообщение</button>
    </form>
    <form action="${pageContext.request.contextPath}/api/logout" method="get">
        <button>Выход</button>
    </form>
</c:if>
<c:if test="${userRole=='ADMIN'}">
    <form action="${pageContext.request.contextPath}/api/admin/statistics" method="get">
        <button>Просмотр статистики</button>
    </form>
</c:if>

</body>
</html>
