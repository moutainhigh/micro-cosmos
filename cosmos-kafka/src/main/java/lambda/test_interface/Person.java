package lambda.test_interface;

public interface Person {
		
	default String getName(String name){
		return name;
	}
}
