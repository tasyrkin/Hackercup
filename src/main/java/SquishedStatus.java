import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SquishedStatus {
	private static long MOD = 4207849484l;

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] maxChar = new int[n];
		String[] inputStr = new String[n];
		String line = null;
		int cnt = 0;
		boolean isFirst = true;
		while (cnt < n && null != (line = br.readLine())) {
			String[] parts = line.split("\\s+");
			for (int i = 0; i < parts.length; i++) {
				if (isFirst) {
					maxChar[cnt] = Integer.parseInt(parts[i]);
				} else {
					inputStr[cnt] = parts[i];
				}
				isFirst = !isFirst;
				if (isFirst)
					cnt++;
			}
		}
		for (int i = 0; i < n; i++) {
			int maxCharVal = maxChar[i];
			String msg = inputStr[i];
			int len = msg.length();
			long[][] A = new long[len][3];
			for (int j = 0; j < len; j++) {
				for (int k = 0; k < 3; k++) {
					if (msg.charAt(j) == '0') {
						continue;
					}
					if (j + k + 1 > len) {
						continue;
					}
					int candidate = Integer.parseInt(msg.substring(j, j + k + 1));
					long three = 0;
					if (j - 3 >= 0) {
						three = A[j - 3][2];
					}
					long two = 0;
					if (j - 2 >= 0) {
						two = A[j - 2][1];
					}
					long one = 0;
					if (j - 1 >= 0) {
						one = A[j - 1][0];
					}
					if (maxCharVal >= candidate) {
						if (j == 0) {
							A[j][k] = 1;
						} else {
							A[j][k] = (A[j][k] + (((one + two) % MOD) + three) % MOD) % MOD;
						}
					}
				}
			}
			long res = 0;
			for (int k = 0; k < 3; k++) {
				if (len - 1 - k >= 0) {
					res = (res + A[len - 1 - k][k]) % MOD;
				}
			}
			System.out.println("Case #" + (i + 1) + ": " + res);
		}
	}
}
