package algo.week2;

/*
 * 연속 부분 수열 X 펄스 수열([1,-1,1,...] or [-1,1,-1,...])의 최대합
 * 1 ≤ sequence의 길이 ≤ 500,000, -100,000 ≤ sequence의 원소 ≤ 100,000
 * 
 * 아이디어
 * 연속 부분 수열의 합 응용
 * 1) {1, -1, 1, ... }을 곱한 상태에서 최대 구하기
 * 2) {-1, 1, -1, ... }을 곱한 상태에서 최대 구하기
 */
public class PG_161988_연속펄스부분수열의합 {

	public static void main(String[] args) {
		
		int[] sequence = {2, 3, -6, 1, 3, -1, 2, 4};
		int L = sequence.length;
		
		for(int i=1; i<L; i+=2) // 1) {1, -1, 1, ... } 곱해주기
			sequence[i]*=-1;
		long[] dp1 = new long[L]; //최대합: 100,000 * 500,000 > int 최대 범위 -> long 사용
		dp1[0] = sequence[0];
		long max1 = 0;
		for(int i=1; i<L; i++)
			dp1[i] = Math.max(0, dp1[i-1]) + sequence[i];
		for(int i=0; i<L; i++)
			max1 = Math.max(max1, dp1[i]);
//		System.out.println(Arrays.toString(dp1));
		
		for(int i=0; i<L; i++) // 2) {1, -1, 1, ... }*(-1) = {-1, 1, -1, ... }
			sequence[i]*=-1;
		long[] dp2 = new long[L];
		dp2[0] = sequence[0];
		long max2 = 0;
		for(int i=1; i<L; i++)
			dp2[i] = Math.max(0, dp2[i-1]) + sequence[i];
		for(int i=0; i<L; i++)
			max2 = Math.max(max2, dp2[i]);
//		System.out.println(Arrays.toString(dp2));
		
		long answer = Math.max(max1, max2);
		System.out.println(answer);
	}

}
