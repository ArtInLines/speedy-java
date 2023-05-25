package lists;

import java.util.List;
import java.util.function.Function;

public class ListBench {
	/**
	 * @param l is assumed to be an empty list
	 */
	public static <T> long test(List<T> l, Function<Integer, T> makeObj, int len) {
		long startTime = System.currentTimeMillis();
		int incLen = len / 4;
		for (int i = 0; i < len; i++) {
			l.add(makeObj.apply(i));
		}
		// if (len != l.size()) {
		// System.out.println("Oops 1");
		// System.exit(1);
		// }
		// Remove from end
		for (int i = 0; i < incLen; i++) {
			l.remove(len - 1 - (i % 16));
			len--;
		}
		// if (len != l.size()) {
		// System.out.println("Oops 2");
		// System.exit(1);
		// }
		// Remove from beginning
		for (int i = 0; i < incLen; i++) {
			l.remove(i % 16);
		}
		len -= incLen;
		// if (len != l.size()) {
		// System.out.println("Oops 3");
		// System.exit(1);
		// }
		// Remove from arbitrary locations
		for (int i = 0; i < incLen; i++) {
			l.remove((i << 3) % len);
			len--;
		}
		// if (len != l.size()) {
		// System.out.println("Oops 4");
		// System.exit(1);
		// }
		// Insert at beginning
		for (int i = 0; i < incLen; i++) {
			l.add(i % 16, makeObj.apply(i));
		}
		len += incLen;
		// if (len != l.size()) {
		// System.out.println("Oops 5");
		// System.exit(1);
		// }
		// Insert at end
		for (int i = 0; i < incLen; i++) {
			l.add(len - 1 - (i % 16), makeObj.apply(i));
			len++;
		}
		// if (len != l.size()) {
		// System.out.println("Oops 6");
		// System.exit(1);
		// }
		// Insert at arbitrary locations
		for (int i = 0; i < incLen; i++) {
			l.add((i << 5) % len, makeObj.apply(i));
			len++;
		}
		// if (len != l.size()) {
		// System.out.println("Oops 7");
		// System.exit(1);
		// }
		// Get & set at beginning
		for (int i = 0; i < incLen; i++) {
			int idx = i % 16;
			T el = l.get(idx);
			l.set(idx, makeObj.apply(i));
		}
		// if (len != l.size()) {
		// System.out.println("Oops 8");
		// System.exit(1);
		// }
		// Get & set at end
		for (int i = 0; i < incLen; i++) {
			int offset = i % 16;
			T el = l.get(len - 1 - offset);
			l.set(len - 1 - offset, makeObj.apply(i));
		}
		// if (len != l.size()) {
		// System.out.println("Oops 9");
		// System.exit(1);
		// }
		// Get & set at arbitrary locations
		for (int i = 0; i < incLen; i++) {
			int idx = (i << 5) % len;
			T el = l.get(idx);
			l.set(idx, makeObj.apply(i));
		}
		return System.currentTimeMillis() - startTime;
	}
}