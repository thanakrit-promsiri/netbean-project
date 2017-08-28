/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlserver;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Sqlserver {

    public static void main(String[] args) {
        Connection conn = null;
        String sql = "SELECT Distinct TABLE_NAME FROM information_schema.TABLES";
        if (args.length > 0) {
            sql = args[0];
        }

        try {

            String dbURL = "jdbc:sqlserver://localhost\\sqlexpress";
            String user = "sa";
            String pass = "password1";
            conn = DriverManager.getConnection(dbURL, user, pass);
            if (conn != null) {
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
                System.out.println("sql :" + sql);

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    String name = rsmd.getColumnName(i);
                    System.out.print(name + "\t");
                }
                System.out.print("\n");
                
                while (rs.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        String name = rsmd.getColumnName(i);

                        System.out.print(rs.getString(name) + "\t");

                    }
                    System.out.print("\n");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
