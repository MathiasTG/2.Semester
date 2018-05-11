package DTO;

public enum ConsentType {
    VERBAL      ("Verbalt"),
    WRITTEN     ("Skrevent");

    private final String name;
    ConsentType(String name){
        this.name=name;
    }

    @Override
    public String toString() {
        return name;
    }
}
