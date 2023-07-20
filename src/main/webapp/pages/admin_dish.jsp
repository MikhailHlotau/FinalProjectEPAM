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

        form {
            margin-top: 20px;
        }

        input[type="submit"] {
            margin-top: 10px;
            width: 180px;
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
    <h1>MENU</h1>
    <form action="/FinalProjectEPAM/controller" method="GET">
        <input type="hidden" name="command" value="FIND_ALL_MENU_ITEMS_COMMAND">
        <input type="submit" value="View the list of dishes">
    </form>
    <form action="/FinalProjectEPAM/controller" method="GET">
          <input type="hidden" name="command" value="FIND_ALL_CUSTOMERS">
          <input type="submit" value="Add dish">
    </form>
    <form action="/FinalProjectEPAM/controller" method="GET">
         <input type="hidden" name="command" value="LOGOUT">
         <input type="submit" value="Change the dish">
    </form>
    <form action="/FinalProjectEPAM/controller" method="GET">
         <input type="hidden" name="command" value="LOGOUT">
         <input type="submit" value="Remove dish">
    </form>
</body>
</html>