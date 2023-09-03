package kimsuhyeok;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 일이 끝나면 pay가 받는다.
 * 우리가 구하고 싶은건 백준이가 얻을 수 있는 최대 수익이기 때문에
 * dp배열에 들어갈 값은 해당 날짜에 벌 수 있는 최대 수익이어야 한다.
 *
 *
 */

public class BOJ_14501_퇴사 {

    static int N;
    static int[] t, p, dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        t = new int[N+1];
        p = new int[N+1];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());

            int time = Integer.parseInt(st.nextToken());
            int pay = Integer.parseInt(st.nextToken());
            t[i] = time;
            p[i] = pay;
        }

        dp = new int[N+1];

        for(int i=0;i<N;i++){
            // 퇴사 전날 까지만 상담 진행
            // N+1일째에 퇴사를 진행하기 때문에 N일까지 상담진행
            if(i+t[i]<=N){
                dp[i+t[i]] = Math.max(dp[i+t[i]], dp[i]+p[i]);
            }
            dp[i+1] = Math.max(dp[i+1], dp[i]);
//            System.out.println("i: "+i);
//            print();
        }
        System.out.println(dp[N]);
    }

    private static void print(){
        for(int i=1;i<=N;i++){
            System.out.println(Arrays.toString(dp));
        }
    }
}
