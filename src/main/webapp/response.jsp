<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="com.univ.webService.servlet.Constants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="sessionId" value="${sessionScope['sessionId'].toString()}"/>
<c:set var="type" value="${sessionScope['type'].toString()}"/>

<html>
<head>Title <%=session.getAttribute("sessionId")%></head>
<body>

<c:if test="${sessionId eq Constants.ERROR}">
    <p>Failed!</p>
    <p>Incorrect login or password</p>
    <p>You entered login - <c:out value="${sessionScope['login']}"/></p>
    <p>You entered password - <c:out value="${sessionScope['pass']}"/></p>
    <br>
    <a href="/mycontext">Try again!</a>
    <%session.invalidate();%>
</c:if>

<c:if test="${(sessionId eq Constants.SHOW_USER_BY_ID) && (type eq 'Admin')}">
    <c:set var="sessionId" value="${Constants.LOGIN_ACCOUNT}" scope="session"/>
    <p>Name - <c:out value="${sessionScope['name']}"/></p>
    <p>Surname - <c:out value="${sessionScope['surname']}"/></p>
    <p>Number - <c:out value="${sessionScope['number']}"/></p>
    <p>Balance - <c:out value="${sessionScope['balance']}"/></p>
    <p>Service activation date - <c:out value="${sessionScope['connectionDate']}"/></p>
    <p>Area - <c:out value="${sessionScope['value']}"/></p>
    <p>Bonus - <c:out value="${sessionScope['chargeAmount']}"/></p>
    <p>Tariff name - <c:out value="${sessionScope['nameTariff']}"/></p>
    <p>Tariff price - <c:out value="${sessionScope['priceTariff']}"/></p>
    <p>Status - <c:out value="${sessionScope['status']}"/></p>
    <form method="post" action="hello">
        <input type="hidden" name="sessionId" id="sessionId" value="12">
        <input style="margin-top: 10px; margin-left: 10px;" type="submit" value="change status">
    </form>
    <form method="post" action="hello">
        <input type="hidden" name="sessionId" id="sessionId" value="2">
        <input style="margin-top: 10px; margin-left: 10px;" type="submit" value="go to the previous page">
    </form>
    <br>
    <a href="/mycontext">Log out</a>
</c:if>

<c:if test="${(sessionId eq Constants.ID_USER) && (type eq 'Abonent')}">
    <table border="1">
        <tr>
            <td><p>Name - <c:out value="${sessionScope['name']}"/></p></td>
        </tr>
        <tr>
            <td><p>Surname - <c:out value="${sessionScope['surname']}"/></p></td>
        </tr>
        <tr>
            <td><p>Number - <c:out value="${sessionScope['number']}}"/></p></td>
        </tr>
        <tr>
            <td><p>Login - <c:out value="${sessionScope['login']}"/></p></td>
        </tr>
        <tr>
            <td><p>Balance - <c:out value="${sessionScope['balance']}"/></p></td>
        </tr>
        <tr>
            <td><p>Bonus - <c:out value="${sessionScope['chargeAmount']}"/></p></td>
        </tr>
        <tr>
            <td><p>Connection date - <c:out value="${sessionScope['connectionDate']}"/></p></td>
        </tr>
        <tr>
            <td><p>Tariff - <c:out value="${sessionScope['tariff']}"/></p></td>
        </tr>
        <tr>
            <td><p>Tariff price - <c:out value="${sessionScope['tariffPrice']}"/></p></td>
        </tr>
    </table>
    <br>
    <br>
    <table border="1">
        <tr>
            <td><p><strong>Affordable tariff plans in your area</strong></p>
            </td>
            <td><p>Price</p></td>
            <td></td>
        </tr>
        <c:set var="sessionId" value="${Constants.LOGIN_ACCOUNT}" scope="session"/>
        <c:forEach items="${sessionScope['tariffs']}" var="tariff">
            <tr>
                <td><p><c:out value="${tariff.getNameTariff()}"/></p></td>
                <td><p><c:out value="${tariff.getPrice()}"/></p></td>

                <c:if test="${tariff.getPrice() > sessionScope['balance'] + sessionScope['bonus']}">
                    <td>
                        <form method="post" action="hello">
                            <input type="hidden" name="sessionId" id="sessionId" value=<c:out
                                    value="${Constants.CHANGE_TARIFF}"/>>
                            <input type="hidden" name="tarifId" id="tarifId" value="${tariff.getIdTariff()}">
                            <input style="margin-top: 10px; margin-left: 10px;" type="submit" value="submit" disabled>
                        </form>
                    </td>
                </c:if>
                <c:if test="${tariff.getPrice() <= sessionScope['balance'] + sessionScope['bonus']}">
                    <td>
                        <form method="post" action="hello">
                            <input type="hidden" name="sessionId" id="sessionId" value=<c:out
                                    value="${Constants.CHANGE_TARIFF}"/>>
                            <input type="hidden" name="tarifId" id="tarifId" value="${tariff.getIdTariff()}">
                            <input style="margin-top: 10px; margin-left: 10px;" type="submit" value="submit">
                        </form>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <br>
    <a href="/mycontext">Log out</a>
</c:if>

<c:if test="${sessionId eq Constants.ID_ADMIN && type eq 'Admin'}">
    <p>Name - <c:out value="${requestScope['name']}"/></p>
    <p>Surname - <c:out value="${requestScope['surname']}"/></p>
    <p>Your role - admin</p>
    <br>
    <a href="/mycontext">Log out</a>
    <br>
    <h3>Users</h3>
    <p>Show more info about user:</p>
    <form method="post" action="hello">
        <input type="text" name="idAbonent" id="idAbonent-input" required="required" pattern="[1-9][0-9]{0,2}">
        <input type="hidden" name="sessionId" id="sessionId" value="109Admin">
        <input style="margin-top: 10px; margin-left: 10px;" type="submit" value="submit">
    </form>
    <table border="1">
        <tr>
            <td>Name</td>
            <td>Surname</td>
            <td>Number</td>
            <td>Id</td>
        </tr>
        <c:forEach items="${requestScope['abonentArr']}" var="abonent">
            <tr>
                <td><c:out value="${abonent.getName()}"/></td>
                <td><c:out value="${abonent.getSurname()}"/></td>
                <td><c:out value="${abonent.getPhoneNumber()}"/></td>
                <td><c:out value="${abonent.getIdAbonent()}"/></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>