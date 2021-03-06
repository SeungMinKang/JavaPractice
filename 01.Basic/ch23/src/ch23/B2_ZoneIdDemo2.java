package ch23;

import java.time.ZoneId;

class B2_ZoneIdDemo2 {
	public static void main(String[] args) {
		ZoneId.getAvailableZoneIds()
			  .stream()
			  .filter(s -> s.startsWith("Asia"))
			  .sorted()
			  .forEach(s -> System.out.println(s));
	}
}