package DTO;

public enum SubmitterType {
    ONGOING_EFFORT      ("Igangværende indsats"),
    OTER_MUNICIPALITY   ("Andre kommuner"),
    MISCELLANEOUS       ("Andre"),
    CITIZIN             ("Borger"),
    RELATIVE            ("Pårørende"),
    DOCTOR              ("Læge"),
    HOSPITAL            ("Hospital"),
    OTHER_MANAGEMENT    ("Anden management");

    private final String name;
    SubmitterType(String name){
        this.name=name;
    }

    @Override
    public String toString() {
        return name;
    }

}
