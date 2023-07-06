<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
    <style>
        body {
            background-color: #f2f2f2;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 100vh;
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

        form {
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <h1>Welcome to My Website!</h1>
    <p class="welcome">Thank you for visiting.</p>
    <p class="user">Hello, ${user}!</p>

    <form action="controller" method="GET">
        <input type="hidden" name="command" value="FIND_ALL_CUSTOMERS">
        <input type="submit" value="Get All Customers" style="font-weight: bold; font-size: 18px; padding: 10px;">
    </form>
</body>
</html>