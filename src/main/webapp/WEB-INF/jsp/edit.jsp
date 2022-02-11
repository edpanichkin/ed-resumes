<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="edpanichkin.resumes.model.ContactType" %>
<%@ page import="edpanichkin.resumes.model.Resume" %>
<%@ page import="java.util.List" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="edpanichkin.resumes.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl>
            <dt>Имя:</dt>
            <dd><input type="text" name="fullName" size="50" value="${resume.fullName}"></dd>
        </dl>
        <h3>Контакты:</h3>
        <p><c:forEach var="type" items="<%=ContactType.values()%>">
        <dl>
        <dt>${type.title}</dt>
        <dd><input type="text" name="${type.name()}" size="30" value="${resume.getContact(type)}"></dd>
    </dl>
        </c:forEach> </p>
        <hr>
        <button type="submit">Сохранить</button>
        <button  onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>