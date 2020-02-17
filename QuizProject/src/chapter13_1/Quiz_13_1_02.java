package chapter13_1;

public class Quiz_13_1_02 {

	public static void main(String[] args) {
		char[] chr = {'G','o','o','d',' ','t','i','m','e'};
		String str = "";
		
		for(int i=0; i < chr.length; i++) {
			str = str + chr[i];
		}
		System.out.println(str);
		
//		for(int i=0; i < chr.length; i++) {
//			System.out.print(chr[i]);
//		}
	}
}
