package algo.week01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 도시 N개 일직선 도로
 * 제일 왼쪽 -> 오른쪽
 * 도로 사이 길이가 다를 수 있음
 * 
 * 처음 출발 시 자동차 기름 없어서 주유소에서 기름 넣고 출발
 * 기름통 크기 무제한, 얼마든지 많은 기름을 넣을 수 있음
 * 1km당 1L 기름 사용
 * 각 도시에는 단 하나의 주유소
 * 주유소 마다 리터당 가격이 다를 수 있음
 * 이동 최소 비용 계산
 * 
 * 입력
 * 첫째 줄: 도시의 개수 N(2 ≤ N ≤ 100,000)
 * 다음 줄: 인접한 두 도시를 연결하는 도로의 길이 N-1개
 * 다음 줄: 주유소의 리터당 가격이 제일 왼쪽 도시부터 순서대로 N개의 자연수
 * 제일 왼쪽 도시부터 제일 오른쪽 도시까지의 거리는 1이상 1,000,000,000 이하의 자연수
 * 리터당 가격은 1 이상 1,000,000,000 이하의 자연수
 * 
 * 출력
 * 제일 왼쪽 도시에서 제일 오른쪽 도시로 가는 최소 비용
 * 
 * 아이디어
 * A(출발점) -> C(경유지 : A-B 사이에서 최솟값) -> B(도착점)
 * 최솟값 = PriceA * Dist(C-A) + PriceC * Dist(B-C)
 * 1) A-C 구간 : A-C 구간 최솟값 찾고 위 과정 반복
 *         A-D-C 최솟값 = PriceA * Dist(D-A) + PriceD * Dist(C-D) 갱신
 * 2) C-B 구간 : 최소값 고정
 * 
 * 풀이 1) 배열로 최솟값 구하기
 * 풀이 2) 우선순위 큐로 최솟값 구하기
 * 
 */
public class BOJ_13305_주유소 {
	
	static class Oil implements Comparable<Oil>{
		int idx, price;

		public Oil(int idx, int price) {
			super();
			this.idx = idx;
			this.price = price;
		}

		@Override
		public int compareTo(Oil o) {
			if(this.price != o.price)
				return this.price > o.price ? 1 : -1;
			else
				return this.idx < o.idx ? 1 : -1;
		}
		
	}
    
    static int N; // 도시의 개수 N
    static int[] dist; // 누적 도로의 길이 목록
    static int[] price; // 리터 당 가격 목록
    static long sum; // 비용 저장
    static PriorityQueue<Oil> pq = new PriorityQueue<Oil>();

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dist = new int[N];
        price = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        dist[0] = 0; // 0에서 0으로 가는 거리 = 0
        for(int i=1; i<N; i++) { // 저장 내용 : 0에서 i로 가는 거리의 길이
            int temp = Integer.parseInt(st.nextToken());
            dist[i] = dist[i-1]+temp;
        }
        st = new StringTokenizer(br.readLine());
        int minIdx = 0;
        for(int i=0; i<N; i++) {
            price[i] = Integer.parseInt(st.nextToken());
            pq.add(new Oil(i, price[i])); // 전체 범위에서 최소 기름값을 가지는 위치 구하기
            //if(price[i]<price[minIdx]) 
            	//minIdx = i;
        }
        minIdx = pq.poll().idx;
        findMin(minIdx, N-1); // 최소 기름값 위치, 그 위치에서 갈 다음 위치
        System.out.println(sum);
        
    }
    
    private static void findMin(int idx, int lastIdx) {
    	sum += (long)(dist[lastIdx]-dist[idx])*price[idx]; // 경유지 -> 도착점 비용 계산
    	if(idx==0) return; // 경유지 = 시작점이면 종료
    	int minIdx = 0; // 현재 최소 기름값 위치 이전의 최소 기름값 위치 갱신
    	//for(int i=0; i<idx; i++) {
    		//if(price[i]<price[minIdx])
            	//minIdx = i;
    	//}
    	while(!pq.isEmpty()) {
    		minIdx = pq.poll().idx;
    		if(minIdx<idx) // 이전 최솟값 위치보다 더 앞에있는 최솟값이면 갱신 후 그 위치에서부터 다시 계산
    			break;
    	}
    	findMin(minIdx, idx);
    }
}