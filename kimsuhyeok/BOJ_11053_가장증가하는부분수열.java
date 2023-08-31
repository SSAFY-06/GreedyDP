package kimsuhyeok;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 구하시오
 *
 */

public class BOJ_11053_가장증가하는부분수열 {

    static int N;
    static int[] arr;
    static int[] dp;
    static int answer;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        dp = new int[N];

        st = new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //0부터 N-1까지 탐색
        for(int i=0;i<N;i++){
            LTS(i);
        }

        answer = dp[0];

        //최댓값 찾기
        for(int i=1;i<N;i++){
            answer = Math.max(answer, dp[i]);
        }
        System.out.println(answer);

    }

    private static int LTS(int n){
        //아직 탐색 안한 곳이라면
        if(dp[n]==0){
            dp[n]=1;

            for(int i=n-1;i>=0;i--){
                if(arr[i]<arr[n]){
                    dp[n] = Math.max(dp[n], LTS(i)+1);
                }
            }
        }
        return dp[n];
    }
}
