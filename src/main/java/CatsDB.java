import java.sql.*;

public class CatsDB {
    private static final String URL = "jdbc:mysql://localhost:3306/cats";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "wownailprokA1141";
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public Cat cats(){
        try {
            Statement statement = connection.createStatement();
            String SQL = "select * from cats;";
            ResultSet res = statement.executeQuery(SQL);
            while(res.next()){
                String name = res.getString("name");
                String color = res.getString("color");
                Cat cat = new Cat(name, color);
                System.out.println(cat);
            }
            statement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public Cat colorsCount() {
        try {
            Statement statement = connection.createStatement();
            String SQL = "select color, count(color) from cats group by color";
            ResultSet res = statement.executeQuery(SQL);
            while(res.next()){
                String color = res.getString("color");
                int colorCount = res.getInt("count(color)");
                System.out.println("color: " + color + "\t" + "color_count: " + colorCount);
            }
            statement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
