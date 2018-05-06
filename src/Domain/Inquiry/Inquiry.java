package Domain.Inquiry;

import Acq.IInquiry;
import Acq.IInquiryBuilder;
import Acq.IUser;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author mathias
 */
public class Inquiry implements IInquiry {
    private UUID id;
    private Citizen citizen;
    private boolean isDraft;
    private boolean supportsVUM;
    private IUser createdBy;
    private String description;
    private boolean intentIsClear;
    private boolean isCitizenInformedOfRights;
    private boolean isCitizenInformedOfDataReservation;
    private String agreementOfProgress;
    private ConsentType consentType;
    private List<GatheredConsent> gatheredConsents;
    private String specialConditions;
    private Municipality actingMunicipality;
    private Municipality payingMunicipality;

    /**
     * Builder providing possibility of creating an Inquiry for the UI layer.
     * This is under development and we should maybe configure
     * some of the parameters such as Citizen and add some checks to the setters. For example CPR number.
     */
    public static class Builder implements IInquiryBuilder {
        private UUID id;
        private Citizen citizen;
        private boolean isDraft;
        private boolean supportsVUM;
        private IUser createdBy;
        private String description;
        private boolean intentIsClear;
        private boolean isCitizenInformedOfRights;
        private boolean isCitizenInformedOfDataReservation;
        private String agreementOfProgress;
        private ConsentType consentType;
        private List<GatheredConsent> gatheredConsents;
        private String specialConditions;
        private Municipality actingMunicipality;
        private Municipality payingMunicipality;

        public Builder(IUser createdBy){
            this.id=UUID.randomUUID();
            this.gatheredConsents=new ArrayList<>();
            this.createdBy=createdBy;
        }

        @Override
        public IInquiryBuilder setCitizen(Citizen citizen) {
            this.citizen=citizen;
            return this;
        }

        @Override
        public IInquiryBuilder setCreatedBy(IUser createdBy) {
            this.createdBy=createdBy;
            return this;
        }

        @Override
        public IInquiryBuilder setDraft(boolean draft) {
            this.isDraft=draft;
            return this;
        }

        @Override
        public IInquiryBuilder setSupportsVUM(boolean supportsVUM) {
            this.supportsVUM=supportsVUM;
            return this;
        }

        @Override
        public IInquiryBuilder setDescription(String description) {
            this.description=description;
            return this;
        }

        @Override
        public IInquiryBuilder setIntentIsClear(boolean intentIsClear) {
            this.intentIsClear=intentIsClear;
            return this;
        }

        @Override
        public IInquiryBuilder setCitizenInformedOfRights(boolean citizenInformedOfRights) {
            this.isCitizenInformedOfRights=citizenInformedOfRights;
            return this;
        }

        @Override
        public IInquiryBuilder setCitizenInformedOfDataReservation(boolean citizenInformedOfDataReservation) {
            this.isCitizenInformedOfDataReservation=citizenInformedOfDataReservation;
            return this;
        }

        @Override
        public IInquiryBuilder setAgreementOfProgress(String agreementOfProgress) {
            this.agreementOfProgress=agreementOfProgress;
            return this;
        }

        @Override
        public IInquiryBuilder setConsentType(ConsentType consentType) {
            this.consentType=consentType;
            return this;
        }

        @Override
        public IInquiryBuilder setSpecialConditions(String specialConditions) {
            this.specialConditions=specialConditions;
            return this;
        }

        @Override
        public IInquiryBuilder setActingMunicipality(Municipality actingMunicipality) {
            this.actingMunicipality=actingMunicipality;
            return this;
        }

        @Override
        public IInquiryBuilder setPayingMunicipality(Municipality payingMunicipality) {
            this.payingMunicipality=payingMunicipality;
            return this;
        }

        /**
         *method returning the built object.
         * @return the complete IInquiry object, with all attributes set by the setters of this builder.
         */
        @Override
        public IInquiry build() {
            return new Inquiry(id, citizen, isDraft,supportsVUM,createdBy,
                    description,intentIsClear,isCitizenInformedOfRights,
                    isCitizenInformedOfDataReservation,agreementOfProgress,consentType,
                    gatheredConsents,specialConditions,actingMunicipality,payingMunicipality);
        }
    }




