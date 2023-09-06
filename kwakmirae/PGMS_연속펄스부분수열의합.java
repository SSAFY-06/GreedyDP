package kwakmirae;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PGMS_연속펄스부분수열의합 {

	public static void main(String[] args) throws IOException {
		// 입력
		// 2 3 -6 1 3 -1 2 4
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] sequence = new int[st.countTokens()];
		for(int i=0; i<sequence.length; i++) {
			sequence[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(solution(sequence));
	}
	
	public static long solution(int[] sequence) {
        int[] perseOne = new int[sequence.length];
        int[] perseTwo = new int[sequence.length];
        for(int i=0; i<sequence.length; i++) {
        	if(i%2!=0) {
        		perseOne[i] = sequence[i];
        		perseTwo[i] = -sequence[i];
        	}
        	else {
        		perseOne[i] = -sequence[i];
        		perseTwo[i] = sequence[i];        		
        	}
        }
        return Math.max(dp(perseOne), dp(perseTwo));
    }
	
	public static long dp(int[] perse) {
		int[] dp = new int[perse.length+1];
		int max=dp[0];
		for(int i=1; i<=perse.length; i++) {
			dp[i] = perse[i-1];
			if(dp[i-1] >= 0) dp[i] += dp[i-1];
			if(max <= dp[i]) max = dp[i];
		}
		return max;
	}

}
