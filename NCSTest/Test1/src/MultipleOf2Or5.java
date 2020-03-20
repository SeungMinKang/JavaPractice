
public class MultipleOf2Or5 {
	public static void main(String[] args) {
		
		// while문 버전
		int i = 1;
		int sum1 = 0;
		while(i <= 1000) {
			if(i%2 == 0 && i%5 == 0) {
			} else if (i%2 == 0) {
				sum1 = sum1 + i;
			} else if (i%5 == 0) {
				sum1 = sum1 + i;
			}
			i++;
		}
		System.out.println(sum1);
		
		// for문 버전
		int sum2 = 0;
		for(int j = 1; j <= 1000; j++) {
			if(j%2 == 0 && j%5 == 0) {
			} else if (j%2 == 0) {
				sum2 = sum2 + j;
			} else if (j%5 == 0) {
				sum2 = sum2 + j;
			}
		}
		System.out.println(sum2);
	}
}