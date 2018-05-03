package Domain;

import java.util.List;

/**
 * @author mathias
 */
public class Citizen {
    private String information;
    private List<Relative> relatives;
    private Representative representative;


    public void addRelative(Relative relative){
        if(!relatives.contains(relative))
            relatives.add(relative);
    }
    public void removeRelative(Relative relative){
        relatives.remove(relative);
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Representative getRepresentative() {
        return representative;
    }

    public void setRepresentative(Representative representative) {
        this.representative = representative;
    }
}
