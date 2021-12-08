<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
<jsp:include page="partials/header.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="partials/navbar.jsp"></jsp:include>

	<main class="container-fluid pb-5">
		<div class="row">
			<div class="col">
				<h1 class="text-center">Bienvenido a la Tierra Media</h1>
				<p class="text-center">texto generico sacado de por ahi</p>
			</div>
			<c:if test="${user == null}">
				<jsp:include page="partials/formLoginRegister.jsp"></jsp:include>
			</c:if>
		</div>
	</main>

	<jsp:include page="partials/footer.jsp"></jsp:include>

</body>
</html>