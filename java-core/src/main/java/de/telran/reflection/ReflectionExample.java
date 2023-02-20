package de.telran.reflection;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionExample {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
//        Class<Cat> catClass = Cat.class;
//        Class catClass1 = Class.forName("ru.geekbrains.java3.lessons.lesson3_io.Cat");
        Cat cat = new Cat("Barsik", "white", 2);
        Class catClass =  cat.getClass();

//        int modifiers = catClass.getModifiers();
//        System.out.println(modifiers);
//        System.out.println(Modifier.isAbstract(modifiers));
//        System.out.println(Modifier.isFinal(modifiers));
//        System.out.println(Modifier.isNative(modifiers));
//        System.out.println(Modifier.isPublic(modifiers));
//        System.out.println(Modifier.isStatic(modifiers));
//        Field[] fields = catClass.getFields();
//        Field[] fields = catClass.getDeclaredFields();
//
//        for (Field field : fields) {
//            System.out.println(field);
//        }
//        Field field = catClass.getDeclaredField("name");
//        Field field1 = catClass.getDeclaredField("age");
//        System.out.println(field.get(null)); //Для статического
//        System.out.println(field.get(cat));
//        System.out.println(field.getShort(cat));
//        field.setAccessible(true);
//        field1.setAccessible(true);
//        field.set(cat, "Murzik");
//        field1.set(cat, 99);
//        System.out.println(cat);


//        Constructor[] constructors = catClass.getDeclaredConstructors();
//        for (Constructor constructor : constructors) {
//            System.out.println(constructor);
//        }
//        Constructor<Cat> cons = catClass.getConstructor(String.class, String.class, int.class);
//        Cat reflector = cons.newInstance("REFLECTOR", "black", 100500);
//        Cat refl2 = (Cat) catClass.newInstance();
//        System.out.println(reflector);
//        System.out.println(refl2);

        Method method = catClass.getDeclaredMethod("run", int.class);
        method.setAccessible(true);
        method.invoke(cat, 100500);

        Field[] fields2 = catClass.getDeclaredFields();
        for (Field fieldX : fields2) {
            fieldX.setAccessible(true);
            if (fieldX.isAnnotationPresent(MyAnnotation.class)) {
                System.out.println(fieldX.get(cat));
                System.out.println(((MyAnnotation)fieldX.getAnnotation(MyAnnotation.class)).value());
            }
        }
    }

   public static class Cat implements Cloneable, Serializable {
        static String type = "CAT";
        public final String name;
        public String color;

        @MyAnnotation(value = "Hello", some = 99)
       final int age = 1;
        private Bowl b;

       public Cat() {
//           age = 1;
           name = "Nameless";
       }

       public Cat(String name, String color, int age) {
            this.name = name;
            this.color = color;
//            this.age = age;
        }

        void voice() {
            System.out.println(name + " mew");
        }
        private void run(int distance) {
            System.out.println(name + " running for " + distance);
        }

        @Override
        public String toString() {
            return "Cat{" +
                    "name='" + name + '\'' +
                    ", color='" + color + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
   public static class Bowl {
        int food;
    }
}
