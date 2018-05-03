package Domain;

/**
 * @author mathias
 */
public class Relative {
    private String name;
    private String address;
    private TypeOfRelative typeOfRelative;

    public Relative(String name, String address, TypeOfRelative typeOfRelative) {
        this.name = name;
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
                t.getTypeOfRelative().toString().equals(this.typeOfRelative.toString())) {
            return true;
        }else return false;
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
}
