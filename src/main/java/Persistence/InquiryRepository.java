package Persistence;

import Acq.IInquiryRepository;
import Acq.IUser;
import DTO.Inquiry;

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

            while(result.next())
            {
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

            while(result.next())
            {
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

            while(result.next())
            {
                //Map into inquiry
            }
            return userInquires;

        } catch (SQLException e) {
            e.printStackTrace();
            return userInquires;
        }
    }


    @Override
    public List<Inquiry> getAllInquiriesByUserId(UUID id)
    {
        StringBuilder query = new StringBuilder();
        //select * from users where users.id = '3b3d1763-d32a-4980-8871-21858c10641b'
        query.append("SELECT * FROM inquiry WHERE createdby = " + "'" + id + "'");

        ResultSet result = super.executeStm(query.toString()).getData();

        List<Inquiry> userInquires = new ArrayList<>();

        try {

            while(result.next())
            {
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

        StringBuilder inquiryBuilder = new StringBuilder();
        inquiryBuilder.append("INSERT INTO Inquiry VALUES('");
        inquiryBuilder.append(inquiry.getId() + "', ");
        inquiryBuilder.append(inquiry.isDraft() + ", ");
        inquiryBuilder.append(inquiry.isSupportsVUM() + ", ");
        if (inquiry.getCreatedBy() != null) {
            inquiryBuilder.append("'" + inquiry.getCreatedBy().getID().toString() + "', '");
        } else {
            inquiryBuilder.append(inquiry.getCreatedBy() + ", '");
        }
        inquiryBuilder.append(inquiry.getDescription() + "', ");
        inquiryBuilder.append(inquiry.isIntentIsClear() + ", ");
        inquiryBuilder.append(inquiry.isCitizenAwareOfInquiry() + ", ");
        inquiryBuilder.append(inquiry.isCitizenInformedOfRights() + ", ");
        inquiryBuilder.append(inquiry.isCitizenInformedOfDataReservation() + ", '");
        inquiryBuilder.append(inquiry.getAgreementOfProgress() + "', '");
        inquiryBuilder.append(inquiry.getSpecialConditions() + "', ");
        if (inquiry.getActingMunicipality() != null) {
            inquiryBuilder.append("'" + inquiry.getActingMunicipality() + "', ");
        } else {
            inquiryBuilder.append(inquiry.getActingMunicipality() + ", ");
        }
        if (inquiry.getPayingMunicipality() != null) {
            inquiryBuilder.append("'" + inquiry.getPayingMunicipality() + "');");
        } else {
            inquiryBuilder.append(inquiry.getPayingMunicipality() + ");");
        }

        if(executeUpdate(inquiryBuilder.toString()).equals(ResponseCode.SUCCESS))
        {
            System.out.println("Mega godt");
        }
        else
        {
            System.out.println("Knap så godt");
        }
    }

    @Override
    public void save(Inquiry inquiry) {

    }

    @Override
    public void delete(Inquiry inquiry) {

    }


}
