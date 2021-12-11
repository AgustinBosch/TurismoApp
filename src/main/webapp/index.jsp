<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
<jsp:include page="partials/header.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="partials/navbar.jsp"></jsp:include>

	<main class="container-fluid p-4">

		<div class="row">
			<div class="col">
				<h1 class="text-center">Bienvenido a la Tierra Media</h1>
				<!-- Saluda al usuario(modificar) -->
				<c:if test="${user !=null}">
					<h2 class="text-center">
						Hola
						<c:out value="${user.getName()}"></c:out>
					</h2>
				</c:if>

				<h3 class="text-center">Disfruta de las mejores atracciones.</h3>
				<h3 class="text-center">
					<c:if test="${user == null}">
				Inicia Sesion para comprar y disfrutar de las mejores atracciones.
					</c:if>
				</h3>
				<h3 class="text-center">
					<c:if test="${user != null}">
				Hace Click en "Atracciones" en la parte superior izquierda para comprar tus atracciones favoritas.
			</c:if>
				</h3>
			</div>
			<c:if test="${user == null}">
				<jsp:include page="partials/formLoginRegister.jsp"></jsp:include>
			</c:if>
		</div>

	</main>

	<jsp:include page="partials/footer.jsp"></jsp:include>

</body>
</html>