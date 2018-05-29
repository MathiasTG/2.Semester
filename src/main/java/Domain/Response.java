package Domain;

import Acq.IResponse;

public class Response implements IResponse {

    private boolean succesful;
    private String message;

    public Response(boolean succesful) {
        this.succesful = succesful;
        this.message = null;
    }

    public Response(boolean succesful , String message) {
        this.succesful = succesful;
        this.message = message;
    }

    @Override
    public boolean isSuccessful() {
        return succesful;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
