<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form:form method="post" action="asignarRestricciones" modelAttribute="usuario">
		<h3>Usuario: ${usuario.email}</h3>
	   	<p>En base a estos gustos te vamos a sugerir la comida.</p>
		<li>
			<c:forEach items="${restricciones}" var="restriccion">
				<input type="checkbox" name="${restriccion.nombre}" />
				<span> ${restriccion.nombre}</span>
				<span>${restriccion.id}</span>
				<br>
			</c:forEach>
		</li>
		<button class="btn btn-success">Confirmar gustos</button>
	</form:form>	
</body>
</html>