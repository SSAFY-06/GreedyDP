package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/** 평범한 배낭
 * 여행에 필요한 물건 N개가 있다. 각 물건은 무게 W와 가치 V를 지닌다.
 * 준서는 최대 K만큼의 무게를 가져가며, 이때 배낭 물건 가치의 최댓값을 알려주자.
 * 
 * 풀이 
 * 각 물건별로 행을 만들고, 무게별로 열을 만든다.
 * 0행을 돌면서 해당 해당 무게일때 최대치의 가치를 채운다.
 * 1행은 0행을 복사하며, 또 돌면서 더 큰수가 가능하다면 갱신한다.
 * 이후도 마찬가지인데, 만약 해당 무게가 어떤 조합이 가능하다면(ex, 7kg일때는 6kg하나로 입력되어있으나 4+3kg일수있음)
 * 둘중 더 큰값으로 저장한다. arr[6kg행][7kg열], arr[4kg행][4kg열]+3kg의V 중 큰값을 찾는다. (누적합의 이용)
 * 이를 점화식으로 만들면 dp[i][j] = Math.max(dp[i][j], dp[i-1][j-w[i]]+v[i])
 * 해석하자면 dp[i][j]에 이전행의 누적값이 들어있으므로 그값과, 전행의 j-w[i]열, 즉 전행에서 전체무게-현행무게 뺀 행값을 더해서 큰걸찾는다.
 * 
 */
public class Main {
	static int N, K, dp[][], w[], v[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dp = new int[N+1][K+1]; //읽기 쉬우라고 패딩값 줌
		w = new int[N+1];
		v = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			w[i] = Integer.parseInt(st.nextToken());
			v[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=K; j++) {
				dp[i][j] = dp[i-1][j]; //일단 전행 복사(누적)
				//무게 남을경우(즉 이전에 채우지 못했거나, 어떠한 조합으로 채울수있는경우) 값 업데이트
				if(j-w[i]>=0) dp[i][j] = Math.max(dp[i][j], dp[i-1][j-w[i]]+v[i]);
			}
		}
		
		System.out.println(dp[N][K]);
	}

}
