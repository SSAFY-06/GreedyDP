package homeWork;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 1. 일단 N개의 멀티탭 구멍의 개수에 해당하는 만큼 전기 용품을 채워 놓는다.
 * 2. 만약 다음에 사용할 전기용품이 멀티탭에 꽃혀있으면 -> 패스
 * 3. 다음에 사용할 전기용품이 멀티탭에 꽃혀있지 않으면
 * 		3-1. 처음부터 마지막 멀티탭 구멍을 돌면서 앞으로 사용하지 않는 콘센트가 있는 경우
 * 			해당 콘센트를 제거 후 그 인덱스의 멀티탭 구멍에 추가
 * 		3-2. 처음부터 마지막 멀티탭 구멍을 돌면서 모든 멀티탭 구멍에 있는 전기용품들이 다시 사용되는 경우
 * 			현재 꽃혀있는 전기용품 중 가장 늦게 사용하는 콘센트를 뽑기
 */


public class BOJ_1700_멀티탭스케줄링 {
	
	static int N, K;
	static int[] appliances;
	static boolean[] visited;
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		appliances = new int[K];
		visited = new boolean[K+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<K;i++) {
			appliances[i] = Integer.parseInt(st.nextToken());
		}
		
		int idx = 0;
		int answer = 0;
		
		for(int i=0;i<K;i++) {
			
			// 만약 콘센트가 꽃혀있지 않으면
			if(!visited[appliances[i]]) {
				// 1. 콘센트를 꽃을 여유 공간이 있는 경우
				if(idx < N) {
					// 콘센트를 꽃음 처리
					visited[appliances[i]] = true;
					// 콘센트 꽃을 인덱스 증가
					idx++;
				}
				// 2. 콘센트를 꽃을 여유 공간이 없는 경우
				else {
					List<Integer> list = new ArrayList<Integer>();
					//현재 꽃혀있는 콘센트가 나중에 사용되는지 확인
					for(int j=i;j<K;j++) {
						if(visited[appliances[j]] && !list.contains(appliances[j])) {
							list.add(appliances[j]);
						}
					}
					//현재 꽃혀있는 콘센트가 나중에 사용되지 않는 경우
					if(list.size() != N) {
						for(int j=0;j<visited.length;j++) {
							if(visited[j] && !list.contains(j)) {
								visited[j] = false;
								break;
							}
						}
					}
					//현재 꽃혀있는 모든 콘센트가 나중에도 사용되는 경우
					else {
						//가장 마지막에 사용될 콘센트를 제거
						int removeIdx = list.get(list.size()-1);
						visited[removeIdx] = false;
					}
					
					visited[appliances[i]] = true;
					answer++;
				}
			}
		}
		
		System.out.println(answer);
	}
	
	
	
}
