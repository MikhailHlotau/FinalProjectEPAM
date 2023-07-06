<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Your Service</title>
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

        form {
            margin-top: 20px;
        }

        label {
            display: inline-block;
            width: 80px;
            text-align: right;
            font-weight: bold;
            margin-right: 10px;
        }

        input[type="text"],
        input[type="password"] {
            width: 200px;
            height: 30px;
            padding: 5px;
            font-size: 16px;
        }

        input[type="submit"] {
            margin-top: 10px;
            width: 100px;
            height: 40px;
            background-color: #336699;
            color: #fff;
            font-size: 16px;
            border: none;
            cursor: pointer;
        }

        .failed {
            color: #cc0000;
            font-size: 24px;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <h1>Cafe "U Mihaila"</h1>
    <form action="controller" method="GET">
        <input type="hidden" name="command" value="login"/>
        <div>
            <label for="login">Login:</label>
            <input type="text" name="login" id="login" value=""/>
        </div>
        <div>
            <label for="password">Password:</label>
            <input type="password" name="password" id="password" value=""/>
        </div>
        <div>
            <input type="submit" name="sub" value="Push"/>
        </div>
        <div class="failed">${failed}</div>
    </form>
</body>
</html>
