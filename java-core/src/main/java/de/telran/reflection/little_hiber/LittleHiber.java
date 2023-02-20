package de.telran.reflection.little_hiber;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class LittleHiber {
    private static Connection connection;

    public static void insertObjectToDatabase(Object o) {
        if (!o.getClass().isAnnotationPresent(Table.class)) {
            throw new RuntimeException("Unable to insert to table");
        }
        PreparedStatement smt = null;

        try {
            // insert into cats (id, name, color) values (?,?,?);
            connect();
            StringBuilder sb = new StringBuilder("insert into ");
            sb.append(((Table) o.getClass().getAnnotation(Table.class)).name());
            sb.append(" (");
            // insert into cats (
            Field[] fields = o.getClass().getDeclaredFields();
            int questions = 0;
            for (Field field : fields) {
                if (field.isAnnotationPresent(HField.class)) {
                    sb.append(field.getName());
                    sb.append(", ");
                    questions++;
                }
            }
            sb.setLength(sb.length() - 2);
            sb.append(") values (");
            for (int i = 0; i < questions; i++) {
                sb.append(i + 1 == questions ? "?);" : "?, ");
            }
            smt = connection.prepareStatement(sb.toString());

            int num = 1;
            for (Field field : fields) {
                if (field.isAnnotationPresent(HField.class)) {
                    field.setAccessible(true);
                    smt.setObject(num++, field.get(o));
                }
            }
            smt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public static void createTable(Class c) {
        Map<Class, String> typeMap = new HashMap<>();
        typeMap.put(int.class, "integer");
        typeMap.put(String.class, "text");

        if (!c.isAnnotationPresent(Table.class)) {
            throw new RuntimeException("Unable to create table");
        }

        Field[] fields1 = c.getDeclaredFields();
        boolean hasId = false;
        for (Field field1 : fields1) {
            if (field1.isAnnotationPresent(Id.class)) {
                if (hasId) throw new RuntimeException("only one id needed");
                hasId = true;
            }
        }

        Statement smt = null;

        try {
            // create table if not exists cats (id integer, name text, color text);
            connect();
            smt = connection.createStatement();
            StringBuilder sb = new StringBuilder("create table if not exists ");
            sb.append(((Table) c.getAnnotation(Table.class)).name());
            sb.append(" (");
            // create table if not exists cats (
            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(HField.class)) {
                    sb.append(field.getName());
                    sb.append(" ");
                    sb.append(typeMap.get(field.getType()));
                    if (field.isAnnotationPresent(Id.class)) {
                        sb.append(" primary key ");
                        if (field.getAnnotation(Id.class).autoincrement()) {
                            sb.append("autoincrement ");
                        }
                    }
                    sb.append(", ");
                }
            }
            sb.setLength(sb.length() - 2);
            sb.append(");");
            smt.execute(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    private static void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:reflect.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
