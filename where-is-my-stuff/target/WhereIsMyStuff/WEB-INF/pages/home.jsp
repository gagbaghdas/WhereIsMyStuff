<%-- 
    Document   : home
    Created on : Sep 4, 2016, 4:00:20 PM
    Author     : lt-gbaghdas
--%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    *{
        margin: 0;
        padding: 0;
    }
    html, body {
        width: 100%;
        height: 100%;
    }
    header {
        width: 100%;
        height: 50px;
        border-bottom: 2px solid black;
    }
    header h2 {
        padding: 10px;
        display: inline-block;
    }
    header a {
        float: right;
        padding-top: 20px;
        padding-right: 20px;
    }
    div:nth-of-type(1) {
        width: 765px;
        height: 50px;
        margin: auto;
        padding-top: 20px;
    }
    div:nth-of-type(1) nav p {
        display: inline-block;
        padding-left: 5%;

    }
    div:nth-of-type(1) nav a {
        padding-left: 10px;
    }
    div:nth-of-type(1) button {
        float: right;
        margin-top: -20px;
        margin-right: 20px;
        padding: 3px 7px;
        cursor: pointer;
    }
    div:nth-of-type(2) {
        width:  765px;
        border: 2px solid black;
        margin: auto;
    }
    div:nth-of-type(2) nav {
        width: 100%;
        height: 100px;
        border-bottom: 2px solid black;
    }
    div:nth-of-type(2) nav span:nth-of-type(1), span:nth-of-type(2) {
        line-height: 70px;
        padding-left: 30px;
    }
    div:nth-of-type(2) nav span:nth-of-type(2) a {
        padding-left: 50px;
    }
    div:nth-of-type(2) nav span:nth-of-type(3) {
        display: block;
    }
    div:nth-of-type(2) nav span:nth-of-type(3) p {
        width: 200px;
        padding-left: 130px;
        display: inline-block;
    }

</style>

<!DOCTYPE html>
<html>
    <head>
        <title></title>
    </head>
    <body>
        <header>
            <h2>where's my stuff ???</h1>
                <c:choose>
                    <c:when test="${user_id == 0}">
                        <a href="login" >${user_name}</a>
                    </c:when>
                    <c:otherwise>
                        <a href="view_profile?user_id=${user_id}&current_user_id=${user_id}" >${user_name}</a>
                    </c:otherwise>
                </c:choose>
        </header>
        <div>
            <nav>
                <p>categories:</p>
                <a href="filter?category_id=0&user_id=${user_id}">All</a>
                <c:forEach items="${categories}" var="entry">
                    <a href="filter?category_id=${entry.key}&user_id=${user_id}">${entry.value.getName()}</a>
                </c:forEach>
            </nav>
            <c:if test="${user_id != 0}">
                <a href="ask_question?user_id=${user_id}">
                    <button>ask question</button>
                </a>
            </c:if>
        </div>
        <div>
            <c:forEach items="${questions}" var="question">
                <nav>
                    <span>${question.getAnswers().size()} answer(s)</span>
                    <span>
                        <a href="view_question?question_id=${question.getId()}&current_user_id=${user_id}">${question.getTitle()}</a>
                    </span>
                    <span>
                        <p>categories: ${question.getCategory().getName()}</p>
                        <p>Publish date: ${question.getPublishDate()}</p>
                        <a href="view_profile?user_id=${question.getUser().getUserId()}&current_user_id=${user_id}">${question.getUser().getUserName()}</a>
                    </span>
                </nav>
            </c:forEach>
        </div>
    </body>
</html>
<script>
    if (window.location.href.indexOf("login") !== -1 || window.location.href.indexOf("post_question") !== -1)
        window.location.href = "home?user_id=${user_id}";
</script>