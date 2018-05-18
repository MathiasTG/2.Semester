package Domain;

public class Validate {


    public Validate(){
    }

    public boolean validateNumber(int lenght, String value){
        if(value.matches("\\d*") && value.length() == (lenght - 1))
            return true;
        else
            return false;
    }

    public boolean validateEmail(String email){
        if(!email.contains("@"))
            return false;
        if (!email.contains("."))
            return false;

        return true;
    }


}
