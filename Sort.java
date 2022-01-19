package hw6;

import java.util.Iterator;
import java.util.Set;
import java.net.SocketTimeoutException;
import java.util.Arrays;
import java.util.HashSet;

public class Sort {
		private static class Interval{
		//data fields
		private int lower;
		private int upper;
		/*
		 * Constructor, constructs a new Interval with specified upper and lower
		 */
		public Interval(int lower, int upper) {
			this.lower = lower;
			this.upper = upper;
		}
	
		/*
		 * returns lower bound
		 */
		public int getLower() {
			return lower;
		}
		
		/*
		 * returns upper bound
		 */
		public int getUpper() {
			return upper;
		}
		
		/*
		 * returns true if the object and the given interval has the same upper and lower bounds
		 */
		public boolean equals(Object o) {
			if(((Interval) o).getLower() == lower && ((Interval) o).getUpper() == upper) {
				return true;
			}
			return false;
		}
		
		/*
		 * returns lower * lower + upper
		 */
		public int hashcode() {
			return lower * lower + upper;
		}
		}
		
		/**
		 * Helper function that partitions the array
		 */
		public static <T extends Comparable<T>> void partition(Set<Interval> s,T[] q, Interval i) {
			int f = i.getLower();
			int m = (i.getUpper()+1)/2;
			int l = i.getUpper();
			int temp = f;
			int a = temp + 1;			
			findPivot(q,f,m,l);
			while (a < l) {
				if (q[a].compareTo(q[f]) < 0) {
					temp++;
					swap(temp,a,q);
					a++;
				} else {
					a++;
				}
			}
			swap(temp,f,q);
			s.add(new Interval(0, temp-1));
			s.add(new Interval(temp+1, l));
			Interval[] temp1 = new Interval[s.size()];
			s.remove(temp1[0]);
		}
		
		/**
		 * Swaps two elements in the given array
		 */
			public static <T extends Comparable<T>> void swap(int a, int b,T[] arr) {
				T temp = arr[a];
				arr[a] = arr[b];
				arr[b] = temp;
			}

		/**
		 * Regular bubble sort
		 */
			public static <T extends Comparable<T>> void bubble(T[] arr) {
				boolean swap = true;
				T temp;				
				while(swap){
					swap = false;
					for (int i = 0; i < arr.length-1; i++){
						if (arr[i].compareTo(arr[i+1])>0){
							temp = arr[i];
							arr[i] = arr[i+1];
							arr[i+1] = temp;
							swap = true;
						}
					}
				}
			}

		/**
		 * Finds middle value from the first, middle, and last
		 */
			public static <T extends Comparable<T>> void findPivot(T[] arr, int a, int b, int c) {
				T i = arr[a];
				T j = arr[b];
				T k = arr[c];
				if (i.compareTo(j) < 0){
					if (j.compareTo(k) < 0) {
						swap(a,b,arr);
					} else {
						swap(a,c,arr);
					}
				} else if  (i.compareTo(k) > 0) {
						swap(a,c,arr);
				}
			}
			
			/**
			 * quick sort method
			 */
			public static <T extends Comparable<T>> T[] sort(T[] array) {
				Set<Interval> s = new HashSet<>();
				s.add(new Interval(0, array.length-1));
				while (!s.isEmpty()) {
					Interval[] tempSet = new Interval[s.size()];
					s.toArray(tempSet);
					Interval a = tempSet[0];
					s.remove(tempSet[0]);
					if (a.getUpper() - a.getLower() <= 3) {
						bubble(array);
					} else {
						partition(s,array,a);
					}
				}
				return array;
			}
			
		public static void main(String[] args) {
		Sort a = new Sort();
		Integer[] test = new Integer[]  {2,1,-3,5,6,7,84,2};
		a.sort(test);
		System.out.println(Arrays.toString(test));

	}
}
