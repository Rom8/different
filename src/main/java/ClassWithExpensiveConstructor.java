/**
 * Created by Rom8 on 20.07.2016.
 */
public class ClassWithExpensiveConstructor {

    private final int value;

    private ClassWithExpensiveConstructor() {
        value = doExpensiveLookup();
    }

    private int doExpensiveLookup() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public int getValue() {
        return value;
    }

}
