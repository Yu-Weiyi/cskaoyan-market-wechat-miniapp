package happy.coding.test;

public class Cat implements Animal{
    public void speak(String language,String name){

        System.out.println(name+"喵喵喵,说"+language);
    }
    public void sleep(){
        System.out.println("睡睡睡");
    }
}
