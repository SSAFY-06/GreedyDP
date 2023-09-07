package homeWork;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1. 모든 노드를 방문할 때까지 순회하며 아직 방문 안한 노드 체크
 * 2. 난 bfs를 이용하여 노드를 방문
 * 		2-1. 다음으로 이동할 수 있는 노드는 현재 노드 값과 L이상 R이하의 값을 가지고 있어야 한다.
 * 3. 방문한 노드들은 visited배열 혹은 list에 담아두기 => 근데 visited 배열을 만들어서 하면 다시 for문을 돌면서 true인값을 세야함 따라서 난 리스트를 이용
 * 		3-1. 노드 값 합 구해주기
 * 4. 모든 노드 방문 끝났으면 인구이동 시작
 * 5. 각 노드 값 변경
 */

public class BOJ_16234_인구이동 {
	
	static class Node{
		int r;
		int c;
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int N,L,R;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,-1,0,1};
	static List<Node> list;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(move());
		
	}
	
	public static int move() {
		int count = 0;
		
		//더 이상 인구이동이 일어나지 않을 때까지 반복해야한다.
		while(true) {
			visited = new boolean[N][N];
			boolean ableToMove = false;	//이 boolean 변수를 이용하여 인구이동이 일어나지 않는 상황을 체크하여 탈출
			
			for(int i=0; i<N;i++) {
				for(int j=0;j<N;j++) {
					//방문하지 않았으면
					if(!visited[i][j]) {
						// 인구 이동할 인구 계산
						int popSum = bfs(i,j);
						
						if(list.size() > 1) {
							changePop(popSum);
							ableToMove = true;
						}
					}
				}
			}
			
			if(!ableToMove) return count;
			count++;
		}
	}
	
	public static int bfs(int r, int c) {
		Queue<Node> queue = new LinkedList<>();
		list = new ArrayList<Node>();
		
		//bfs를 시작하는 노드
		Node start = new Node(r,c);
		
		//queue와 list에 노드 추가 및 방문 체크
		queue.offer(start);
		list.add(start);
		visited[start.r][start.c] = true;
		
		//아직은 시작점의 값이 인구 총 합계
		int sum = map[start.r][start.c];
		
		//queue가 빌 때까지 반복
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			
			//4방 탐색
			for(int i=0;i<4;i++) {
				//다음 노드 계산
				int nr = cur.r+dr[i];
				int nc = cur.c+dc[i];
				
				//만약 범위 안에 있고 아직 방문하지 않았따면
				if(isInRange(nr, nc) && !visited[nr][nc]) {
					//노드간의 값 차이 계산
					int diff = calDiff(nr, nc, cur);
					
					//만약 노드간의 값 차이가 L이상 R 이하이면
					if(checkPopRange(diff)) {
						//queue와 리스트에 다음 노드 추가 및 방문 체크
						queue.offer(new Node(nr, nc));
						list.add(new Node(nr, nc));
						visited[nr][nc]= true;
						
						//인구 총 합계 갱신
						sum+= map[nr][nc];
					}
				}
			}
		}
		return sum;
		
	}
	
	//인구 수 바꿔주는 함수
	public static void changePop(int sum) {
		int avg = sum/list.size();
		for(Node n:list) {
			map[n.r][n.c] = avg;
		}
	}

  //두 노드간의 차이가 L이상 R이하인지 체크하는 함수
	public static boolean checkPopRange(int pop) {
		return pop>= L && pop<=R;
	}

  // 두 노드간의 값 차이 계산하는 함수
	public static int calDiff(int r, int c, Node node) {
		return Math.abs(map[node.r][node.c] - map[r][c]);
	}

  // BFS에서 범위 체크하는 함수
	public static boolean isInRange(int r, int c) {
		return r>=0 && c>=0 && r<N && c<N;
	}
}
