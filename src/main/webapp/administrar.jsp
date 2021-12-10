<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
<jsp:include page="partials/header.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="partials/navbar.jsp"></jsp:include>
	<main class="container-fluid pb-5">

		<div class="accordion" id="accordionExample">
			<div class="accordion-item">
				<h2 class="accordion-header" id="headingOne">
					<button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">Atracciones</button>
				</h2>
				<div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
					<div class="accordion-body">

						<table class="table table-striped table-hover">
							<tr>
								<th>id</th>
								<th>Nombre</th>
								<th>Genero</th>
								<th>Oro</th>
								<th>Tiempo</th>
								<th>Opciones</th>
							</tr>
							<c:forEach items="${atracciones}" var="atr">
								<tr>
									<td><c:out value="${atr.getId()}"></c:out></td>
									<td><c:out value="${atr.getNombre()}"></c:out></td>
									<td><c:out value="${atr.getGenero()}"></c:out></td>
									<td><c:out value="${atr.getCosto()}"></c:out></td>
									<td><c:out value="${atr.getDuracion()}"></c:out></td>
									<td>
									<a href="/TurismoApp/editarAtraccion.do?id=${atr.getId()}"><i class="bi bi-pencil-square"></i></a>
									<a href="/TurismoApp/borrarAtraccion.do?id=${atr.getId()}"><i class="bi bi-trash-fill"></i></a>
									</td>
								</tr>
							</c:forEach>
							
							
						</table>
					<a><button class="card-link btn btn-primary">+ Nueva atraccion</button></a>

					</div>
				</div>
			</div>
			<div class="accordion-item">
				<h2 class="accordion-header" id="headingTwo">
					<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">Promociones</button>
				</h2>
				<div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
					<div class="accordion-body">

						<table class="table table-striped table-hover">
							<tr>
								<th>id</th>
								<th>Nombre</th>
								<th>Genero</th>
								<th>Oro</th>
								<th>Tiempo</th>
								<th>Opciones</th>
							</tr>
							<c:forEach items="${promociones}" var="promo">
								<tr>
									<td><c:out value="${promo.getId()}"></c:out></td>
									<td><c:out value="${promo.getNombre()}"></c:out></td>
									<td><c:out value="${promo.getGenero()}"></c:out></td>
									<td><c:out value="${promo.getCosto()}"></c:out></td>
									<td><c:out value="${promo.getDuracion()}"></c:out></td>
									<td>
									<a href="/TurismoApp/editarPromo.do?id=${promo.getId()}"><i class="bi bi-pencil-square"></i></a>
									<a href="/TurismoApp/borrarPromo.do?id=${promo.getId()}"><i class="bi bi-trash-fill"></i></a>
									</td>

								</tr>
							</c:forEach>
						</table>
					<a><button class="card-link btn btn-primary">+ Nueva promo</button></a></tr>
					</div>
				</div>
			</div>
			<div class="accordion-item">
				<h2 class="accordion-header" id="headingThree">
					<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">Usuarios</button>
				</h2>
				<div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionExample">
					<div class="accordion-body">

						<table class="table table-striped table-hover">
							<tr>
								<th>id</th>
								<th>Nombre</th>
								<th>Genero</th>
								<th>Oro</th>
								<th>Tiempo</th>
								<th>Admin</th>
								<th>Opciones</th>
							</tr>
							<c:forEach items="${usuarios}" var="user">
								<tr>
									<td><c:out value="${user.getId()}"></c:out></td>
									<td><c:out value="${user.getName()}"></c:out></td>
									<td><c:out value="${user.getTipoPref()}"></c:out></td>
									<td><c:out value="${user.getOro()}"></c:out></td>
									<td><c:out value="${user.getTiempoDisponible()}"></c:out></td>
									<td><c:out value="${user.getAdmin()}"></c:out></td>

									<td>
									<a href="/TurismoApp/editarUsuario.do?id=${user.getId()}"><i class="bi bi-pencil-square"></i></a>
									<a href="/TurismoApp/borrarUsuario.do?id=${user.getId()}"><i class="bi bi-trash-fill"></i></a>
									</td>
								</tr>
							</c:forEach>
						</table>
					<a><button class="card-link btn btn-primary">+ Nuevo usuario</button></a>
					</div>
				</div>
			</div>
		</div>
	</main>
	<jsp:include page="partials/footer.jsp"></jsp:include>
</body>
</html>