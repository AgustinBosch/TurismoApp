package controller.atracciones;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.AtraccionService;

@WebServlet("/borrarAtraccion.do")
public class BorrarAtraccionServlet extends HttpServlet {

	private static final long serialVersionUID = -3464454994593713201L;
	AtraccionService atraccionService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		atraccionService = new AtraccionService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		atraccionService.borrar(id);
		
		resp.sendRedirect("/TurismoApp/administrar.do");
	}
}
