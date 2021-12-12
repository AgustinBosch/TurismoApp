package controller.promociones;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Atraccion;
import model.promocion.Promo;
import services.AtraccionService;
import services.PromocionService;

@WebServlet("/crearPromo.do")
public class CrearPromocionServlet extends HttpServlet {

	private static final long serialVersionUID = 6317395433100013065L;
	PromocionService promocionService;
	AtraccionService atraccionService;

	@Override
	public void init() throws ServletException {
		super.init();
		promocionService = new PromocionService();
		atraccionService = new AtraccionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Atraccion> atracciones = atraccionService.buscarAtracciones();
		req.setAttribute("atracciones", atracciones);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/promocion/crearPromo.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String tipo = req.getParameter("tipopromo");
		String genero = req.getParameter("generopromo");
		String extra = req.getParameter("extrapromo");
		String descripcion = req.getParameter("descripcionpromo");
		String[] atracciones = req.getParameterValues("atraccionespromo");

		Promo p = promocionService.crearPromo(tipo, genero, extra, descripcion, atracciones);

		if (p.isValido()) {
			resp.sendRedirect("/TurismoApp/administrar.do");
		} else {
			List<Atraccion> todasAtr = atraccionService.buscarAtracciones();
			req.setAttribute("atracciones", todasAtr);
			req.setAttribute("promocion", p);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/promocion/crearPromo.jsp");
			dispatcher.forward(req, resp);
		}
	}

}
