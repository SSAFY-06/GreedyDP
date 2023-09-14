package algo.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
 * 
 * 아이디어
 * while(인구 이동 가능한 경우) {
 * 		방문 배열 초기화
 * 		배열 전체 탐색 {
 * 				해당 점에서 dfs 탐색 {...}
 * 				dfs 종료 후 평균 구하기
 * 				tempA에 평균 들어가는 부분 입력
 * 			}
 * }
 */
public class BOJ_16234_인구이동 {
	
	static int N, L, R;
	static int[][] A;
	static int[][] tempA; // i일차 A 이동 후 위치 저장
	static int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}}; // 4방 탐색
	static boolean[][] visited; // dfs 방문 확인
	static boolean movable = true; // 인구 이동 가능 여부
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
		
		int date = -1; // 인구 이동 날짜 수
		while(movable) { // 인구 이동이 가능할 때 실행
			movable = false; // 일단 움직이지 않는다고 가정 - 추후 탐색에서 인구 이동이 가능할 때 바꿔주기
			visited = new boolean[N][N]; // 하루 인구 이동 후 다시 전체 탐색하므로 초기화
			tempA = new int[N][N];
			// 배열의 모든 부분 탐색
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					// 만약 이전에 이미 탐색한 곳이면 통과
					if(visited[i][j]) continue;
					// 평균값 구하기 위한 전제조건
					visited[i][j]=true; // 현재 지점 방문처리
					sum = A[i][j]; // 인구 수의 합
					cnt = 1; // 도시 수
					dfs(i, j);
					if(cnt==1) { // 만약 dfs 탐색했으나 이동 불가능한 경우
						tempA[i][j]=A[i][j]; // 원본 값 복사
						continue;
					}
					int avg = sum/cnt; // 평균 구하기(소수점 내림)
					// 배열 내부에서 바꿔줘야 하는 연결된 부분 변경
					for(int p=0; p<N; p++) {
						for(int q=0; q<N; q++) {
							if(visited[p][q] && tempA[p][q]==0) // 방문한 점  and 아직 값을 넣지 않은 점
								tempA[p][q] = avg;
						}
					}
				}
			}
			// 인구 이동 완료 - 날짜 1일 추가
			date++;
			// A 배열을 업데이트
			A = tempA.clone();
		}
		
		System.out.println(date);
	}
	
	private static void dfs(int x, int y) {
		// 4방 탐색
		for(int d=0; d<4; d++) {
			int dx = x+deltas[d][0];
			int dy = y+deltas[d][1];
			// 범위 밖이거나 이미 방문했거나 두 도시 간 차이가 L 미만, R 초과면 이동 불가
			if(dx<0 || dx>=N || dy<0 || dy>=N || visited[dx][dy] || Math.abs(A[x][y]-A[dx][dy])<L || Math.abs(A[x][y]-A[dx][dy])>R) continue;
			// 이동 가능한 도시가 있음 - 인구 이동 가능 확인
			movable = true;
			// 이동하려는 지점을 방문처리
			visited[dx][dy] = true;
			sum += A[dx][dy];
			cnt++;
			dfs(dx,dy);
		}
	}

}
