package Domain.Inquiry;

public class GatheredConsent {
    private ConsentEntity consentEntity;
    private String contactInfo;

    public GatheredConsent(ConsentEntity consentEntity, String contactInfo){
        this.consentEntity=consentEntity;
        this.contactInfo=contactInfo;
    }

    @Override
    public boolean equals(Object obj) {
        GatheredConsent t;
        if(obj instanceof GatheredConsent){
            t=(GatheredConsent) obj;
        }else return false;
        if(t.contactInfo.equals(this.contactInfo)&&
                t.consentEntity==this.consentEntity)
            return true;
        else return false;
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
