package Persistence;

import Acq.IRepositoryInquiry;
import Acq.IUser;
import DTO.Inquiry;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class RepositoryInquiry implements IRepositoryInquiry{


    @Override
    public Inquiry getById(UUID uuid) {
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
