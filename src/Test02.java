import sun.security.jca.GetInstance;

/**
 * Author: 王俊超
 * Date: 2015-04-21
 * Time: 13:58
 * Declaration: All Rights Reserved !!!
 */
public class Test02 {

    /**
     * 单例模式，饿汉（类初始化时进行实例化）式，线程安全
     * <p>
     * 优点是：写起来比较简单，而且不存在多线程同步问题，避免了synchronized所造成的性能问题；
     * <p>
     * 缺点是：当类SingletonTest被加载的时候，会初始化static的instance，
     * 静态变量被创建并分配内存空间，从这以后，这个static的instance对象便
     * 一直占着这段内存（即便你还没有用到这个实例），当类被卸载时，静态变量
     * 被摧毁，并释放所占有的内存，因此在某些特定条件下会耗费内存。
     */
    public static class Singleton {
        private final static Singleton INSTANCE = new Singleton();

        private Singleton() {

        }

        public static Singleton getInstance() {
            return INSTANCE;
        }
    }

    /**
     * 单例模式，懒汉（在第一次调用是实例化）式，线程不安全
     * <p>
     * 优点是：写起来比较简单，当类SingletonTest被加载的时候，
     * 静态变量static的instance未被创建并分配内存空间，
     * 当getInstance方法第一次被调用时，初始化instance变量，
     * 并分配内存，因此在某些特定条件下会节约了内存；
     * <p>
     * 缺点是：并发环境下很可能出现多个SingletonTest实例。
     */
    public static class Singleton2 {
        private static Singleton2 instance = null;

        private Singleton2() {

        }

        public static Singleton2 getInstance() {
            if (instance == null) {
                instance = new Singleton2();
            }

            return instance;
        }
    }


    /**
     * 单例模式，懒汉式，线程安全，多线程环境下效率不高
     * <p>
     * 优点是：使用synchronized关键字避免多线程访问时，出现多个SingletonTest实例。
     * <p>
     * 缺点是：同步方法频繁调用时，效率略低。
     */
    public static class Singleton3 {
        private static Singleton3 instance = null;

        private Singleton3() {

        }

        public static synchronized Singleton3 getInstance() {
            if (instance == null) {
                instance = new Singleton3();
            }

            return instance;
        }
    }

    /**
     * 单例模式，饿汉式，变种，线程安全
     */
    public static class Singleton4 {
        private static Singleton4 instance = null;

        static {
            instance = new Singleton4();
        }

        private Singleton4() {

        }

        public static Singleton4 getInstance() {
            return instance;
        }
    }

    /**
     * 单例模式，懒汉式，使用静态内部类，线程安全【推荐】
     * <p>
     * 当getInstance方法第一次被调用的时候，它第一次读取
     * SingletonHolder.INSTANCE，内部类SingletonHolder
     * 得到初始化；而这个类在装载并被初始化的时候，会初始化它
     * 的静态域，从而创建Singleton的实例，由于是静态的域，
     * 因此只会在虚拟机装载类的时候初始化一次，并由虚拟机来
     * 保证它的线程安全性。
     * <p>
     * 优点：getInstance方法并没有被同步，并且只是执行一个域
     * 的访问，因此延迟初始化并没有增加任何访问成本
     */
    public static class Singleton5 {
        private final static class SingletonHolder {
            private static final Singleton5 INSTANCE = new Singleton5();
        }

        private Singleton5() {

        }

        public static Singleton5 getInstance() {
            return SingletonHolder.INSTANCE;
        }
    }

    /**
     * 使用枚举方式，线程安全【推荐】
     * https://zybuluo.com/natsumi/note/692892
     * <p>
     * 传统的两私有一公开（私有构造方法、私有静态实例(懒实例化/直接实例化)、
     * 公开的静态获取方法）涉及线程安全问题（即使有多重检查锁也可以通过反射破
     * 坏单例），目前最为安全的实现单例的方法是通过内部静态enum的方法来实现，
     * 因为JVM会保证enum不能被反射并且构造器方法只执行一次。
     * <p>
     * 1.从Java1.5开始支持;
     * 2.无偿提供序列化机制;
     * 3.绝对防止多次实例化，即使在面对复杂的序列化或者反射攻击的时候;
     */
    public enum Singleton6 {
        INSTANCE;//理解为public static final Singleton6 INSTANCE;

        Singleton6() {//这个方法默认就是private的
            //do something
        }
    }

    /**
     * Singleton3改进版，使用双重校验锁，避免频繁加锁，线程安全【推荐】
     */
    public static class Singleton7 {
        private volatile static Singleton7 instance = null;

        private Singleton7() {

        }

        public static Singleton7 getInstance() {
            if (instance == null) {
                synchronized (Singleton7.class) {
                    if (instance == null) {
                        instance = new Singleton7();
                    }
                }
            }

            return instance;
        }
    }

    public static void main(String[] args) {
        System.out.println(Singleton.getInstance() == Singleton.getInstance());
        System.out.println(Singleton2.getInstance() == Singleton2.getInstance());
        System.out.println(Singleton3.getInstance() == Singleton3.getInstance());
        System.out.println(Singleton4.getInstance() == Singleton4.getInstance());
        System.out.println(Singleton5.getInstance() == Singleton5.getInstance());
        System.out.println(Singleton6.INSTANCE == Singleton6.INSTANCE);
        System.out.println(Singleton7.getInstance() == Singleton7.getInstance());
    }
}
