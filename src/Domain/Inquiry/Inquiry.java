package Domain.Inquiry;

import Domain.User;

import java.util.List;
import java.util.UUID;

/**
 * @author mathias
 */
public class Inquiry {
    private Citizen citizen;
    private boolean isDraft;
    private boolean supportsVUM;
    private User createdBy;


    private UUID id;
    private String desciption;
    private boolean isIntentIsClear;
    private boolean isCitizenInformedOfRights;
    private boolean isCitizenInformedOfDataReservation;
    private String agreementOfProgress;
    private ConsentType consentType;
    private List<GatheredConsent> gatheredConsents;
    private String specialConditions;
    private Municipality actingMunicipality;
    private Municipality payingMunicipality;


    public Inquiry() {

    }


    

}
