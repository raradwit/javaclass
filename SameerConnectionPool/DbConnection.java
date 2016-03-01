/*
package Feb24.Feb24.Assignment.Try2;
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by zephyr on 3/1/16.
 */
public class DbConnection {
    private static final String url= "jdbc:mysql://localhost:3306/demo";
    private static final String user = "library";
    private static final String password ="library";
    private int maxConnection = 3;
    private Connection connection;

    public static DbConnection dbConnection;
    public static HashMap connectionPool = new HashMap();

    public DbConnection(){
        initializeConnectionPool();
    }

    private void initializeConnectionPool(){
        for (int i = 0; i <maxConnection ; i++) {
            connectionPool.put(createNewConnectionForPool(),false);
        }
    }

    private Connection createNewConnectionForPool()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }

        return connection;
    }

    public static  DbConnection getDbConnection() {
        if(dbConnection==null){
            dbConnection = new DbConnection();
            return dbConnection;
        }
        else{
            return dbConnection;
        }
    }

    public Connection getConnectionFromPool(){
        connection = null;
        if (connectionPool.containsValue(false)){
            Set set = connectionPool.entrySet();
            Iterator i = set.iterator();

            while(i.hasNext()) {
                Map.Entry me = (Map.Entry)i.next();
                if (me.getValue().equals(false)){
                    connection= (Connection)me.getKey();
                    setConnectionBusy(connection);
                    return connection;
                }
            }
        }
        return connection;
    }

    public void setConnectionBusy(Connection c){
        Set set = connectionPool.entrySet();

        for (Object aSet : set) {
            Map.Entry me = (Map.Entry) aSet;
            if (me.getKey().equals(c)) {
                me.setValue(true);
            }
        }
     }

    public void freeConnection(Connection c){
        Set set = connectionPool.entrySet();

        for (Object aSet : set) {
            Map.Entry me = (Map.Entry) aSet;
            if (me.getKey().equals(c)) {
                me.setValue(false);
            }
        }
    }
}
