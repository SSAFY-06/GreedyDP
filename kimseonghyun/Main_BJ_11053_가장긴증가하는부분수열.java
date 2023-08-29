package 알고리즘스터디.Week1_Greedy_DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_BJ_11053_가장긴증가하는부분수열 {
    static int[] arr;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) { // 배열 입력 받기
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int end = 0; // 시작 인덱스
        int maxCnt = 0; // 가장 큰 부분수열의 길이
        int start = 0;
        while (end != N) {
            int cnt = 1;
            for (int i = 0; i <= end; i++) {
                if (i == 0) start = arr[i];
                if (arr[i] > start) {
                    cnt++;
                    start = arr[i];
                }
            }

            maxCnt = Math.max(maxCnt, cnt);
            end++;
        }
        System.out.println(maxCnt);
    }
}
