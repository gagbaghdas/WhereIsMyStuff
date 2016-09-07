
<%-- 
    Document   : login
    Created on : Sep 4, 2016, 4:00:20 PM
    Author     : lt-gbaghdas
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>


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
    .container {
        width: 100%;
        height: 80%;
        margin-top: 50px;
    }
    .container div {
        width: 48%;
        display: inline-block;
        text-align: center;
    }
    .container div:nth-of-type(1) {
        border-right: 2px solid black;
    }
    h1 {
        margin-bottom: 30px;
    }
    span {
        width: 200px;
        display: block;
        margin: auto;
    }
    input {
        display: block;
        margin: 15px;
        padding: 5px 10px;
    }
    nav {
        margin-top: 15px;
    }
    label {
        margin-left: -15px;
    }
    button {
        margin-left: 10px;
        padding: 5px 10px;
        background-color: red;
        color: #fff;
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
                <a href="login">sign in</a>
        </header>
        <h2 id='result_sign_in'>" "</h2>
        <h2 id='result_sign_up'></h2>
        <div class="container">
            <div>
                <h1>login</h1>
                <span>
                    <input id = "login_user_name" type="text" placeholder="username"></input>
                    <input id = "login_password" type="password" placeholder="password"></input>
                    <a id="login_id" onclick="logIn()"  href = "">
                        <input type="submit" value="sign in">
                    </a>
                    <nav>
                        <label>sign in using</label>
                        <button>Google</button>
                    </nav>
                </span>

            </div>
            <div>
                <h1>signup</h1>
                <span>
                    <input id = "signup_user_name" type="text" placeholder="username"></input>
                    <input id = "email" type="text" placeholder="email"></input>
                    <input id = "signup_password" type="password" placeholder="password"></input>
                    <a id="signup_id" onclick="signUp()"  href = "">
                        <input type="submit" value="sign up">
                    </a>	
                </span>

            </div>
        </div>
    </body>
</html>

<script>
    var response = "${response}";
    var responseColor = response === "You have successfully registered" ? "green" : "red";
    document.getElementById("result_sign_in").innerHTML = "${error}";
    document.getElementById("result_sign_up").innerHTML = "${response}";
    document.getElementById("result_sign_in").style.color = "red";
    document.getElementById("result_sign_up").style.color = responseColor;
    function logIn() {
        var link = "login_?user_name=" + getLoginUserName() + "&password=" + getLoginPassword();
        document.getElementById("login_id").setAttribute("href", link);
    }

    function signUp() {
        var link = "signup_?user_name=" + getSignUpUserName() + "&password=" + getSignUpPassword() + "&email=" + getEmail();
        document.getElementById("signup_id").setAttribute("href", link);
    }

    function getLoginUserName() {
        return document.getElementById("login_user_name").value;
    }

    function getLoginPassword() {
        return CryptoJS.MD5(document.getElementById("login_password").value);
    }

    function getSignUpUserName() {
        return document.getElementById("signup_user_name").value;
    }

    function getSignUpPassword() {
        return CryptoJS.MD5(document.getElementById("signup_password").value);
    }
    function getEmail() {
        return document.getElementById("email").value;
    }

</script>