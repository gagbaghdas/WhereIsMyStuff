<%-- 
    Document   : answer_the_question
    Created on : Sep 4, 2016, 9:45:46 PM
    Author     : lt-gbaghdas
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
    * {
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
    nav:nth-of-type(1) {
        width: 100%;
        height: 70px;
        border-bottom: 2px solid black;
    }
    nav:nth-of-type(2) {
        width: 100%;
        height: 80px;
        border-bottom: 2px solid black;
    }
    nav h2 {
        line-height: 70px;
        padding-left: 10px;
    }
    nav:nth-of-type(2) p {
        padding: 10px;
    }
    nav:nth-of-type(2) p:nth-of-type(2) {
        display: inline-block;
    }
    nav:nth-of-type(2) span p {
        display: inline-block;
    }
    nav:nth-of-type(2) span {
        float: right;
        padding-right: 20px;
    }
    .container {
        width: 750px;
        margin: auto;
    }
    .container div {
        width: 100%;
        height: 260px;
        display: table;
        border: 2px solid black;
        margin-top: 20px;
        margin-bottom: 20px;
    }
    .container div p {
        padding: 20px 10px;
    }
    .container span {
        width: 300px;
        display: table-cell;
        vertical-align: bottom;
    }
    .container span p {
        display: inline-block;
    }
    input {
        display: block;
        margin-top: 20px;
        width: 100%;
        height: 150px;
        border: 2px solid black;
    }
    button {
        float: right;
        margin-top: 20px;
        padding: 5px 10px;
        cursor: pointer;
    }
</style>
<!DOCTYPE html>
<html>
    <head>
        <title></title>
        <link rel="stylesheet" type="text/css" href="page4.css">
    </head>
    <body>
        <header>
            <h2>where's my stuff ???</h1>
                <c:choose>
                    <c:when test="${current_user_id == 0}">
                        <a href="login" >sign in</a>
                    </c:when>
                    <c:otherwise>
                        <a href="view_profile?user_id=${current_user_data.getUserId()}&current_user_id=${current_user_data.getUserId()}" >${current_user_data.getUserName()}</a>
                    </c:otherwise>
                </c:choose>
        </header>
        <nav>
            <h2>${question_data.getTitle()}</h2>
        </nav>
        <nav>
            <p>${question_data.getQuestion()}</p>
            <p>categories: ${question_data.getCategory().getName()}</p>
            <span>
                <p>Publish date: ${question_data.getPublishDate()}</p>
                <a href="view_profile?user_id=${question_data.getUserId()}&current_user_id=${current_user_id}">${question_data.getUser().getUserName()}</a>
            </span>
        </nav>
        <div class="container">
            <c:forEach items="${question_data.getAnswers()}" var="answer">
                <div>
                    <p>${answer.getAnswer()}</p>
                    <span>
                        <p>Publish date: ${answer.getPublishDate()}</p>
                        <a href="view_profile?user_id=${answer.getUserId()}&current_user_id=${current_user_id}">${answer.getUser().getUserName()}</a>
                    </span>
                </div>
            </c:forEach>
            <c:if test="${current_user_id != 0}">
                <label>Your answer</label>
                <input id ="answer_text" type="text"></input>
                <a id="post_answer" onclick="postAnswer()"  href = "">
                    <button>Post</button>
                </a>
            </c:if>
        </div>
    </body>
</html>
<script>
    if (window.location.href.indexOf("post_answer") !== -1)
        window.location.href = "view_question?question_id=${question_data.getId()}&current_user_id=${current_user_id}";

    function postAnswer() {
        var link = "post_answer?user_id=${current_user_id}&text=" + getText() + "&question_id=${question_data.getId()}" + "&publish_date=" + getPublishDate();
        document.getElementById("post_answer").setAttribute("href", link);
    }

    function getText() {
        return document.getElementById("answer_text").value;
    }

    function getPublishDate() {
        var today = new Date();
        var dd = today.getDate();
        var mm = today.getMonth() + 1; //January is 0!
        var yyyy = today.getFullYear();

        if (dd < 10) {
            dd = '0' + dd;
        }

        if (mm < 10) {
            mm = '0' + mm;
        }

        return yyyy + '-' + mm + '-' + dd;
    }
</script>