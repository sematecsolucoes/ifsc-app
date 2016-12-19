package br.com.sematec.produtos.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sematec.modelo.Usuario;
import br.com.sematec.produtos.dao.ProdutoDAO;
import br.com.sematec.produtos.dao.UsuarioDAO;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException {
		try {
			Usuario usuario = new Usuario();
			usuario.setLogin(req.getParameter("login"));
			usuario.setSenha(req.getParameter("senha"));
			usuario = new UsuarioDAO().buscaUsuarioPorLoginESenha(usuario);
			HttpSession session = req.getSession(true);
			if (usuario != null) {
				session.setAttribute("usuarioLogado", usuario);
				req.setAttribute("produtoList", new ProdutoDAO().lista());
				String pagina = "/WEB-INF/jsp/produto/lista.jsp";
				RequestDispatcher dispatcher = req.getRequestDispatcher(pagina);
				dispatcher.forward(req, resp);
			} else {
				req.setAttribute("erro", "email ou senha inválidos.");
				String pagina = "/index.jsp";
				RequestDispatcher dispatcher = req.getRequestDispatcher(pagina);
				dispatcher.forward(req, resp);
			}
		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}
}
