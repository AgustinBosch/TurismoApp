<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
<jsp:include page="partials/header.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="partials/navbar.jsp"></jsp:include>

	<main class="container-fluid  p-4">
		<c:choose>
			<c:when test="${user !=null}">
				<p class="fs-1 text-center text-decoration-underline">
					Hola
					<c:out value="${user.getNombre()}"></c:out> :D
				</p>
				<c:choose>
					<c:when test="${user.getItinerario().getVisitas().isEmpty()}">
						<p class="fs-2 text-center">Usted no compro nada >:C</p>
					</c:when>

					<c:otherwise>
					<p class="fs-4 text-start">Este es el detalle de tu compra: </p>
						<table class="datatable table table-striped table-hover">
							<thead>
								<tr>
									<th>Nombre</th>
									<th>Genero</th>
									<th>Oro</th>
									<th>Tiempo</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${user.getItinerario().getVisitas()}" var="producto">
									<tr>
										<td><c:out value="${producto.getNombre()}"></c:out></td>
										<td><c:out value="${producto.getGenero()}"></c:out></td>
										<td><c:out value="${producto.getCosto()}"></c:out></td>
										<td><c:out value="${producto.getDuracion()}"></c:out></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<p class="fs-4 text-end">
							Gasto un total de:
							<c:out value="${user.getItinerario().getCostoTotal()}"></c:out>
							oros y un total de
							<c:out value="${user.getItinerario().getTiempoTotal()}"></c:out>
							horas.
						</p>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<h1>Por favor ingrese al sistema</h1>
			</c:otherwise>
		</c:choose>

	</main>
	<jsp:include page="partials/footer.jsp"></jsp:include>

</body>
</html>