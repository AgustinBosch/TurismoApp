<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/header.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/partials/navbar.jsp"></jsp:include>
	<main class="container-fluid p-4">


		<c:if test="${atraccion != null && !atraccion.isValido()}">
			<div class="alert alert-danger">
				<p>Campos invalidos</p>
			</div>
		</c:if>

		<form action="/TurismoApp/editarAtraccion.do" method="post">
			<input type="hidden" name="id" value="${atraccion.getId()}">
			<jsp:include page="/views/atraccion/formulario.jsp"></jsp:include>
		</form>
	</main>

</body>
</html>