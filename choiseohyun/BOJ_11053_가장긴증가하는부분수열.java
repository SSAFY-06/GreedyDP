import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 가장 긴 증가하는 부분수열
 * 
 * 수열 A가 주어졌을때 가장 긴 증가하는 부분수열의 길이를 구하는 프로그램을 작성하라
 * A={10,20,10,30,20,50}인 경우 가장 긴 증가하는 부분수열은 {10,20,30,50}이고 길이는 4이다.
 * 
 * 풀이 : 각 원소의 가장 긴 부분수열 길이를 저장하면 되지않나
 * a[0] = 1일거고
 * a[1] = 이전에 자기보다 작은 원소를 찾아 -> 10-> 1이니까 1+1 해서 2넣음
 * a[2] = 이전에 자기보다 작은 원소 없음 -> 1 넣음
 * a[3] = 30보다 작은원소중 길이 가장 큰것(가장 큰수와 같을듯) -> a[2] -> 2니까 3넣음
 * a[4] = 20보다 작은 원소중 길이 가장 큰것-> a[2] = 1이니까 2넣음
 * a[5] = 50보다 작은 원소중 a[3]+1 = 4
 * 
 * arr[n] = arr[k]+1 <- arr[k]는 n미만중에 가장 큰수..
 */
public class Main {
	static int N, len[], arr[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		len = new int[N]; //부분수열 길이의 최댓값을 저장한다
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		Arrays.fill(len, -1);
		len[0] = 1;
		if(N>1) {
			if(arr[1]>arr[0]) len[1] = 2;
			else len[1] = 1;
		}
		System.out.println(solution());
		//System.out.println(Arrays.toString(len));
	}
	
	private static int solution() {
		setLength(N-1); //N-1까지 셋팅한다
		//가장 큰 길이를 구한다.
		int max = 1;
		for(int m : len) max = Math.max(max, m);
		
		return max;
	}
	
	//길이배열을 셋팅한다
	private static int setLength(int n) {
		if(len[n] != -1) return len[n];
		else {
			int max = 0;
			for(int i=0; i<n; i++) { //앞에서부터 돌면서 값이 자기보다 작은 배열중 가장 큰 길이값을 구한다
				if(arr[n]>arr[i]) max=Math.max(max, setLength(i));
			}
			len[n-1] = setLength(n-1);
			return len[n] = max+1; //앞의 길이가 있다면 거기에 +1, 없다면 0+1로 셋팅된다.
		}
	}
	
	//내가 작성한 탑다운 방식의 경우 최초로 구하는 원소의 길이가 1이면(즉 자기보다 작은게 없으면 setLength()자체를 재귀호출하지 않으므로
	//나머지 값들이 전부 -1이됨..;;
	
}
