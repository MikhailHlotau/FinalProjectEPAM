<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="glotov.servlet.model.Customer" %>
<html>
<head>
    <title>List of Customers</title>
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
    <h1>List of Customers:</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Login</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Phone</th>
            <th>Email</th>
            <th>Role</th>
            <th>Banned</th>
            <th>Bonus Points</th>
            <th>Loyalty Points</th>
        </tr>
        <% for (Customer customer : (List<Customer>) request.getAttribute("customers")) { %>
            <tr>
                <td><%= customer.getId() %></td>
                <td><%= customer.getLogin() %></td>
                <td><%= customer.getFirstName() %></td>
                <td><%= customer.getLastName() %></td>
                <td><%= customer.getPhone() %></td>
                <td><%= customer.getEmail() %></td>
                <td><%= customer.getRole() %></td>
                <td><%= customer.isBanned() %></td>
                <td><%= customer.getBonusPoints() %></td>
                <td><%= customer.getLoyaltyPoints() %></td>
            </tr>
        <% } %>
    </table>
</body>
</html>