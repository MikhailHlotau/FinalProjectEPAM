<%--
    Это комментарий JSP.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
    <style>
        body {
            background-color: #f2f2f2;
            text-align: center;
            font-family: Arial, sans-serif;
        }

        h1 {
            color: #336699;
            font-size: 32px;
        }

        .welcome {
            color: #336699;
            font-size: 32px;
            font-weight: bold;
        }

        .user {
            color: #ff6600;
            font-size: 32px;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <h1>Welcome to My Website!</h1>
    <p class="welcome">Thank you for visiting.</p>
    <p class="user">Hello, ${user}!</p>
</body>
</html>