<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<header>
	<nav class="navbar navbar-expand-sm navbar-light">
		<div class="container-fluid">
			<i class="navbar-brand bi bi-gem" href="login.html"></i>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link" href="index.jsp">Inicio</a>
					</li>
					<c:choose>
						<c:when test="${user != null}">
							<li class="nav-item"><a class="nav-link"
								href="/TurismoApp/atracciones.do">Atracciones</a></li>
							<c:if test="${user.getAdmin()}">
								<li class="nav-item"><a class="nav-link"
									href="administracion.jsp">Administrar</a></li>
							</c:if>
						</c:when>
						<c:otherwise>
							<li class="nav-item"><a class="nav-link disabled"
								href="#">Atracciones</a></li>
						</c:otherwise>
					</c:choose>

				</ul>

				<c:if test="${user != null}">
					<div class="info-usuario fs-5">
						<i class="bi bi-piggy-bank-fill"> <c:out value="${user.getOro()}"></c:out></i> 
						<i class="bi bi-clock-fill"> <c:out value="${user.getTiempoDisponible()}"></c:out></i> 
						<i class="bi bi-person-fill"> <c:out value="${user.getName()}"></c:out></i>
						<a href="/TurismoApp/logout"><i id="boton-logout" class="bi bi-arrow-right-square-fill"></i></a>
					</div>
				</c:if>

			</div>
		</div>
	</nav>
</header>