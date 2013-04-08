package com.adaba.servlets;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adaba.pojo.GameRoom;

/**
 * Servlet implementation class GameServlet
 */
@WebServlet(description = "Servlet providing access to information and actions related to joining a game", urlPatterns = { "/GameServlet" })
public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Global variable: parameter used to request game list
	 */
	public static final String GET_KEY = "key";
	public static final String GET_GAMEROOMS_VAL = "roomlist";

	public static final String POST_KEY = "action";
	public static final String POST_NEWGAME_VAL = "create";
	public static final String POST_JOINGAME_VAL = "join";

	private final List<GameRoom> rooms = new LinkedList<GameRoom>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GameServlet() { super(); }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		String param = request.getParameter(GET_KEY); // What do we want to get?

		if (param == null) {			
		} else if (param.equalsIgnoreCase(GET_GAMEROOMS_VAL)) {
			response.getWriter().println("Room Alpha");
			response.getWriter().println("Room Bravo");
			response.getWriter().println("Room Charlie");
			response.getWriter().println("Room Delta");
			response.getWriter().println("Room Echo");
			response.getWriter().println("Room Foxtrot");
			response.getWriter().println("Room Golf");
			response.getWriter().println("Room Hotel");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		String param = request.getParameter(POST_KEY); // What do we want to get?

		if (param == null) {			
		} else if (param.equalsIgnoreCase(POST_NEWGAME_VAL)) {
			
		} else if (param.equalsIgnoreCase(POST_JOINGAME_VAL)) {

		}
	}
}
