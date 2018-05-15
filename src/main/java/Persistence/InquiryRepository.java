package Persistence;

import Acq.IRepositoryInquiry;
import Acq.IUser;
import DTO.Inquiry;

import java.sql.SQLException;
import java.util.UUID;

public class InquiryRepository extends AbstractRepository implements IRepositoryInquiry {


    public InquiryRepository() throws SQLException {
        super();
    }

    @Override
    public Inquiry getById(UUID uuid) {

        super.executeStm("SELECT * FROM Inquiry where ID=" + uuid);

        return In
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
