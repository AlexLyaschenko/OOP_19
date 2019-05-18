<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="com.univ.webService.servlet.Constants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="sessionId" value="${sessionScope['sessionId'].toString()}"/>
<c:set var="type" value="${sessionScope['type'].toString()}"/>

<html>
<link rel="stylesheet" href="css/userStyle.css">
<head><%=session.getAttribute("sessionId")%>
</head>
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

<c:if test="${(sessionId eq Constants.SHOW_USER_BY_ID) && (type eq Constants.ADMIN)}">
    <c:set var="sessionId" value="${Constants.LOGIN_ACCOUNT}" scope="session"/>
    <p>Name - <c:out value="${sessionScope['name']}"/></p>
    <p>Surname - <c:out value="${sessionScope['surname']}"/></p>
    <p>Number - <c:out value="${sessionScope['number']}"/></p>
    <p>Balance - <c:out value="${sessionScope['balance']}"/></p>
    <p>Service activation date - <c:out value="${sessionScope['connectionDate']}"/></p>
    <p>Area - <c:out value="${sessionScope['area']}"/></p>
    <p>Bonus - <c:out value="${sessionScope['chargeAmount']}"/></p>
    <p>Tariff name - <c:out value="${sessionScope['nameTariff']}"/></p>
    <p>Tariff price - <c:out value="${sessionScope['priceTariff']}"/></p>
    <p>Status - <c:out value="${sessionScope['status']}"/></p>
    <form method="post" action="main_page">
        <input type="hidden" name="sessionId" id="sessionId" value=<c:out value="${Constants.CHANGE_USER_STATUS}"/>>
            <%--        <input style="margin-top: 10px; margin-left: 10px;" type="submit" value="change status">--%>
        <button type="submit">change status</button>
    </form>
    <form method="post" action="main_page">
        <input type="hidden" name="sessionId" id="sessionId" value=<c:out value="${Constants.LOGIN_ACCOUNT}"/>>
            <%--        <input style="margin-top: 10px; margin-left: 10px;" type="submit" value="go to the previous page">--%>
        <button type="submit">go to the previous page</button>
    </form>
    <br>
    <a href="/mycontext">Log out</a>
</c:if>

<c:if test="${(sessionId eq Constants.ID_USER) && (type eq Constants.ABONENT)}">
    <table border="1">
        <tr>
            <td><p>Name - <c:out value="${requestScope['name']}"/></p></td>
        </tr>
        <tr>
            <td><p>Surname - <c:out value="${requestScope['surname']}"/></p></td>
        </tr>
        <tr>
            <td><p>Number - <c:out value="${requestScope['number']}"/></p></td>
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

                <c:if test="${tariff.getPrice() > (sessionScope['balance'] + sessionScope['chargeAmount'])}">
                    <td>
                        <form method="post" action="main_page">
                            <input type="hidden" name="sessionId" id="sessionId" value=<c:out
                                    value="${Constants.CHANGE_TARIFF}"/>>
                            <input type="hidden" name="tarifId" id="tarifId" value="${tariff.getIdTariff()}">
                                <%--                            <input style="margin-top: 10px; margin-left: 10px;" type="submit" value="submit" disabled>--%>
                            <button type="submit" disabled>submit</button>
                        </form>
                    </td>
                </c:if>
                <c:if test="${tariff.getPrice() <= (sessionScope['balance'] + sessionScope['chargeAmount'])}">
                    <td>
                        <form method="post" action="main_page">
                            <input type="hidden" name="sessionId" id="sessionId" value=<c:out
                                    value="${Constants.CHANGE_TARIFF}"/>>
                            <input type="hidden" name="tarifId" id="tarifId" value="${tariff.getIdTariff()}">
                                <%--                            <input style="margin-top: 10px; margin-left: 10px;" type="submit" value="submit">--%>
                            <button type="submit">submit</button>
                        </form>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <br>
    <a href="/mycontext">Log out</a>
</c:if>

