<%@ include file="/pages/include/include.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>
<%@ include file="/pages/include/welcomeHeader.jsp" %>
<fieldset>
        <legend><fmt:message key="info.client.title.group"/></legend>
    <table>
        <thead>
        <th><fmt:message key="info.client.title.id"/></th>
        <th><fmt:message key="info.client.title.firstname"/></th>
        <th><fmt:message key="info.client.title.lastname"/></th>
        <th><fmt:message key="info.client.title.role"/></th>
        <th><fmt:message key="info.client.title.bankAccountID"/></th>
        </thead>

            <tr>
                <td>
                        ${client.login}
                </td>
                <td>
                        ${client.firstName}
                </td>
                <td>
                        ${client.lastName}
                </td>
                <td>
                        ${client.role}
                </td>
                <td>
                        ${client.bankAccountID}
                </td>
            </tr>
    </table>
</fieldset>

</body>
</html>