package model;

public interface Sugerible {

	public String getNombre();

	public String getGenero();

	public double getCosto();

	public double getDuracion();

	public int getId();
	
	public String getDescripcion();

	public boolean esPromo();

	public boolean tieneCupo();

	public void ocuparLugar();

	public boolean tengoSugerible(Sugerible s);

}
