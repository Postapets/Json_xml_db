import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SqlLiteDB {

    public static void main(String[] args) {
        insertIntoSqlite();
    }
    public static void insertIntoSqlite(){
        Connection c;
        Statement stmt;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:company.db");
            c.setAutoCommit(false);
            System.out.println("Opened database");
            String sql;
//
//            stmt = c.createStatement();
//            sql = "CREATE TABLE COMPANY " +
//                    "(ID INT PRIMARY KEY     NOT NULL," +
//                    " NAME           TEXT    NOT NULL, " +
//                    " AGE            INT     NOT NULL, " +
//                    " ADDRESS        VARCHAR(50), " +
//                    " SALARY         REAL)";
//            stmt.executeUpdate(sql);
//            stmt.close();
//            c.commit();
//            System.out.println("-- Table created successfully");
            stmt = c.createStatement();
            stmt.executeUpdate("DELETE FROM COMPANY;");
            stmt.close();
            try (BufferedReader br = new BufferedReader(new FileReader("C:\\test\\files\\XML_file.xml"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
                    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                    User user = (User) jaxbUnmarshaller.unmarshal(new StringReader(line));
                    stmt = c.createStatement();
                    stmt.executeUpdate(String.format("INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) VALUES (%d, '%s', %d, '%s', %d );",user.getId(),user.getName(),user.getAge(),user.getAddress(),user.getSalary()));
                    stmt.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM COMPANY;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String address = rs.getString("address");
                float salary = rs.getFloat("salary");
                System.out.printf("ID=%s NAME=%s AGE=%s ADDRESS=%s SALARY=%s%n", id, name, age, address, salary);
            }
            rs.close();
            stmt.close();
            c.commit();
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}