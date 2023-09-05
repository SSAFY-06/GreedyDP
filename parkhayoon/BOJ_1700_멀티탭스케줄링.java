package algo.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제
 * 자기가 사용하고 있는 전기용품의 사용순서를 알아내었고,
 * 이를 기반으로 플러그를 빼는 횟수를 최소화하는 방법을 고안
 *
 * 입력
 * 첫 줄: 멀티탭 구멍의 개수 N(1≤N≤100), 전기 용품의 총 사용횟수 K(1≤K≤100)
 * 둘째 줄: 전기용품의 이름이 K 이하의 자연수로 사용 순서대로 주어짐
 *
 * 출력
 * 하나씩 플러그를 빼는 최소의 횟수
 *
 * 아이디어
 * 최소 횟수 플러그부터 뽑기?
 * 어디서 배운 거 같긴 한데... - 정처기?
 *
 * 1. 초기상태
 * 끼운 플러그 배열이 비어있으면 입력값 넣기
 * 단, 이미 꼽힌 상태면 지나가기
 *
 * 2. 반복
 * 만약 플러그 배열이 꽉 차있음, 새로운 용품이름이 들어옴 - 무조건 뽑아야 함
 * 무엇을 뽑을까?
 *
 * 예시
 * 2 7
 * 2 3 2 3 1 2 7 - 사용 빈도 수 배열에 저장, 적은 순서대로 뽑기
 */
public class BOJ_1700_멀티탭스케줄링 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] plugs = new int[N];
        int[] products = new int[K];
        int[] check = new int[K+1]; // 배열이 플러그에 있는지 or기기 사용 횟수 저장

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<K; i++) {
            products[i] = Integer.parseInt(st.nextToken());
            check[products[i]]++;
        }

        // 1. 플러그가 꽉 찰 때까지 확인
        int idx = 0;
        for(int p : products) {
            if(plugs[idx]==p) { // 만약 사용하려는 용품을 이미 사용중이면
                check[p]--; // 사용 횟수 1 감소
            }
            else if(plugs[idx]==0) { // 만약 플러그가 비어있고, 해당 용품을 사용하지 않는 경우
                plugs[idx]=p; // 비어있는 플러그에 용품 연결
                check[p]--; // 사용 횟수 1 감소
                idx++; // 다음 플러그에 넣을 용품 고르기
            }
            if(idx==N) break; // 플러그를 마지막 부분까지 채움
        }

        // 2. 플러그가 꽉 찬 후 뽑을 용품 고르기
        // 1) 남은 횟수가 적은 플러그부터 뽑기?
        // 2) 가장 나중에 오는 플러그부터 뽑기?
        for(int i=idx+1; i<K; i++) {
            for(int j=0; j<N; j++) { // 플러그 탐색
                if(plugs[j]==products[i]) { // 이미 플러그 사용중 - 카운드 줄이기
                    check[plugs[i]]--;
                    idx++;
                }
                else { // 신규 플러그 - 뽑을 플러그 고르기

                }
            }
        }

    }

}
