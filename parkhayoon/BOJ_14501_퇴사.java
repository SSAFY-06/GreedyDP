import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
문제
퇴사일: 오늘부터 N+1일
남은 N일 동안 최대한 많은 상담
하루에 하나씩 서로 다른 사람의 상담
각각의 상담: 완료까지 걸리는 시간 Ti, 상담했을 때 받을 수 있는 금액 Pi
하루에 여러 개의 상담을 할 순 없음
이때 얻을 수 있는 최대 수익을 구하시오

입력
첫째 줄: 날짜 N (1 ≤ N ≤ 15)
N개 줄: 1일부터 N일까지 Ti, Pi (1 ≤ Ti ≤ 5, 1 ≤ Pi ≤ 1,000)

출력
얻을 수 있는 최대 이익

아이디어
dp[]
1) 1일까지 얻을 수 있는 최대이익 → 2일까지 얻을 수 있는 최대이익 → …
2) 종료일 순서대로 정렬
시작일 = i
종료일 = i + Ti - 1
cost = Pi
while(종료일 < N)
if(dp[종료일]==0)
dp[종료일] =

0 1 2 3 4 5 6
2 5 2 3 5 8 7
10 20 10 20 15 40 200

정렬
0 2 10 V
2 2 10
3 3 20 V
1 5 20
4 5 15 V
6 7 200
5 8 40

0 1 2 3 4 5 6 7 8 9
4 4 4 4 4 5 7 9 11 13
50 40 30 20 10 10 20 30 40 50

정렬
0 4 50 V
1 4 40
2 4 30
3 4 20
4 4 10
5 5 10 V
6 7 20 - 이론에 오류가 발생! -> sum이 아닌 dp[]를 사용해보자
7 9 30 V
8 11 40
9 13 50
 */
public class BOJ_14501_퇴사 {

    static class Work implements Comparable<Work>{
        int start, end, price; // 상담 시작일, 종료일, 금액

        public Work(int start, int end, int price) {
            this.start = start;
            this.end = end;
            this.price = price;
        }


        @Override
        public int compareTo(Work o) {
            if(this.end==o.end)
                return this.start>o.start?1:-1;
            return this.end>o.end?1:-1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N];

        PriorityQueue<Work> pq = new PriorityQueue<>();
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            pq.add(new Work(i, i+time-1, price)); // 시작일, 종료일(시작일~시작일+기간), 비용
        }

        while(!pq.isEmpty()) { // 일단은 모든 상담을 확인
            Work current = pq.poll(); // 현재 상담 확인
            if(current.end>=N) break; // 종료일 기준 정렬 - 초과하면 이후 상담들도 불가능 -> 반복문 종료
            dp[current.end] = Math.max(dp[current.end], current.price); // 현재 상담만 선택한 경우 확인
            for(int i=0; i<current.start; i++) {
                dp[current.end] = Math.max(dp[current.end], dp[i]+current.price); // 겹치지 않는 상담들을 한 경우 확인
            }
        }
        int maxSum = 0;
        for(int d : dp) // 기간 별 최적 답 중 최댓값 출력
            maxSum = Math.max(maxSum, d);
        System.out.println(maxSum);
    }
}