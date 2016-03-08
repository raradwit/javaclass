/*
package Feb24.Feb24.Assignment.Try2;
*/

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by zephyr on 3/1/16.
 */
public class Test1 {
    private Connection connection=null;

    public void dbConnect(){
        DbConnection dbConnection= DbConnection.getDbConnection();
        System.out.println("Waiting for Connection Test 1");
        while (connection==null){
            connection=dbConnection.getConnectionFromPool();
        }
        dbConnection.setConnectionBusy(connection);
        System.out.println("Test1 Connected.");

        Runnable r = () -> {
            System.out.println("Test 1 Inserting");
            insertValue();
            dbConnection.freeConnection(connection);
            System.out.println("Test 1 Completed");
        };
        new Thread(r, "Test 1").start();
    }

    public void insertValue(){
        int n=100;
        for(int i=0;i<n;i++){
            String sql = "INSERT INTO test(id) VALUES('"+i+"')";
            try {
                Statement statement = connection.createStatement();
                statement.execute(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
