<%@ include file="/pages/include/include.jsp" %>
<html>
<head>
    <title></title>
    <style type="text/css">
        table {
            border-collapse: collapse;
        }

        td, th {
            border-bottom: 1px solid #ccc;
            border-right: 1px solid #ccc;
        }

        td:last-child, th:last-child {
            border-right: none;
        }

        tr:nth-child(odd) {
            background-color: #777;
            color: #ddd;
        }
    </style>
</head>
<body>
<%@ include file="/pages/include/welcomeHeader.jsp" %>
<fieldset>
    <legend><fmt:message key="list.order.orderListTitle"/></legend>
    <table>
        <thead>
        <th><fmt:message key="list.order.orderID"/></th>
        <th><fmt:message key="list.order.sum"/></th>
        <th><fmt:message key="list.order.paid"/></th>
        </thead>
        <c:forEach var="order" items="${orderList}">
            <tr>
                <td>${order.bankAccountID}</td>
                <td>${order.sum}</td>
                <td>${order.paid}</td>
            </tr>
        </c:forEach></table>
</fieldset>
</body>
</html>
