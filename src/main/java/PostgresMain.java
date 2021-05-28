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
//            sql = "create table company " +
//                    "(id int primary key not null," +
//                    " name           text not null, " +
//                    " age            int not null, " +
//                    " address        varchar(50), " +
//                    " salary         real)";
//            stmt.executeUpdate(sql);
//            stmt.close();
//            c.commit();
//            System.out.println("-- Table created successfully");
//
//            //--------------- INSERT ROWS ---------------
//            stmt = c.createStatement();
//            sql = "insert into company (id,name,age,address,salary) values (1, 'Саша', 21, 'Белгород', 24000.00 );" +
//                    "insert into company (id,name,age,address,salary) values (2, 'Петя', 28, 'Псков', 150000.00 );" +
//                    "insert into company (id,name,age,address,salary) values (3, 'Женя', 23, 'Краснодар', 21000.00 );" +
//                    "insert into company (id,name, age,address,salary) values (4, 'Костя', 25, 'Ростов-На-Дону', 44000.00 );";
//            stmt.executeUpdate(sql);
//
//            stmt.close();
//            c.commit();
//            System.out.println("-- Records created successfully");
            //Создаем или записываем в json  файл с помощью copy to
            stmt = c.createStatement();
            stmt.execute( "COPY (SELECT row_to_json(data) FROM (" +
                    "SELECT * FROM COMPANY) data) TO 'C:\\test\\files\\JSON_file.json';" );
            System.out.println("-- Json file just created/written");
            stmt.close();
            c.commit();
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
            rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );

            //вывод данных созданной таблицы
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
        System.out.println("                                                                         PostgresMain отработал успешно");

    }

    public static void main(String[] args) {
        PGDatabase();
        ParserJsonToXml.getxml();
        SqlLiteDB.insertIntoSqlite();
    }
}
