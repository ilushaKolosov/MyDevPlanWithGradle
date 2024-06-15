package Task3_ClassLoader;

public class StartClass {
    public static void main(String[] args) {
        String classPath = "classes/Task3_ClassLoader";
        CustomClassLoader customClassLoader = new CustomClassLoader(classPath);

        try {
            Class<?> helloWorldClass = customClassLoader.loadClass("example.HeyGuys");
            Object helloWorldInstance = helloWorldClass.getDeclaredConstructor().newInstance();

            helloWorldClass.getMethod("sayHello").invoke(helloWorldInstance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
