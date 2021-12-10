package controller.atracciones;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Atraccion;
import model.Sugerible;
import model.Usuario;
import services.AtraccionService;
import services.SugeribleService;

@WebServlet("/atracciones.do")
public class ListaAtraccionesServlet extends HttpServlet {

	private static final long serialVersionUID = 7106456794773591642L;
	private SugeribleService sugeribleService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		sugeribleService = new SugeribleService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String gusto = ((Usuario)req.getSession().getAttribute("user")).getTipoPref();
		List<Sugerible> sugeribles = sugeribleService.buscarSugeribles(gusto);
		req.setAttribute("sugeribles", sugeribles);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/atracciones.jsp");
		dispatcher.forward(req, resp);
	}
}
