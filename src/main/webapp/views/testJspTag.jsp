<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<body>
<% out.println("Testing JSP integration!"); %>
<% System.out.println("[LOG message] Testing JSP integration!"); %>
<%-- Using el/jsp --%>
<jsp:useBean id="teste" class="com.hotelbooking.controller.UserController"/>
${teste.teste}

<%-- using jstl --%>
<c:forEach var="contato" items="${teste.teste}">
    ${contato}
</c:forEach>
</body>
</html>