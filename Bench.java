import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import lists.ListBench;
import lists.UnorderedList;
import util.BigObject;

public class Bench {
	public static void main(String[] args) {
		int len = 100_000;
		System.out.println("Benchmark for Lists of Integers:");
		benchLists(i -> i, len);
		System.out.println();
		System.out.println("Benchmark for Lists of big Objects:");
		benchLists(i -> new BigObject(i), len);
	}

	public static <T> void benchLists(Function<Integer, T> makeObj, int len) {
		long time = 0;

		List<T> unorderedList = new UnorderedList<>();
		time = ListBench.test(unorderedList, makeObj, len);
		unorderedList.clear();
		System.out.println("Time for UnorderedList: " + Long.toString(time) + "ms");

		List<T> arrayList = new ArrayList<>();
		time = ListBench.test(arrayList, makeObj, len);
		arrayList.clear();
		System.out.println("Time for ArrayList: " + Long.toString(time) + "ms");

		// List<T> linkedList = new LinkedList<>();
		// time = ListBench.test(linkedList, makeObj, len);
		// linkedList.clear();
		// System.out.println("Time for LinkedList: " + Long.toString(time) + "ms");
	}
}
