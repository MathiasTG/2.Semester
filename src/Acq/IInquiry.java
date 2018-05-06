package Acq;

import Domain.Inquiry.Citizen;
import Domain.Inquiry.ConsentType;
import Domain.Inquiry.GatheredConsent;
import Domain.Inquiry.Municipality;

import java.util.List;
import java.util.UUID;

public interface IInquiry {
    UUID getId();
    Citizen getCitizen();
    boolean isDraft();
    boolean isSupportsVUM();
    IUser getCreatedBy();
    String getDescription();
    boolean isIntentIsClear();
    boolean isCitizenInformedOfRights();
    boolean isCitizenInformedOfDataReservation();
    String getAgreementOfProgress();
    ConsentType getConsentType();
    List<GatheredConsent> getGatheredConsents();
    void addGatheredConsent(GatheredConsent gatheredConsent);
    void removeGatheredConsent(GatheredConsent gatheredConsent);
    String getSpecialConditions();
    Municipality getActingMunicipality();
    Municipality getPayingMunicipality();
    void setCitizen(Citizen citizen);
    void setDraft(boolean draft) ;
    void setSupportsVUM(boolean supportsVUM);
    void setDescription(String description);
    void setIntentIsClear(boolean intentIsClear);
    void setCitizenInformedOfRights(boolean citizenInformedOfRights);
    void setCitizenInformedOfDataReservation(boolean citizenInformedOfDataReservation) ;
    void setAgreementOfProgress(String agreementOfProgress) ;
    void setConsentType(ConsentType consentType) ;
    void setSpecialConditions(String specialConditions) ;
    void setActingMunicipality(Municipality actingMunicipality) ;
    void setPayingMunicipality(Municipality payingMunicipality) ;
}
