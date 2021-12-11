<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
<jsp:include page="partials/header.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="partials/navbar.jsp"></jsp:include>

	<div class="itinerario-font">
	<main class="container-fluid pb-5 mb-5">
		<div class="row">
			<h1 class="text-center titulo">Itinerario</h1>
			<div class="col">
				<h2 class="text-center"><c:out value="${user.getItinerario()}"></c:out></h2>	
			</div>
			<h2 class="text-center"> COMPRA TOTAL : <c:out value="${user.getItinerario().getCostoTotal()}"></c:out> Monedas / <c:out value="${user.getItinerario().getTiempoTotal()}"></c:out> Tiempo</h2>
		</div>
		<div class="row">
			
		</div>
	</main>
</div>
	<jsp:include page="partials/footer.jsp"></jsp:include>

</body>
</html>