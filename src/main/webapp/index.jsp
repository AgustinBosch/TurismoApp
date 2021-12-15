<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
<jsp:include page="partials/header.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="partials/navbar.jsp"></jsp:include>

	<main class="container-fluid p-4">



		<div id="carouselExampleSlidesOnly" class="carousel slide"
			data-bs-ride="carousel">
			<div class="row">
				<div class="col">


					<div class="carousel-item active">
						<c:choose>
							<c:when test="${user !=null}">
								<h2 class="text-center">
									Hola
									<c:out value="${user.getNombre().toUpperCase()}"></c:out>
								</h2>
							</c:when>
							<c:otherwise>
								<h3 class="text-center">Inicia Sesion para comprar y
									disfrutar de las mejores atracciones.</h3>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="carousel-inner">
						<div class="carousel-item ">
							<h1 class="text-center">Bienvenido a la Tierra Media</h1>
						</div>
						<div class="carousel-item">
							<h3 class="text-center">Disfruta de las mejores atracciones.</h3>
						</div>
						<div class="carousel-item">
							<c:choose>
								<c:when test="${user !=null}">
									<h3 class="text-center">Hace Click en "Productos" en la
										parte superior izquierda para comprar tus atracciones
										favoritas.</h3>
								</c:when>
								<c:otherwise>
									<h3 class="text-center">Las mejores ofertas en Productos.</h3>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<c:if test="${user == null }">
						<div class="row">
							<div class="col gif"></div>
							<div class="col gif">
								<img alt=""
									src="https://cloud.educaplay.com/recursos/107/3439481/imagen_1_1513357774.gif">
							</div>
							<div class="col gif"></div>

						</div>
					</c:if>
				</div>



				<c:if test="${user == null}">
					<jsp:include page="partials/formLoginRegister.jsp"></jsp:include>
				</c:if>

			</div>
		</div>


	</main>

	<jsp:include page="partials/footer.jsp"></jsp:include>

</body>
</html>