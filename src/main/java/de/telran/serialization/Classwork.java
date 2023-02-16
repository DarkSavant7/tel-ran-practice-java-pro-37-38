package de.telran.serialization;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class Classwork {

    //Jackson, Gson
    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder();
        try (Scanner sc = new Scanner(new FileInputStream("cats/murzik.json"))) {
            sc.useDelimiter("%%%%");
            sb.append(sc.next());
//            while (sc.hasNext()) {
//                sb.append(sc.nextLine());
//
//            }
        } catch (IOException e) {
            System.out.println(e);
        }

        System.out.println(sb);


//        javaSerialization();
//        Cat murzik = new Cat("Murzik", "red");
//        murzik.setAge(10);

        Gson gson = new Gson();
//        String murzikJson = gson.toJson(murzik);
//
//        try (FileOutputStream fileOutputStream = new FileOutputStream("cats/murzik.json")) {
//            fileOutputStream.write(murzikJson.getBytes());
//        } catch (IOException e) {
//            System.out.println(e);
//        }

//        StringBuilder sb = new StringBuilder();
//        try (BufferedReader fileReader = new BufferedReader(new FileReader("cats/murzik.json"))) {
//            fileReader.lines()
//                    .forEach(sb::append);
//
////            Cat murzik = gson.fromJson(sb.toString(), Cat.class);
//
////            System.out.println(murzik);
//        } catch (IOException e) {
//            System.out.println(e);
//        }




    }

    private static void javaSerialization() {
        //        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("cats/murzik"))) {
//            Cat murzik = new Cat("Murzik", "red");
////            murzik.setAge(10);
//            oos.writeObject(murzik);
//        } catch (IOException e) {
//            System.out.println(e);
//        }

//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("cats/murzik_ex"))) {
//            CatExternalizable murzik = new CatExternalizable("Murzik", "red");
//            oos.writeObject(murzik);
//        } catch (IOException e) {
//            System.out.println(e);
//        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("cats/murzik"))) {
            Cat murzik = (Cat) ois.readObject();
            System.out.println(murzik);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }
}
