package com.adaba.servlets;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adaba.pojo.GameRoom;

/**
 * Servlet implementation class GameServlet
 */
@WebServlet(description = "Servlet providing access to information and actions related to joining a game", urlPatterns = { "/GameServlet" })
public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * SLF4J logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(GameServlet.class);

	/**
	 * Global variable: parameter used to request game list
	 */
	public static final String GET_KEY = "req";
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
			logger.info("GET received for gameroom list.");
			response.getWriter().append("Room Alpha\n");
			response.getWriter().append("Room Bravo\n");
			response.getWriter().append("Room Charlie\n");
			response.getWriter().append("Room Delta\n");
			response.getWriter().append("Room Echo\n");
			response.getWriter().append("Room Foxtro\n");
			response.getWriter().append("Room Golf\n");
			response.getWriter().append("Room Hotel\n");
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
			logger.info("POST received for new game creation.");
		} else if (param.equalsIgnoreCase(POST_JOINGAME_VAL)) {
			logger.info("POST received for game join.");
		}
	}
}
