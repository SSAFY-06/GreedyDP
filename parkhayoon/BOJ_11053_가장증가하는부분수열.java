package algo.week01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11053_가장증가하는부분수열 {

    static int N; // 수열의 길이 N
    static int[] arr; // 수열
    static int[] dp; // 선택한 부분 수열의 길이 저장

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        dp = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        dp();
        int result = 0;
        for(int i=0; i<N; i++)
            result = Math.max(result, dp[i]+1);
        System.out.println(result);
    }

    private static void dp() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<i; j++) { // 선택한 i값 이전에서 비교
                if(arr[j]<arr[i]) // 만약 앞의 값보다 뒤의 값이 더 긴 경우 - 오름차순의 경우, 부분 수열로 추가 가능
                    dp[i] = Math.max(dp[i], dp[j]+1); // 현재까지 해당 값까지 최대 부분수열의 길이가 더 긴지 확인
            }
        }
    }

}