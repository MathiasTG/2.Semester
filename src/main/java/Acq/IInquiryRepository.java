package Acq;

import DTO.Inquiry;
import Persistence.ResponseMessage;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface IInquiryRepository {


    /**
     * Makes a call to the database and returns a list of inquiries with the id.
     * @param id which should belong to an inquiry in the database
     * @param builder is used for building a user for building an inquiry
     * @return a list of inquiries with the id
     */
    List<Inquiry> getInquriesByInquiryId(UUID id, IUserBuilder builder);

    /**
     * Makes a call to the database and returns a list of inquiries with the id.
     * @param cpr which should belong to a citizen belonging to an inquiry in the database
     * @param builder is used for building a user for building an inquiry
     * @return list of inquiries for a citizen found by cpr
     */
    List<Inquiry> getInquiresByCPR(String cpr, IUserBuilder builder);

    /**
     * Makes a call to the database and returns a list of inquiries with the id.
     * @param name which should belong to a citizen belonging to an inquiry in the database
     * @param builder is used for building a user for building an inquiry
     * @return list of inquiries for a citizen found by name
     */
    List<Inquiry> getInquiresByCitizenName(String name, IUserBuilder builder);

    /**
     * Makes a call to the database and returns a list of inquiries made by user.
     * @param id of the user who made the inquiry
     * @param builder is used for building a user for building an inquiry
     * @return list of inquiries made by a user
     */
    List<Inquiry> getAllInquiriesByUserId(UUID id, IUserBuilder builder);

    /**
     * Adds an inquiry to the database
     * @param inquiry the inquiry to be added to the database
     */
    void create(Inquiry inquiry);

    /**
     * Updates an inquiry in the database
     * @param inquiry the inquiry which will be used to update the one in the database
     */
    void updateInquiry(Inquiry inquiry);
}
