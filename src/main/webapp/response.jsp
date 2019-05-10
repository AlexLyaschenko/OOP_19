<%@ page import="java.util.ArrayList" %>
<%@ page import="com.univ.webService.dataModel.Abonent" %>
<%@ page import="com.univ.webService.servlet.Constants" %>
<%@ page import="com.univ.webService.dataModel.Tariff" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.sql.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String sessionId = session.getAttribute("sessionId").toString(),
            type = session.getAttribute("type").toString();
%>
<html>
<head>Title <%=session.getAttribute("sessionId")%> + 14</head>
<body>
<%if (sessionId.equals(Constants.ERROR)) {%>
<p>Failed!</p>
<p>Incorrect login or password</p>
<p>You entered login - <%=session.getAttribute("login")%>
</p>
<p>You entered password - <%=session.getAttribute("pass")%>
</p>
<br>
<a href="/mycontext">Try again!</a>
<%
        session.invalidate();
    }


    if (sessionId.equals(Constants.SHOW_USER_BY_ID) && type.equals("Admin")) {
        session.setAttribute("sessionId", Constants.LOGIN_ACCOUNT);
%>
<p>Name - <%=session.getAttribute("name")%>
</p>
<p>Surname - <%=session.getAttribute("surname")%>
</p>
<p>Number - <%=session.getAttribute("number")%>
</p>
<p>Balance - <%=session.getAttribute("balance")%>
</p>
<p>Service activation date - <%=session.getAttribute("connectionDate")%>
</p>
<p>Area - <%=session.getAttribute("area")%>
</p>
<p>Charge amount - <%=session.getAttribute("chargeAmount")%>
</p>
<p>Tariff name - <%=session.getAttribute("nameTariff")%>
</p>
<p>Tariff price - <%=session.getAttribute("priceTariff")%>
</p>
<p>Status - <%=session.getAttribute("status")%>
</p>
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
<%
    }

    if (sessionId.equals(Constants.ID_USER) && type.equals("Abonent")) {
%>
<table border="1">
    <tr>
        <td><p>Name -  <%=request.getAttribute("name")%>
        </p></td>
    </tr>
    <tr>
        <td><p>Surname -  <%=request.getAttribute("surname")%>
        </p></td>
    </tr>
    <tr>
        <td><p>Number -  <%=request.getAttribute("number")%>
        </p></td>
    </tr>
    <tr>
        <td><p>Login - <%=session.getAttribute("login")%>
        </p></td>
    </tr>
    <tr>
        <td><p>Balance - <%=session.getAttribute("balance")%>
        </p></td>
    </tr>
    <tr>
        <td><p>Bonus - <%=session.getAttribute("chargeAmount")%>
        </p></td>
    </tr>
    <tr>
        <td><p>Connection date - <%=session.getAttribute("connectionDate")%>
        </p></td>
    </tr>
    <tr>
        <td><p>Tariff - <%=session.getAttribute("tariff")%>
        </p></td>
    </tr>
    <tr>
        <td><p>Tariff price - <%=session.getAttribute("tariffPrice")%>
        </p></td>
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
    <%session.setAttribute("sessionId", Constants.LOGIN_ACCOUNT);%>
    <%
        ArrayList<Tariff> tariffs = (ArrayList<Tariff>) session.getAttribute("tariffs");
        for (Tariff tariff : tariffs) {
    %>
    <tr>
        <td><p><%=tariff.getNameTariff()%>
        </p></td>
        <td><p><%=tariff.getPrice()%>
        </p></td>
        <%if (tariff.getPrice() > Integer.parseInt(session.getAttribute("balance").toString()) + Integer.parseInt(session.getAttribute("chargeAmount").toString())) {%>
        <td>
            <form method="post" action="hello">
                <input type="hidden" name="sessionId" id="sessionId" value=<%=Constants.CHANGE_TARIFF%>>
                <input type="hidden" name="tarifId" id="tarifId" value=<%=tariff.getIdTariff()%>>
                <input style="margin-top: 10px; margin-left: 10px;" type="submit" value="submit" disabled>
            </form>
        </td>
        <%} else {%>
        <td>
            <form method="post" action="hello">
                <input type="hidden" name="sessionId" id="sessionId" value=<%=Constants.CHANGE_TARIFF%>>
                <input type="hidden" name="tarifId" id="tarifId" value=<%=tariff.getIdTariff()%>>
                <input style="margin-top: 10px; margin-left: 10px;" type="submit" value="submit">
            </form>
        </td>
        <%}%>
    </tr>
    <%}%>
</table>
<br>
<a href="/mycontext">Log out</a>
<% } %>


<%
    if (sessionId.equals(Constants.ID_ADMIN) && type.equals("Admin")) {
        session.setAttribute("sessionId", Constants.LOGIN_ACCOUNT);
%>
<p>Name -  <%=request.getAttribute("name")%>
</p>
<p>Surname -  <%=request.getAttribute("surname")%>
</p>
<p>Your role - admin</p>
<br>
<a href="/mycontext">Log out</a>
<br>
<h3>Users</h3>
<p>Show more info aboute user:</p>
<form method="post" action="hello">
    <input type="text" name="idAbonent" id="idAbonent-input">
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
    <%
        ArrayList<Abonent> abonentArr = (ArrayList<Abonent>) request.getAttribute("abonentArr");
        for (Abonent abonent : abonentArr) {
    %>
    <tr>
        <td><%=abonent.getName()%>
        </td>
        <td><%=abonent.getSurname()%>
        </td>
        <td><%=abonent.getPhoneNumber()%>
        </td>
        <td><%=abonent.getIdAbonent()%>
        </td>
    </tr>
    <%
        }
    %>
</table>
<% } %>
</body>
</html>