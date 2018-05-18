package Persistence;


import javax.swing.plaf.FontUIResource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public abstract class AbstractRepository {
    private IConfiguration config;
    private static ExecutorService executor;
    protected static Connection conn;


    public AbstractRepository()  {
        if(conn==null)
            try {
                initiate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

    }

    /**
     * initiates connection and executor.
     * @throws SQLException
     */
    private void initiate() throws SQLException{
        config=new Configuration();
        conn= DriverManager.getConnection(config.getServerUrl(),config.getUsername(),config.getPassword());
        executor= Executors.newFixedThreadPool(3);
    }


    protected ResponseMessage executeStm(final String statement){
        //response message is final because we need to access it from a seperate thread.
        ResponseMessage res = new ResponseMessage();
        Future<ResponseMessage> t =executor.submit(()-> {
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
        },res);
        while (!t.isDone()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return res;
    }


    protected ResponseCode executeUpdate(final String... statements){
        List<Future> list = new ArrayList<>();
        final ResponseCode[] res = new ResponseCode[1];
        Arrays.asList(statements).forEach( t ->
            list.add(executor.submit(()-> {
                {
                    try {
                        synchronized (conn) {
                            PreparedStatement st = conn.prepareStatement(t);
                            st.executeUpdate();
                            res[0]=ResponseCode.SUCCESS;
                        }
                    } catch (SQLException e) {
                       res[0] =ResponseCode.REJECTED;
                    }
                }
            })));

        for(Future f : list){
            while(!f.isDone()) {
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return res[0];
    }

}
