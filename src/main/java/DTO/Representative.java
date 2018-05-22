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

        @Override
        public Representative build() {
            return new Representative(contactInfo, type);
        }
    }
    private Representative(String contactInfo, TypeOfRepresentative type) {
        this.contactInfo = contactInfo;
        this.type = type;
        id = UUID.randomUUID();
    }

    public String getContactInfo() {return this.contactInfo; }

    public TypeOfRepresentative getType() {
        return type;
    }

    public UUID getId() {
        return id;
    }
}

