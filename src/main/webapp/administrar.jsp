<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
<jsp:include page="partials/header.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="partials/navbar.jsp"></jsp:include>
	<main class="container-fluid p-4">

		<div class="accordion" id="accordionExample">
			<div class="accordion-item">
				<h2 class="accordion-header" id="headingOne">
					<button class="accordion-button" type="button"
						data-bs-toggle="collapse" data-bs-target="#collapseOne"
						aria-expanded="true" aria-controls="collapseOne">Atracciones</button>
				</h2>
				<div id="collapseOne" class="accordion-collapse collapse"
					aria-labelledby="headingOne" data-bs-parent="#accordionExample">
					<div class="accordion-body">

						<a href="/TurismoApp/crearAtraccion.do"><button
								class="card-link btn btn-primary mb-3">+ Nueva
								atraccion</button></a>
						<table class="datatable table table-striped table-hover">
							<thead>
								<tr>
									<th>id</th>
									<th>Nombre</th>
									<th>Genero</th>
									<th>Oro</th>
									<th>Tiempo</th>
									<th>Opciones</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${atracciones}" var="atr">
									<tr>
										<td><c:out value="${atr.getId()}"></c:out></td>
										<td><c:out value="${atr.getNombre()}"></c:out></td>
										<td><c:out value="${atr.getGenero()}"></c:out></td>
										<td><c:out value="${atr.getCosto()}"></c:out></td>
										<td><c:out value="${atr.getDuracion()}"></c:out></td>
										<td><a
											href="/TurismoApp/editarAtraccion.do?id=${atr.getId()}"><i
												class="bi bi-pencil-square"></i></a> <a
											href="/TurismoApp/borrarAtraccion.do?id=${atr.getId()}"><i
												class="bi bi-trash-fill"></i></a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>


					</div>
				</div>
			</div>
			<div class="accordion-item">
				<h2 class="accordion-header" id="headingTwo">
					<button class="accordion-button collapsed" type="button"
						data-bs-toggle="collapse" data-bs-target="#collapseTwo"
						aria-expanded="false" aria-controls="collapseTwo">Promociones</button>
				</h2>
				<div id="collapseTwo" class="accordion-collapse collapse"
					aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
					<div class="accordion-body">

						<a href="/TurismoApp/crearPromo.do"><button
								class="card-link btn btn-primary mb-3">+ Nueva promo</button></a>
						<table class="datatable table table-striped table-hover">
							<thead>
								<tr>
									<th>id</th>
									<th>Nombre</th>
									<th>Genero</th>
									<th>Atracciones</th>
									<th>Oro</th>
									<th>Tiempo</th>
									<th>Opciones</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${promociones}" var="promo">
									<tr>
										<td><c:out value="${promo.getId()}"></c:out></td>
										<td><c:out value="${promo.getNombre()}"></c:out></td>
										<td><c:out value="${promo.getGenero()}"></c:out></td>
										<td><c:out value="${promo.getMisAtraccionesString()}"></c:out></td>
										<td><c:out value="${promo.getCosto()}"></c:out></td>
										<td><c:out value="${promo.getDuracion()}"></c:out></td>
										<td><a
											href="/TurismoApp/editarPromo.do?id=${promo.getId()}"><i
												class="bi bi-pencil-square"></i></a> <a
											href="/TurismoApp/borrarPromo.do?id=${promo.getId()}"><i
												class="bi bi-trash-fill"></i></a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="accordion-item">
				<h2 class="accordion-header" id="headingThree">
					<button class="accordion-button collapsed" type="button"
						data-bs-toggle="collapse" data-bs-target="#collapseThree"
						aria-expanded="false" aria-controls="collapseThree">Usuarios</button>
				</h2>
				<div id="collapseThree" class="accordion-collapse collapse"
					aria-labelledby="headingThree" data-bs-parent="#accordionExample">
					<div class="accordion-body">

						<a href="/TurismoApp/crearUsuario.do"><button
								class="card-link btn btn-primary mb-3">+ Nuevo usuario</button></a>
						<table class="datatable table table-striped table-hover">
							<thead>
								<tr>
									<th>id</th>
									<th>Nombre</th>
									<th>Genero</th>
									<th>Oro</th>
									<th>Tiempo</th>
									<th>Admin</th>
									<th>Opciones</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${usuarios}" var="user">
									<tr>
										<td><c:out value="${user.getId()}"></c:out></td>
										<td><c:out value="${user.getNombre()}"></c:out></td>
										<td><c:out value="${user.getTipoPref()}"></c:out></td>
										<td><c:out value="${user.getOro()}"></c:out></td>
										<td><c:out value="${user.getTiempoDisponible()}"></c:out></td>
										<td><c:out value="${user.getAdmin()}"></c:out></td>

										<td><a
											href="/TurismoApp/editarUsuario.do?id=${user.getId()}"><i
												class="bi bi-pencil-square"></i></a><a
											href="/TurismoApp/borrarUsuario.do?id=${user.getId()}"><i
												class="bi bi-trash-fill"></i></a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

					</div>
				</div>
			</div>

			<!-- -- -->
			<div class="accordion" id="accordionPanelsStayOpenExample">
				<div class="accordion-item">
					<h2 class="accordion-header" id="panelsStayOpen-headingOne">
						<button class="accordion-button" type="button"
							data-bs-toggle="collapse"
							data-bs-target="#panelsStayOpen-collapseOne" aria-expanded="true"
							aria-controls="panelsStayOpen-collapseOne">Compras</button>
					</h2>
					<div id="panelsStayOpen-collapseOne"
						class="accordion-collapse collapse show"
						aria-labelledby="panelsStayOpen-headingOne">
						<div class="accordion-body">
							<table class="datatable table table-striped table-hover">
								<thead>
									<tr>
										<th>id</th>
										<th>Usuario</th>
										<th>Compra</th>

									</tr>
								</thead>
								<tbody>
									<c:forEach items="${usuarios}" var="user">
										<tr>

											<td><c:out value="${user.getId()}"></c:out></td>
											<td><c:out value="${user.getNombre()}"></c:out></td>
											<td><div class="dropdown">
													<a class="btn btn-secondary dropdown-toggle" href="#"
														role="button" id="dropdownMenuLink"
														data-bs-toggle="dropdown" aria-expanded="false">
														Detalles </a>

													<ul class="dropdown-menu"
														aria-labelledby="dropdownMenuLink">
														<c:choose>
															<c:when
																test="${user.getItinerario().getVisitas().isEmpty()}">
																<p >No hizo compras >:C</p>
															</c:when>
															<c:otherwise>
																<li><c:forEach
																		items="${user.getItinerario().getVisitas()}"
																		var="producto">

																		<ul>
																			<li class="compra"><c:out
																					value="${producto.getNombre()}"></c:out></li>
																			<li>Precio: <c:out
																					value="${producto.getCosto()}"></c:out>
																			</li>
																			<li>Tiempo: <c:out
																					value="${producto.getDuracion()}"></c:out></li>
																			<hr>
																		</ul>


																	</c:forEach></li>
															</c:otherwise>
															</c:choose>
													</ul>
													
												</div></td>









										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>

			</div>





		</div>
	</main>



	<jsp:include page="partials/footer.jsp"></jsp:include>
</body>
</html>