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
					<li class="nav-item"><a class="nav-link" href="login.html">Inicio</a>
					</li>
					<c:choose>
						<c:when test="${user != null}">
							<li class="nav-item"><a class="nav-link"
								href="atracciones.html">Atracciones</a></li>
							<c:if test="${user.getAdmin()}">
								<li class="nav-item"><a class="nav-link"
									href="administracion.html">Administrar</a></li>
							</c:if>
						</c:when>
						<c:otherwise>
							<li class="nav-item"><a class="nav-link disabled"
								href="atracciones.html">Atracciones</a></li>
						</c:otherwise>
					</c:choose>
					
				</ul>
				<a href="/TurismoApp/logout">Logout</a>
			</div>
		</div>
	</nav>
</header>