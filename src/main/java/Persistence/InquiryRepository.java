package Persistence;

import Acq.IInquiryRepository;
import Acq.IUser;
import DTO.Citizen;
import DTO.Inquiry;
import DTO.Representative;
import DTO.Submitter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class InquiryRepository extends AbstractRepository implements IInquiryRepository {

    @Override
    public Inquiry getById(UUID uuid) {

        ResultSet result = super.executeStm("SELECT * FROM Inquiry where ID=" + uuid).getData();

        try {
            result.getRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Inquiry> getInquriesByInquiryId(UUID id) {
        StringBuilder query = new StringBuilder();
        //select * from users where users.id = '3b3d1763-d32a-4980-8871-21858c10641b'
        query.append("SELECT * FROM inquiry WHERE id = " + "'" + id + "'");

        ResultSet result = super.executeStm(query.toString()).getData();

        List<Inquiry> userInquires = new ArrayList<>();

        try {

            while (result.next()) {
                //Map into inquiry
            }
            return userInquires;

        } catch (SQLException e) {
            e.printStackTrace();
            return userInquires;
        }
    }

    @Override
    public List<Inquiry> getInquiresByCPR(String cpr) {
        StringBuilder query = new StringBuilder();

        //("Select * From");
        //("(Select * from inquiry, citizen WHERE inquiry.citizenId = citizen.id) as b ");
        //("WHERE b.cpr = cpr);

        ResultSet result = super.executeStm(query.toString()).getData();

        List<Inquiry> userInquires = new ArrayList<>();

        try {

            while (result.next()) {
                //Map into inquiry
            }
            return userInquires;

        } catch (SQLException e) {
            e.printStackTrace();
            return userInquires;
        }
    }

    @Override
    public List<Inquiry> getInquiresByCitizenName(String name) {
        StringBuilder query = new StringBuilder();

        //("Select * From");
        //("(Select * from inquiry, citizen WHERE inquiry.citzenId = citizen.id) as b ");
        //("(WHERE b.cpr = cpr);

        ResultSet result = super.executeStm(query.toString()).getData();

        List<Inquiry> userInquires = new ArrayList<>();

        try {

            while (result.next()) {
                //Map into inquiry
            }
            return userInquires;

        } catch (SQLException e) {
            e.printStackTrace();
            return userInquires;
        }
    }


    @Override
    public List<Inquiry> getAllInquiriesByUserId(UUID id) {
        StringBuilder query = new StringBuilder();
        //select * from users where users.id = '3b3d1763-d32a-4980-8871-21858c10641b'
        query.append("SELECT * FROM inquiry WHERE createdby = " + "'" + id + "';");

        ResultSet result = executeStm(query.toString()).getData();

        List<Inquiry> userInquires = new ArrayList<>();

        try {

            while (result.next()) {
                //Map into inquiry
            }
            return userInquires;

        } catch (SQLException e) {
            e.printStackTrace();
            return userInquires;
        }
    }

    @Override
    public void create(Inquiry inquiry) {


        if (inquiry.getCitizen() != null && executeStm("Select CPR from citizen where cpr=" + inquiry.getCitizen().getCpr()).getData() == null) {

            createCitizen(inquiry.getCitizen());

        }

        if (inquiry.getSubmittedBy() != null && executeStm("Select ID from submitter where id=" + inquiry.getSubmittedBy().getId().toString()).getData() == null){

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

    @Override
    public void save(Inquiry inquiry) {

    }

    @Override
    public void delete(Inquiry inquiry) {

    }


}