    /**
     * empty constructor where id and list of gathered consents are generated automatically.
     * all other fields are null.
     */
    private Inquiry() {
        this.id=UUID.randomUUID();
        this.gatheredConsents= new ArrayList<>();
    }

    /**
     * All parameters.
     * @param id
     * @param citizen
     * @param isDraft
     * @param supportsVUM
     * @param createdBy
     * @param description
     * @param intentIsClear
     * @param isCitizenInformedOfRights
     * @param isCitizenInformedOfDataReservation
     * @param agreementOfProgress
     * @param consentType
     * @param gatheredConsents
     * @param specialConditions
     * @param actingMunicipality
     * @param payingMunicipality
     */
    private Inquiry(UUID id,
                   Citizen citizen,
                   boolean isDraft,
                   boolean supportsVUM,
                   IUser createdBy,
                   String description,
                   boolean intentIsClear,
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
        this.description = description;
        this.intentIsClear = intentIsClear;
        this.isCitizenInformedOfRights = isCitizenInformedOfRights;
        this.isCitizenInformedOfDataReservation = isCitizenInformedOfDataReservation;
        this.agreementOfProgress = agreementOfProgress;
        this.consentType = consentType;
        this.gatheredConsents = gatheredConsents;
        this.specialConditions = specialConditions;
        this.actingMunicipality = actingMunicipality;
        this.payingMunicipality = payingMunicipality;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public Citizen getCitizen() {
        return citizen;
    }

    @Override
    public void setCitizen(Citizen citizen) {
        this.citizen = citizen;
    }

    @Override
    public boolean isDraft() {
        return isDraft;
    }

    @Override
    public void setDraft(boolean draft) {
        isDraft = draft;
    }

    @Override
    public boolean isSupportsVUM() {
        return supportsVUM;
    }

    @Override
    public void setSupportsVUM(boolean supportsVUM) {
        this.supportsVUM = supportsVUM;
    }

    @Override
    public IUser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(IUser createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean isIntentIsClear() {
        return intentIsClear;
    }

    @Override
    public void setIntentIsClear(boolean intentIsClear) {
        this.intentIsClear = intentIsClear;
    }

    @Override
    public boolean isCitizenInformedOfRights() {
        return isCitizenInformedOfRights;
    }

    @Override
    public void setCitizenInformedOfRights(boolean citizenInformedOfRights) {
        isCitizenInformedOfRights = citizenInformedOfRights;
    }

    @Override
    public boolean isCitizenInformedOfDataReservation() {
        return isCitizenInformedOfDataReservation;
    }

    @Override
    public void setCitizenInformedOfDataReservation(boolean citizenInformedOfDataReservation) {
        isCitizenInformedOfDataReservation = citizenInformedOfDataReservation;
    }

    @Override
    public String getAgreementOfProgress() {
        return agreementOfProgress;
    }

    @Override
    public void setAgreementOfProgress(String agreementOfProgress) {
        this.agreementOfProgress = agreementOfProgress;
    }

    @Override
    public ConsentType getConsentType() {
        return consentType;
    }

    @Override
    public List<GatheredConsent> getGatheredConsents() {
        return gatheredConsents;
    }

    @Override
    public void setConsentType(ConsentType consentType) {
        this.consentType = consentType;
    }

    @Override
    public String getSpecialConditions() {
        return specialConditions;
    }

    @Override
    public void setSpecialConditions(String specialConditions) {
        this.specialConditions = specialConditions;
    }

    @Override
    public Municipality getActingMunicipality() {
        return actingMunicipality;
    }

    @Override
    public void setActingMunicipality(Municipality actingMunicipality) {
        this.actingMunicipality = actingMunicipality;
    }

    @Override
    public Municipality getPayingMunicipality() {
        return payingMunicipality;
    }

    @Override
    public void setPayingMunicipality(Municipality payingMunicipality) {
        this.payingMunicipality = payingMunicipality;
    }

    public void addGatheredConsent(GatheredConsent gatheredConsent){
        gatheredConsents.add(gatheredConsent);
    }

    public void removeGatheredConsent(GatheredConsent gatheredConsent){
        gatheredConsents.remove(gatheredConsent);
    }

}
