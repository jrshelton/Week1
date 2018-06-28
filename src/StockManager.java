import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class StockManager {
    public static boolean resetTable() {
        String sql = "DROP TABLE IF EXISTS `stocks`";

        ResultSet keys = null;

        try (
                Connection conn = SqlHelper.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            int affected = stmt.executeUpdate();
            if (affected == 1) {
                if (keys != null) keys.close();
                return true;

            } else {
                if (keys != null) keys.close();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean create() {
        String sql = "CREATE TABLE `stocks`( " +
                "  `stocks_ID` int(11) NOT NULL AUTO_INCREMENT," +
                "  `symbol` varchar(45) DEFAULT NULL," +
                "  `price` double DEFAULT NULL," +
                "  `volume` int(11) DEFAULT NULL," +
                "  `date` datetime DEFAULT NULL," +
                "  PRIMARY KEY (`stocks_ID`)" +
                ") ENGINE=InnoDB AUTO_INCREMENT=24090 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci";
        ResultSet keys = null;
        int af = 0;
        try (
                Connection conn = SqlHelper.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            int affect = stmt.executeUpdate();
            if (affect == 1) {
                if (keys != null) keys.close();
                af = 1;
            }
        }catch(
      SQLException e)

       {
          e.printStackTrace();
       }finally {

           if (af == 1) {
            return true;
           }
           return false;
           }
    }



    public static boolean insert(Stock bean) throws Exception{
        String sql = "INSERT into stocks (symbol, price, volume, date) " +
                "VALUES (?, ?, ?, ?)";

        ResultSet keys = null;
        try (
                Connection conn = SqlHelper.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {

            stmt.setString(1, bean.getSymbol());
            stmt.setDouble(2, bean.getPrice());
            stmt.setInt(3, bean.getVolume());
            stmt.setTimestamp(4, new java.sql.Timestamp(bean.getDate().getTime()));

            int affected = stmt.executeUpdate();

            if (affected == 1) {
                if (keys != null) keys.close();
                return true;
            } else {
                System.err.println("No rows affected");
                return false;
            }

        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally{
            if (keys != null) keys.close();
        }
    }

    public static void FindMinPrice(String name, Date day){


        String sql = "{CALL FindMinValue(?,?,?)}";

        maxMinhelper(name, day, sql);

    }

    public static void FindMaxPrice(String name, Date day){
        String sql = "{CALL FindMaxValue(?,?,?)}";

        maxMinhelper(name, day, sql);
        ResultSet keys = null;

    }

    public static void findTotalVolume(String name, Date day){
        String sql = "{CALL SumVolume(?,?)}";

        ResultSet keys;
        try (
                Connection conn = SqlHelper.getConnection();
                CallableStatement stmt = conn.prepareCall(sql);
        ) {

            stmt.setString(1, name);
            stmt.setDate(2, new java.sql.Date(day.getTime()));

            keys = stmt.executeQuery();
            if(keys.next()) {
                System.out.println(keys.getString(1));
            }

        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    private static void maxMinhelper(String name, Date day, String sql) {
        Calendar morn = new GregorianCalendar() ;
        morn.setTime(day);
        morn.set(Calendar.HOUR_OF_DAY, 0);
        Calendar night = new GregorianCalendar() ;
        night.setTime(day);
        night.set(Calendar.HOUR_OF_DAY, 22);

        ResultSet keys;
        try (
                Connection conn = SqlHelper.getConnection();
                CallableStatement stmt = conn.prepareCall(sql);
        ) {

            stmt.setString(1, name);
            stmt.setDate(2, new java.sql.Date(morn.getTime().getTime()));


            stmt.setDate(3, new java.sql.Date(night.getTime().getTime()));


            keys = stmt.executeQuery();
            if(keys.next()) {
                System.out.println(keys.getString(1));
            }

        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public static void closingPrice(String name, Date day){
        String sql = "{CALL ClosingPrice(?,?)}";

        ResultSet keys;
        try (
                Connection conn = SqlHelper.getConnection();
                CallableStatement stmt = conn.prepareCall(sql);
        ) {

            stmt.setString(1, name);
            stmt.setDate(2, new java.sql.Date(day.getTime()));

            keys = stmt.executeQuery();
            if(keys.next()) {
                System.out.println(keys.getString(1));
            }

        } catch (SQLException e) {
            System.err.println(e);
        }
    }


    }

