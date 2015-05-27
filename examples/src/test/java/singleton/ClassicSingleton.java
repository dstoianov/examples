package singleton;

/**
 * Created by dstoianov on 2015-05-27.
 */
public class ClassicSingleton {

    private static ClassicSingleton instance = null;

    protected ClassicSingleton() {
        // Exists only to defeat instantiation.
    }

    public static synchronized ClassicSingleton getInstance() {
        if (instance == null) {
            instance = new ClassicSingleton();
        }
        return instance;
    }

}
