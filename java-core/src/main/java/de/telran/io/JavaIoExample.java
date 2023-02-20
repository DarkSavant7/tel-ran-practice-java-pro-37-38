package de.telran.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class JavaIoExample {
    public static void main(String[] args) throws IOException {
//        simpleFileExample();
        fileTreeWalk(new File("D:"));
//        directoriesExample();


//        simpleRead();
//        readingMethodComparison();

//        try (BufferedReader br = new BufferedReader(new FileReader("example/2.txt"))) {
//            String line;
//            int counter = 0;
//            while ((line = br.readLine()) != null && counter < 1000) {
//                System.out.println(line);
//                counter++;
//            }
//        }

    }

    private static void readingMethodComparison() throws IOException {
        try (FileInputStream fis = new FileInputStream("example/2.txt")) {
            long start = System.currentTimeMillis();
            int b;
            while ((b = fis.read()) > -1) {
//                System.out.print((char) b);
//                list.add((byte) b);
            }

            System.out.println("Simple read");
            System.out.println(System.currentTimeMillis() - start);
        }

        try (FileInputStream fis = new FileInputStream("example/2.txt")) {
            byte[] buf = new byte[5230592];
            long start = System.currentTimeMillis();
            int b;
            while ((b = fis.read(buf)) > -1) {
//                System.out.print((char) b);
//                list.add((byte) b);
            }

            System.out.println("Buffered read");
            System.out.println(System.currentTimeMillis() - start);
        }

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("example/2.txt"), 5230592)) {
            long start = System.currentTimeMillis();
            int b;
            while ((b = bis.read()) > -1) {
//                System.out.print((char) b);
//                list.add((byte) b);
            }

            System.out.println("BufferedStream read");
            System.out.println(System.currentTimeMillis() - start);
        }
    }

    private static void simpleRead() throws IOException {
        try (FileInputStream fis = new FileInputStream("example/1.txt")) {
            int b;
//            ArrayList<Byte> list = new ArrayList<>();
            while ((b = fis.read()) > -1) {
                System.out.print((char) b);
//                list.add((byte) b);
            }

//            System.out.println(new String(list.toArray()));
        }
    }

    private static void directoriesExample() {
        File file = new File("example/1/2/3/4/5/6/7/8/9/0/file.txt");

        System.out.println(file.mkdirs());
//        file.createNewFile();
    }

    private static void fileTreeWalk(File root) {
       if (root.isFile()) {

           System.out.println("File --> " + root.getPath());
       } else {
//           System.out.println("Directory --> " + root.getPath());
//           File[] files = root.listFiles();
//           File[] files = root.listFiles(new FileFilter() {
//               @Override
//               public boolean accept(File pathname) {
//                   return !pathname.isHidden();
//               }
//           });

//           File[] files = root.listFiles(f -> !f.isHidden() && !f.getName().contains(".idea"));
           File[] files = root.listFiles(f -> f.isDirectory() || f.getName().endsWith(".txt"));

           for (File file : files) {
               fileTreeWalk(file);
           }
       }
    }

    private static void simpleFileExample() throws IOException {
        File file = new File("example/1.txt");
//        System.out.println(file.exists());
//        System.out.println(file.canRead());

        if (!file.exists()) {
            file.createNewFile();
        }

        System.out.println(file.isFile());
        System.out.println(file.isDirectory());

        String s = "Hello world";
//        String s = "Another string";

        try (FileOutputStream fos = new FileOutputStream(file, true)) {
            fos.write(s.getBytes());
        }
    }
}
