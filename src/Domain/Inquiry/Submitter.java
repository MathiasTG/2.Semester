package Domain.Inquiry;

public class Submitter {
    private SubmitterType type;
    private String contactInfo;

    public Submitter(SubmitterType type, String info){
        this.type = type;
        this.contactInfo = info;
    }

    public SubmitterType getType() {
        return type;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setType(SubmitterType type) {
        this.type = type;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

}
