package Persistence;

import Acq.IInquiryRepository;
import DTO.*;
import Persistence.PersistenceModels.PersistencePassword;
import Persistence.PersistenceModels.PersistenceUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InquiryRepository extends AbstractRepository implements IInquiryRepository {


    @Override
    public List<Inquiry> getInquriesByInquiryId(UUID id) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM inquiry WHERE id ='" + id + "'");

        return buildInquiry(query.toString());
    }

    @Override
    public List<Inquiry> getInquiresByCPR(String cpr) {
        StringBuilder query = new StringBuilder();

        query.append("SELECT * FROM Inquiry WHERE conserning='" + cpr + "';");

        return buildInquiry(query.toString());
    }

    @Override
    public List<Inquiry> getInquiresByCitizenName(String name) {
        StringBuilder query = new StringBuilder();

        query.append("SELECT * FROM inquiry WHERE conserning IN (SELECT cpr FROM citizen where name = '" + name + "');");

        return buildInquiry(query.toString());
    }


    @Override
    public List<Inquiry> getAllInquiriesByUserId(UUID id) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM inquiry WHERE createdby = " + "'" + id + "';");

        return buildInquiry(query.toString());
    }

    public List<Inquiry> buildInquiry(String query){

        ResultSet inquirySet = super.executeStm(query).getData();


        List<Inquiry> userInquires = new ArrayList<>();

        try {

            while (inquirySet.next()) {

                ResultSet userSet = executeStm("SELECT * FROM users WHERE id='" + inquirySet.getString(4)).getData();
                ResultSet passSet = executeStm("SELECT * FROM password WHERE passid='" + userSet.getString(3)).getData();
                ResultSet citizenSet = executeStm("SELECT * FROM citizen WHERE cpr='" + inquirySet.getString(5)).getData();
                ResultSet repSet = executeStm("SELECT * FROM representative WHERE id='" + citizenSet.getString(5)).getData();
                ResultSet subSet = executeStm("SELECT * FROM submitter WHERE id='" + inquirySet.getString(6)).getData();


                userInquires.add(
                        new Inquiry.Builder(new PersistenceUser(UUID.fromString(userSet.getString(1)),
                                userSet.getString(2), userSet.getInt(3),
                                new PersistencePassword(passSet.getString(1),
                                        passSet.getTimestamp(2).toLocalDateTime(),passSet.getBoolean(3))))
                                .setId(UUID.fromString(inquirySet.getString(1)))
                                .setDraft(inquirySet.getBoolean(2))
                                .setSupportsVUM(inquirySet.getBoolean(3))
                                .setCitizen(new Citizen.Builder(citizenSet.getString(1),
                                        citizenSet.getString(2),
                                        citizenSet.getString(3))
                                        .setEmail(citizenSet.getString(4))
                                        .setPhoneNumber(citizenSet.getInt(5)))
                                .setRepresentative(new Representative.Builder(repSet.getString(2),
                                        TypeOfRepresentative.valueOf(repSet.getString(3)))
                                        .setId(UUID.fromString(repSet.getString(1)))
                                        .build()
                                ).build())
                        .setSubmittedBy(new Submitter.Builder(UUID.fromString(subSet.getString(1)))
                                .setType(SubmitterType.valueOf(subSet.getString(3)))
                                .setContactInfo(subSet.getString(2))
                                .build())
                        .setDescription(inquirySet.getString(7))
                        .setIntentIsClear(inquirySet.getBoolean(8))
                        .setCitizenAwareOfInquiry(inquirySet.getBoolean(9))
                        .setCitizenInformedOfRights(inquirySet.getBoolean(10))
                        .setCitizenInformedOfDataReservation(inquirySet.getBoolean(11))
                        .setAgreementOfProgress(inquirySet.getString(12))
                        .setConsentType(ConsentType.valueOf(inquirySet.getString(13)))
                        .setSpecialConditions(inquirySet.getString(14))
                        .setActingMunicipality(inquirySet.getString(15))
                        .setPayingMunicipality(inquirySet.getString(16))
                        .setIsRelevantToGatherConsent(inquirySet.getBoolean(17))
                        .build();


            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return userInquires;
    }

    @Override
    public void create(Inquiry inquiry) {


        if (inquiry.getCitizen() != null && executeStm("Select CPR from citizen where cpr=" + inquiry.getCitizen().getCpr()).getData() == null) {

            createCitizen(inquiry.getCitizen());

        }

        if (inquiry.getSubmittedBy() != null && executeStm("Select ID from submitter where id=" + inquiry.getSubmittedBy().getId().toString()).getData() == null) {

            createSubmitter(inquiry.getSubmittedBy());

        }


        StringBuilder inquiryBuilder = new StringBuilder();
        inquiryBuilder.append("INSERT INTO Inquiry VALUES('");
        inquiryBuilder.append(inquiry.getId().toString() + "', ");
        inquiryBuilder.append(inquiry.isDraft() + ", ");
        inquiryBuilder.append(inquiry.isSupportsVUM() + ", ");
        if (inquiry.getCreatedBy() != null) {
            inquiryBuilder.append("'" + inquiry.getCreatedBy().getID().toString() + "', ");
        } else {
            inquiryBuilder.append(null + ", ");
        }
        if (inquiry.getCitizen() != null) {
            inquiryBuilder.append("'" + inquiry.getCitizen().getCpr() + "', ");
        } else {
            inquiryBuilder.append(null + ", ");
        }
        if (inquiry.getSubmittedBy() != null) {
            inquiryBuilder.append("'" + inquiry.getSubmittedBy().getId().toString() + "', '");
        } else {
            inquiryBuilder.append(null + ", '");
        }
        inquiryBuilder.append(inquiry.getDescription() + "', ");
        inquiryBuilder.append(inquiry.isIntentIsClear() + ", ");
        inquiryBuilder.append(inquiry.isCitizenAwareOfInquiry() + ", ");
        inquiryBuilder.append(inquiry.isCitizenInformedOfRights() + ", ");
        inquiryBuilder.append(inquiry.isCitizenInformedOfDataReservation() + ", '");
        inquiryBuilder.append(inquiry.getAgreementOfProgress() + "', '");
        inquiryBuilder.append(inquiry.getConsentType().toString() + "', '");
        inquiryBuilder.append(inquiry.getSpecialConditions() + "', '");
        inquiryBuilder.append(inquiry.getActingMunicipality() + "', '");
        inquiryBuilder.append(inquiry.getPayingMunicipality() + "', ");
        inquiryBuilder.append(inquiry.getIsRelevantToGatherConsent() + ");");
        if (executeUpdate(inquiryBuilder.toString()).equals(ResponseCode.SUCCESS)) {
            System.out.println("Mega godt");
        } else {
            System.out.println("Knap så godt");
        }
        if (inquiry.getIsRelevantToGatherConsent()) {
            gatherConsent(inquiry);
        }
    }

    private void createSubmitter(Submitter submittedBy) {

        StringBuilder subBuilder = new StringBuilder();
        subBuilder.append("INSERT INTO submitter VALUES('")
                .append(submittedBy.getId().toString() + "', '")
                .append(submittedBy.getType().toString() + "', '")
                .append(submittedBy.getContactInfo() + "');");

        executeUpdate(subBuilder.toString());

    }

    private void createCitizen(Citizen citizen) {

        if (citizen.getRepresentative() != null && executeStm("Select ID from representative where id=" + citizen.getRepresentative().getId().toString()).getData() == null) {
            createRepresentative(citizen.getRepresentative());
        }

        StringBuilder citizenBuilder = new StringBuilder();
        Citizen c = citizen;

        citizenBuilder.append("INSERT INTO citizen VALUES('")
                .append(c.getCpr() + "', '")
                .append(c.getName() + "', '")
                .append(c.getAddress() + "', '")
                .append(c.getEmail() + "', '")
                .append(c.getPhoneNumber() + "', ");

        if (c.getRepresentative() != null) {
            citizenBuilder.append("'" + c.getRepresentative().getId().toString() + "');");
        } else {
            citizenBuilder.append(null + ");");
        }

        executeUpdate(citizenBuilder.toString());

    }

    private void createRepresentative(Representative representative) {

        StringBuilder rB = new StringBuilder();

        rB.append("INSERT INTO representative VALUES('")
                .append(representative.getId().toString() + "', '")
                .append(representative.getContactInfo() + "', '")
                .append(representative.getType().toString() + "');");
        executeUpdate(rB.toString());
    }

    private void gatherConsent(Inquiry inquiry) {

        List<GatheredConsent> consentList = inquiry.getGatheredConsents();

        StringBuilder consentBuilder = new StringBuilder();
        StringBuilder relationBuilder = new StringBuilder();

        consentBuilder.append("INSERT INTO gatheredConsent VALUES");

        relationBuilder.append("INSERT INTO consentForInquiry VALUES");

        for (GatheredConsent consent : consentList) {

            consentBuilder.append("('" + consent.getId() + "', '")
                    .append(consent.getConsentEntity() + "', '")
                    .append(consent.getContactInfo() + "');");

            relationBuilder.append("('" + inquiry.getId() + "', '" + consent.getId() + "');");

        }

        executeUpdate(consentBuilder.toString(), relationBuilder.toString());

    }

    @Override
    public void save(Inquiry inquiry) {

    }

    @Override
    public void delete(Inquiry inquiry) {

    }


}