<c:if test="${sessionId eq Constants.ID_ADMIN && type eq Constants.ADMIN}">
    <c:set var="sessionId" value="${Constants.LOGIN_ACCOUNT}" scope="session"/>
    <h2>Name - <c:out value="${requestScope['name']}"/></h2>
    <h2>Surname - <c:out value="${requestScope['surname']}"/></h2>
    <h2>Your role - admin</h2>
    <br>
    <a href="/mycontext">Log out</a>
    <br>
    <h1>Users</h1>
    <p>Show more info about user:</p>
    <form method="get" action="main_page">
        <input type="text" name="idAbonent" id="idAbonent-input" required="required" pattern="[1-9][0-9]{0,2}">
        <input type="hidden" name="sessionId" id="sessionId" value=<c:out value="${Constants.SHOW_USERS}"/>>
            <%--        <input style="margin-top: 10px; margin-left: 10px;" type="submit" value="submit">--%>
        <button type="submit">submit</button>
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
    <br>
    <br>
    <p>Show number of users in region by week</p>
    <form method="get" action="main_page">
        <input type="text" name="idWeek" id="idWeek" required="required" pattern="[1-9][0-9]{0,2}">
        <input type="hidden" name="sessionId" id="sessionId" value=<c:out value="${Constants.SHOW_PEOPLE_IN_REGION}"/>>
        <button type="submit">submit</button>
    </form>
    <br>
    <br>
    <p>Show number of users for each tariff by week</p>
    <form method="get" action="main_page">
        <input type="text" name="idWeek" id="idWeek" required="required" pattern="[1-9][0-9]{0,2}">
        <input type="hidden" name="sessionId" id="sessionId" value=<c:out value="${Constants.SHOW_PEOPLE_IN_TARIFF}"/>>
        <button type="submit">submit</button>
    </form>
    <br>
    <br>
    <p>Show history for each user by week</p>
    <form method="get" action="main_page">
        <input type="text" name="idWeek" id="idWeek" required="required" pattern="[1-9][0-9]{0,2}">
        <input type="hidden" name="sessionId" id="sessionId" value=<c:out value="${Constants.SHOW_HISTORY}"/>>
        <button type="submit">submit</button>
    </form>
    <br>
    <br>
    <p>Add statistics of users by the region for the current week</p>
    <form method="post" action="main_page">
        <input type="hidden" name="sessionId" id="sessionId" value=<c:out value="${Constants.ADD_TO_DB_BY_REGION}"/>>
        <button type="submit">submit</button>
    </form>
    <br>
    <br>
    <p>Add statistics of users by the tariff for the current week</p>
    <form method="post" action="main_page">
        <input type="hidden" name="sessionId" id="sessionId" value=<c:out value="${Constants.ADD_TO_DB_BY_TARIFF}"/>>
        <button type="submit">submit</button>
    </form>
</c:if>
<c:if test="${sessionId eq Constants.SHOW_PEOPLE_IN_REGION}">
    <c:if test="${requestScope['numberOfPeopleArr'].size() == 0}">
        <h2>Incorrect week, please try again</h2>
    </c:if>
    <c:if test="${requestScope['numberOfPeopleArr'].size() != 0}">
        <table>
            <tr>
                <td>Week number</td>
                <td>Area id</td>
                <td>Number of users</td>
            </tr>
            <c:forEach items="${requestScope['numberOfPeopleArr']}" var="people">
                <tr>
                    <td><c:out value="${people.getIdWeek()}"/></td>
                    <td><c:out value="${people.getIdArea()}"/></td>
                    <td><c:out value="${people.getNumberOfPeople()}"/></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <form method="post" action="main_page">
        <input type="hidden" name="sessionId" id="sessionId" value=<c:out value="${Constants.LOGIN_ACCOUNT}"/>>
        <button type="submit">go to the previous page</button>
    </form>
</c:if>
<c:if test="${sessionId eq Constants.SHOW_PEOPLE_IN_TARIFF}">
    <c:if test="${requestScope['peopleInTariffArr'].size() == 0}">
        <h2>Incorrect week, please try again</h2>
    </c:if>
    <c:if test="${requestScope['peopleInTariffArr'].size() != 0}">
        <table>
            <tr>
                <td>Week number</td>
                <td>idTariff</td>
                <td>Number of users</td>
            </tr>
            <c:forEach items="${requestScope['peopleInTariffArr']}" var="people">
                <tr>
                    <td><c:out value="${people.getIdWeek()}"/></td>
                    <td><c:out value="${people.getIdTariff()}"/></td>
                    <td><c:out value="${people.getNumberOfPeople()}"/></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <form method="post" action="main_page">
        <input type="hidden" name="sessionId" id="sessionId" value=<c:out value="${Constants.LOGIN_ACCOUNT}"/>>
            <%--        <input style="margin-top: 10px; margin-left: 10px;" type="submit" value="go to the previous page">--%>
        <button type="submit">go to the previous page</button>
    </form>
</c:if>
<c:if test="${sessionId eq Constants.SHOW_HISTORY}">
    <c:if test="${requestScope['historyArr'].size() == 0}">
        <h2>Incorrect week, please try again</h2>
    </c:if>
    <c:if test="${requestScope['historyArr'].size() != 0}">
        <table>
            <tr>
                <td>Week number</td>
                <td>idAbonent</td>
                <td>Tariff</td>
                <td>Status</td>
                <td>Time</td>
            </tr>
            <c:forEach items="${requestScope['historyArr']}" var="history">
                <tr>
                    <td><c:out value="${history.getIdWeek()}"/></td>
                    <td><c:out value="${history.getIdAbonent()}"/></td>
                    <td><c:out value="${history.getTariffName()}"/></td>
                    <td><c:out value="${history.getStatus()}"/></td>
                    <td><c:out value="${history.getDate()}"/></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <form method="post" action="main_page">
        <input type="hidden" name="sessionId" id="sessionId" value=<c:out value="${Constants.LOGIN_ACCOUNT}"/>>
            <%--        <input style="margin-top: 10px; margin-left: 10px;" type="submit" value="go to the previous page">--%>
        <button type="submit">go to the previous page</button>
    </form>
</c:if>
</body>
</html>