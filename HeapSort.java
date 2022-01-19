package sorting;

import java.util.Arrays;
import recitation9.Heap;
public class HeapSort {

	public static int[] heapSort(int[] arr) {
		Heap sortingHeap = new Heap(arr.length);
		//building min heap
		//add function
		for(int i = 0; i < arr.length; i++) {
			sortingHeap.add(arr[i]);
		}
		
		System.out.println(sortingHeap);
		//remove until heap is empty
		for(int i = 0; i < arr.length; i++) {
			arr[i] = sortingHeap.remove();
		}
		
		
		System.out.println(sortingHeap);
		return arr;
	}
	
	
	public static void main(String[] args) {
		
	}
}