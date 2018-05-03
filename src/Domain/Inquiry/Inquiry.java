package Domain.Inquiry;

import Domain.User;

/**
 * @author mathias
 */
public class Inquiry {
    private Citizen citizen;
    //private Details details;
    /*
      vi skal ha lavet en løsning til at gemme alle detaljerne i en henvendelse.
      hvordan vil det bliver sendt fra UI laget.
     */
    private boolean isDraft;
    private boolean supportsVUM;
    private User createdBy;
}
