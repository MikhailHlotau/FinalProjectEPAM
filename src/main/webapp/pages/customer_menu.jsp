<%@ page contentType="text/html; charset=UTF-8" language="java" %>
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

        .customer {
            color: #ff6600;
            font-size: 32px;
            font-weight: bold;
        }

        form {
            margin-top: 20px;
        }

        input[type="submit"] {
            margin-top: 10px;
            width: 240px;
            height: 40px;
            background-color: #336699;
            color: #fff;
            font-size: 16px;
            border: none;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <h1>Welcome!</h1>
    <p class="welcome">Thank you for visiting.</p>
    <p class="customer">Hello, customer ${customer}!</p>

   <div class="register-link">
               <form action="pages/admin_customers.jsp">
                   <input type="submit"  value="MAKE AN ORDER">
               </form>
   </div>
    <div class="register-link">
                <form action="pages/admin_dish.jsp">
                    <input type="submit"  value="PAYMENT FOR THE ORDER">
                </form>
    </div>
    <div class="register-link">
                <form action="pages/admin_orders.jsp">
                     <input type="submit"  value="HISTORY OF ORDERS">
                </form>
    </div>
    </form>
    <form action="controller" method="GET">
         <input type="hidden" name="command" value="LOGOUT">
         <input type="submit" value="SIGN OUT">
    </form>
</body>
</html>