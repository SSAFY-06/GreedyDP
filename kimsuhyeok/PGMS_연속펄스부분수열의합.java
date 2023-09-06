/**
 * 부분수열을 구하는 점화식
 * M(n)을 S(n-1)을 포함한 부분수열의 최대 합이라고 가정하자.
 * 배열의 예시는 [2,3,-6,1,3,-1,2,4]라고 하자
 * 부분수열의 길이가 1이라면 S(1)=2, S(2)=3, S(3)=-6과 같이 될 것이다.
 * 부분수열의 길이가 1 이상이라면 최댓값은 M(n-1)+S(n-1)이나 S(n-1) 중 최댓값이 될 것이다.
 * 그렇다면 M(n) = MAX(M(n-1)+S(n-1), S(n-1))이 된다.
 * 
 * @author SSAFY
 *
 */

class Solution {
    
    static long[] pulse, reversePulse;
    static long[] calPulse, calRPulse;
    static long[] dp, dp2;
    
    public long solution(int[] sequence) {
        long answer = 0;
        
        int len = sequence.length;
        pulse = new long[len];
        reversePulse = new long[len];
        dp = new long[len+1];
        dp2 = new long[len+1];
        calPulse = new long[len];
        calRPulse = new long[len];
        
        initPulse(len, sequence);
        calculatePulse(len, sequence);
        calculateDP(len);
    
        return getAnswer(len);;
    }
    
    static void initPulse(int len, int[] sequence){
        for(int i=0;i<len;i++) {
			if(i%2==0) {
				pulse[i] = 1;
				reversePulse[i] = -1;
			}
			else {
				pulse[i] = -1;
				reversePulse[i] = 1;
			}
		}
    }
    
    static void calculatePulse(int len, int[] sequence){
        for(int i=0;i<len;i++) {
			calPulse[i] = sequence[i]*pulse[i];
			calRPulse[i] = sequence[i]*reversePulse[i];
		}
    }
    
    static void calculateDP(int len){
        for(int i=1;i<=len;i++) {
			dp[i] = Math.max(dp[i-1]+calPulse[i-1], calPulse[i-1]);
			dp2[i] = Math.max(dp2[i-1]+calRPulse[i-1], calRPulse[i-1]);
		}
    }
    
    static long getAnswer(int len){
        long answer = 0;
        for(int i=1;i<=len;i++){
            answer = Math.max(answer, Math.max(dp[i], dp2[i]));
        }
        
        return answer;
    }
    
}
