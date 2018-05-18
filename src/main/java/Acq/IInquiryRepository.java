package Acq;

import DTO.Inquiry;
import Persistence.ResponseMessage;

import java.util.UUID;

public interface IInquiryRepository {




    /**
     *
     * @param uuid of the Inquiry you want to get
     * @return the Inquiry of the chosen id
     */
    Inquiry getById(UUID uuid);



    /**
     *
     * @param a
     * @return
     */
    Inquiry getByParameters(String... a);


    /**
     *
     * @param user
     * @return
     */
    Inquiry getByUser(IUser user);


    void create(Inquiry inquiry);

    /**
     *
     * @param inquiry you want to save in the database
     */
    void save(Inquiry inquiry);



    /**
     *
     * @param inquiry deletes the chosen inguiry from the database
     */
    void delete(Inquiry inquiry);
}
