import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class AlphabetSoup {

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
			char[] arr = br.readLine().toCharArray();
			Map<Character, Integer> map = new HashMap<Character, Integer>();
			for (int i = 0; i < arr.length; i++) {
				Integer cnt = map.get(arr[i]);
				if(cnt == null){
					cnt = 0;
				}
				cnt++;
				map.put(arr[i], cnt);
			}
			char[]hack = "HACKERCUP".toCharArray();
			Integer cnt = Integer.MAX_VALUE;
			for(int i = 0; i < hack.length; i++){
				Integer curr = map.get(hack[i]);
				if(curr == null){
					cnt = 0;
					break;
				}
				if(curr < cnt){
					cnt = curr;
				}
			}
			System.out.println("Case #" + number + ":" + cnt);
		}
	}

}
