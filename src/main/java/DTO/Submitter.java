package DTO;

import java.util.UUID;

public class Submitter {
    private SubmitterType type;
    private String contactInfo;
    private UUID id;

    public Submitter(SubmitterType type, String info){
        this.type = type;
        this.contactInfo = info;
        id = UUID.randomUUID();
    }

    public Submitter(SubmitterType type, String contactInfo, UUID id) {
        this.type = type;
        this.contactInfo = contactInfo;
        this.id = id;
    }

    public SubmitterType getType() {
        return type;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setType(SubmitterType type) {
        this.type = type;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

}
