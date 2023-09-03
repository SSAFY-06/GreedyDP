package kimsuhyeok;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15486_퇴사2 {

    static int N;
    static int[] t, p, dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        t = new int[N+1];
        p = new int[N+1];

        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());

            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N+1];

        for(int i=0;i<N;i++){
            if(i+t[i]<=N){
                dp[i+t[i]] = Math.max(dp[i+t[i]], dp[i]+p[i]);
            }
            dp[i+1] = Math.max(dp[i+1], dp[i]);
        }


        System.out.println(dp[N]);
    }
}
