package de.telran.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.stream.Stream;

public class JavaIoSecond {

    public static void main(String[] args) throws IOException {

//        countLettersLoop();
//        countStreamApi();
//        serializationExample();

//
//        System.out.println(Files.isDirectory(Paths.get("example")));
//
//        Files.createFile(Paths.get("example/some.txt"));

//        Files.lines(Paths.get("example/1.txt"))
//                .forEach(System.out::println);
//        Paths.get("").toFile();

//        File file = new File("");
//        file.toPath();

//        Files.find()

    }

    private static void serializationExample() {
        //        Cat barsik = new Cat("Barsik", "White");
//        barsik.setAge(10);
//        Cat barsik = new Cat();
//        barsik.setName("Barsik");
//        barsik.setColor("White");

        CatExternalizable barsik = new CatExternalizable("Barsik", "White");
        barsik.setAge(10);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("cats/barsik.ser"))) {
            oos.writeObject(barsik);
        } catch (IOException e) {
            System.err.println(e);
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("cats/barsik.ser"))) {
//           Cat resurrected = (Cat) ois.readObject();
            CatExternalizable resurrected = (CatExternalizable) ois.readObject();
            System.out.println(barsik == resurrected);
            System.out.println(barsik.equals(resurrected));

            System.out.println(barsik);
            System.out.println();
            System.out.println(resurrected);
        } catch (IOException e) {
            System.err.println(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void countStreamApi() {
        try (BufferedReader br = new BufferedReader(new FileReader("example/3.txt"))) {
            long result = br.lines()
                    .flatMap(JavaIoSecond::streamString)
                    .filter(Character::isUpperCase)
                    .count();

            System.out.printf("Uppers: %d%n", result);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private static Stream<Character> streamString(String s) {
        return s.codePoints().mapToObj(c -> (char) c);
    }

    private static void countLettersLoop() {
        try (BufferedReader br = new BufferedReader(new FileReader("example/3.txt"))) {
            long upperCount = 0;
            long lowerCount = 0;
            String line;
            while ((line = br.readLine()) != null) {
                for (Character c : line.toCharArray()) {
                    if (Character.isLetter(c) && Character.isUpperCase(c)) {
                        upperCount++;
                    } else if (Character.isLetter(c) && Character.isLowerCase(c)) {
                        lowerCount++;
                    }
                }
            }

            System.out.printf("Uppers: %d, lowers: %d\n", upperCount, lowerCount);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
