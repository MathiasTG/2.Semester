package Persistence;

import javax.sql.ConnectionPoolDataSource;
import javax.sql.PooledConnection;
<<<<<<< HEAD
//import javax.sql.PooledConnectionBuilder;
=======
>>>>>>> dc6a6a12c4dc07ae351f066e43cbe93d3f724f18
import java.sql.*;
import java.util.concurrent.*;

public abstract class AbstractRepository {
    private IConfiguration config;
    private static Executor executor;
    private static Connection conn;
    private static PooledConnection conn2;


    public AbstractRepository() throws SQLException {
        if(conn==null)
            initiate();
    }
    private void initiate() throws SQLException {
        config=new Configuration();
        conn= DriverManager.getConnection(config.getServerUrl());
        executor= Executors.newFixedThreadPool(4);
        //conn2 = conn.createPooledConnectionBuilder();
    }

    protected ResponseMessage executeStm(final String statement){
        final ResponseMessage res = new ResponseMessage();
        executor.execute(new Runnable() {
            public void run() {
                try {
                    PreparedStatement st = conn.prepareStatement(statement);
                    res.setData(st.executeQuery());
                    res.setResponseCode(ResponseCode.SUCCESS);

                } catch (SQLException e) {
                    res.setData(null);
                    res.setResponseCode(ResponseCode.REJECTED);
                }
            }
        });
        return res;
    }

}
