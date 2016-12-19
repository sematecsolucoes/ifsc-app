package br.com.sematec.produtos.controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sematec.modelo.Produto;
import br.com.sematec.produtos.dao.ProdutoDAO;

@WebServlet(urlPatterns = "/produto")
public class ProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProdutoDAO dao;
	private final Logger LOGGER = Logger.getLogger(ProdutoServlet.class.getName());

	public ProdutoServlet() {
		this.dao = new ProdutoDAO();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String funcao = req.getParameter("funcao");
		switch (funcao) {
		case "formulario":
			formulario(req, resp);
			break;
		case "remove":
			remove(req, resp);
			break;
		case "lista":
			lista(req, resp);
			break;
		default:
			LOGGER.warning("função desconhecida:" + funcao);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String descricao = req.getParameter("descricao");
		String nome = req.getParameter("nome");
		Double preco = req.getParameter("preco") != null ? Double.valueOf(req.getParameter("preco")) : 0d;
		String dataInicioVenda = req.getParameter("dataInicioVenda");
		Boolean usado = req.getParameter("usado") != null ? req.getParameter("usado").equals("on") : false;
		Produto produto = new Produto(nome, descricao, preco, dataInicioVenda, usado);
		dao.adiciona(produto);
		req.setAttribute("produtoList", dao.lista());
		String pagina = "/WEB-INF/jsp/produto/lista.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(pagina);
		dispatcher.forward(req, resp);
	}

	private void formulario(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pagina = "/WEB-INF/jsp/produto/formulario.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(pagina);
		dispatcher.forward(req, resp);
	}

	public List<Produto> lista() {
		return dao.lista();
	}

	private void lista(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("produtoList", dao.lista());
		String pagina = "/WEB-INF/jsp/produto/lista.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(pagina);
		dispatcher.forward(req, resp);
	}

	private void remove(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String text;
		if (req.getParameter("id") != null) {
			Long id = Long.valueOf(req.getParameter("id"));
			Produto produto = dao.find(id);
			dao.remove(id);
			text = produto.getNome() + " removido com sucesso.";
		} else {
			text = "id do item a ser removido não foi informado.";
		}
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(text);
	}

	public void remove(Produto produto) {
		dao.remove(produto);
	}
}
