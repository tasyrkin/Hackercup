import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The problem was not solved
 * @author Compaq_Cq
 *
 */
public class Auction {
	public static int gcd(int a, int b) {
		if (a < b)
			return gcd(b, a);
		if (b == 0)
			return a;
		int r = a % b;
		return gcd(b, r);
	}

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		int n = 10000000;
		long s = System.currentTimeMillis();
		long max = Long.MIN_VALUE;
		int imax = 0;
		int jmax = 0;
		for (int i = n; i >= 0; i--) {
			for (int j = n; j >= 0; j--) {
				long scm = ((long) i * (long) j) / gcd(i, j);
				if (scm > max) {
					imax = i;
					jmax = j;
					max = scm;
				}
			}
			System.out.println(imax + "," + jmax + "," + max);
		}
		System.out.println((System.currentTimeMillis() - s));
		// BufferedReader br = new BufferedReader(new
		// InputStreamReader(System.in));
		// int t = Integer.parseInt(br.readLine());
		// int number = 0;
		// while (number++ < t) {
		// String[] words = br.readLine().split("\\s+");
		// int width = Integer.parseInt(words[0]);
		// int height = Integer.parseInt(words[1]);
		// boolean found = false;
		// int font = Math.max(width, height);
		// fontLoop: for (; font > 0; font--) {
		// int numOfLines = height / font;
		// int numOfCharsInLine = width / font;
		// for (int i = 2; i < words.length; numOfLines--) {
		// int currLineLen = words[i].length();
		// int cntPassed = 0;
		// while (currLineLen <= numOfCharsInLine) {
		// cntPassed++;
		// if (i + cntPassed >= words.length)
		// break;
		// currLineLen += 1 + words[i + cntPassed].length();
		// }
		// if (cntPassed == 0) {
		// continue fontLoop;
		// }
		// i += cntPassed;
		// }
		// if (numOfLines >= 0) {
		// found = true;
		// break;
		// }
		// }
		// System.out.println("Case #" + number + ": " + (found ? font : 0));
		// }
	}

}
