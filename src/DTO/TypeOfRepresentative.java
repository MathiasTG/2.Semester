package DTO;

public enum TypeOfRepresentative {

    LEGAL_GUARDIAN          ("Værge"),
    POWER_OF_ATTORNEY       ("Fuldmagt"),
    PART_REPRESENTATIVE     ("Partsrepræsentant");

    private final String name;
    TypeOfRepresentative (String name){
        this.name=name;
    }

    @Override
    public String toString() {
        return name;
    }
}
