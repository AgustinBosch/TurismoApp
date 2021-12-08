<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
<jsp:include page="partials/header.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="partials/navbar.jsp"></jsp:include>

	<main class="container-fluid pb-5 mb-5">
		<div class="row">
			<c:forEach items="${sugeribles}" var="sug">
				<div class="col-sm-6 col-md-4 col-lg-3">
					<div class="card m-4">
						<!--<img src="..." class="card-img-top" alt="..."> -->
						<div class="card-body">
							<h5 class="card-title"><c:out value="${sug.getNombre()}"></c:out> </h5>
							<p class="card-text"><c:out value="${sug.getDescripcion()}"></c:out></p>
						</div>
						<ul class="list-group list-group-flush">
							<li class="list-group-item"><i class="bi bi-piggy-bank-fill">
									Coste: <c:out value="${sug.getCosto()}"></c:out></i></li>
							<li class="list-group-item"><i class="bi bi-clock-fill">
									Duracion: <c:out value="${sug.getDuracion()}"></c:out></i></li>
						</ul>
						<div class="card-body">
							<button class="card-link btn btn-primary">Ver mas</button>
							<button class="card-link btn btn-primary">Comprar!</button>
						</div>
					</div>
				</div>


			</c:forEach>
		</div>
	</main>

	<jsp:include page="partials/footer.jsp"></jsp:include>

</body>
</html>