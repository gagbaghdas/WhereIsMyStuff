<%-- 
    Document   : ask_question
    Created on : Sep 4, 2016, 4:24:38 PM
    Author     : lt-gbaghdas
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    nav {
        width: 100%;
        height: 70px;
        border-bottom: 2px solid black;
    }
    nav p {
        line-height: 70px;
        padding-left: 50px;
    }
    div {
        width: 800px;
        margin: 30px auto;
    }
    td {
        padding-top: 20px;
    }
    tr td:nth-of-type(1) {
        width: 150px;
        padding-right: 20px;
        text-align: right;
        vertical-align: top;
    }
    tr:nth-of-type(1) td:nth-of-type(2) input {
        width: 600px;
    }
    tr:nth-of-type(2) td:nth-of-type(2) input {
        width: 600px;
        height: 300px;
    }
    tr:nth-of-type(3) td:nth-of-type(2) input {
        margin-bottom: 15px;
    }
    div button {
        float: right;
        padding: 5px 10px;
        margin-right: 25px;
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
                <a href="">${user_data.getUserName()}</a>
        </header>
        <nav>
            <p>Create a question</p>
        </nav>
        <div>
            <table>
                <tr>
                    <td>title</td>
                    <td><input id = "title" type="text"></input></td>
                </tr>
                <tr>
                    <td>what it's all about</td>
                    <td><input id = "text" type="text"></input></td>
                </tr>
                <tr>
                    <td><label>select category:</label></td>
                    <td>
                        <form action="">
                            <c:forEach items="${categories}" var="entry">
                                <input type="radio" name="category" value="${entry.value.getId()}"> ${entry.value.getName()}<br>
                            </c:forEach>
                        </form>
                    </td>

                </tr>
            </table>
            <a id="post_link" onclick="postQuestion()"  href = "">
               <button> Post Your Question</button>
            </a>
        </div>
    </body>
</html>

<script>
    function postQuestion() {
        var link = "post_question?user_id=${user_data.getUserId()}&current_user_id=${user_data.getUserId()}&title=" + getTitle() + "&text=" + getText() + "&category_id=" + getCategoryId() + "&publish_date=" + getPublishDate();
        document.getElementById("post_link").setAttribute("href", link);
    }

    function getTitle() {
        return document.getElementById("title").value;
    }

    function getText() {
        return document.getElementById("text").value;
    }

    function getCategoryId() {
        var oRadio = document.forms[0].elements['category'];

        for (var i = 0; i < oRadio.length; i++)
        {
            if (oRadio[i].checked)
            {
                return oRadio[i].value;
            }
        }

        return 0;
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