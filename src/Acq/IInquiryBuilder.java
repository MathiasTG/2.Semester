package Acq;

import Domain.Inquiry.Citizen;
import Domain.Inquiry.ConsentType;
import Domain.Inquiry.Municipality;

public interface IInquiryBuilder extends IBuilder<IInquiry>{
    IInquiryBuilder setCitizen(Citizen citizen);
    IInquiryBuilder setCreatedBy(IUser createdBy);
    IInquiryBuilder setDraft(boolean draft);
    IInquiryBuilder setSupportsVUM(boolean supportsVUM);
    IInquiryBuilder setDescription(String description);
    IInquiryBuilder setIntentIsClear(boolean intentIsClear);
    IInquiryBuilder setCitizenInformedOfRights(boolean citizenInformedOfRights);
    IInquiryBuilder setCitizenInformedOfDataReservation(boolean citizenInformedOfDataReservation);
    IInquiryBuilder setAgreementOfProgress(String agreementOfProgress);
    IInquiryBuilder setConsentType(ConsentType consentType);
    IInquiryBuilder setSpecialConditions(String specialConditions);
    IInquiryBuilder setActingMunicipality(Municipality actingMunicipality);
    IInquiryBuilder setPayingMunicipality(Municipality payingMunicipality);
}
