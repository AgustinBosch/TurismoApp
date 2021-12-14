<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<div class="mb-3">
	<label for="nombreatraccion" class="form-label">Nombre</label>
	<input required type="text" class="form-control" id="nombreatraccion" value="${atraccion.getNombre()}" name="nombreatraccion" placeholder="Nombre" minlength="3">
</div>

<div class="mb-3">
	<label for="generoatraccion" class="form-label">Genero</label>
	<select class="form-select" name="generoatraccion">
		<option value="AVENTURA" ${atraccion.getGenero().equals("AVENTURA") ? "selected" : ""}>Aventura</option>
		<option value="PAISAJE" ${atraccion.getGenero().equals("PAISAJE") ? "selected" : ""}>Paisaje</option>
		<option value="DEGUSTACION" ${atraccion.getGenero().equals("DEGUSTACION") ? "selected" : ""}>Degustacion</option>
	</select>
</div>


<div class="mb-3">
	<label for="oroatraccion" class="form-label">Oro</label>
	<input required value="${atraccion.getCosto()}" min="1" step="0.1" type="number" class="form-control" id="oroatraccion" name="oroatraccion" placeholder="Oro">
</div>


<div class="mb-3">
	<label for="tiempoatraccion" class="form-label">Tiempo</label>
	<input required value="${atraccion.getDuracion()}" min="1" step="0.1" type="number" class="form-control" id="tiempoatraccion" name="tiempoatraccion" placeholder="Tiempo">
</div>

<div class="mb-3">
	<label for="cupoatraccion" class="form-label">Cupo</label>
	<input required value="${atraccion.getCupo()}" min="1" type="number" class="form-control" id="cupoatraccion" name="cupoatraccion" placeholder="Cupo">
</div>


<div class="mb-3">
	<label for="descripcionatraccion" class="form-label">Descripcion</label>
	<textarea required minlength="3" maxlength="300" class="form-control" id="descripcionatraccion" rows="3" name="descripcionatraccion" placeholder="Descripcion">${atraccion.getDescripcion()}</textarea>
</div>


<button type="submit" class="btn btn-primary">Submit</button>
