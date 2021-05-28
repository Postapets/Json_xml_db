import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PostgresMain {

    public static void PGDatabase() {

        Connection c;
        Statement stmt;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "postgres");
            c.setAutoCommit(false);
            System.out.println("Opened database");
            String sql;

//            //-------------- CREATE TABLE ---------------
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
//
//            //--------------- INSERT ROWS ---------------
//            stmt = c.createStatement();
//            sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) VALUES (1, 'Paul', 32, 'California', 20000.00 );" +
//                    "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) VALUES (2, 'Allen', 25, 'Texas', 15000.00 );" +
//                    "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );" +
//                    "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );";
//            stmt.executeUpdate(sql);
//
//            stmt.close();
//            c.commit();
//            System.out.println("-- Records created successfully");


//            //-------------- UPDATE DATA ------------------
//            stmt = c.createStatement();
//            sql = "UPDATE COMPANY set SALARY = 25000.00 where ID=1;";
//            stmt.executeUpdate(sql);
//            c.commit();
//            stmt.close();
//
//            System.out.println("-- Operation UPDATE done successfully");


            //--------------- SELECT DATA ------------------
//            stmt = c.createStatement();
//            ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
//            while ( rs.next() ) {
//                int id = rs.getInt("id");
//                String  name = rs.getString("name");
//                int age  = rs.getInt("age");
//                String  address = rs.getString("address");
//                float salary = rs.getFloat("salary");
//                System.out.println(String.format("ID=%s NAME=%s AGE=%s ADDRESS=%s SALARY=%s",id,name,age,address,salary));
//            }
//            rs.close();
//            stmt.close();
//            c.commit();
//            System.out.println("-- Operation SELECT done successfully");


//            //-------------- DELETE DATA ----------------------
//            stmt = c.createStatement();
//            sql = "DELETE from COMPANY where ID=2;";
//            stmt.executeUpdate(sql);
//            c.commit();
//            stmt.close();
//            System.out.println("-- Operation DELETE done successfully");

            stmt = c.createStatement();
            stmt.execute( "COPY (SELECT row_to_json(data) FROM (" +
                    "SELECT * FROM COMPANY) data) TO 'C:\\test\\files\\JSON_file.json';" );
            System.out.println("-- Json file just created/written");
            stmt.close();
            c.commit();
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
            rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );

            while ( rs.next() ) {
                int id = rs.getInt("id");
                String  name = rs.getString("name");
                int age  = rs.getInt("age");
                String  address = rs.getString("address");
                float salary = rs.getFloat("salary");
                System.out.println(String.format("ID=%s NAME=%s AGE=%s ADDRESS=%s SALARY=%s",id,name,age,address,salary));
            }
            rs.close();
            stmt.close();
            c.commit();

            c.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("-- All Operations done successfully");

    }

    public static void main(String[] args) {
        PGDatabase();
        ParserJsonToXml.getxml();
        SqlLiteDB.insertIntoSqlite();

    }
}
