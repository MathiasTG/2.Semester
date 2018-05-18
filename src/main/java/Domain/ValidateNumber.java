package Domain;

public class ValidateNumber {

    private int lenght;
    private String value;

    public ValidateNumber(int lenght, String value){
        this.lenght = lenght;
        this.value = value;
    }

    public boolean validate(){
        if(this.value.matches("\\d*") && this.value.length() == (lenght - 1))
            return true;
        else
            return false;
    }
}
