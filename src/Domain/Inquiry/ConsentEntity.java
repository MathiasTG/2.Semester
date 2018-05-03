package Domain.Inquiry;

public enum ConsentEntity {

    PERSONAL_DOCTOR         ("personlig læge"),
    SPECIAL_DOCTOR          ("special læge"),
    HOSPITAL                ("hospital"),
    UNEMPLOYMENT_FUND       ("arbejdsløshedsfond"),
    OFFER                   ("tilbud"),
    PREVIOUS_MUNICIPALITY   ("tidligere kommune"),
    OTHER_MANAGEMENT        ("anden ledelse"),
    OTHER                   ("anden");


    private final String name;
    ConsentEntity(String name){
        this.name=name;
    }

    @Override
    public String toString() {
        return name;
    }
}
