package kimsuhyeok;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * 각 회의에 대해 시작시간과 끝나는 시간이 주어진다.
 * 각 회의가 겹치지 않게 하면서 회의실을 사용할 수 있는 회의의 최대 개수를 찾아보자
 */

/**
 * 일단
 * 1. 종료 시간을 비교해보자 -> 이전 종료 시간에 대해 겹치는 것들을 제외할 수 있을 것이다.
 * 2. 그러기 위해서는 종료시간을 sort하는 작업이 필요하다.
 * 3. 종료 시간별로 정렬하고 가장 빨리 끝나는 것들을 선택하고 그 과정에서 겹치는 것은 제외한다.
 */
class Meeting implements Comparable<Meeting> {
    int startTime;
    int endTime;
    public Meeting(int startTime, int endTime){
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public int compareTo(Meeting o) {
        if(this.endTime == o.endTime){
            return this.startTime - o.startTime;
        }
        return this.endTime - o.endTime;
    }
}
public class BOJ_1931_회의실배정 {

    static int N;
    static Meeting[] meetings;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        meetings = new Meeting[N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            meetings[i] = new Meeting(start,end);
        }

//        System.out.println("정렬 전");
//        print();
        //endTime으로 정렬
        Arrays.sort(meetings);
//        System.out.println("정렬 후");
//        print();

        int answer = getMeetingSpaceCnt();
        System.out.println(answer);

    }

    //회의실 개수 세주는 함수
    public static int getMeetingSpaceCnt(){
        int cnt = 0;
        int prev_end_time = 0;

        for(int i=0;i<N;i++){
            //만약 직전 회의 종료 시간이 다음 회의 시작시간보다 작거나 같으면
            if(prev_end_time <= meetings[i].startTime){
                //해당 회의는 들어갈 수 있으므로
                //직전 회의 종료 시간을 다음회의 종료시간으로 갱신
                prev_end_time = meetings[i].endTime;
                //가능한 회의 횟수 증가
                cnt++;
            }
        }
        return cnt;
    }

    //meetings 배열 출력해주는 함수
    public static void print(){
        for(int i=0;i<N;i++){
            System.out.print(meetings[i].startTime+" "+meetings[i].endTime);
            System.out.println();
        }

    }

}
