import java.sql.*;


public class DataConnection {
    Connection dbConnection;
    Statement statement;

    static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "Alex","Hardpassword1");
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }


//    public String testGet() {
//        String selectTableSQL = "SELECT idAbonent, Name, Surname, phoneNumber, idAreaCode, idBilling from Abonent";
//        String userid = "heh";
//        try {
//            dbConnection = getDBConnection();
//            statement = dbConnection.createStatement();
//
//            // выбираем данные с БД
//            ResultSet rs = statement.executeQuery(selectTableSQL);
//
//            // И если что то было получено то цикл while сработает
//            while (rs.next()) {
//               // userid = rs.getString("idAbonent");
//                String username = rs.getString("Name");
//                String userSurname = rs.getString("Surname");
//                String phoneNumber = rs.getString("phoneNumber");
//                String idAreaCode = rs.getString("idAreaCode");
//                String idBilling = rs.getString("idBilling");
//
//                System.out.println("userid : " + userid);
//                System.out.println("username : " + username);
//                System.out.println("userSurname : " + userSurname);
//                System.out.println("phoneNumber : " + phoneNumber);
//                System.out.println("idAreaCode : " + idAreaCode);
//                System.out.println("idBilling : " + idBilling);
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//
//        }
//        return userid;
//    }
}
