import javafx.application.Platform;

/**
 * Created by Rom8 on 17.07.2016.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(Main.class.getClassLoader());        //Application class loader (app Main class)
        System.out.println(Platform.class.getClassLoader());    //Extension class loader (JavaFX here)
        System.out.println(Thread.class.getClassLoader());      //Bootstrap class loader (java Thread class)
    }

}
