package kimsuhyeok;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 근데 이 문제는 굳이 dp를 안쓰는게 좋을 것 같다.
 * 뭔가 정렬을 하고
 * 이분 탐색을 해서 원하는 값을 빠르게 찾는 것이 효율적일 듯 싶다
 */

public class BOJ_11053_LTS {

    static int N;
    static int[] arr;
    static int[] out;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        out = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int answer = search();
        System.out.println(answer);
    }

    private static int search(){
        out[0] = arr[0];
        int idx = 1;

        for(int i=1;i<N;i++){
            if(out[idx-1]<arr[i]){
                out[idx++] = arr[i];
            }
            else if(out[0]>arr[i]){
                out[0] = arr[i];
            }
            else{
                int temp = Arrays.binarySearch(out, 0, idx, arr[i]);
                out[temp<0 ? -(temp+1) : temp] = arr[i];
            }
        }

        return idx;
    }
}
