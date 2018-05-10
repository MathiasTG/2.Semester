package DTO;

import Acq.IBuilder;
import Acq.ILegalGuardian;

/**
 * @author mathias
 */
public class Relative implements ILegalGuardian {
    private String name;
    private String address;
    private String email;
    private String phoneNumber;
    private TypeOfRelative typeOfRelative;

    public static class Builder implements IBuilder<Relative> {
        private String name;
        private String address;
        private String email;
        private String phoneNumber;
        private TypeOfRelative typeOfRelative;

        public Builder(String name,String address, TypeOfRelative typeOfRelative){
            this.name=name;
            this.address=address;
            this.typeOfRelative=typeOfRelative;
        }

        public Relative.Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber=phoneNumber;
            return this;
        }

        public Relative.Builder setEmail(String email) {
            this.email=email;
            return this;
        }

        @Override
        public Relative build() {
            return new Relative(name,phoneNumber,email,address,typeOfRelative);
        }
    }
    private Relative(String name,String phoneNumber,String email, String address, TypeOfRelative typeOfRelative) {
        this.name = name;
        this.phoneNumber=phoneNumber;
        this.email=email;
        this.address = address;
        this.typeOfRelative = typeOfRelative;
    }

    @Override
    public boolean equals(Object obj) {
        Relative t;
        if(obj instanceof Relative)
            t = (Relative) obj;
        else return false;
        if(t.getAddress().equals(this.address) &&
                t.getName().equals(this.name)&&
                t.phoneNumber.equals(this.phoneNumber)&&
                t.email.equals(this.email)&&
                t.getTypeOfRelative().toString().equals(this.typeOfRelative.toString())) {
            return true;
        }else return false;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public TypeOfRelative getTypeOfRelative() {
        return typeOfRelative;
    }

    public void setTypeOfRelative(TypeOfRelative typeOfRelative) {
        this.typeOfRelative = typeOfRelative;
    }

    @Override
    public String getContactInfo() {
        return "Email address: " + this.email + "\n" +
                "Address: " + this.address + "\n" +
                "Phonenumber: " + this.phoneNumber;
    }
}
