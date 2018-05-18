package Persistence;

import Acq.IInquiryRepository;
import Acq.IUser;
import DTO.Inquiry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class InquiryRepository extends AbstractRepository implements IInquiryRepository {


    public InquiryRepository() throws SQLException {
        super();
    }

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
    public void save(Inquiry inquiry) {

    }

    @Override
    public void delete(Inquiry inquiry) {

    }
}
