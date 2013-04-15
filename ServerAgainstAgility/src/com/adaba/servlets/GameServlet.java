package com.adaba.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adaba.cards.Type;
import com.adaba.deck.Deck;
import com.adaba.deck.DeckCreator;
import com.adaba.game.Game;
import com.adaba.game.Player;

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

	private final Map<String, Game> games = new HashMap<String, Game>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GameServlet() { 
		super(); 
		BasicConfigurator.configure();

		// Add some dummy data
		Deck whiteDeck = null;
		Deck blackDeck = null;
		try {
			whiteDeck = DeckCreator.makeCoHDeck(Type.WHITE);
			blackDeck = DeckCreator.makeCoHDeck(Type.BLACK);
		} catch (IOException e) {
			logger.error("Exception while reading card resource", e);
		}
		List<Player> players = new LinkedList<Player>();
		players.add(new Player("Todd"));
		players.add(new Player("Jeff"));
		players.add(new Player("Kim"));
		games.put("TestGame w/ 3 players", new Game(players, whiteDeck, blackDeck));

		try {
			whiteDeck = DeckCreator.makeCoHDeck(Type.WHITE);
			blackDeck = DeckCreator.makeCoHDeck(Type.BLACK);
		} catch (IOException e) {
			logger.error("Exception while reading card resource", e);
		}
		players = new LinkedList<Player>();
		players.add(new Player("Bill"));
		players.add(new Player("Drew"));
		players.add(new Player("Adam"));
		players.add(new Player("Mark"));
		players.add(new Player("Phil"));
		games.put("TestGame w/ 5 players", new Game(players, whiteDeck, blackDeck));

		try {
			whiteDeck = DeckCreator.makeCoHDeck(Type.WHITE);
			blackDeck = DeckCreator.makeCoHDeck(Type.BLACK);
		} catch (IOException e) {
			logger.error("Exception while reading card resource", e);
		}
		players = new LinkedList<Player>();
		games.put("TestGame w/ no players", new Game(players, whiteDeck, blackDeck));
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		String req = request.getParameter(GET_KEY); // What do we want to get?

		if (req == null) {			
		} else if (req.equalsIgnoreCase(GET_GAMEROOMS_VAL)) {
			logger.info("GET received for gameroom list.");

			for (String game : games.keySet())
				response.getWriter().append(game + "\n");
		} else if (req.equalsIgnoreCase("hand")) {
			logger.info("GET received for hand.");
			Game game = games.get(request.getParameter("game"));

			// TODO
			response.getWriter().append("Card Hotel\n");
		} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		String action = request.getHeader("game"); // What do we want to get?
		if (action == null) {
		} else if (action.equalsIgnoreCase("join")) {
			String game = request.getHeader("game"); // What do we want to get?
			if (game != null) {
				logger.info("POST received for joining game " + game);
				if (games.get(game) != null) {
					games.get(game).addPlayer(new Player("foo"));
				}
			}
		} else if (action.equalsIgnoreCase("play")) {
			String card = request.getHeader("card"); // What do we want to get?
			if (card != null) {
				logger.info("POST received for playing card " + card);
			}
		}
	}
}
