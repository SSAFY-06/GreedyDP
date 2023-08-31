package workshop_2023_8_30;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12865_평범한배낭 {
	
	static class Stuff{
		int weight;
		int value;
		public Stuff(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}
	}
	
	static int N, K;	//N : 물건의 개수, K : 가방의 최대 무게
	static int[][] dp;
	static Stuff[] stuff;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		dp = new int[N+1][K+1];
		stuff = new Stuff[N+1];
		
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			stuff[i] = new Stuff(w,v);
		}
		
		//배낭의 여유 무게
		int weightLeft = K;
		
		for(int i=1;i<=N;i++) {
			for(int j=0;j<=K;j++) {
				if(stuff[i].weight <= j) {
					dp[i][j] = Math.max(dp[i-1][j], stuff[i].value+dp[i-1][j-stuff[i].weight]);
				}
				else {
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		
//		print();
		System.out.println(dp[N][K]);
		
	}
	
	private static void print() {
		for(int i=0;i<=N;i++) {
			for(int j=0;j<=K;j++) {
				System.out.print(dp[i][j]+" ");
			}
			System.out.println();
		}
	}

}
