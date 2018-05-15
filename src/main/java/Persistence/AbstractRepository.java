package Persistence;

import java.sql.*;
import java.util.concurrent.*;

public abstract class AbstractRepository {
    private IConfiguration config;
    private static Executor executor;
    private static Connection conn;


    public AbstractRepository() throws SQLException {
        if(conn==null)
            initiate();
    }
    private void initiate() throws SQLException {
        config=new Configuration();
        conn= DriverManager.getConnection(config.getServerUrl());
        executor= Executors.newFixedThreadPool(2);
    }

    protected ResponseMessage executeStm(final String statement){
        final ResponseMessage res = new ResponseMessage();
        executor.execute(()-> {
            {
                try {
                    synchronized (conn) {
                        PreparedStatement st = conn.prepareStatement(statement);
                        res.setData(st.executeQuery());
                        res.setResponseCode(ResponseCode.SUCCESS);
                    }
                } catch (SQLException e) {
                    res.setData(null);
                    res.setResponseCode(ResponseCode.REJECTED);
                }
            }
        });
        return res;
    }

}
