package Domain.Inquiry;

import Domain.User;

import java.util.List;
import java.util.UUID;

/**
 * @author mathias
 */
public class Inquiry {
    private UUID id;
    private Citizen citizen;
    private boolean isDraft;
    private boolean supportsVUM;
    private User createdBy;
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

    private Inquiry() {
        this.id=UUID.randomUUID();
    }

    /**
     * GetRekt params
     * @param id
     * @param citizen
     * @param isDraft
     * @param supportsVUM
     * @param createdBy
     * @param desciption
     * @param isIntentIsClear
     * @param isCitizenInformedOfRights
     * @param isCitizenInformedOfDataReservation
     * @param agreementOfProgress
     * @param consentType
     * @param gatheredConsents
     * @param specialConditions
     * @param actingMunicipality
     * @param payingMunicipality
     */
    public Inquiry(UUID id,
                   Citizen citizen,
                   boolean isDraft,
                   boolean supportsVUM,
                   User createdBy,
                   String desciption,
                   boolean isIntentIsClear,
                   boolean isCitizenInformedOfRights,
                   boolean isCitizenInformedOfDataReservation,
                   String agreementOfProgress,
                   ConsentType consentType,
                   List<GatheredConsent> gatheredConsents,
                   String specialConditions,
                   Municipality actingMunicipality,
                   Municipality payingMunicipality) {
        this.id=id;
        this.citizen = citizen;
        this.isDraft = isDraft;
        this.supportsVUM = supportsVUM;
        this.createdBy = createdBy;
        this.desciption = desciption;
        this.isIntentIsClear = isIntentIsClear;
        this.isCitizenInformedOfRights = isCitizenInformedOfRights;
        this.isCitizenInformedOfDataReservation = isCitizenInformedOfDataReservation;
        this.agreementOfProgress = agreementOfProgress;
        this.consentType = consentType;
        this.gatheredConsents = gatheredConsents;
        this.specialConditions = specialConditions;
        this.actingMunicipality = actingMunicipality;
        this.payingMunicipality = payingMunicipality;
    }

    public UUID getId() {
        return id;
    }

    public Citizen getCitizen() {
        return citizen;
    }

    public void setCitizen(Citizen citizen) {
        this.citizen = citizen;
    }

    public boolean isDraft() {
        return isDraft;
    }

    public void setDraft(boolean draft) {
        isDraft = draft;
    }

    public boolean isSupportsVUM() {
        return supportsVUM;
    }

    public void setSupportsVUM(boolean supportsVUM) {
        this.supportsVUM = supportsVUM;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public boolean isIntentIsClear() {
        return isIntentIsClear;
    }

    public void setIntentIsClear(boolean intentIsClear) {
        isIntentIsClear = intentIsClear;
    }

    public boolean isCitizenInformedOfRights() {
        return isCitizenInformedOfRights;
    }

    public void setCitizenInformedOfRights(boolean citizenInformedOfRights) {
        isCitizenInformedOfRights = citizenInformedOfRights;
    }

    public boolean isCitizenInformedOfDataReservation() {
        return isCitizenInformedOfDataReservation;
    }

    public void setCitizenInformedOfDataReservation(boolean citizenInformedOfDataReservation) {
        isCitizenInformedOfDataReservation = citizenInformedOfDataReservation;
    }

    public String getAgreementOfProgress() {
        return agreementOfProgress;
    }

    public void setAgreementOfProgress(String agreementOfProgress) {
        this.agreementOfProgress = agreementOfProgress;
    }

    public ConsentType getConsentType() {
        return consentType;
    }

    public void setConsentType(ConsentType consentType) {
        this.consentType = consentType;
    }

    public String getSpecialConditions() {
        return specialConditions;
    }

    public void setSpecialConditions(String specialConditions) {
        this.specialConditions = specialConditions;
    }

    public Municipality getActingMunicipality() {
        return actingMunicipality;
    }

    public void setActingMunicipality(Municipality actingMunicipality) {
        this.actingMunicipality = actingMunicipality;
    }

    public Municipality getPayingMunicipality() {
        return payingMunicipality;
    }

    public void setPayingMunicipality(Municipality payingMunicipality) {
        this.payingMunicipality = payingMunicipality;
    }

    public List<GatheredConsent> getGatheredConsents() {
        return gatheredConsents;
    }

    public void addGatheredConsent(GatheredConsent gatheredConsent){
        gatheredConsents.add(gatheredConsent);
    }
    public void removeGatheredConsent(GatheredConsent gatheredConsent){
        gatheredConsents.remove(gatheredConsent);
    }
}
