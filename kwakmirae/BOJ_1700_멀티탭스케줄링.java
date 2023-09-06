package kwakmirae;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1700_멀티탭스케줄링 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] appliances = new int[K];
		for(int i=0; i<K; i++) {
			appliances[i] = Integer.parseInt(st.nextToken());
		}
		
		if(N >= K) {
			System.out.println(0);
			return;
		}
		
		int cnt = 0;
		int put = 0;
		List<Integer> list;
		boolean[] use = new boolean[K+1];
		for(int i=0; i<K; i++) {
			if(use[appliances[i]]) continue;
			// 꽂을 자리가 남음
			if(put < N) {
				use[appliances[i]] = true;
				put++;
				continue;
			}
			
			//꽂을 자리가 없을 때
			// 1. 현재 꽂혀있는 플러그 중에 나중에 사용되는 장치 탐색 
			list = new ArrayList<Integer>();
			for(int j=i; j<K; j++) {
				if(use[appliances[j]] && !list.contains(appliances[j])) list.add(appliances[j]);
	
			}
			
			// 2. 현재 꽂혀있는 플러그들이 나중에 모두 사용되는 경우
			if(list.size() == N) {
				int remove = list.get(list.size()-1);
				use[remove] = false;
			}
			// 3. 현재 꽂혀있는 플러그들 중 나중에 사용되지 않는 플러그가 존재하는 경우
			else {
				for(int k=1; k<=K; k++) {
					if(use[appliances[k]] && !list.contains(k)) {
						use[k] = false;
						break;
					}
				}
			}
			
			// 4. 플러그를 뽑았으니 새로운 플러그 꽂기
			use[appliances[i]] = true;
			cnt++;
		}
		System.out.println(cnt);
	}

}
