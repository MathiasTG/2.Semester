package DTO;

import Acq.IBuilder;

import java.net.Proxy;
import java.util.UUID;

/**
 * @author mathias
 */
public class Representative {

    private String contactInfo;
    private TypeOfRepresentative type;
    private UUID id;

    public static class Builder implements IBuilder<Representative> {
        private String contactInfo;
        private TypeOfRepresentative type;
        private UUID id;

        public Builder(String contactInfo, TypeOfRepresentative type){
            this.contactInfo = contactInfo;
            this.type = type;

        }

        public Representative.Builder setContactInfo(String contactInfo) {
            this.contactInfo = contactInfo;
            return this;
        }

        public Representative.Builder setTypeOfRepresentative(TypeOfRepresentative type){
            this.type = type;
            return this;
        }

        public Representative.Builder setId (UUID id){
            this.id = id;
            return this;
        }

        @Override
        public Representative build() {
            return new Representative(contactInfo, type, id);
        }
    }
    private Representative(String contactInfo, TypeOfRepresentative type) {
        this.contactInfo = contactInfo;
        this.type = type;
        id = UUID.randomUUID();
    }

    private Representative(String contactInfo, TypeOfRepresentative type, UUID id) {
        this.contactInfo = contactInfo;
        this.type = type;
        this.id = id;
    }

    public String getContactInfo() {return this.contactInfo; }

    public TypeOfRepresentative getType() {
        return type;
    }

    public UUID getId() {
        return id;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void setType(TypeOfRepresentative type) {
        this.type = type;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}

