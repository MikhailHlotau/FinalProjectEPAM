<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="glotov.servlet.model.MenuItem" %>
<html>
<head>
    <title>List of Menu Items</title>
    <style>
        body {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            padding: 0;
        }

        h1 {
            margin: 0;
            color: #ff6600;
            font-size: 32px;
        }

        table {
            margin: 0 auto;
            font-family: Arial, sans-serif;
            color: blue;
            border-collapse: collapse;
        }

        th, td {
            padding: 15px;
            border: 1px solid black;
        }
    </style>
</head>
<body>
    <h1>List of Menu Items:</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
        </tr>
        <% for (MenuItem menuItem : (List<MenuItem>) request.getAttribute("menuItems")) { %>
            <tr>
                <td><%= menuItem.getId() %></td>
                <td><%= menuItem.getName() %></td>
                <td><%= menuItem.getDescription() %></td>
                <td><%= menuItem.getPrice() %></td>
            </tr>
        <% } %>
    </table>
</body>
</html>