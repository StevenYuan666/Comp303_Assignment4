package Test;
import Assignment4.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class TestCompositeOrFilterStrategy {
    CompositeOrFilterStrategy f;

    @BeforeEach
    void setup(){
        f = new CompositeOrFilterStrategy();
        LanguageFilterStrategy l = new LanguageFilterStrategy(Language.ENGLISH);
        StudioFilterStrategy s = new StudioFilterStrategy("WarnerBrothers");
        f.add(l);
        f.add(s);
    }

    /*
     * White box
     * Since we use the reflection to get a private field, filters, to see if we successfully add the filter
     * to the composition
     */
    @Test
    void testAdd() throws IllegalAccessException, NoSuchFieldException {
        FirstEpisodeFilterStrategy ff = new FirstEpisodeFilterStrategy();
        f.add(ff);
        Field field = f.getClass().getDeclaredField("filters");
        field.setAccessible(true);
        Object value = field.get(f);
        value = (ArrayList) value;
        assertEquals(((ArrayList<?>) value).get(2), ff);
    }

    //Black box, since we don't need to know the code, we only test the function
    @Test
    void testFilterMovie1(){
        File file = new File("");
        Movie m = new Movie(file, "BAT Man", Language.ENGLISH, "WarnerBrothers");
        assertEquals(true, f.filter(m));
    }

    //Black box, since we don't need to know the code, we only test the function
    @Test
    void testFilterMovie2(){
        File file = new File("");
        Movie m = new Movie(file, "BAT Man", Language.ENGLISH, "OtherStudio");
        assertEquals(true, f.filter(m));
    }

    //Black box, since we don't need to know the code, we only test the function
    @Test
    void testFilterMovie3(){
        File file = new File("");
        Movie m = new Movie(file, "BAT Man", Language.FRENCH, "WarnerBrothers");
        assertEquals(true, f.filter(m));
    }

    //Black box, since we don't need to know the code, we only test the function
    @Test
    void testFilterMovie4(){
        File file = new File("");
        Movie m = new Movie(file, "BAT Man", Language.LATIN, "OtherStudio");
        assertEquals(false, f.filter(m));
    }

    //Black box, since we don't need to know the code, we only test the function
    @Test
    void testFilterTVShow1(){
        TVShow t = new TVShow("Test", Language.ENGLISH, "WarnerBrothers");
        assertEquals(true, f.filter(t));
    }

    //Black box, since we don't need to know the code, we only test the function
    @Test
    void testFilterTVShow2(){
        TVShow t = new TVShow("Test", Language.ENGLISH, "OtherStudio");
        assertEquals(true, f.filter(t));
    }

    //Black box, since we don't need to know the code, we only test the function
    @Test
    void testFilterTVShow3(){
        TVShow t = new TVShow("Test", Language.ANCIENT_GREEK, "WarnerBrothers");
        assertEquals(true, f.filter(t));
    }

    //Black box, since we don't need to know the code, we only test the function
    @Test
    void testFilterTVShow4(){
        TVShow t = new TVShow("Test", Language.KLINGON, "OtherStudio");
        assertEquals(false, f.filter(t));
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
            e = (Episode) c.newInstance(file, t, "Test", 0);
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
            e = (Episode) c.newInstance(file, t, "Test", 0);
        }
        assertEquals(true, f.filter(e));
    }

    /*
     * White box
     * since the constructor of episode is package private, we need to use the reflection to create episode object
     * To use the reflection, we have to know the code of the method
     */
    @Test
    void testFilterEpisode3() throws NoSuchMethodException, InstantiationException,
            InvocationTargetException, IllegalAccessException {
        File file = new File("");
        TVShow t = new TVShow("Test", Language.SPANISH, "WarnerBrothers");
        Constructor<Episode> c = Episode.class.getDeclaredConstructor(File.class,
                TVShow.class, String.class, int.class);
        Episode e = null;
        if(c.getModifiers() == 0){
            c.setAccessible(true);
            e = (Episode) c.newInstance(file, t, "Test", 0);
        }
        assertEquals(true, f.filter(e));
    }

    /*
     * White box
     * since the constructor of episode is package private, we need to use the reflection to create episode object
     * To use the reflection, we have to know the code of the method
     */
    @Test
    void testFilterEpisode4() throws NoSuchMethodException, InstantiationException,
            InvocationTargetException, IllegalAccessException {
        File file = new File("");
        TVShow t = new TVShow("Test", Language.SPANISH, "OtherStudio");
        Constructor<Episode> c = Episode.class.getDeclaredConstructor(File.class,
                TVShow.class, String.class, int.class);
        Episode e = null;
        if(c.getModifiers() == 0){
            c.setAccessible(true);
            e = (Episode) c.newInstance(file, t, "Test", 0);
        }
        assertEquals(false, f.filter(e));
    }
}
