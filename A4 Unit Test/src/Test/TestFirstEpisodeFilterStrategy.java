package Test;
import Assignment4.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import static org.junit.jupiter.api.Assertions.*;
public class TestFirstEpisodeFilterStrategy {
    FirstEpisodeFilterStrategy f;

    @BeforeEach
    void setUp(){
        f = new FirstEpisodeFilterStrategy();
    }

    //Black box, since we don't need to know the code, we only test the function
    @Test
    void testFilterMovie(){
        File file = new File("");
        Movie m = new Movie(file, "BAT Man", Language.ENGLISH, "WarnerBrothers");
        assertEquals(false, f.filter(m));
    }

    //Black box, since we don't need to know the code, we only test the function
    @Test
    void testFilterTVShow(){
        TVShow t = new TVShow("Test", Language.ENGLISH, "OtherStudio");
        assertEquals(false, f.filter(t));
    }

    //Black box, since we don't need to know the code, we only test the function
    @Test
    void testFilterTVShow2(){
        TVShow t = new TVShow("Test", Language.ENGLISH, "OtherStudio");
        t.createAndAddEpisode(new File(""), "ep1");
        assertEquals(true, f.filter(t));
    }

    /*
     * White box
     * since the constructor of episode is package private, we need to use the reflection to create episode object
     * To use the reflection, we have to know the code of the method
     */
    @Test
    void testFilterEpisode1() throws NoSuchMethodException, InstantiationException,
            InvocationTargetException, IllegalAccessException {
        File file = new File("");
        TVShow t = new TVShow("Test", Language.ENGLISH, "WarnerBrothers");
        Constructor<Episode> c = Episode.class.getDeclaredConstructor(File.class,
                TVShow.class, String.class, int.class);
        Episode e = null;
        if(c.getModifiers() == 0){
            c.setAccessible(true);
            e = (Episode) c.newInstance(file, t, "Test", 1);
        }
        assertEquals(true, f.filter(e));
    }

    /*
     * White box
     * since the constructor of episode is package private, we need to use the reflection to create episode object
     * To use the reflection, we have to know the code of the method
     */
    @Test
    void testFilterEpisode2() throws NoSuchMethodException, InstantiationException,
            InvocationTargetException, IllegalAccessException {
        File file = new File("");
        TVShow t = new TVShow("Test", Language.ENGLISH, "OtherStudio");
        Constructor<Episode> c = Episode.class.getDeclaredConstructor(File.class,
                TVShow.class, String.class, int.class);
        Episode e = null;
        if(c.getModifiers() == 0){
            c.setAccessible(true);
            e = (Episode) c.newInstance(file, t, "Test", 3);
        }
        assertEquals(false, f.filter(e));
    }
}
