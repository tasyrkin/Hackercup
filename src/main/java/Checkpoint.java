import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Checkpoint {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Integer[] arr = new Integer[10000000+1];
		arr[1] = 1;
		maincycle: for(int n1 = 1; n1 <= 10000000; n1++){
			for(int k1 = 1; k1 <= n1/2; k1++){
				long up = 1;
				long down = 1;
				long prev = up;
				for(int m1 = 0; m1 < k1; m1++){
					prev = up;
					up *= (n1-m1);
					down *= (m1+1);
					if(up/down > 10000000)
						continue maincycle;
					if(up < 0){
						throw new RuntimeException("negative");
					}
					if(prev > up){
						throw new RuntimeException("overflow");						
					}
				}
				long num = up/down;
				Integer count = arr[(int)num];
				if(count == null || count > n1){
					count = n1;
				}
				arr[(int)num] = count;
			}				
		}
		for (int i = 0; i < n; i++) {
			int s = Integer.parseInt(br.readLine());
			long min = Integer.MAX_VALUE;
			for(int j = 1; j * j <= s; j++){
				if(s%j==0){
					int curr = arr[j]+arr[s/j];
					if(curr < min)min = curr;
				}
			}
			System.out.println("Case #" + (i+1) + ": " + min);
		}
	}
}
