import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by Rom8 on 20.07.2016.
 */
public class ObtainUnsafe {

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);

    }

}
