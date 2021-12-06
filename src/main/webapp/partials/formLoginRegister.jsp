<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="col-lg-4 login">
	<div class="accordion" id="accordionExample">
		<div class="accordion-item">
			<h2 class="accordion-header" id="headingOne">
				<button class="accordion-button" type="button"
					data-bs-toggle="collapse" data-bs-target="#collapseOne"
					aria-expanded="true" aria-controls="collapseOne">Iniciar
					sesion</button>
			</h2>
			<div id="collapseOne" class="accordion-collapse collapse show"
				aria-labelledby="headingOne" data-bs-parent="#accordionExample">
				<div class="accordion-body">

					<!--  Formulario inicio sesion -->
					<form action="login" method="post">
						<div class="form-outline mb-4">
							<c:if test="${flash != null}">
								<div class="alert alert-danger">
									<p>
										<c:out value="${flash}" />
									</p>
								</div>
							</c:if>
							<label class="form-label" for="nombrelogin"> Nombre </label> <input
								type="text" id="nombrelogin" class="form-control"
								name="nombrelogin" placeholder="Nombre" />
						</div>
						<div class="form-outline mb-4">
							<label class="form-label" for="passwordlogin">Password</label> <input
								type="password" id="passwordlogin" class="form-control"
								name="passwordlogin" placeholder="Password" />
						</div>
						<!-- Submit button -->
						<button type="submit" class="btn btn-primary btn-block mb-4">
							Entrar!</button>
					</form>
				</div>
			</div>
		</div>
		<div class="accordion-item">
			<h2 class="accordion-header" id="headingTwo">
				<button class="accordion-button collapsed" type="button"
					data-bs-toggle="collapse" data-bs-target="#collapseTwo"
					aria-expanded="false" aria-controls="collapseTwo">
					Registrarse</button>
			</h2>
			<div id="collapseTwo" class="accordion-collapse collapse"
				aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
				<div class="accordion-body">

					<!--  Formulario registro sesion -->
					<form action="register" method="post">
						<div class="row">
							<div class="form-outline mb-4">
								<label class="form-label" for="nombreregister"> Nombre </label>
								<input type="text" id="nombreregister" class="form-control"
									name="nombreregister" placeholder="Ingrese Nombre" />
							</div>
						</div>
						<div class="row">
							<div class="form-outline mb-4">
								<label class="form-label" for="passwordregister">Password</label>
								<input type="password" id="passwordregister"
									class="form-control" name="passwordregister"
									placeholder="Ingrese Pass" />
							</div>
						</div>
						<div class="row">
							<div class="form-outline col-6 mb-4">
								<label class="form-label" for="tiporegister"> Tipo
									Preferido </label> <select name="tiporegister" id="tiporegister"
									class="form-select">
									<option value="AVENTURA">Aventura</option>
									<option value="DEGUSTACION">Degustacion</option>
									<option value="PAISAJE">Paisaje</option>
								</select>
							</div>

							<div class="form-outline col mb-4">
								<label class="form-label" for="ororegister"> Oro </label> <input
									type="number" id="ororegister" class="form-control"
									name="ororegister" placeholder="Oro" />
							</div>

							<div class="form-outline col mb-4">
								<label class="form-label" for="tiemporegister"> Horas </label> <input
									type="number" id="tiemporegister" class="form-control"
									name="tiemporegister" placeholder="Horas" />
							</div>
						</div>

						<!-- Submit button -->
						<button type="submit" class="btn btn-primary btn-block mb-4">
							Sign in</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>