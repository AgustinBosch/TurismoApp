package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.AdministrarService;
import services.AtraccionService;
import services.PromoService;
import services.UsuarioService;


@WebServlet("/administrar.do")
public class AdministrarServlet extends HttpServlet {

	private static final long serialVersionUID = -4007572705340183648L;
	AtraccionService atraccionService;
	UsuarioService usuariosService;
	PromoService promoService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		atraccionService = new AtraccionService();
		usuariosService = new UsuarioService();
		promoService = new PromoService();
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("atracciones", atraccionService.buscarAtracciones());
		req.setAttribute("usuarios", usuariosService.buscarUsuarios());
		req.setAttribute("promociones", promoService.buscarPromos());
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/administrar.jsp");
		dispatcher.forward(req, resp);
	}
	

}
