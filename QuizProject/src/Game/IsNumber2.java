package Game;

public class IsNumber2 {
	public static void main(String[] args) {
		String value = "12o34";
		String ch = "";
		boolean isNumber = true;
		
		for(int i=0; i<value.length(); i++) {
			ch = value.substring(i, i+1);
			//System.out.println(ch);
			
			try {
				int num = Integer.parseInt(ch);
			} catch (Exception e) {
				isNumber = false;
				break;
			}
		}
		
		if(isNumber) {
			System.out.println(value + "는 숫자입니다.");
		} else {
			System.out.println(value + "는 숫자가 아닙니다.");
		}
	}
}