package homework;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MultiplayerGameTest {

	//Tests constructor
	@Test
	  void testConstructor() {
		MultiplayerGame game1 = new MultiplayerGame(1);
		assertTrue(game1.size() == 0);
	}
	//tests size()
	@Test
	  void testSize() {
		MultiplayerGame game1 = new MultiplayerGame(1);
		assertTrue(game1.size() == 0);
	}
	//tests addGamePiece()
	@Test
	  void testAddGamePiece() {
		MultiplayerGame game1 = new MultiplayerGame(1);
		game1.addGamePiece(0, "Teke", 0);
		assertTrue(game1.size() == 1);
	}
	//tests removeGamePiece()
	@Test
	  void testRemoveGamePiece() {
		MultiplayerGame game1 = new MultiplayerGame(1);
		game1.addGamePiece(0, "Metin", 0);
		game1.removeGamePiece(0,"Metin");
		assertTrue(game1.size()== 0);
	}
	//tests hasGamePiece()
	@Test
	  void testHasGamePiece() {
		MultiplayerGame game1 = new MultiplayerGame(1);
		game1.addGamePiece(0, "Spork", 0);
		assertTrue(game1.hasGamePiece("Spork"));
	}
	//tests removeAllGamePieces()
	@Test
	  void testRemoveAllGamePieces() {
		MultiplayerGame game1 = new MultiplayerGame(2);
		game1.addGamePiece(1, "Spoon", 2);
		game1.removeAllGamePieces(1);
		assertTrue(game1.size() == 0);
	}
	// tests increaseStrength()
	@Test
	  void testIncreaseStrength() {
	MultiplayerGame game1 = new MultiplayerGame(1);
	game1.addGamePiece(0, "Dunk", 5);
	game1.increaseStrength(0, 1);
	game1.addGamePiece(0,"Munk",5);
	game1.addGamePiece(0, "Funk", 7);
	game1.initializeTurnTracker();
	game1.nextEntity();
	game1.nextEntity();
	assertTrue(game1.currentEntityToString().compareToIgnoreCase("GamePiece: Dunk strength 6") == 0);
	}
	//tests removePlayer()
	@Test
	void testremovePlayer() {
	MultiplayerGame game1 = new MultiplayerGame(2);
	game1.removePlayer(0);
	assertTrue(game1.toString().compareToIgnoreCase("Player1") == 0);
	}
	//tests swapPieces()
	@Test
	void testSwapPieces() {
		MultiplayerGame game1 = new MultiplayerGame(2);
		game1.addGamePiece(0, "D", 0);
		game1.addGamePiece(1, "a", 0);
		game1.swapPieces(0, 1);
		assertTrue(game1.toString().compareToIgnoreCase("Player0 GamePiece: a strength 0 Player1 GamePiece: D strength 0") == 0);
	}
	//tests toString()
	@Test
	void testToString() {
		MultiplayerGame game1 = new MultiplayerGame(1);
		game1.addGamePiece(0, "D", 0);
		assertTrue(game1.toString().compareToIgnoreCase("Player0 GamePiece: D strength 0") == 0);
	}
	//tests initializeTurnTracker()
	@Test
	void testInitializeTurnTracker() {
		MultiplayerGame game1 = new MultiplayerGame(1);
		game1.initializeTurnTracker();
		assertTrue(game1.currentEntityToString().compareToIgnoreCase("Player0") == 0);
	}
	//tests nextPlayer()
	@Test
	void testNextPlayer() {
		MultiplayerGame game1 = new MultiplayerGame(2);
		game1.initializeTurnTracker();
		game1.nextPlayer();
		assertTrue(game1.currentEntityToString().compareToIgnoreCase("Player1") == 0);
	}
	//tests nextEntity()
	@Test
	void testNextEntity() {
		MultiplayerGame game1 = new MultiplayerGame(1);
		game1.addGamePiece(0, "DDD", 0);
		game1.initializeTurnTracker();
		game1.nextEntity();
		assertTrue(game1.currentEntityToString().compareToIgnoreCase("GamePiece: DDD strength 0") == 0);
	}
	//tests prevPlayer()
	@Test
	void testPrevPlayer() {
		MultiplayerGame game1 = new MultiplayerGame(2);
		game1.initializeTurnTracker();
		game1.nextPlayer();
		game1.prevPlayer();
		assertTrue(game1.currentEntityToString().compareToIgnoreCase("Player0") == 0);
	}
	//tests currentEntityToString()
	@Test
	void testCurrentEntityToString() {
		MultiplayerGame game1 = new MultiplayerGame(2);
		game1.initializeTurnTracker();
		game1.nextPlayer();
		game1.nextEntity();
		assertTrue(game1.currentEntityToString().compareToIgnoreCase("Player0") == 0);
	}
}
