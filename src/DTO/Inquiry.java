package DTO;

import Acq.IBuilder;
import Acq.IUser;

import java.util.ArrayList;
import java.util.Arrays;
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
    private IUser createdBy;
    private String description;
    private boolean intentIsClear;
    private boolean citizenAwareOfInquiry;
    private boolean citizenInformedOfRights;
    private boolean citizenInformedOfDataReservation;
    private String agreementOfProgress;
    private ConsentType consentType;
    private List<GatheredConsent> gatheredConsents;
    private String specialConditions;
    private Municipality actingMunicipality;
    private Municipality payingMunicipality;
    private Submitter submittedBy;

    /**
     * Builder providing possibility of creating an Inquiry for the UI layer.
     * This is under development and we should maybe configure
     * some of the parameters such as Citizen and add some checks to the setters. For example CPR number.
     */
    public static class Builder implements IBuilder<Inquiry> {
        private UUID id;
        private Citizen citizen;
        private boolean isDraft;
        private boolean supportsVUM;
        private IUser createdBy;
        private String description;
        private boolean intentIsClear;
        private boolean citizenAwareOfInquiry;
        private boolean citizenInformedOfRights;
        private boolean citizenInformedOfDataReservation;
        private String agreementOfProgress;
        private ConsentType consentType;
        private List<GatheredConsent> gatheredConsents;
        private String specialConditions;
        private Municipality actingMunicipality;
        private Municipality payingMunicipality;
        private Submitter submittedBy;

        public Builder(IUser createdBy){
            this.gatheredConsents=new ArrayList<>();
            this.createdBy=createdBy;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        public Inquiry.Builder setCitizen(Citizen citizen) {
            this.citizen=citizen;
            return this;
        }


        public Inquiry.Builder setCreatedBy(IUser createdBy) {
            this.createdBy=createdBy;
            return this;
        }


        public Inquiry.Builder setDraft(boolean draft) {
            this.isDraft=draft;
            return this;
        }


        public Inquiry.Builder setSupportsVUM(boolean supportsVUM) {
            this.supportsVUM=supportsVUM;
            return this;
        }


        public Inquiry.Builder setDescription(String description) {
            this.description=description;
            return this;
        }


        public Inquiry.Builder setIntentIsClear(boolean intentIsClear) {
            this.intentIsClear=intentIsClear;
            return this;
        }


        public Inquiry.Builder setCitizenInformedOfRights(boolean citizenInformedOfRights) {
            this.citizenInformedOfRights =citizenInformedOfRights;
            return this;
        }


        public Inquiry.Builder setCitizenInformedOfDataReservation(boolean citizenInformedOfDataReservation) {
            this.citizenInformedOfDataReservation =citizenInformedOfDataReservation;
            return this;
        }


        public Inquiry.Builder setAgreementOfProgress(String agreementOfProgress) {
            this.agreementOfProgress=agreementOfProgress;
            return this;
        }


        public Inquiry.Builder setSubmittedBy(Submitter submittedBy) {
            this.submittedBy=submittedBy;
            return this;
        }


        public Inquiry.Builder addGatheredConsents(GatheredConsent... gatheredConsents) {
            this.gatheredConsents.addAll(Arrays.asList(gatheredConsents));
            return this;
        }


        public Inquiry.Builder setConsentType(ConsentType consentType) {
            this.consentType=consentType;
            return this;
        }


        public Inquiry.Builder setSpecialConditions(String specialConditions) {
            this.specialConditions=specialConditions;
            return this;
        }


        public Inquiry.Builder setActingMunicipality(Municipality actingMunicipality) {
            this.actingMunicipality=actingMunicipality;
            return this;
        }


        public Inquiry.Builder setPayingMunicipality(Municipality payingMunicipality) {
            this.payingMunicipality=payingMunicipality;
            return this;
        }

        public void setCitizenAwareOfInquiry(boolean citizenAwareOfInquiry) {
            this.citizenAwareOfInquiry = citizenAwareOfInquiry;
        }

        /**
         *method returning the built object.
         * @return the complete IInquiry object, with all attributes set by the setters of this builder.
         */
        @Override
        public Inquiry build() {
            return new Inquiry(id, citizen, isDraft,supportsVUM,createdBy,
                    description,intentIsClear, citizenInformedOfRights,
                    citizenInformedOfDataReservation,agreementOfProgress,consentType,
                    gatheredConsents,specialConditions,actingMunicipality,payingMunicipality,submittedBy,citizenAwareOfInquiry);
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
                   Municipality payingMunicipality,
                    Submitter submittedBy,boolean citizenAwareOfInquiry) {
        this.id=id;
        this.citizen = citizen;
        this.isDraft = isDraft;
        this.supportsVUM = supportsVUM;
        this.createdBy = createdBy;
        this.description = description;
        this.intentIsClear = intentIsClear;
        this.citizenInformedOfRights = isCitizenInformedOfRights;
        this.citizenInformedOfDataReservation = isCitizenInformedOfDataReservation;
        this.agreementOfProgress = agreementOfProgress;
        this.consentType = consentType;
        this.gatheredConsents = gatheredConsents;
        this.specialConditions = specialConditions;
        this.actingMunicipality = actingMunicipality;
        this.payingMunicipality = payingMunicipality;
        this.submittedBy=submittedBy;
        this.citizenAwareOfInquiry=citizenAwareOfInquiry;
    }


    public void setSubmittedBy(Submitter submittedBy) {
        this.submittedBy=submittedBy;
    }


    public Submitter getSubmittedBy() {
        return submittedBy;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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


    public IUser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(IUser createdBy) {
        this.createdBy = createdBy;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public boolean isIntentIsClear() {
        return intentIsClear;
    }


    public void setIntentIsClear(boolean intentIsClear) {
        this.intentIsClear = intentIsClear;
    }


    public boolean isCitizenInformedOfRights() {
        return citizenInformedOfRights;
    }


    public void setCitizenInformedOfRights(boolean citizenInformedOfRights) {
        this.citizenInformedOfRights = citizenInformedOfRights;
    }


    public boolean isCitizenInformedOfDataReservation() {
        return citizenInformedOfDataReservation;
    }


    public void setCitizenInformedOfDataReservation(boolean citizenInformedOfDataReservation) {
        this.citizenInformedOfDataReservation = citizenInformedOfDataReservation;
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


    public List<GatheredConsent> getGatheredConsents() {
        return gatheredConsents;
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

    public void addGatheredConsent(GatheredConsent gatheredConsent){
        gatheredConsents.add(gatheredConsent);
    }

    public void removeGatheredConsent(GatheredConsent gatheredConsent){
        gatheredConsents.remove(gatheredConsent);
    }

    public boolean isCitizenAwareOfInquiry() {
        return citizenAwareOfInquiry;
    }

    public void setCitizenAwareOfInquiry(boolean citizenAwareOfInquiry) {
        this.citizenAwareOfInquiry = citizenAwareOfInquiry;
    }


}
