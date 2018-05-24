package Acq;

import DTO.Inquiry;
import Persistence.ResponseMessage;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface IInquiryRepository {






    List<Inquiry> getInquriesByInquiryId(UUID id);

    List<Inquiry> getInquiresByCPR(String cpr);

    List<Inquiry> getInquiresByCitizenName(String name);

    /**
     *
     * @param id
     * @return
     */
    List<Inquiry> getAllInquiriesByUserId(UUID id);


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
