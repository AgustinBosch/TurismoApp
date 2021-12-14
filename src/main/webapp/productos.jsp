<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
<jsp:include page="partials/header.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="partials/navbar.jsp"></jsp:include>

	<main class="container-fluid p-4">
		<div class="row" id="atracciones">
			<c:forEach items="${sugeribles}" var="sug">
				<div class="col-sm-6 col-md-4 col-lg-3">
					<div class="card m-4">
						<!--<img src="..." class="card-img-top" alt="..."> -->
						<div class="card-body card-descripcion text-center">
							<h5 class="card-title">
								<c:out value="${sug.getNombre()}"></c:out>
							</h5>
							<p class="card-text">
								<c:out value="${sug.getDescripcion()}"></c:out>
							</p>
						</div>
						<ul class="list-group list-group-flush">
							<li class="list-group-item"><i class="bi bi-bag-check-fill"> Genero: <c:out value="${sug.getGenero()}"></c:out></i></li>
							<li class="list-group-item"><i class="bi bi-piggy-bank-fill"> Coste: <c:out value="${sug.getCosto()}"></c:out></i></li>
							<li class="list-group-item"><i class="bi bi-clock-fill"> Duracion: <c:out value="${sug.getDuracion()}"></c:out></i></li>
						</ul>
						<div class="card-body">
							<c:choose>
								<c:when test="${user.yaCompreSugerible(sug)}">
									<a href="#"><button class="card-link btn btn-primary disabled">Ya lo compre!</button></a>
								</c:when>
								<c:when test="${user.puedoComprarSugerible(sug) && sug.tieneCupo()}">
									<a href="/TurismoApp/comprar.do?id=${sug.getId()}&promo=${sug.esPromo()}"><button class="card-link btn btn-primary">Comprar!</button></a>
								</c:when>
								<c:otherwise>
									<a href="#"><button class="card-link btn btn-primary disabled">No se puede Comprar</button></a>
								</c:otherwise>
							</c:choose>



							<%-- 							<a href="/TurismoApp/comprar.do?id=${sug.getId()}&promo=${sug.esPromo()}"><button class="card-link btn btn-primary">Comprar!</button></a> --%>
						</div>
					</div>
				</div>


			</c:forEach>
		</div>
		
		
		
	</main>

	<jsp:include page="partials/footer.jsp"></jsp:include>

</body>
</html>