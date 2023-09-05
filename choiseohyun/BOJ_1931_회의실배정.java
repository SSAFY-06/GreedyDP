import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/** 회의실 배정
 * 한개의 회의실이 있는데 이를 사용하고자 하는 N개의 회의실에 대해 회의실 사용표를 만든다.
 * 시작시간과 끝나는시간이 주어지고 회의가 겹치지 않게 하며 회의실을 사용할 수 있는 최대 개수를 찾아라.
 * 
 * 풀이 : 수업때 대강 말해줬던거같은데...
 * 음 회의 리스트를 끝나는 시간이 빠른 순서대로 정렬한 뒤에 겹치지 않게 뽑으면 됐을걸?
 * 
 * 헷갈리는 부분이 정렬을 어떻게 하냐 였는데,
 * 1. 빨리끝나는 순으로 정렬 -> 그래야 뒤를 채우지.. 
 * 2. 끝나는 시간이 같다면 빨리시작하는 순으로 정렬 -> 늦게시작하는 순으로 채우려고 했었는데 틀렷습니다 뜸
 * 생각해보니 당연함 늦게시작하는순으로 채워봐야 끝나는시간순 정렬했으므로 이후 나오는 회의는 앞에 비는 시간을 채울 수 없음
 * 무엇보다 문제에서 주어진 바로끝나는 회의, 예를들어 (1,4)와 (4,4)가 있을경우
 * (1,4)->(4,4)로 진행해야함. (4,4)->(1,4)로 진행하면 1을 비교할때 기존 시작시간인 4시 이전에 시작한다는 조건에
 * 걸려버리므로 불가능하다라는 잘못된 결론이 나와버림.. 즉 둘다 오름차순 정렬해야함
 * 
 */
public class Main {
	static List<Meeting> list = new ArrayList<>();
	static int N;
	static Meeting current;
	static class Meeting implements Comparable<Meeting>{
		int start, end;
		public Meeting(int s, int e) {
			start = s;
			end = e;
		}
		
        //끝나는 시간 기준으로 오름차순정렬, 시간이 같다면 시작시간 기준으로 오름차순 정렬(즉 둘다 이른 시간으로)
		@Override
		public int compareTo(Meeting o) {
			if(this.end != o.end) return this.end - o.end;
			else return this.start-o.start; 
		}
		
		//파라미터로 주어진 미팅과 비교하여 본 미팅과 겹치는 시간이 있는지 확인하는 메소드
		public boolean checkDuplicate(Meeting m) {
			//시작시간이 겹치는경우
			if(m.start<this.start || m.start<this.end) return false;
			//끝시간이 겹치는경우
			if(m.end<this.start || m.end<this.end) return false;
			return true;
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Meeting(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
		}
		Collections.sort(list);

		System.out.println(solution());
	}

	private static int solution() {
		current = list.get(0); //current와 겹치지 않는 회의를 뽑는다.
		int cnt = 1;
		
		for(int i=1; i<N; i++) {
			if(current.checkDuplicate(list.get(i))) {
				//가능한 시간대로면 정답에 추가한 뒤 current를 갈아낀다
				cnt++;
				current = list.get(i);
			}
		}
		
		return cnt;
	}
}
