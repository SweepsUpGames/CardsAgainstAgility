package com.adaba.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DefaultServlet
 */
@WebServlet(description = "Servlet created for architectural testing", urlPatterns = { "/DefaultServlet" })
public class DefaultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DefaultServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
				String param = request.getParameter("req"); // What do we want to get?

				if (param == null) {			
				} else if (param.equalsIgnoreCase("gamelist")) {
					response.getWriter().println("<h3>Game Alpha</h3>");
					response.getWriter().println("<h3>Game Bravo</h3>");
					response.getWriter().println("<h3>Game Charlie</h3>");
					response.getWriter().println("<h3>Game Delta</h3>");
					response.getWriter().println("<h3>Game Echo</h3>");
					response.getWriter().println("<h3>Game Foxtrot</h3>");
					response.getWriter().println("<h3>Game Golf</h3>");
					response.getWriter().println("<h3>Game Hotel</h3>");
				} else if (param.equalsIgnoreCase("players")) {
					response.getWriter().println("<h3>Player Able</h3>");
					response.getWriter().println("<h3>Player Baker</h3>");
					response.getWriter().println("<h3>Player Chuck</h3>");
					response.getWriter().println("<h3>Player Dougie</h3>");
					response.getWriter().println("<h3>Player Ethan</h3>");
					response.getWriter().println("<h3>Player Fantana</h3>");
					response.getWriter().println("<h3>Player Gus</h3>");
					response.getWriter().println("<h3>Player Hotep</h3>");
				}

		response.getWriter().println("<h1>Cards Against Agility</h1>");
		response.getWriter().println("<h2>\"Get Agile, bitches\"</h2>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
