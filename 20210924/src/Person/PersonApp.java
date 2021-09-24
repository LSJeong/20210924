package Person;

public class PersonApp {
	public static void main(String[] args) {
		System.out.println("*************사람 정보************");
		PersonExe exe = PersonExe.getInstance();
		exe.execute();
	}
}
