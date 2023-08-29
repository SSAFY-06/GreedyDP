package kimsuhyeok;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 5 2 4 1 -> 5 2 2 1로 고려하여 계산
 *
 * 다른 예시 8 10 7 3 4 3 2 7 1 -> 8 8 7 3 3 3 2 2 1로 고려하여 계산
 *
 */

public class BOJ_13305_주유소 {

    static int N;
    static long[] dist;
    static long[] cost;

    static long answer;
    static long minCost;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        dist = new long[N-1];
        cost = new long[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N-1;i++){
            dist[i] = Long.parseLong(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            cost[i] = Long.parseLong(st.nextToken());
        }

        answer = 0;
        minCost = cost[0];

        calculate();
        System.out.println(answer);
    }

    private static void calculate() {
        for(int i=0;i<N-1;i++){

            if(cost[i]<minCost){
                minCost = cost[i];
            }
            answer += (minCost * dist[i]);
        }
    }


}
