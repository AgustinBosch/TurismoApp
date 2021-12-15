package controller.promociones;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.PromocionService;


@WebServlet("/borrarPromo.do")
public class BorrarPromocionServlet extends HttpServlet {

	private static final long serialVersionUID = 4222838003169059367L;
	
	PromocionService promocionService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		promocionService = new PromocionService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		promocionService.borrar(id);
		resp.sendRedirect("/TurismoApp/administrar.do");
	}
}
