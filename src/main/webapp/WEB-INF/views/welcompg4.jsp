<%--
  Created by IntelliJ IDEA.
  User: kamel
  Date: 1/12/2017
  Time: 3:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="searchByCity" method="get">
    <select name="city">
          <option value="London" name ="city">London</option>
        <option value="Paris" name="city">Paris</option>
        <option value="Cork" name ="city">Cork</option>
        <option value="Berlin" name="city">Berlin</option>
        <input type="submit" value="Get Customers">
    </select>
</form>
<table border=1>
    <c:forEach var="myvar" items="${cList}">
        <tr>
            <td> ${myvar.getCustomerId()}</td> <td> ${myvar.getCompanyName()}</td>

            <%--<td><a href="delete?id=${myvar.customerId}"> Delete </a></td>--%>
        </tr>
    </c:forEach>
</table>
</body>
</html>
