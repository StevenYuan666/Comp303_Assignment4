package Test;
import Assignment4.*;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
public class TestEpisode {

    /*
     * White box
     * since the constructor of episode is package private, we need to use the reflection to create episode object
     * To use the reflection, we have to know the code of the method. Then we check whether the requirements of clone
     * are fulfilled.
     */
    @Test
    public void testClone() throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        File file = new File("");
        TVShow t = new TVShow("Test", Language.SPANISH, "OtherStudio");
        Constructor<Episode> c = Episode.class.getDeclaredConstructor(File.class,
                TVShow.class, String.class, int.class);
        Episode testEpisode = null;
        if(c.getModifiers() == 0){
            c.setAccessible(true);
            testEpisode = (Episode) c.newInstance(file, t, "Test", 0);
        }
        Episode copy = testEpisode.clone();
        assertEquals(false, copy == testEpisode);
        assertEquals(true, copy.getClass().equals(testEpisode.getClass()));
        assertEquals(true, copy.equals(testEpisode));
    }

    /*
     * White box
     * since the constructor of episode is package private, we need to use the reflection to create episode object
     * To use the reflection, we have to know the code of the method. Additionally, we need to access the private filed
     * aPath to check if the input File has updated successfully.
     */
    @Test
    public void testUpdate() throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException, NoSuchFieldException {
        File file = new File("");
        TVShow t = new TVShow("Test", Language.SPANISH, "OtherStudio");
        Constructor<Episode> c = Episode.class.getDeclaredConstructor(File.class,
                TVShow.class, String.class, int.class);
        Episode testEpisode = null;
        if(c.getModifiers() == 0){
            c.setAccessible(true);
            testEpisode = (Episode) c.newInstance(file, t, "Test", 0);
        }
        Method update = testEpisode.getClass().getDeclaredMethod("update", File.class, String.class, int.class);
        if(update.getModifiers() == 0){
            update.setAccessible(true);
            File newfile = new File("New File to Update");
            String newtitle = "New Title";
            update.invoke(testEpisode, newfile, newtitle, 10);
            assertEquals(newtitle, testEpisode.getTitle());
            assertEquals(10, testEpisode.getEpisodeNumber());
            Field field = testEpisode.getClass().getDeclaredField("aPath");
            field.setAccessible(true);
            Object value = field.get(testEpisode);
            value = (File) value;
            assertEquals(value, newfile);
        }
    }

    /*
     * White box
     * since the constructor of episode is package private, we need to use the reflection to create episode object
     * To use the reflection, we have to know the code of the method
     */
    @Test
    public void testEquals() throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        File file1 = new File("");
        TVShow t = new TVShow("Test", Language.SPANISH, "OtherStudio");
        File file2 = new File("");
        Constructor<Episode> c = Episode.class.getDeclaredConstructor(File.class,
                TVShow.class, String.class, int.class);
        Episode testEpisode = null;
        Episode copyEpisode = null;
        Episode copyEpisode2 = null;
        if(c.getModifiers() == 0){
            c.setAccessible(true);
            testEpisode = (Episode) c.newInstance(file1, t, "Test", 0);
            copyEpisode = (Episode) c.newInstance(file2, t, "Test", 0);
            copyEpisode2 = (Episode) c.newInstance(file2, t, "Copy", 1);
        }
        assertEquals(true, testEpisode.equals(copyEpisode));
        assertEquals(false, testEpisode.equals(copyEpisode2));
    }
}
