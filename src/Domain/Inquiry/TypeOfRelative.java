package Domain.Inquiry;

/**
 * @author Mathias
 */
public enum TypeOfRelative {
    //Consider changing this from hardcoded to something else.
    FATHER  ("Far"),
    MOTHER  ("Mor"),
    DAUGHTER("Datter"),
    SON     ("Søn"),
    AUNT    ("Tante"),
    UNCLE   ("Onkel");

    private final String name;
    TypeOfRelative(String name){
        this.name=name;
    }

    @Override
    public String toString() {
        return name;
    }
}
