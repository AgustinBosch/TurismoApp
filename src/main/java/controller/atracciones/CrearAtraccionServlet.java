package controller.atracciones;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Atraccion;
import services.AtraccionService;

@WebServlet("/crearAtraccion.do")
public class CrearAtraccionServlet extends HttpServlet {

	private static final long serialVersionUID = -6481611759453378904L;
	AtraccionService atraccionService;

	@Override
	public void init() throws ServletException {
		super.init();
		atraccionService = new AtraccionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/atraccion/crearAtraccion.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("nombreatraccion");
		String oro = req.getParameter("oroatraccion");
		String tiempo = req.getParameter("tiempoatraccion");
		String genero = req.getParameter("generoatraccion");
		String descripcion = req.getParameter("descripcionatraccion");
		String cupo = req.getParameter("cupoatraccion");

		Atraccion a = atraccionService.crearAtraccion(nombre, oro, genero, tiempo, cupo, descripcion);

		if (a.isValido()) {
			resp.sendRedirect("/TurismoApp/administrar.do");
		} else {
			req.setAttribute("atraccion", a);
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/atraccion/crearAtraccion.jsp");
			dispatcher.forward(req, resp);
		}

	}

}
