package lambda;

/**
 * Created by thomas on 2018/2/28 11:53.
 */
public class AddMainTest {

    public static void main(String[] args){
        int x = 10; int y = 20;
        add((x1,y1)->{
            System.out.println(11111);
        });
    }

    public static void add(AddFunction add){

    }
}
