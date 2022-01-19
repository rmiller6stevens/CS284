package homework;

import java.util.Arrays;
import java.util.Random;

public class CoinPurse {
	//instance variables
	private int numGalleons;
	private int numSickles;
	private int numKnuts;
	private static final int CAPACITY = 256;
	
	//constructors
	public CoinPurse() {
		numGalleons = 0;
		numSickles = 0;
		numKnuts = 0;
	}
	public CoinPurse(int g, int s, int k) {
		numGalleons = g;
		numSickles = s;
		numKnuts = k;
		if((g + s + k) > CAPACITY){
			throw new IllegalArgumentException("Coing Purse would be too full!");
		}
	}

	//mutators
	public void depositGalleons(int n) {
		if((numGalleons + numSickles + numKnuts + n) > CAPACITY) {
			throw new IllegalArgumentException("Coin Purse would be too full!");
		}
		numGalleons += n;
	}
	public void depositSickles(int n) {
		if((numGalleons + numSickles + numKnuts + n) > CAPACITY) {
			throw new IllegalArgumentException("Coin Purse would be too full!");
		}
		numSickles += n;
	}
	public void depositKnuts(int n) {
		if((numGalleons + numSickles + numKnuts + n) > CAPACITY) {
			throw new IllegalArgumentException("Coin Purse would be too full!");
		}
		numKnuts += n;
	}
	public void withdrawGalleons(int n) {
		if((numGalleons + numSickles + numKnuts - n) > CAPACITY) {
			throw new IllegalArgumentException("Coin Purse would be empty!");
		}
		numGalleons -= n;
	}
	public void withdrawSickles(int n) {
		if((numGalleons + numSickles + numKnuts - n) > CAPACITY) {
			throw new IllegalArgumentException("Coin Purse would be empty!");
		}
		numSickles -= n;
	}
	public void withdrawKnuts(int n) {
		if((numGalleons + numSickles + numKnuts - n) > CAPACITY) {
			throw new IllegalArgumentException("Coin Purse would be empty!");
		}
		numKnuts -= n;
	}
	
	//getters
	public int numCoins() {
		return numGalleons + numSickles + numKnuts;
	}
	public int totalValue() {
		return numKnuts + (numGalleons * 493) + (numSickles * 29);
	}
	public String toString() {
		return "You have " + numGalleons + " Galleons, " + numSickles + " Sickles, and " + numKnuts + " Knuts in your coin purse";
	}
	
	
	//general methods
	public boolean exactChange(int n) {
		int gCount = numGalleons;
		int sCount = numSickles;
		int kCount = numKnuts;
		if(n== 0) {
			return true;
		}
		while(n >= 493 && gCount > 0) {
			n -= 493;
			gCount --;
		if(n == 0) {
			return true;
		}
		}
		while(n >= 29 && sCount > 0) {
			n -= 29;
			sCount --;
			if(n == 0) {
				return true;
			}
		}
		while(n > 0 && kCount > 0) {
			n--;
			kCount --;
			if(n == 0) {
				return true;
			}
		}
		return false;
	}
	public int[] withdraw(int n) {
		int[] w = {0,0,0};
		int gCount = numGalleons;
		int sCount = numSickles;
		int kCount = numKnuts;
		if(n > 126208) {
			throw new IllegalArgumentException("Your coin purse can't hold that amount!");
		}
		if(exactChange(n) == true) {
		while(n >= 493 && gCount > 0) {
			n -= 493;
			gCount --;
			w[0] = w[0] + 1;
		}
		while(n >= 29 && sCount > 0) {
			n -= 29;
			sCount --;
			w[1] = w[1] + 1;
		}
		while(n > 0 && kCount > 0) {
			n--;
			kCount --;
			w[2] = w[2] + 1;
			}
	numGalleons = numGalleons - w[0];
	numSickles = numSickles - w[1];
	numKnuts = numKnuts - w[2];
	return w;
	}
	else {
		while((n >= 493) && (n > 29)) {
			n -= 493;
			w[0] = w[0] + 1;
		}
		while(n > 1){
			n -= 29;
			w[1] = w[1] + 1;
		}
		while(n == 1) {
			n--;
			w[2] = w[2] + 1;
			}
		return w;
		}
	}
	public int drawRandCoin() {
		int range;
		double randomCoin;
		range = numCoins();
		Random random = new Random();
		randomCoin = random.nextInt(range);
		if(randomCoin < numGalleons) {
			return 2;
		}
		else if(randomCoin < numSickles + numGalleons) {
			return 1;
		}
		else {
			return 0;
		}
	}
	public int[] drawRandSequence(int n) {
		int[] arr = new int[n];
		for(int x = 0; x < n; x++) {
			arr[x] = drawRandCoin();
		}
		return arr;
	}
	public static int compareSequences(int[] coinSeq1, int[] coinSeq2) {
		if(coinSeq1.length != coinSeq2.length) {
			throw new IllegalArgumentException("Sequences must be equal length!");
		}
		if(coinSeq1.length == 0) {
			throw new IllegalArgumentException("Array must have length!");
		}
		int win1 = 0;
		int win2 = 0;
		for(int x = 0; x < coinSeq1.length; x++) {
			if(coinSeq1[x] > coinSeq2[x]) {
				win1++;
			}
			if(coinSeq1[x] < coinSeq2[x]) {
				win2++;
			}
		}
		if(win1 > win2) {
			return 1;
		} else if(win2 > win1) {
			return -1;
		}
		else {
			return 0;
		}
	}
	public int[] randSequenceNoRepl(int n) {
		int[] arr = new int[n];
		int t = 0;
		if(n > numCoins()) {
			throw new IllegalArgumentException("Not enough coins in the purse!");
		}
		for(int z = 0; z < n; z ++) {
			t = drawRandCoin();
			if(t == 2) {
				numGalleons --;
				arr[z] = 2;
			}
			if(t == 1) {
				numSickles --;
				arr[z] = 1;
			}
			if(t == 0) {
				numKnuts--;
				arr[z] = 0;
			}
		}
		return arr;
	}
	
	//args
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CoinPurse cp = new CoinPurse(10, 10, 10);
		//cp.depositGalleons(2);
		//cp.numCoins();
		//cp.exactChange(493);
		//System.out.println(cp.exactChange(30));
		//System.out.println(Arrays.toString(cp.withdraw(521)));
		//System.out.println(cp.drawRandCoin());
		//System.out.println(cp.compareSequences(new int[] {0,1}, new int[] {1,1}));
		//System.out.println(Arrays.toString(cp.drawRandSequence(9)));
		System.out.println(Arrays.toString(cp.randSequenceNoRepl(5)));
	}

}