package Persistence;

import Acq.IInquiryRepository;
import Acq.IUser;
import DTO.Inquiry;

import java.sql.ResultSet;
import java.sql.SQLException;
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
    public Inquiry getByParameters(String... a) {
        return null;
    }

    @Override
    public Inquiry getByUser(IUser user) {
        return null;
    }

    @Override
    public void create(Inquiry inquiry) {

        StringBuilder inquiryBuilder = new StringBuilder();
        inquiryBuilder.append("INSERT INTO Inquiry VALUES('");
        inquiryBuilder.append(inquiry.getId() + "', ");
        inquiryBuilder.append(inquiry.isDraft() + ", ");
        inquiryBuilder.append(inquiry.isSupportsVUM() + ", ");
        if (inquiry.getCreatedBy() != null) {
            inquiryBuilder.append("'" + inquiry.getCreatedBy() + "', '");
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


        ResultSet result = executeStm(inquiryBuilder.toString()).getData();
    }

    @Override
    public void save(Inquiry inquiry) {

    }

    @Override
    public void delete(Inquiry inquiry) {

    }


}
