package DTO;

public enum ConsentEntity {
    PERSONAL_DOCTOR("Personlig Læge"),
    SPECIAL_DOCTER("Speciallæge"),
    HOSPITAL("Hospital"),
    UNEMPLOYMENT_FUND("A-Kasse"),
    OFFER("Tilbud"),
    EMPLOYER("Arbejdsgiver"),
    PREVIOUS_MUNICIPALITY("Tidligere Kommune"),
    OTHER_MANAGEMENT("Anden forvaltning"),
    OTHER("Andre");



    private String name;
    ConsentEntity(String name){
        this.name=name;
    }

    @Override
    public String toString() {
        return name;
    }
}
