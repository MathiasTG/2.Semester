package Persistence;


import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.*;

public abstract class AbstractRepository {
    private static IConfiguration config;
    private static ExecutorService executor;


    public AbstractRepository()  {
        if(executor==null)
            try {
                initiate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

    }

    /**
     * initiates connection to database and starts executor.
     * @throws SQLException
     */
    private void initiate() throws SQLException{
        config=new Configuration();
        executor= Executors.newFixedThreadPool(3);
    }

    /**
     *
     * Call to get the current database connection. Use to establish connection to database.
     *
     * @return Database Connection
     */
    protected Connection startConnection() {
        try {
            Properties props = new Properties();
            props.setProperty("user",config.getUsername());
            props.setProperty("password",config.getPassword());

            return DriverManager.getConnection(config.getServerUrl(),props);
        } catch (SQLException e) {
            System.err.println(e.getErrorCode());
            System.err.println(e.getMessage());
            return null;
        }
    }


    /**
     *
     * Use to perform SQL queries upon the database
     *
     * @param statement executing SQL statement
     * @return ResponseMessage containing results from database
     */
    protected ResponseMessage executeStm(final PreparedStatement statement){
        //response message is final because we need to access it from a seperate thread.
        ResponseMessage res = new ResponseMessage();
        Future<ResponseMessage> t =executor.submit(()-> {
            {
                try {
                        res.setData(statement.executeQuery());
                        res.setResponseCode(ResponseCode.SUCCESS);
                } catch (SQLException e) {
                    res.setData(null);
                    res.setResponseCode(ResponseCode.REJECTED);
                }
            }
        },res);
        while (!t.isDone()) {
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return res;
    }

    /**
     *
     * Use to perform SQL updates upon the database
     *
     * @param statements executing SQL statement
     * @return ResponseMessage containing results from database
     */
    protected ResponseCode executeUpdate(final PreparedStatement... statements){
        final ResponseCode[] res = new ResponseCode[1];

        Future f = executor.submit(()->
            Arrays.asList(statements).forEach( t ->
                {
                    try {
                        t.executeUpdate();
                        res[0]=ResponseCode.SUCCESS;
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                       res[0] =ResponseCode.REJECTED;
                    }
                }
            )
        );

        while(!f.isDone()) {
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return res[0];
    }

}
