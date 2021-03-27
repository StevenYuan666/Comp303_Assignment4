package Test;
import Assignment4.*;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class TestTVShow {
    TVShow t;

    /*
     * White box
     * since the constructor of episode is package private, we need to use the reflection to create episode object
     * To use the reflection, we have to know the code of the method. Additionally, this test is to test if the
     * private field prototype has been initialized correctly, so we have to know the code.
     */
    @Test
    public void testPrototype() throws NoSuchFieldException, IllegalAccessException,
            NoSuchMethodException, InvocationTargetException, InstantiationException {
        t = new TVShow("Test", Language.ENGLISH, "WarnerBrothers");
        Field protoType = t.getClass().getDeclaredField("aPrototype");
        protoType.setAccessible(true);
        Object value = protoType.get(t);
        value = (Episode) value;
        Episode toCompare = null;
        Constructor<Episode> c = Episode.class.getDeclaredConstructor(File.class,
                TVShow.class, String.class, int.class);
        if(c.getModifiers() == 0){
            c.setAccessible(true);
            toCompare = (Episode) c.newInstance(new File("Prototype"), t, "Prototype", 0);
        }
        assertEquals(true, toCompare.equals(value));
    }

    /*
     * White box
     * since the constructor of episode is package private, we need to use the reflection to create episode object
     * To use the reflection, we have to know the code of the method. Additionally, we used the reflection to get
     * the private field aEpisodes to check if we add the episode to the end of the list correctly.
     */
    @Test
    public void testCreateAndAddEpisode() throws NoSuchFieldException, IllegalAccessException,
            NoSuchMethodException, InvocationTargetException, InstantiationException {
        t = new TVShow("Test", Language.ENGLISH, "WarnerBrothers");
        File file1 = new File("First Episode");
        t.createAndAddEpisode(file1, "1st episode");
        Field field = t.getClass().getDeclaredField("aEpisodes");
        field.setAccessible(true);
        Object value = field.get(t);
        value = (ArrayList) value;
        Episode toCompare = null;
        Constructor<Episode> c = Episode.class.getDeclaredConstructor(File.class,
                TVShow.class, String.class, int.class);
        if(c.getModifiers() == 0){
            c.setAccessible(true);
            toCompare = (Episode) c.newInstance(file1, t, "1st episode", 1);
        }
        assertEquals(((ArrayList<?>) value).get(0), toCompare);
    }
}
