<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="mb-3">
	<label for="nombreusuario" class="form-label">Nombre</label>
	<input required type="text" class="form-control" id="nombreusuario" value="${usuario.getNombre()}" name="nombreusuario" placeholder="Nombre" minlength="3">
</div>

<div class="mb-3">
	<label for="passwordusuario" class="form-label">Password</label>
	<input required type="password" class="form-control" id="passwordusuario" name="passwordusuario" placeholder="Password" minlength="3">
</div>

<div class="mb-3">
	<label for="generousuario" class="form-label">Genero</label>
	<select class="form-select" name="generousuario">
		<option value="AVENTURA" ${usuario.getTipoPref().equals("AVENTURA") ? "selected" : ""}>Aventura</option>
		<option value="PAISAJE" ${usuario.getTipoPref().equals("PAISAJE") ? "selected" : ""}>Paisaje</option>
		<option value="DEGUSTACION" ${usuario.getTipoPref().equals("DEGUSTACION") ? "selected" : ""}>Degustacion</option>
	</select>
</div>

<div class="form-check mb-3">
	<label class="form-check-label" for="adminusuario"> Admin</label>
	<input class="form-check-input" type="checkbox" value="true" name="adminusuario" id="adminusuario"  ${usuario.getAdmin() ? "checked" : ""}>
</div>

<div class="mb-3">
	<label for="orousuario" class="form-label">Oro</label>
	<input required value="${usuario.getOro()}" min="1" step="0.1" type="number" class="form-control" id="orousuario" name="orousuario" placeholder="Oro">
</div>


<div class="mb-3">
	<label for="tiempousuario" class="form-label">Tiempo</label>
	<input required value="${usuario.getTiempoDisponible()}" min="1" step="0.1" type="number" class="form-control" id="tiempousuario" name="tiempousuario" placeholder="Tiempo">
</div>


<button type="submit" class="btn btn-primary">Submit</button>
