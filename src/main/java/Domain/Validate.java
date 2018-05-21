package Domain;

import java.util.regex.Pattern;

public class Validate {


    public Validate(){
    }

    public boolean validateNumber(int lenght, String value){
        if(!value.matches("\\d*") || value.length() != lenght) // Regular expression, matches the digits (0-9) exactly
            return false;
        else
            return true;
    }

    public boolean validateEmail(String email){
        //The emailRegex is a regular expression provided in a validation regex repository for email
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+   //^matches the beginning of the line, with the allowed specified characters
                "[a-zA-Z0-9_+&*-]+)*@" +    //Matches 0 or more of the expression to the left, always including @
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +  //The plus matches 1 or more of the regular expression to the left, including '.', meaning longer addresses are allowed
                "A-Z]{1,7}$";  //$Matches the end of the line, with 1-7 allowed lenght


        Pattern pat = Pattern.compile(emailRegex); //A compiled representation of a regular expression
                                                    //must be compiled before made into a matcher object
                                                    //Has no public constructor, initiated with the static compile method
        if (email == null)
            return false;
        //The matcher class interprets the pattern, also has no public constructor
        if(pat.matcher(email).matches()) //matches the compiled regular expression against the character sequence received
            return true;
        else
            return false;
    }


}
