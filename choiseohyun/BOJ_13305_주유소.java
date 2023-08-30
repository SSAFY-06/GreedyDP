package self;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 주유소
 * 어떤나라에 N개 도시가 일직선 도로 위에 있다.
 * 제일 왼쪽에서 제일 오른쪽 도시로 자동차를 이용하여 이동한다. 인접 두 도시 사이 도로는 서로 길이가 다를수있다.
 * 무제한기름통을 넣구 출발하며 1km마다 1리터의 기름을 사용한다. 각 도시엔 단 하나의 주유소가 있으며 도시마다 가격이다르다
 *
 * 각 도시에 있는 주유소의 기름가격과 각 도시를 연결하는 도로의 길이를 입력받아 최소비용을 계산하여라.
 * 
 * 증가하는부분을 찾고.. 그 직전 즉 가장작은곳에서 그 뒤의 도로길이만큼 충전한다.
 * 
 * 일단 출발해야하니까 맨앞까지 가는 주유소를 충전하고..
 * 그니까 본인앞으로 증가하는부분수열을 찾고, 그 직전에서만 충전하는거다. 
 * 
 * 1  2 3  4 5 6 7 8
 * 10 7 11 4 2 3 5 1
 * 10 7 7 4 2 2 2 1
 * 
 * 일단 10원으로 충전해서 2로가고
 * 
 * 증가하는 부분수열 7,11을 찾아서 둘다 7로 이동
 * 7월으로 충전해서 3으로 가고
 * 7원으로 충전해서 4로가고
 * 
 * 증가하는 부분수열 없음.. 4원으로 5로가고
 * 
 * 증가하는 부분수열 567은 2로 셋팅, 즉 5에서 2원으로 충전해서 6,7,8로 간다
 * 
 */
public class 주유소 {
	static long len[], cost[];
	static int n;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//도시 갯수
		n = Integer.parseInt(br.readLine());
		//인접두도시연결하는 도로길이
		len = new long[n-1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n-1; i++) len[i] = Integer.parseInt(st.nextToken()); 
		//각도시별 주유비
		cost = new long[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++)  cost[i] = Integer.parseInt(st.nextToken());
		
		long total = 0; //총 비용 저장한다.
		
		//본인을 포함하여 증가하는 부분수열을 찾은 뒤, 해당 수열의 마지막 인덱스까지의 비용을 계산한다
		int currIndex = 0; //본인노드(탐색하려는 오름차순수열의 최솟값, 즉 첫번째값)
		int coverIndex; 
		while(true) {
			if(currIndex==n-1) break; //n-1이 도착점이므로 break;
			
			coverIndex = getSub(currIndex); //결과 : 증가하는 부분수열 가장 끝부분의 인덱스(즉 현재비용으로 커버해야하는 인덱스)
			for(int i=currIndex; i<=coverIndex; i++) total += len[i]*cost[currIndex];
			System.out.println(currIndex+"가 현재노드, "+coverIndex+"까지 탐색하는데 드는 비용"+total);
			currIndex = coverIndex+1; //이동이 끝났으니 현재 위치도 바꿔줌
		}

		System.out.println(total);
	}


	private static int getSub(int s) {
		int index = s;
		for(int i=s; i<n-1; i++) {
			if(cost[i]<=cost[i+1]) {
				index = i+1;
			}
			else break;
		}
		//증가하는 수열에 맨 마지막 원소가 포함되는거 예외처리
		if(index == n-1) index--;
		return index;
	}
}
