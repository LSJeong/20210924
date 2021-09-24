package Person;

import java.util.Scanner;

public class ScanUtil {
	static Scanner scn = new Scanner(System.in);
	
	public static int readInt(String msg) {
		System.out.println(msg);
		int result = 0;
		while (true) {
			String val = scn.nextLine();
			try {
				result = Integer.parseInt(val);
				break;
			} catch (Exception e) {
				System.out.println("잘못된 값을 입력했습니다. 다시 입력하세요.");
			}
		}
		return result;
	}
	
	
	public static String readStr(String msg) {
		System.out.println(msg);
		return scn.nextLine();
	}
	
}