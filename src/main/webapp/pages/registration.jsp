<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
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
            margin: 0;
        }

        form {
            margin-top: 20px;
        }

        label {
            display: inline-block;
            width: 120px;
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
            margin-bottom: 10px;
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

        .register-link {
            color: #cc0000;
            font-size: 24px;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <h1>Cafe "U Mihaila"</h1>
    <%-- Вывод сообщения об ошибке, если есть --%>
    <% String message = (String) request.getAttribute("message"); %>
    <% if (message != null) { %>
        <p class="failed"><%= message %></p>
    <% } %>
    <%-- Форма для регистрации --%>
    <form action="/FinalProjectEPAM/controller" method="POST">
        <input type="hidden" name="command" value="registration_customer"/>
        <div>
            <label for="login">Login:</label>
            <input type="text" id="login" name="login" required><br>
        </div>
        <div>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required><br>
        </div>
        <div>
            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" name="firstName" required><br>
        </div>
        <div>
            <label for="lastName">Last Name:</label>
            <input type="text" id="lastName" name="lastName" required><br>
        </div>
        <div>
            <label for="phone">Phone:</label>
            <input type="text" id="phone" name="phone" required><br>
        </div>
        <div>
            <label for="email">Email:</label>
            <input type="text" id="email" name="email" required><br>
        </div>
        <input type="submit" value="Register">
    </form>
</body>
</html>