package Domain;

import java.util.regex.Pattern;

public class Validate {


    public Validate(){
    }

    public boolean validateNumber(int lenght, String value){
        if(!value.matches("\\d*") || value.length() != lenght)
            return false;
        else
            return true;
    }

    public boolean validateEmail(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{1,7}$";


        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        if(pat.matcher(email).matches())
            return true;
        else
            return false;
    }


}
