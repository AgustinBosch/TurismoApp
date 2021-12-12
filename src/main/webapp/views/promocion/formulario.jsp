<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="mb-3">
	<label for="tipopromo" class="form-label">Tipo</label>
	<select class="form-select" name="tipopromo">
		<option value="Absoluta" ${promocion.getTipoPromo().equals("Absoluta") ? "selected" : ""}>Absoluta</option>
		<option value="Porcentual" ${atraccion.getTipoPromo().equals("Porcentual") ? "selected" : ""}>Porcentual</option>
		<option value="AxB" ${atraccion.getTipoPromo().equals("AxB") ? "selected" : ""}>AxB</option>
	</select>
</div>

<div class="mb-3">
	<label for="generopromo" class="form-label">Genero</label>
	<select class="form-select" name="generopromo">
		<option value="AVENTURA" ${promocion.getGenero().equals("AVENTURA") ? "selected" : ""}>Aventura</option>
		<option value="PAISAJE" ${promocion.getGenero().equals("PAISAJE") ? "selected" : ""}>Paisaje</option>
		<option value="DEGUSTACION" ${promocion.getGenero().equals("DEGUSTACION") ? "selected" : ""}>Degustacion</option>
	</select>
</div>

<div class="mb-3">
	<label for="extrapromo" class="form-label">Precio de promo Absoluta - Descuento Porcentual (50 = 50%) - MAX 100</label>
	<input required type="number" class="form-control" id="extrapromo" value="${atraccion.getNombre()}" name="extrapromo" placeholder="Extra" min="0" max="100">
</div>

<div class="mb-3">
	<label for="descripcionpromo" class="form-label">Descripcion</label>
	<textarea required minlength="3" maxlength="300" class="form-control" id="descripcionpromo" rows="3" name="descripcionpromo" placeholder="Descripcion">${promo.getDescripcion()}</textarea>
</div>

<div class="mb-3">
	<label for="atraccionespromo" class="form-label">Atracciones (Usar mismo tipo)</label>
	<select multiple class="form-select" name="atraccionespromo">
		<c:forEach items="${atracciones}" var="atr">
			<option value="${atr.getId()}" ${promo.getMisAtracciones().contains(atr.getNombre()) ? "selected" : ""}><c:out value="${atr.getNombre()}"></c:out> - <c:out value="${atr.getGenero()}"></c:out></option>
		</c:forEach>
	</select>
</div>

<button type="submit" class="btn btn-primary">Submit</button>