package happy.coding.test;

import java.lang.reflect.Method;

public class AnimalProxy implements Animal{
    Invoke invoke;
    static Method speakMethod;
    static Method sleepMethod;
    static {

        try {
            speakMethod=Animal.class.getMethod("speak", String.class, String.class);
            sleepMethod=Animal.class.getMethod("sleep");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
    public AnimalProxy(Invoke invoke){
        this.invoke=invoke;
    }
    @Override
    public void speak(String language,String name) {
        //调用传入的invoke，我要给提供正确的传参
        invoke.run(speakMethod,new Object[]{language,name});
    }

    @Override
    public void sleep() {
        invoke.run(sleepMethod,new Object[0]);
    }
}
