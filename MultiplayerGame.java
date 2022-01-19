package homework;
/*
 * Robert Miller
 * I pledge I have abided by the Steven's Honor System 
 */
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MultiplayerGame{
	
	//instance variables
	
	private GameEntity turnTracker;
	private GameEntity[] index;
	
	//constructor, makes new Multiplayer Game happen
	public MultiplayerGame(int n) {
		if(n <= 0) {
			throw new ArrayIndexOutOfBoundsException("index");
		}
		index = new GameEntity[n];
		index[0] = new GamePlayer(null, null, 0);
	for(int i = 1; i < n; i++) {	
			index[i] = new GamePlayer(index[i-1],null,i);
			index[i-1].next = index[i];
		}
	index[n-1].next = index[0];
	index[0].prev = index[n-1];
	}
	
	//getter
	//returns size of Multiplayer game
	public int size() {
		int count = 0;
		int here = 0;
		 for(int i = 0; i < index.length; i++) {
			 if(index[i] != null) {
				 here = i;
				 break;
			 }
		 }
		 if(index[here].next == index[here]) {
			return 0;
		} else {
			GameEntity current = index[here].next;
		while(current != index[here]) {
			count += current.size();
			current = current.next;
			}
		}
		return count;
	}
	
	//"Checks"
	//goes through the entire array and double linked list to check for a player existing, if it doesnt it returns false
	private boolean existPlayer(int playerId) {
		if(playerId+1 > index.length || playerId < 0) {
			return false;
		}
		if(index[playerId] == null) {
			return false;
		}
			if(index[0].next != null) {
				GameEntity check = index[0].next;
			while(check != null) {
				if(check.next == index[playerId]) {
					return true;
				} else {
					check = check.next;
				}
			}
		}
		return false;
		}
	//helper function added to make my life easier
	
	//mutators
	//adds GamePiece object
	public void addGamePiece(int playerId, String name, int strength) {	
		if(existPlayer(playerId) == false) {
			throw new IllegalArgumentException("addGamePiece: no such Player");
		}
		GameEntity current = index[playerId];
		while(current.next.isGamePlayer() == false) {
			if(((GamePiece)current.next).getName() == name){
				throw new IllegalArgumentException("addGamePiece: duplicate Entry");	
			} else {
				if(((GamePiece)current.next).getStrength() < strength) {
					break;
					
				} else { 
					current = current.next;
			}
		}
		}
		current.next = new GamePiece(current,current.next, name, strength);
		current.next.next.prev = current.next;
	}
	//removes GamePiece object
	public void removeGamePiece(int playerId, String name) {
		boolean exists = false;
		if(existPlayer(playerId) == false) {
			throw new IllegalArgumentException("removeGamePiece: no such Player ");
		} 
		GameEntity current = index[playerId].next;
         while(current.isGamePlayer() == false){
          if(current.getName() == name){
        	  exists = true;
            	current.prev.next = current.next;
            	current.next.prev = current.prev;
             }
          current = current.next;
         }
         if(exists == false) {
        	 throw new IllegalArgumentException("removeGamePiece: entity does not exist");
         }
		}
	
	//returns true is game has an object
	public boolean hasGamePiece(String name) {
		 GameEntity current = index[0].next;
		 boolean contain = false;
         while(current != index[0]){
          if(current.getName() == name){
            	contain = true;
             }
          current = current.next;
         }
		 return contain;
	}
	
	//removes all GamePieces
	public void removeAllGamePieces(int playerId) {
		if(existPlayer(playerId) == false) {
			throw new IllegalArgumentException("removeAllGamePieces: no such Player");
		}
	    	GameEntity current = index[playerId];
	    	current = current.next;
	    	while(current.isGamePlayer() == false) {
	    		current = current.next;
	      }
	    index[playerId].next = current;
	    current.prev = index[playerId];
	}
	
	//increases Strength stat
	public void increaseStrength(int playerId, int n) {
		if(existPlayer(playerId) == false) {
			throw new IllegalArgumentException("increasesStrength: no such Player");
		}
		GameEntity current = index[playerId].next;
			while(current.isGamePlayer() == false) {
				((GamePiece)current).updateStrength(n);
				current = current.next;
		}
	}

	//removes player
	public void removePlayer(int playerId) {
		if(existPlayer(playerId) == false) {
			throw new IllegalArgumentException("removePlayer: no such Player");
		}
		if(playerId == 0 && index.length == 1) {
			System.out.println("oof");
			index[0].next = null;
			index[0].prev = null;
			index[0] = null;
		} else {
			removeAllGamePieces(playerId);
			index[playerId].prev.next = index[playerId].next;
			index[playerId].next.prev = index[playerId].prev;
			index[playerId] = null;
		}
	}
	
	//swaps GamePieces
	public void swapPieces(int playerId1, int playerId2) {
		if(existPlayer(playerId1) == false || existPlayer(playerId2) == false) {
			throw new IllegalArgumentException("swapPieces: no such Player");
		}
		GameEntity current1 = index[playerId1];
		while(current1.next.isGamePlayer() == false) {
			current1 = current1.next;
		}
		GameEntity current2 = index[playerId2];
		while(current2.next.isGamePlayer() == false) {
			current2 = current2.next;
		}
		current2.next = index[playerId2];
		current1.next = index[playerId1];
		index[playerId1].next = current2;
		index[playerId2].next = current1;
	}
	
	//toString function, prints out everything
	public String toString() {
		String output = "";
		int here = 0;
		 for(int i = 0; i < index.length; i++) {
			 if(index[i] != null) {
				 here = i;
				 break;
			 }
		 }
		 
		 	GameEntity current = index[here];
			 while(current.next != index[here]) {
					 output = output + current.toString() + " ";
					 current = current.next;
	}
			 output = output + current.toString();
			 return output;
	}

	
	//helper functions (not mutators, just actions necessary for the game)
	//starts the turn tracker at first player
	public void initializeTurnTracker() {
		int here = 0;
		 for(int i = 0; i < index.length; i++) {
			 if(index[i] != null) {
				 here = i;
				 break;
			 }
		 }
		 turnTracker = index[here];
	}

	//moves on to next player
	public void nextPlayer() {
		if(turnTracker.isGamePlayer() == true) {
			turnTracker = turnTracker.next;
			while(turnTracker.isGamePlayer() != true) {
				turnTracker = turnTracker.next;
			}
		}
	while(turnTracker.isGamePlayer() == false) {
		turnTracker = turnTracker.next;
		}
	}
	
	// moves to next entity
	public void nextEntity() {
		turnTracker = turnTracker.next;
	}

	//moves back to previous player
	public void prevPlayer() {
		if(turnTracker.isGamePlayer() == true) {
			turnTracker = turnTracker.prev;
			while(turnTracker.isGamePlayer() != true) {
				turnTracker = turnTracker.prev;
			}
		}
	while(turnTracker.isGamePlayer() == false) {
		turnTracker = turnTracker.prev;
		}
	}
	
	//prints entity toString
	public String currentEntityToString() {
		return turnTracker.toString();
	}
	
	public static void main(String args[]) {
		MultiplayerGame game1 = new MultiplayerGame(1);
		game1.addGamePiece(0, "DDD", 0);
		game1.initializeTurnTracker();
		game1.nextEntity();
		System.out.println(game1.currentEntityToString());
	}
}
