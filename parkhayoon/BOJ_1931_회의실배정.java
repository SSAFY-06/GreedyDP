package algo.week01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 한개의 회의실에서 N개의 회의에 대하여 사용표 만들기
 * 각 회의 I에 대해 시작시간, 종료시간이 주어짐
 * 각 회의가 겹치지 않게 하면서 회의 사용 최대 개수
 * 회의는 중단 불가눙, 한 회의 종료 동시 다음 회의 시작 가능, 시작시간과 종료시간 동일할 수 있음
 * 
 * 입력
 * 첫째 줄: 회의의 수 N(1 ≤ N ≤ 100,000)
 * 이후 N개 줄: 각 회의의 정보(시작시간, 종료시간)(0 ≤ 시간 ≤ 2^31 - 1)
 * 
 * 출력
 * 각 회의가 겹치지 않게 하면서 회의 사용 최대 개수
 * 
 * 아이디어
 * 최대한 많은 회의를 골라야 함
 * 1) 무조건 제일 짧은 회의? - 가장 짧은 회의 선정이 최적 조건을 만족하진 않음 ex. 1-5, 6-10, 4-7
 * 2) 제일 빨리 끝나는 회의? - 시간 순서대로 지나감 -> 빨리 끝날수록 더 많은 회의 넣을 수 있음
 * 
 * 오류 발생 후 검색
 * 반례)
 * 2
 * 4 4
 * 3 4
 * 2 4
 * 정답: 2, 출력: 1
 * => end뿐만 아니라 start도 정렬!
 */
public class BOJ_1931_회의실배정 {

	static class Time implements Comparable<Time>{ // 회의실 시작, 종료 시간 저장
		int start, end;

		public Time(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Time o) { // end, start 순으로 정렬
			if(this.end != o.end)
				return this.end>o.end ? 1 : -1;
			else
				return this.start>o.start ? 1 : -1;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Time> pq = new PriorityQueue<>(); // 최소 종료 시간 찾기 - PriorityQueue

		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			pq.add(new Time(start, end));
		}

		int sum = 1; // 회의 1개를 선택한 채 시작하므로 1부터 시작
		int oldEnd = pq.poll().end; // 초기값: 제일 먼저 종료되는 회의
		while(!pq.isEmpty()) {
			Time current = pq.poll();
			if(current.start<oldEnd) continue; // 이전 회의 종료 시간보다 현재 선택한 회의 시작시간이 더 빠른 경우 - 시간이 겹치므로 확인하지 않음
			oldEnd = current.end; // 시간이 겹치지 않는 경우 종료 갱신
			sum++;
		}

		System.out.println(sum);
		
	}

}
