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
     * Request all inquiries with respect to the given parameter.
     * @param cpr of citizen as a String value
     * @param builder IUserBuilder
     * @return List<Inquiry></>
     */
    List<Inquiry> getInquiresByCPR(String cpr, IUserBuilder builder);

    /**
     *
     * Request all inquiries with respect to the given parameter.
     *
     * @param name of the citizen wished to search for
     * @param builder IUserBuilder
     * @return List<Inquiry></>
     */
    List<Inquiry> getInquiresByCitizenName(String name, IUserBuilder builder);

    /**
     *
     * Request all inquiries of the currentUser by his/hers ID.
     *
     * @param id UUID of currentUser
     * @param builder IUserBuilder
     * @return List<Inquiry></>
     */
    List<Inquiry> getAllInquiriesByUserId(UUID id, IUserBuilder builder);

    /**
     *
     * Creates an inquiry in the database
     *
     * @param inquiry you want to create in the database
     */
    void create(Inquiry inquiry);

    /**
     *
     * @param inquiry you want to save in the database
     */
    void save(Inquiry inquiry);

    /**
     *
     * Deletes an inquiry in the database
     *
     * @param inquiry deletes the chosen inguiry from the database
     */
    void delete(Inquiry inquiry);

    /**
     *
     * Updates and inquiry in the database
     *
     * @param inquiry you want to update
     */
    void updateInquiry(Inquiry inquiry);
}
