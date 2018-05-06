package Domain.Inquiry;

import Acq.IBuilder;
import Acq.ILegalGuardian;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author mathias
 */
public class Citizen implements ILegalGuardian {
    private String cpr;
    private String name;
    private String email;
    private String address;
    private int phoneNumber;
    private Set<Relative> relatives;
    private Representative representative;
    private ILegalGuardian legalGuardian;

    public static class Builder implements IBuilder<Citizen> {
        private String cpr;
        private String name;
        private String email;
        private String address;
        private int phoneNumber;
        private Set<Relative> relatives;
        private Representative representative;
        private ILegalGuardian legalGuardian;

        public Builder(String cpr, String name, String address){
            this.relatives=new TreeSet<>();
            this.cpr=cpr;
            this.name=name;
            this.address=address;
        }
        public Citizen.Builder setRelatives(Relative... relatives) {
            this.relatives.addAll(Arrays.asList(relatives));
            return this;
        }

        public Citizen.Builder setRepresentative(Representative representative) {
            this.representative=representative;
            return this;
        }

        public Citizen.Builder setEmail(String email) {
            this.email=email;
            return this;
        }

        public Citizen.Builder setPhoneNumber(int phoneNumber) {
            this.phoneNumber=phoneNumber;
            return this;
        }

        public Citizen.Builder setLegalGuardian(ILegalGuardian legalGuardian) {
            this.legalGuardian=legalGuardian;
            return this;
        }

        @Override
        public Citizen build() {
            return new Citizen(cpr,email,name,address,phoneNumber,relatives,representative,legalGuardian);
        }
    }

    private Citizen(String cpr,String name, String email, String address, int phoneNumber, Set<Relative> relatives, Representative representative, ILegalGuardian legalGuardian) {
        this.cpr = cpr;
        this.email = email;
        this.name=name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.relatives = relatives;
        this.representative = representative;
        this.legalGuardian=legalGuardian;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ILegalGuardian getLegalGuardian() {
        return legalGuardian;
    }

    public void setLegalGuardian(ILegalGuardian legalGuardian) {
        this.legalGuardian = legalGuardian;
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
