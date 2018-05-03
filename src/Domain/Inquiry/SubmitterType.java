package Domain.Inquiry;

public enum SubmitterType {
    ONGOING_EFFORT      ("igangværende indsats"),
    OTER_MUNICIPALITY   ("andre kommuner"),
    MISCELLANEOUS       ("andre"),
    CITIZIN             ("borger"),
    RELATIVE            ("pårørende"),
    DOCTOR              ("læge"),
    HOSPITAL            ("hospital"),
    OTHER_MANAGEMENT    ("anden management");

    private final String name;
    SubmitterType(String name){
        this.name=name;
    }

    @Override
    public String toString() {
        return name;
    }

}
