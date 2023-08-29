package 알고리즘스터디.Week1_Greedy_DP;

/**
 * N개의 도시 사이에 도로
 * 1km 당 1L
 * 각 도시에는 주유소 - 기름 충전
 * 주유소의 리터당 가격
 *
 * 생각나는 풀이
 * 주유비가 싸다면 최대한 많이 충전해놓으면 좋지 않을까?
 * 총 가야할 거리 : 6km
 *
 * 처음 출발:
 * 다음으로 가야할 키로수 만큼 기름을 넣어야 한다.
 * 근데 제일 싸다면? 아예 다 넣고 가는게 낫지 않을까?
 *
 * 그러니 총 거리를 알고 있어야 하고,
 * 다음에 올 주유소의 기름 가격을 알고 있어야 한다.
 * 기름 가격을 비교하고 가자
 *

 * 구현할 기능
 *
 * 주유비로 사용한 총 금액 money
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_13305_주유소 {
    static int N; // 도시 개수
    static long[] distances; // 각 도시간의 거리들 (N-1개)
    static long[] oilCosts; // 각 도시의 주유비 (N개)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        distances = new long[N-1]; // 도시 간의 거리들
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N-1; i++) { // 거리 배열 채우기
            distances[i] = Integer.parseInt(st.nextToken());
        }

        oilCosts = new long[N]; // 각 도시의 주유 비용
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) { // 주유 비용 채우기
            oilCosts[i] = Integer.parseInt(st.nextToken());
        } // oilCost의 마지막 인덱스를 버릴까 생각했지만, 어차피 안쓰는 인덱스임

        // 다음 주유소 가격이 현재보다 비싸다면 현재 금액으로 충전할 거리 누적
        // 근데 일단 2번 도시까진 가고 시작.
        long moneySum = distances[0] * oilCosts[0]; // 도시1 ~도시2 이동 비용
        long lowPrice = oilCosts[0]; // 1번 주유소의 가격을 최소 가격으로 정해놓는다.
        //2번 주유소부터 시작
        for (int i = 1; i < N-1; i++) {
             if(oilCosts[i] < lowPrice) { // 현재 주유비가 최소 주유비보다 싸다면
                 lowPrice = oilCosts[i]; // 최소 주유비를 현재 주유비로 설정
             } else { // 현재 주유비가 더 비싸다면
                 oilCosts[i] = lowPrice; // 현재 주유비를 최소 주유비로 바꿔준다.
             }

            moneySum += lowPrice * distances[i]; // 업데이트된 주유비에 이동거리를 곱해 이동 비용에 더해준다.
        }

        System.out.println(moneySum); // 출력

    }
}

/**
 * 어려웠던 점
 * 주유비 비교를 할 때 이전의 값과만 비교하고 이전 값이 다음 값보다 작다면 다음값에 이전값을 넣어줬는데
 * 이럼 안된다.
 * 반례: 2 2 3 4 5 6 (주유비)
 * 지금의 가격보다 더 낮은 가격이 나올 때까지 최소가격 변수를 만들어 쭉 적용시켜야 한다.
 *
 */