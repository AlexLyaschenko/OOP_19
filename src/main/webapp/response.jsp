<%@ page import="java.util.ArrayList" %>
<%@ page import="com.univ.webService.Abonent" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String sessionId = session.getAttribute("sessionId").toString(),
            type = session.getAttribute("type").toString();
%>

<html>
<%if (sessionId.equals("0")) {%>
<p>Failed!</p>
<p>Incorrect login or password</p>
<p>You entered login - <%=session.getAttribute("login")%>
</p>
<p>You entered password - <%=session.getAttribute("pass")%>
</p>
<br>
<a href="/mycontext">Try again!</a>
<%}%>
<%if (sessionId.equals("102") && type.equals("Admin")) {%>
<h1>Changed!</h1>
<h1><%=session.getAttribute("testSession")%>
</h1>
<%}%>
<%if (sessionId.equals("8") && type.equals("Admin")) {%>
<%session.setAttribute("sessionId", "102");%>
<p>Name - <%=session.getAttribute("name")%>
<p>Surname - <%=session.getAttribute("surname")%>
<p>Number - <%=session.getAttribute("number")%>
<p>Balance - <%=session.getAttribute("balance")%>
<p>Service activation date - <%=session.getAttribute("connectionDate")%>
<p>Area - <%=session.getAttribute("area")%>
<p>Charge amount - <%=session.getAttribute("chargeAmount")%>
<p>Tariff name - <%=session.getAttribute("nameTariff")%>
<p>Tariff price - <%=session.getAttribute("priceTariff")%>
<form method="post" action="hello">
    <input type="submit" value="<%=session.getAttribute("status")%>" id="stAbonent" name="stAbonent"/>
    <input type="hidden" value="<%=session.getAttribute("billingId")%>" id="billingId" name="billingId"/>
</form>
<%}%>
<%if (sessionId.equals("100") && type.equals("Abonent")) {%>
<p>Name -  <%=session.getAttribute("name")%>
</p>
<p>Surname -  <%=session.getAttribute("surname")%>
</p>
<p>Number -  <%=session.getAttribute("number")%>
</p>
<p>Your role - user</p>
<br>
<% } %>
<% if (sessionId.equals("7") && type.equals("Admin")) {%>
<%session.setAttribute("sessionId", "109");%>
<p>Name -  <%=session.getAttribute("name")%>
</p>
<p>Surname -  <%=session.getAttribute("surname")%>
</p>
</p>
<p>Your role - admin</p>
<br>
<br>
<h3>Users</h3>
<h3><%=session.getAttribute("idAbonent")%>
</h3>
<table border="1">
    <tr>
        <td>Name</td>
        <td>Surname</td>
        <td>Number</td>
        <td>More info</td>
    </tr>
    <%
        ArrayList<Abonent> abonentArr = (ArrayList<Abonent>) session.getAttribute("abonentArr");
        for (Abonent abonent : abonentArr) {
    %>
    <tr>
        <td><%=abonent.getName()%>
        </td>
        <td><%=abonent.getSurname()%>
        </td>
        <td><%=abonent.getPhoneNumber()%>
        </td>
        <td>
            <form method="post" action="hello">
                <input style="margin-top: 10px; margin-left: 10px;" type="submit" name = "view" value=>
                <input type="hidden" name = "idAbonent" value =<%=abonent.getIdAbonent()%>>
            </form>
        </td>
    </tr>

    <%
        }
    %>

</table>
<% } %>
</html>