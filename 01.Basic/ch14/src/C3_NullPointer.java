class C3_NullPointer {
	public static void main(String[] args) {
		String str = null;
		System.out.println(str);	// null 출력
		int len = str.length();		// Exception!
		
		if(str != null) {
			len = str.length();
		}
	}
}