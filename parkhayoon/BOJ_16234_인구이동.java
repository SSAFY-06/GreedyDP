package algo.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 크기 NxN, 1x1 칸으로 나눠짐
 * 각 칸은 나라 하나씩 존재, A[r][c] = r행 c열 인구수
 * 1. 국경선 공유 두 국가의 인구차가  L ≤ x ≤ R이면 하루동안 국경 개방
 * 2. 모든 국가가 위에 따라 국경선 개방 후 인구 이동 시작
 * 3. 국경선이 열려서 연결되어있음 - 연합
 * 4. 연합의 각 칸의 인구수 = 연합의 인구수 / 연합 이루는 칸 수, 소수점 버림
 * 5. 이동 후 연합 해체, 모든 국경선 닫음
 * 인구 이동이 발생하는 기간?
 *
 * 입력
 * 첫째 줄: N,L,R(1 ≤ N ≤ 50, 1 ≤ L ≤ R ≤ 100)
 * N개 줄: 각 나라의 인구수(0 ≤ A[r][c] ≤ 100)
 * 인구 이동 발생 기간 ≤ 2000
 */
public class BOJ_16234_인구이동 {

    static int N, L, R;
    static int[][] A;
    static int[][] tempA;
    static int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}};
    static boolean[][] visited;
    static boolean movable = true;
    static int sum, cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        tempA = new int[N][N];
        visited = new boolean[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int date = -1;
        while(movable) {
            movable = false;
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    sum = A[i][j];
                    cnt = 1;
                    dfs(i, j);
                    if(cnt==0) {
                        tempA[i][j] = A[i][j];
                        continue;
                    }
                    int avg = sum/cnt;
                    for(int p=0; p<N; p++) {
                        for(int q=0; q<N; q++) {
                            if(visited[p][q] && tempA[p][q]==0)
                                tempA[p][q] = avg;
                        }
                    }
                }
            }
            date++;
            A = tempA.clone();
        }
        System.out.println(date);
    }

    private static void dfs(int x, int y) {
        visited[x][y]=true;
        for(int d=0; d<4; d++) {
            int dx = x+deltas[d][0];
            int dy = y+deltas[d][1];
            if(dx<0 || dx>=N || dy<0 || dy>=N || visited[dx][dy] || Math.abs(A[x][y]-A[dx][dy])<L || Math.abs(A[x][y]-A[dx][dy])>R) continue;
            movable = true;
            visited[dx][dy] = true;
            sum += A[dx][dy];
            cnt++;
            dfs(dx,dy);
        }
    }

}
