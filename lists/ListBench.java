package lists;

import java.util.List;

public class ListBench {
	/**
	 * @param l is assumed to be an empty list
	 */
	public static long test(List<Integer> l) {
		long startTime = System.currentTimeMillis();
		int len = 100_000;
		int incLen = 10_000;
		for (int i = 0; i < len; i++) {
			l.add(i);
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
			l.add(i % 16, i);
		}
		len += incLen;
		// if (len != l.size()) {
		// System.out.println("Oops 5");
		// System.exit(1);
		// }
		// Insert at end
		for (int i = 0; i < incLen; i++) {
			l.add(len - 1 - (i % 16), i);
			len++;
		}
		// if (len != l.size()) {
		// System.out.println("Oops 6");
		// System.exit(1);
		// }
		// Insert at arbitrary locations
		for (int i = 0; i < incLen; i++) {
			l.add((i << 5) % len, i);
			len++;
		}
		// if (len != l.size()) {
		// System.out.println("Oops 7");
		// System.exit(1);
		// }
		// Get & set at beginning
		for (int i = 0; i < incLen; i++) {
			int idx = i % 16;
			Integer el = l.get(idx);
			l.set(idx, i);
		}
		// if (len != l.size()) {
		// System.out.println("Oops 8");
		// System.exit(1);
		// }
		// Get & set at end
		for (int i = 0; i < incLen; i++) {
			int offset = i % 16;
			Integer el = l.get(len - 1 - offset);
			l.set(len - 1 - offset, i);
		}
		// if (len != l.size()) {
		// System.out.println("Oops 9");
		// System.exit(1);
		// }
		// Get & set at arbitrary locations
		for (int i = 0; i < incLen; i++) {
			int idx = (i << 5) % len;
			Integer el = l.get(idx);
			l.set(idx, i);
		}
		return System.currentTimeMillis() - startTime;
	}
}