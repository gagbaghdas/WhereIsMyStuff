<%-- 
    Document   : profile
    Created on : Sep 4, 2016, 8:26:41 PM
    Author     : lt-gbaghdas
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@page session="true"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
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
        padding-top: 40px;
        padding-bottom: 5px;
        margin: auto;
        width: 90%;
        border-bottom: 2px solid black;
    }
    nav p {
        display: inline-block;
    }
    nav a {
        float: right;
    }
    img {
        width: 70px;
        height: 70px;
        margin: 20px 7%;
    }
    table {
        margin-left: 3%;
    }
    tr td:nth-of-type(1) {
        padding: 10px;
        width: 100px;
        text-align: right;
    }
    td input {
        border: none;
        outline: none;
    }
    input:focus {
        padding: 7px;
        border: 1px solid #ddd;
    }
    button {
        display: none;
        float: right;
        margin-top: 20px;
        margin-right: 70px;
        padding: 5px 10px;
        cursor: pointer;
    }
</style>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.2/components/core-min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.2/components/md5.js"></script>
        <title></title>
    </head>
    <body>
        <header>
            <h2>where's my stuff ???</h1>
                <c:choose>
                    <c:when test="${current_user_id != user_data.getUserId()}">
                        <c:choose>
                            <c:when test="${current_user_id == 0}">
                                <a href="login" >sign in</a>
                            </c:when>
                            <c:otherwise>
                                <a href="view_profile?user_id=${current_user_id}&current_user_id=${current_user_id}">${current_user_data.getUserName()}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                    <c:otherwise>
                        <a href="view_profile?user_id=${current_user_id}&current_user_id=${current_user_id}">${user_data.getUserName()}</a>
                    </c:otherwise>
                </c:choose>
        </header>
        <nav>
            <p>${user_data.getUserName()}'s profile</p>
            <c:if test="${current_user_id == user_data.getUserId()}">
                <a  id = "save_id" href="" onclick="saveUserData()">
                    save
                </a>
            </c:if>
        </nav>
        <img id ="image" src="${user_profile_pic}"/>
        <c:choose>
            <c:when test="${current_user_id == user_data.getUserId()}">
                <table>
                    <tr>
                        <td>username:</td>
                        <td><input id="user_name" type="text" value="${user_data.getUserName()}"></input></td>
                    </tr>
                    <tr>
                        <td>email:</td>
                        <td><input id="email" type="text" value="${user_data.getEmail()}"></input></td>
                    </tr>
                    <tr>
                        <td>new password:</td>
                        <td><input id="password" type="text" value="" placeholder="Enter new password"></input></td>
                    </tr>
                </table>
            </c:when>
            <c:otherwise>
                <table>
                    <tr>
                        <td>username:</td>
                        <td>${user_data.getUserName()}</td>
                    </tr>
                    <tr>
                        <td>email:</td>
                        <td>${user_data.getEmail()}</td>
                    </tr>
                </table>
            </c:otherwise>
        </c:choose>

    </body>
</html>

<script>
    if (window.location.href.indexOf("save_user_data") !== -1)
        window.location.href = "view_profile?user_id=${current_user_id}&current_user_id=${current_user_id}";
    function saveUserData() {
        var link = "save_user_data?user_id=${current_user_id}&user_name=" + getUserName() + "&password=" + getPassword() + "&email=" + getEmail() + "&pic_url=" + getPicUrl();
        document.getElementById("save_id").setAttribute("href", link);
    }

    function getUserName() {
        return document.getElementById("user_name").value;
    }

    function getPassword() {
        return document.getElementById("password").value === "" ? "" : CryptoJS.MD5(document.getElementById("password").value);
    }

    function getEmail() {
        return document.getElementById("email").value;
    }

    function getPicUrl() {
        return document.getElementById("image").src;
    }

</script>