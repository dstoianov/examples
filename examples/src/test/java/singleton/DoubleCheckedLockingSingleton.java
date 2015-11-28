package singleton;

/**
 * Created by Funker on 23.08.2015.
 */
public class DoubleCheckedLockingSingleton {

    private static DoubleCheckedLockingSingleton instance = null;

    protected DoubleCheckedLockingSingleton() {
    }

    // Lazy Initialization (If required then only)
    public static DoubleCheckedLockingSingleton getInstance() {
        if (instance == null) {
            // Thread Safe. Might be costly operation in some case
            synchronized (DoubleCheckedLockingSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return instance;
    }
}
