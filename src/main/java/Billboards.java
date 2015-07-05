import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Billboards {

	/**
	 * @param args
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		int number = 0;
		while (number++ < t) {
			String[] words = br.readLine().split("\\s+");
			int width = Integer.parseInt(words[0]);
			int height = Integer.parseInt(words[1]);
			boolean found = false;
			int font = Math.max(width, height);
			fontLoop : for (; font > 0; font--) {
				int numOfLines = height / font;
				int numOfCharsInLine = width / font;
				for (int i = 2; i < words.length; numOfLines--) {
					int currLineLen = words[i].length();
					int cntPassed = 0;
					while (currLineLen <= numOfCharsInLine) {
						cntPassed++;
						if(i + cntPassed >= words.length)break;
						currLineLen += 1 + words[i+cntPassed].length();
					}
					if(cntPassed == 0){
						continue fontLoop;
					}
					i += cntPassed;
				}
				if(numOfLines >= 0){
					found = true;
					break;
				}
			}
			System.out.println("Case #" + number + ": " + (found ? font : 0));
		}
	}

}
