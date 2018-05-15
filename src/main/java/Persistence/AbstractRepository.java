package Persistence;

import java.sql.*;
import java.util.concurrent.*;

public abstract class AbstractRepository {
    private IConfiguration config;
    private static ExecutorService executor;
    private static Connection conn;


    public AbstractRepository() throws SQLException {
        if(conn==null)
            initiate();
    }

    /**
     * initiates connection and executor.
     * @throws SQLException
     */
    private void initiate() throws SQLException {
        config=new Configuration();
        conn= DriverManager.getConnection(config.getServerUrl());
        executor= Executors.newFixedThreadPool(3);
    }


    protected ResponseMessage executeStm(final String statement){
        //response message is final because we need to access it from a seperate thread.
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
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE,TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            res.setResponseCode(ResponseCode.EXECUTION_TIMEOUT);
        }
        return res;
    }

}
