package lambda.test_interface;


public class MainTest {

	public static void main(String[] args) {
		Person p = new Student();
		String name = p.getName("thomas");

		System.out.println(name);
	}

}
