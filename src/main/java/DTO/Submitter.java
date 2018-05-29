package DTO;

import Acq.IBuilder;

import java.util.UUID;

public class Submitter {
    private SubmitterType type;
    private String contactInfo;
    private UUID id;

    public static class Builder implements IBuilder<Submitter> {

        private UUID id;
        private SubmitterType type;
        private String contactInfo;

        public Builder(UUID id){
            this.id = id;
        }

        public Submitter.Builder setType(SubmitterType type){
            this.type = type;
            return this;
        }

        public Submitter.Builder setContactInfo(String contactInfo){
            this.contactInfo = contactInfo;
            return this;
        }


        @Override
        public Submitter build() {
            return new Submitter(type, contactInfo, id);
        }
    }

    public Submitter(SubmitterType type, String info){
        this.type = type;
        this.contactInfo = info;
        id = UUID.randomUUID();
    }

    private Submitter(SubmitterType type, String contactInfo, UUID id) {
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
