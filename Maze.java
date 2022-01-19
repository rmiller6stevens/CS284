package Maze;
import java.util.*;
import java.util.ArrayList;

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    public boolean findMazePath(int x, int y) {
    	if(x > maze.getNCols() - 1 || x < 0 || y < 0 || y > maze.getNRows() - 1) {
    		return false;
    	} else if(maze.getColor(x, y) != NON_BACKGROUND) {
    		return false;
    	}
    	if(x == maze.getNCols() - 1 && y == maze.getNRows() -1) {
    		maze.recolor(x, y, PATH);
    		return true;
    	} else {
    	maze.recolor(x, y, PATH);
    	if(findMazePath(x + 1, y)){
    		return true;
    	} else if(findMazePath(x-1, y)) {
    		return true;
    	} else if(findMazePath(x, y + 1)) {
    		return true;
    	}else if(findMazePath(x, y - 1)) {
    		return true;
    	}
    	maze.recolor(x, y, TEMPORARY);
    }
    	return false;
    }
/*
 * Returns the path taken that solves the maze
 */
   public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x,int y){
	   maze.recolor(PATH, NON_BACKGROUND);
	   maze.recolor(TEMPORARY, NON_BACKGROUND);
	   
	   ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
	   Stack<PairInt> trace = new Stack<>();
	   findMazePathStackBased(0,0, result, trace);
	   return result;	
    }
    
   
   //helper for Problem 2
   public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
	if(x <= maze.getNCols() - 1 && x >= 0 && y >= 0 && y <= maze.getNRows() - 1) {
   	trace.push(new PairInt(x,y));
   	if(x == maze.getNCols() - 1 && y == maze.getNRows() -1) {
		ArrayList<PairInt> coordinate = new ArrayList<>();
		coordinate.addAll(trace);
		result.add(coordinate);
   		}
   	else {
   		maze.recolor(x, y, PATH);
   		findMazePathStackBased(x-1,y,result,trace);
   		findMazePathStackBased(x,y-1,result,trace);
   		findMazePathStackBased(x+1,y,result,trace);
   		findMazePathStackBased(x,y+1,result,trace);
   	}
   	maze.recolor(x, y, NON_BACKGROUND);
	}
   }
  
   public ArrayList<PairInt> findMazePathMin(int x, int y){
	if(!findMazePath(x,y)) {
		return null;
	}
	ArrayList<ArrayList<PairInt>> shortName = findAllMazePaths(x,y);
	ArrayList<PairInt> min = shortName.get(0);
	for(int i = 0; i < findAllMazePaths(x,y).size(); i++) {
		if(min.size() > shortName.get(i).size()) {
			min = shortName.get(i);
		}
	}
	return min;
   }

    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }

    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
}


/*</listing>*/
