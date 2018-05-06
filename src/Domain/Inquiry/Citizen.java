package Domain.Inquiry;

import Domain.Inquiry.Relative;
import Domain.Inquiry.Representative;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author mathias
 */
public class Citizen implements ILegalGuardian {
    private String cpr;
    private String email;
    private String address;
    private int phoneNumber;
    private Set<Relative> relatives;
    private Representative representative;

    public Citizen(String cpr, String email, String address, int phoneNumber, Set<Relative> relatives, Representative representative) {
        this.cpr = cpr;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.relatives = relatives;
        this.representative = representative;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<Relative> getRelatives() {
        return relatives;
    }

    public String getContactInfo(){
        return "Email address: " + this.email + "\n" +
                "Address: " + this.address + "\n" +
                "Phonenumber: " + this.phoneNumber;

    }

    public void addRelative(Relative relative){
            relatives.add(relative);
    }
    public void removeRelative(Relative relative){
        relatives.remove(relative);
    }


    public Representative getRepresentative() {
        return representative;
    }

    public void setRepresentative(Representative representative) {
        this.representative = representative;
    }
    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

}
