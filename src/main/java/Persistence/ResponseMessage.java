package Persistence;

import java.sql.ResultSet;

public class ResponseMessage{
    private ResultSet data;
    private ResponseCode responseCode;

    public ResponseMessage(){
        this.responseCode=null;
        this.data=null;
    }
    public ResponseMessage (ResultSet data, ResponseCode code){
        this.responseCode=code;
        this.data=data;
    }

    public ResultSet getData() {
        return data;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public void setData(ResultSet data) {
        this.data = data;
    }

    public void setResponseCode(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }
}
