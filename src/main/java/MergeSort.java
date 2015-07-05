import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class MergeSort {

	public static int[]workArray = null;
	public static int[]tmpArray = null;
	/**
	 * @param args
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for(int i = 0; i < t; i++){
			int n = Integer.parseInt(br.readLine());
			char[]arr = br.readLine().toCharArray();
			workArray = new int[n];
			for(int j = 0; j < n; j++) {
				workArray[j] = j;
			}
			tmpArray = new int[n];
			merge_sort(workArray, 0, workArray.length-1, arr, 0);
			for(int j = 0; j < n; j++) {
				tmpArray[workArray[j]] = j+1;
			}			
			System.out.println("Case #" + (i+1) + ": " + checksum(tmpArray));
		}
	}
	public static int merge_sort(int[]input, int start, int finish, char[]arr, int cnt){
		int n = finish-start+1;
		if(n <= 1){
			return cnt;
		}
		int mid = n/2;
		cnt = merge_sort(input, start, start+mid-1, arr, cnt);
		cnt = merge_sort(input, start+mid, finish, arr, cnt);
		cnt = merge(input, start, start+mid-1, start+mid, finish, arr, cnt);
		return cnt;
	}
	public static int merge(int[]input, int start1, int finish1, int start2, int finish2, char[]arr, int cnt) {
		int i1 = start1;
		int i2 = start2;
		int i = start1;
		while(i1 <= finish1 && i2 <= finish2){
			if(arr[cnt] == '1') {
				tmpArray[i++] = input[i1++];
			} else if (arr[cnt] == '2') {
				tmpArray[i++] = input[i2++];
			}
			cnt++;
		}
		while(i1 <= finish1){
			tmpArray[i++] = input[i1++];
		}
		while(i2 <= finish2){
			tmpArray[i++] = input[i2++];
		}
		for(i = start1; i <= finish2; i++){
			input[i] = tmpArray[i];
		}
		return cnt;
	}
	public static long checksum(int[]arr){
		long result = 1;
		for(int i = 0; i < arr.length; i++){
			result = (31 * result + arr[i]) % 1000003;
		}
		return result;
	}
}
