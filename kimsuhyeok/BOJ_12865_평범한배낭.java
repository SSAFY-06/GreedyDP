package kimsuhyeok;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12865_평범한배낭{

	static class Stuff{
		int weight;
		int value;
		public Stuff(int weight, int value){
			this.weight = weight;
			this.value = value;
		}
	}

	static int N,K;
	static int[][] dp;
	static Stuff[] stuffs;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		dp = new int[N+1][K+1];
		stuffs = new Stuff[N+1];

		for(int i=1;i<=N;i++){
			st = new StringTokenizer(br.readLine());

			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			stuffs[i] = new Stuff(w,v);
		}

		int weightLeft = K;

		for(int i=1;i<=N;i++){
			for(int j=0;j<=K;j++){
				if(stuffs[i].weight<=j){
					dp[i][j] = Math.max(dp[i-1][j], stuffs[i].value+dp[i-1][j-stuffs[i].weight]);
				}
				else{
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		System.out.println(dp[N][K]);
	}

}