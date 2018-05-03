package Domain.Inquiry;

/**
 * @author mathias
 */
public class Representative implements ILegalGuardian {
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String relation;

    public Representative(String name, String address,
                          String phoneNumber, String email,
                          String relation) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.relation = relation;
    }

    @Override
    public String getContactInfo() {
        return "Email address: " + this.email + "\n" +
                "Address: " + this.address + "\n" +
                "Phonenumber: " + this.phoneNumber;
    }

    @Override
    public boolean equals(Object obj) {
        Representative t;
        if(obj instanceof Representative)
            t= (Representative) obj;
        else
            return false;
        if(t.address.equals(this.address)&&
                t.email.equals(this.email)&&
                t.name.equals(this.name)&&
                t.phoneNumber.equals(this.phoneNumber)&&
                t.relation.equals(this.relation))
            return true;
        return false;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
