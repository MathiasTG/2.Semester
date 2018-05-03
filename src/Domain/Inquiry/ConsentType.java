package Domain.Inquiry;

public enum ConsentType {
    VERBAL      ("verbalt"),
    WRITTEN     ("skrevent");

    private final String name;
    ConsentType(String name){
        this.name=name;
    }

    @Override
    public String toString() {
        return name;
    }
}
