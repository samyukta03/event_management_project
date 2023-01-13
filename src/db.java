import java.sql.*;

public class db {
    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:XE", "system", "samyu");
        Statement st = null;
        ResultSet rs = null;
        st = conn.createStatement();
        String str = "create table table1(sno int,value varchar(10))";
        PreparedStatement stmnt = conn.prepareStatement(str);
        str = "insert into table1 values(1,'success')";
        stmnt = conn.prepareStatement(str);
        stmnt.executeUpdate();
        str = "insert into table1 values(2,'success')";
        stmnt = conn.prepareStatement(str);
        stmnt.executeUpdate();
        str =  "insert into table1 values(3,'failure')";
        stmnt = conn.prepareStatement(str);
        stmnt.executeUpdate();
        str = "commit";
        stmnt = conn.prepareStatement(str);
        stmnt.executeUpdate();
        rs = st.executeQuery("select * from table1");
        while(rs.next()){
            int id = rs.getInt(1);
            String s = rs.getString(2);
            System.out.println("Creating & Inserting table through JDBC program");
            System.out.println("SNo: "+id);
            System.out.println("Status: "+s);
        }

        str = "update table1 set value='updated:s' where sno=3";
        stmnt = conn.prepareStatement(str);
        stmnt.executeUpdate();
        stmnt = conn.prepareStatement("commit");
        stmnt.executeUpdate();
        rs = st.executeQuery("select * from table1");
        while(rs.next()){
            int id = rs.getInt(1);
            String s = rs.getString(2);
            System.out.println("Updating table through JDBC program");
            System.out.println("SNo:   "+id);
            System.out.println("Status:  "+s);
        }
        str = "delete from table1 where sno=3";
        stmnt = conn.prepareStatement(str);
        stmnt.executeUpdate();
        stmnt = conn.prepareStatement("commit");
        stmnt.executeUpdate();
        rs = st.executeQuery("select * from table1");
        while(rs.next()){
            int id = rs.getInt(1);
            String s = rs.getString(2);
            System.out.println("Deleting from table through JDBC program");
            System.out.println("SNo "+id);
            System.out.println("Status "+s);
        }


    }

}
