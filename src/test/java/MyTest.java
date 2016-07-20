import org.junit.Test;
import sun.misc.Unsafe;
import sun.reflect.ReflectionFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

/**
 * Created by Rom8 on 20.07.2016.
 */
public class MyTest {

    private static Unsafe unsafe;

    static {
        Field theUnsafe = null;
        try {
            theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testObjectCreation() throws Exception {
        ClassWithExpensiveConstructor instance = (ClassWithExpensiveConstructor)
                unsafe.allocateInstance(ClassWithExpensiveConstructor.class);
        assertEquals(0, instance.getValue());
    }

    @Test
    public void testReflectionFactory() throws Exception {
        @SuppressWarnings("unchecked")
        Constructor<ClassWithExpensiveConstructor> silentConstructor =
                (Constructor<ClassWithExpensiveConstructor>) ReflectionFactory.getReflectionFactory()
                .newConstructorForSerialization(ClassWithExpensiveConstructor.class, Object.class.getConstructor());
        silentConstructor.setAccessible(true);
        assertEquals(0, silentConstructor.newInstance().getValue());
    }
}
