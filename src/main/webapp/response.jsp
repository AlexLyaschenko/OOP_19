<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String sessionid = request.getAttribute("sessionId").toString(),
        type = request.getAttribute("type").toString();
%>

<html>
<%if (sessionid.equals("0")) {%>
<p>Failed!</p>
<p>Incorrect login or password</p>
<br>
<a href="/mycontext">Try again!</a>
<%}%>


<% if (!sessionid.equals("0") && type.equals("Abonent")) {%>
<p>Name -  <%=request.getAttribute("name")%></p>
<p>Surname -  <%=request.getAttribute("surname")%></p>
<p>Number -  <%=request.getAttribute("number")%></p>
</br>
<% } %>
<% if (!sessionid.equals("null") && type.equals("driver")) {%>
<p>Current role: Driver</p>
<% } %>
</html>