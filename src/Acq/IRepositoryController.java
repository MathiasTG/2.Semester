package Acq;

import java.util.UUID;

public interface IRepositoryController {

    /**
     *
     * @param iInquiry deletes the chosen inguiry from the database
     */
    void delete(IInquiry iInquiry);


    /**
     *
     * @param uuid of the Inquiry you want to get
     * @return the IInquiry of the chosen id
     */
    IInquiry getById(UUID uuid);


    /**
     *
     * @param cpr of the Inquiry you want to get
     * @return the IInwuiry of the chosen cpr number
     */
    IInquiry getByCpr(String cpr);


    /**
     *
     * @param name of the Inquiry you want to get
     * @return the IInwuiry of the chosen cpr number
     */
    IInquiry getByName(String name);


    /**
     *
     * @param iInquiry you want to save in the database
     */
    void save(IInquiry iInquiry);
}
