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

@WebServlet("/editarAtraccion.do")
public class EditarAtraccionServlet extends HttpServlet {

	private static final long serialVersionUID = 48924652489099588L;
	AtraccionService atraccionService;

	@Override
	public void init() throws ServletException {
		super.init();
		atraccionService = new AtraccionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");
		Atraccion a = atraccionService.buscarId(id);
		
		req.setAttribute("atraccion", a);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/atraccion/editarAtraccion.jsp");
		dispatcher.forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String nombre = req.getParameter("nombreatraccion");
		String oro = req.getParameter("oroatraccion");
		String genero = req.getParameter("generoatraccion");
		String tiempo = req.getParameter("tiempoatraccion");
		String cupo = req.getParameter("cupoatraccion");
		String descripcion = req.getParameter("descripcionatraccion");
		
		Atraccion a = atraccionService.actualizar(id, nombre, oro, genero, tiempo, cupo, descripcion);
		
		if (a.isValido()) {
			resp.sendRedirect("/TurismoApp/administrar.do");
		} else {
			req.setAttribute("atraccion", a);
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/atraccion/editarAtraccion.jsp");
			dispatcher.forward(req, resp);
		}
	}

}
