package kimsuhyeok;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14501_퇴사_재귀 {

    static int N;
    static int[] t, p;
    static int answer;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        t = new int[N+1];
        p = new int[N+1];
        answer = Integer.MIN_VALUE;

        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());

            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        getMaxValue(0,0);

        System.out.println(answer);
    }

    public static void getMaxValue(int idx, int pay){
        if(idx>N){
            return;
        }
        if(idx==N){
            answer = Math.max(answer, pay);
            return;
        }

        getMaxValue(idx+t[idx+1], pay+p[idx+1]);
        getMaxValue(idx+1, pay);
    }
}
