<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<header>
	<nav class="navbar fixed-top navbar-expand-sm navbar-light">
		<div class="container-fluid">
			<i class="navbar-brand bi bi-gem" href="login.html"></i>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link" href="index.jsp">Inicio</a></li>
					<c:choose>
						<c:when test="${user != null}">
							<li class="nav-item"><a class="nav-link" href="/TurismoApp/productos.do">Productos</a></li>
							<li class="nav-item"><a class="nav-link" href="itinerario.jsp">Itinerario</a></li>
							<c:if test="${user.getAdmin()}">
								<li class="nav-item"><a class="nav-link" href="/TurismoApp/administrar.do">Administrar</a></li>
							</c:if>
						</c:when>
						<c:otherwise>
							<li class="nav-item"><a class="nav-link disabled" href="#">Productos</a></li>
						</c:otherwise>
					</c:choose>
				</ul>

				<c:if test="${user != null}">
					<div class="info-usuario fs-5 ">
						<i class="bi bi-piggy-bank-fill">
							<c:out value="${user.getOro()}"></c:out>
						</i>
						<i class="bi bi-clock-fill">
							<c:out value="${user.getTiempoDisponible()}"></c:out>
							<i class="bi bi-person-fill"></i>
							<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
								<c:out value="${user.getNombre().toUpperCase()}"></c:out>
							</button>
						</i>
						<a href="/TurismoApp/logout">
							<i id="boton-logout" class="bi bi-arrow-right-square-fill"></i>
						</a>

					</div>
				</c:if>

			</div>
		</div>
	</nav>

	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">
						Usuario:
						<c:out value="${user.getName().toUpperCase()}"></c:out>
					</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<h5>
						Tipo Preferido:
						<c:out value="${user.getTipoPref()}"></c:out>
					</h5>
					<h5>
						Oro Disponible:
						<c:out value="${user.getOro()}"></c:out>
					</h5>
					<h5>
						Tiempo Disponible:
						<c:out value="${user.getTiempoDisponible()}"></c:out>
					</h5>
				</div>

			</div>
		</div>
	</div>
</header>