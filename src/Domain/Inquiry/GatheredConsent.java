package Domain.Inquiry;

public class GatheredConsent {
    private ConsentEntity consentEntity;
    private String contactInfo;

    public GatheredConsent(ConsentEntity consentEntity, String contactInfo){
        this.consentEntity=consentEntity;
        this.contactInfo=contactInfo;
    }

    public ConsentEntity getConsentEntity() {
        return consentEntity;
    }

    public void setConsentEntity(ConsentEntity consentEntity) {
        this.consentEntity = consentEntity;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}
