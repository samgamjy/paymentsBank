<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/pages/include/include.jsp" %>
<html>
<head>
    <title></title>
    <style type="text/css">
        table {
            border: solid;
        }

        td, th {
            border-top: 2px double #ccc;
            border-left: 2px double #ccc;
            border-bottom: 2px double #ccc;
            border-right: 2px double #ccc;

        }
    </style>
</head>
<body>
<fieldset>
    <legend><fmt:message key="client.control.service.controlClientTitle"/> <b><%=username%>
    </b></legend>
    <table width="800">
        <thead>
        <th><fmt:message key="client.control.service.userInfo"/></th>
        </thead>
        <tr>
            <td><fmt:message key="client.control.service.userInfo.login"/></td>
            <td><fmt:message key="client.control.service.userInfo.firstName"/></td>
            <td><fmt:message key="client.control.service.userInfo.lastName"/></td>
            <td><fmt:message key="client.control.service.userInfo.bankAccountID"/></td>
        </tr>
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
                ${client.bankAccountID}
            </td>
        </tr>
    </table>
    <br>
    <table width="800" , align="justify">
        <thead>
        <th><fmt:message key="client.control.service.bankInfo"/></th>
        </thead>
        <tr>
            <td><fmt:message key="client.control.service.bankInfo.bankAccountID"/></td>
            <td><fmt:message key="client.control.service.bankInfo.sum"/></td>
            <td><fmt:message key="client.control.service.bankInfo.valid"/></td>
            <td><fmt:message key="client.control.service.bankInfo.blocked"/></td>
            <td><fmt:message key="client.control.service.bankInfo.creditCardID"/></td>
            <td><fmt:message key="client.control.service.bankInfo.orderList"/></td>
        </tr>
        <tr>
            <td>
                ${bankAccount.id}
            </td>
            <td>
                ${bankAccount.sum}
            </td>
            <td>
                ${bankAccount.valid}
            </td>
            <td>
                ${bankAccount.blocked}
            </td>
            <td>
                ${bankAccount.creditCardID}
            </td>
            <td>
                <input name="orderList" list="orderList">
                <datalist id="orderList">
                    <c:forEach var="order" items="${orderList}">
                        <option>${order.sum}</option>
                    </c:forEach>
                </datalist>
            </td>
        </tr>
    </table>
    <br>
    <table width="800" , align="justify">
        <thead>
        <th><fmt:message key="client.control.service.availableEvents"/></th>
        </thead>
        <tr>
            <td>
                <a href="/PaymentControl?page=BLOCK_CREDIT_CARD&bankAccountID=${client.bankAccountID}&blocked=${!bankAccount.blocked}"><c:choose><c:when
                        test="${bankAccount.blocked}"><fmt:message
                        key="client.control.service.enableEvents.unblock"/></c:when><c:otherwise><fmt:message
                        key="client.control.service.enableEvents.block"/></c:otherwise></c:choose>></a>
            </td>
        </tr>
        <tr>
            <td>
                <form action="/PaymentControl" method="get">
                    <input type="hidden" name="page" value="CREATE_ORDER">
                    <input type="hidden" name="bankAccountID" value="${client.bankAccountID}">
                    <table width="70%">
                        <thead>
                        <th><fmt:message key="client.control.service.enableEvents.createOrder"/></th>
                        </thead>
                        <tr>
                            <td>
                                <label for="sum"><fmt:message
                                        key="client.control.service.enableEvents.createOrder.sum"/></label>
                                <br>
                                <input type="number" id="sum" name="sum" value="0"/>
                            </td>
                        </tr>
                        <tfoot>
                        <tr>
                            <td><fmt:message
                                    key="client.control.service.enableEvents.createOrder"/></td>
                            <td align="right" colspan="2">
                                <input type="submit" value=<fmt:message
                                        key="client.control.service.enableEvents.createOrder.create"/>>
                            </td>
                        </tr>
                        </tfoot>

                    </table>

                </form>
            </td>
        </tr>
    </table>
</fieldset>

</body>
</html>
