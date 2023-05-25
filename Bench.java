import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import lists.ListBench;
import lists.UnorderedList;

public class Bench {
	public static void main(String[] args) {
		long time = 0;

		List<Integer> unorderedList = new UnorderedList<>();
		time = ListBench.test(unorderedList);
		unorderedList.clear();
		System.out.println("Time for UnorderedList: " + Long.toString(time) + "ms");

		List<Integer> arrayList = new ArrayList<>();
		time = ListBench.test(arrayList);
		arrayList.clear();
		System.out.println("Time for ArrayList: " + Long.toString(time) + "ms");

		List<Integer> linkedList = new LinkedList<>();
		time = ListBench.test(linkedList);
		linkedList.clear();
		System.out.println("Time for LinkedList: " + Long.toString(time) + "ms");
	}
}
