package controller.productos;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Sugerible;
import model.Usuario;
import persistence.commons.DAOFactory;
import persistence.dao.UsuarioDAO;
import services.ComprarService;

@WebServlet("/comprar.do")
public class ComprarProductoServlet extends HttpServlet {

	private static final long serialVersionUID = 502342851488788609L;
	private ComprarService cs;

	@Override
	public void init() throws ServletException {
		super.init();
		cs = new ComprarService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer usuarioId = ((Usuario) req.getSession().getAttribute("user")).getId();
		boolean esPromo = req.getParameter("promo").equals("true");
		Integer sugeribleId = Integer.parseInt(req.getParameter("id"));
		
		cs.comprar(usuarioId, sugeribleId, esPromo);
		
		Usuario user = DAOFactory.getUsuarioDAO().findbyID(usuarioId);

		req.getSession().setAttribute("user", user);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/productos.do");
		dispatcher.forward(req, resp);

	}
}
