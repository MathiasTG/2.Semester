package Persistence;

import Acq.IInquiryRepository;
import Acq.IUserBuilder;
import DTO.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InquiryRepository extends AbstractRepository implements IInquiryRepository {


    @Override
    public List<Inquiry> getInquriesByInquiryId(UUID id, IUserBuilder builder) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM inquiry WHERE id =?;");
        try (Connection conn = startConnection();
             PreparedStatement statement = conn.prepareStatement(query.toString())) {
            statement.setString(1, id.toString());
            return buildInquiry(statement, builder);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Inquiry> getInquiresByCPR(String cpr, IUserBuilder builder) {
        StringBuilder query = new StringBuilder();

        query.append("SELECT * FROM Inquiry WHERE conserning=?;");
        try (Connection conn = startConnection();
             PreparedStatement statement = conn.prepareStatement(query.toString())) {
            statement.setString(1, cpr);
            return buildInquiry(statement, builder);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Inquiry> getInquiresByCitizenName(String name, IUserBuilder builder) {
        StringBuilder query = new StringBuilder();

        query.append("SELECT * FROM inquiry WHERE conserning IN (SELECT cpr FROM citizen where name = ?);");
        try (Connection conn = startConnection();
             PreparedStatement statement = conn.prepareStatement(query.toString())) {
            statement.setString(1, name);
            return buildInquiry(statement, builder);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Inquiry> getAllInquiriesByUserId(UUID id, IUserBuilder builder) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM inquiry WHERE createdby = ?;");
        try (Connection conn = startConnection();
             PreparedStatement statement = conn.prepareStatement(query.toString())) {
            statement.setString(1, id.toString());
            return buildInquiry(statement, builder);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Inquiry> buildInquiry(PreparedStatement query, IUserBuilder builder) {

        List<Inquiry> userInquires = new ArrayList<>();
        String userStm = "SELECT * FROM users WHERE id=?";
        String passStm = "SELECT * FROM password WHERE passid=?";
        String citizenStm = "SELECT * FROM citizen WHERE cpr=?";
        String repStm = "SELECT * FROM representative WHERE id=?";
        String subStm = "SELECT * FROM submitter WHERE id=?";
        String consentStm = "Select * from gatheredconsent where id in (select consent from consentforinquiry where inquiry = ?)";

        try (ResultSet inquirySet = executeStm(query).getData()) {//retrieves data from the inquiryset
            while (inquirySet.next()) {//For every row in the inquirySet, we do the below
                //This is allowed because all connections, preparedstatements and resultsets are declared in
                //Try-with-ressources block. therefore we have no memory leak.

                try (Connection conn = startConnection();//Initiates connection and prepared statements. in try-with-resources
                     PreparedStatement userPre = conn.prepareStatement(userStm);
                     PreparedStatement passPre = conn.prepareStatement(passStm);
                     PreparedStatement citizenPre = conn.prepareStatement(citizenStm);
                     PreparedStatement repPre = conn.prepareStatement(repStm);
                     PreparedStatement subPre = conn.prepareStatement(subStm);
                     PreparedStatement consentPre = conn.prepareStatement(consentStm)
                ) {
                    //Setting strings for preparedstatements which takes input from inquirySet.
                    //password statement and rep statement takes input from the queries below, so they get input
                    //after the below prepared statements have been executed.
                    userPre.setString(1, inquirySet.getString(4));
                    citizenPre.setString(1, inquirySet.getString(5));
                    subPre.setString(1, inquirySet.getString(6));
                    consentPre.setString(1, inquirySet.getString(1));


                    try (ResultSet userSet = executeStm(userPre).getData();//Executes the prepared statements.
                         ResultSet citizenSet = executeStm(citizenPre).getData();
                         ResultSet subSet = executeStm(subPre).getData();
                         ResultSet consentSet = executeStm(consentPre).getData()) {

                        //Calling next on resultsets,
                        // except consentset which is called in a while loop in the inquirybuilder.
                        userSet.next();
                        citizenSet.next();
                        subSet.next();

                        //Setting input for password and representative statements.
                        passPre.setString(1, userSet.getString(3));
                        repPre.setString(1, citizenSet.getString(6));

                        try (ResultSet passSet = executeStm(passPre).getData();//executing prepared statements
                             ResultSet repSet = executeStm(repPre).getData()) {
                            passSet.next();
                            repSet.next();

                            //Adding every inquiry to the list.
                            userInquires.add(
                                    new Inquiry.Builder(builder.setID(UUID.fromString(userSet.getString(1)))
                                            .setUsername(userSet.getString(2))
                                            .setAccessRight(userSet.getInt(4))
                                            .setPassword(passSet.getString(1))
                                            .build())
                                            .setId(UUID.fromString(inquirySet.getString(1)))
                                            .setDraft(inquirySet.getBoolean(2))
                                            .setSupportsVUM(inquirySet.getBoolean(3))
                                            .setCitizen(new Citizen.Builder(citizenSet.getString(1),
                                                    citizenSet.getString(2),
                                                    citizenSet.getString(3))
                                                    .setEmail(citizenSet.getString(4))
                                                    .setPhoneNumber(citizenSet.getInt(5))
                                                    .setRepresentative(new Representative.Builder(repSet.getString(2),
                                                            castToRepresentativeType(repSet.getString(3)))
                                                            .setId(UUID.fromString(repSet.getString(1)))
                                                            .build()
                                                    ).build())
                                            .setSubmittedBy(new Submitter.Builder(UUID.fromString(subSet.getString(1)))
                                                    .setType(castToSubmittterType(subSet.getString(2)))
                                                    .setContactInfo(subSet.getString(3))
                                                    .build())
                                            .setDescription(inquirySet.getString(7))
                                            .setIntentIsClear(inquirySet.getBoolean(8))
                                            .setCitizenAwareOfInquiry(inquirySet.getBoolean(9))
                                            .setCitizenInformedOfRights(inquirySet.getBoolean(10))
                                            .setCitizenInformedOfDataReservation(inquirySet.getBoolean(11))
                                            .setAgreementOfProgress(inquirySet.getString(12))
                                            .setConsentType(castToConsentType(inquirySet.getString(13)))
                                            .setSpecialConditions(inquirySet.getString(14))
                                            .setActingMunicipality(inquirySet.getString(15))
                                            .setPayingMunicipality(inquirySet.getString(16))
                                            .setIsRelevantToGatherConsent(inquirySet.getBoolean(17))
                                            .addGatheredConsents(new ArrayList<GatheredConsent>() {{
                                                while (consentSet.next()) {//This is where next is called on consentset.
                                                    add(new GatheredConsent(
                                                            castToConsentEntity(consentSet.getString(2)),
                                                            consentSet.getString(3),
                                                            UUID.fromString(consentSet.getString(1))));
                                                }
                                            }})
                                            .build());
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return userInquires;
    }

    @Override
    public void create(Inquiry inquiry) {
        String cprStm = "Select CPR from citizen where cpr=?";
        String submitterStm = "Select ID from submitter where id=?";

        try (Connection conn = startConnection();
             PreparedStatement cprPre = conn.prepareStatement(cprStm);
             PreparedStatement submitterPre = conn.prepareStatement(submitterStm)) {

            cprPre.setString(1, inquiry.getCitizen().getCpr());
            submitterPre.setString(1, inquiry.getSubmittedBy().getId().toString());

            ResultSet cizExists = executeStm(cprPre).getData();
            if (inquiry.getCitizen() != null && (cizExists == null || !cizExists.next())) {
                createCitizen(inquiry.getCitizen());
            }
            ResultSet subExists = executeStm(submitterPre).getData();
            if (inquiry.getSubmittedBy() != null && (subExists == null || !subExists.next())) {
                createSubmitter(inquiry.getSubmittedBy());
            }

            String inquiryString = "INSERT INTO Inquiry VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement inquiryPre = conn.prepareStatement(inquiryString);
            inquiryPre.setString(1, inquiry.getId().toString());
            inquiryPre.setBoolean(2, inquiry.isDraft());
            inquiryPre.setBoolean(3, inquiry.isSupportsVUM());
            if (inquiry.getCreatedBy() != null) {
                inquiryPre.setString(4, inquiry.getCreatedBy().getID().toString());
            } else {
                inquiryPre.setString(4, null);
            }
            if (inquiry.getCitizen() != null) {
                inquiryPre.setString(5, inquiry.getCitizen().getCpr());
            } else {
                inquiryPre.setString(5, null);
            }
            if (inquiry.getSubmittedBy() != null) {
                inquiryPre.setString(6, inquiry.getSubmittedBy().getId().toString());
            } else {
                inquiryPre.setString(6, null);
            }

            inquiryPre.setString(7, inquiry.getDescription());
            inquiryPre.setBoolean(8, inquiry.isIntentIsClear());
            inquiryPre.setBoolean(9, inquiry.isCitizenAwareOfInquiry());
            inquiryPre.setBoolean(10, inquiry.isCitizenInformedOfRights());
            inquiryPre.setBoolean(11, inquiry.isCitizenInformedOfDataReservation());
            inquiryPre.setString(12, inquiry.getAgreementOfProgress());
            if (inquiry.getConsentType() != null)
                inquiryPre.setString(13, inquiry.getConsentType().toString());
            else
                inquiryPre.setString(13, null);
            inquiryPre.setString(14, inquiry.getSpecialConditions());
            inquiryPre.setString(15, inquiry.getActingMunicipality());
            inquiryPre.setString(16, inquiry.getPayingMunicipality());
            inquiryPre.setBoolean(17, inquiry.getIsRelevantToGatherConsent());


            if (executeUpdate(inquiryPre).equals(ResponseCode.SUCCESS)) {
                System.out.println("Mega godt");
            } else {
                System.out.println("Knap så godt");
            }
            if (inquiry.getIsRelevantToGatherConsent()) {
                gatherConsent(inquiry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void createSubmitter(Submitter submittedBy) {
        String subStm = "INSERT INTO submitter VALUES(?,?,?);";
        try (Connection conn = startConnection(); PreparedStatement statement = conn.prepareStatement(subStm)) {
            statement.setString(1, submittedBy.getId().toString());
            statement.setString(2, submittedBy.getType().toString());
            statement.setString(3, submittedBy.getContactInfo());
            executeUpdate(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createCitizen(Citizen citizen) {
        String checkStm = "Select ID from representative where id=?;";
        String citizenStm = "INSERT INTO citizen VALUES(?,?,?,?,?,?);";
        try (Connection conn = startConnection();
             PreparedStatement checkPre = conn.prepareStatement(checkStm);
             PreparedStatement citizenPre = conn.prepareStatement(citizenStm)) {

            if (citizen.getRepresentative() != null) {
                checkPre.setString(1, citizen.getRepresentative().getId().toString());
                ResultSet set = executeStm(checkPre).getData();
                if (set == null || !set.next())
                    createRepresentative(citizen.getRepresentative());
            }
            citizenPre.setString(1, citizen.getCpr());
            citizenPre.setString(2, citizen.getName());
            citizenPre.setString(3, citizen.getAddress());
            citizenPre.setString(4, citizen.getEmail());
            citizenPre.setString(5, String.valueOf(citizen.getPhoneNumber()));
            if (citizen.getRepresentative() != null) {
                citizenPre.setString(6, citizen.getRepresentative().getId().toString());
            } else {
                citizenPre.setString(6, null);
            }
            System.out.println("executes citizen update");
            System.out.println(executeUpdate(citizenPre));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void createRepresentative(Representative representative) {
        String stm = "INSERT INTO representative VALUES(?,?,?);";
        try (Connection conn = startConnection(); PreparedStatement statement = conn.prepareStatement(stm)) {
            statement.setString(1, representative.getId().toString());
            statement.setString(2, representative.getContactInfo());
            statement.setString(3, representative.getType().toString());
            executeUpdate(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void gatherConsent(Inquiry inquiry) {

        String conStm = "Insert into gatheredConsent VALUES(?,?,?);";
        String relStm = "insert into consentForInquiry values(?,?);";
        List<GatheredConsent> consentList = inquiry.getGatheredConsents();

        try (Connection conn = startConnection();
             PreparedStatement conPre = conn.prepareStatement(conStm);
             PreparedStatement relPre = conn.prepareStatement(relStm)) {
            for (GatheredConsent consent : consentList) {
                conPre.setString(1, consent.getId().toString());
                conPre.setString(2, consent.getConsentEntity().toString());
                conPre.setString(3, consent.getContactInfo());

                relPre.setString(1, inquiry.getId().toString());
                relPre.setString(2, consent.getId().toString());
                executeUpdate(conPre, relPre);
                conPre.clearParameters();
                relPre.clearParameters();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Inquiry inquiry) {

    }

    @Override
    public void delete(Inquiry inquiry) {

    }

    @Override
    public void updateInquiry(Inquiry inquiry) {
        String cprStm = "Select CPR from citizen where cpr=?";
        String submitterStm = "Select ID from submitter where id=?";

        try (Connection conn = startConnection();
             PreparedStatement cprPre = conn.prepareStatement(cprStm);
             PreparedStatement submitterPre = conn.prepareStatement(submitterStm)) {

            cprPre.setString(1, inquiry.getCitizen().getCpr());
            submitterPre.setString(1, inquiry.getSubmittedBy().getId().toString());

            ResultSet cizExists = executeStm(cprPre).getData();
            if (inquiry.getCitizen() != null && (cizExists == null || !cizExists.next())) {
                updateCitizen(inquiry.getCitizen());
            }
            ResultSet subExists = executeStm(submitterPre).getData();
            if (inquiry.getSubmittedBy() != null && (subExists == null || !subExists.next())) {
                updateSubmitter(inquiry.getSubmittedBy());
            }

            String inquiryString = "UPDATE Inquiry set isdraft = ?, supportvum = ?, createdby = ?," +
                    " conserning = ?, descriptionofinquiry = ?, intentisclear = ?," +
                    " citizenawareofinquiry = ?,citizeninformedofrights = ?, citizeninformedofdatareservation = ?," +
                    " agreementofprogress = ?, consenttype = ?, specialconditions = ?, actingmunicipality = ?," +
                    " payingmunicipality = ?, isconsentrelevant = ? " +
                    "where id = ?;";
            PreparedStatement inquiryPre = conn.prepareStatement(inquiryString);
            inquiryPre.setBoolean(1, inquiry.isDraft());
            inquiryPre.setBoolean(2, inquiry.isSupportsVUM());
            if (inquiry.getCreatedBy() != null) {
                inquiryPre.setString(3, inquiry.getCreatedBy().getID().toString());
            } else {
                inquiryPre.setString(3, null);
            }
            if (inquiry.getCitizen() != null) {
                inquiryPre.setString(4, inquiry.getCitizen().getCpr());
            } else {
                inquiryPre.setString(4, null);
            }


            inquiryPre.setString(5, inquiry.getDescription());
            inquiryPre.setBoolean(6, inquiry.isIntentIsClear());
            inquiryPre.setBoolean(7, inquiry.isCitizenAwareOfInquiry());
            inquiryPre.setBoolean(8, inquiry.isCitizenInformedOfRights());
            inquiryPre.setBoolean(9, inquiry.isCitizenInformedOfDataReservation());
            inquiryPre.setString(10, inquiry.getAgreementOfProgress());
            if (inquiry.getConsentType() != null)
                inquiryPre.setString(11, inquiry.getConsentType().toString());
            else
                inquiryPre.setString(11, null);
            inquiryPre.setString(12, inquiry.getSpecialConditions());
            inquiryPre.setString(13, inquiry.getActingMunicipality());
            inquiryPre.setString(14, inquiry.getPayingMunicipality());
            inquiryPre.setBoolean(15, inquiry.getIsRelevantToGatherConsent());
            inquiryPre.setString(16, inquiry.getId().toString());


            if (executeUpdate(inquiryPre).equals(ResponseCode.SUCCESS)) {
                System.out.println("Mega godt");
            } else {
                System.out.println("Knap så godt");
            }
            if (inquiry.getIsRelevantToGatherConsent()) {
                updateConsent(inquiry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void updateConsent(Inquiry inquiry) {
        String conStm = "update gatheredConsent set consententity = ?,contactinfo = ? where id = ?;";

        List<GatheredConsent> consentList = inquiry.getGatheredConsents();

        try (Connection conn = startConnection();
             PreparedStatement conPre = conn.prepareStatement(conStm)){

            for (GatheredConsent consent : consentList) {
                conPre.setString(1, consent.getConsentEntity().toString());
                conPre.setString(2, consent.getContactInfo());
                conPre.setString(3, consent.getId().toString());

                executeUpdate(conPre);
                conPre.clearParameters();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateCitizen(Citizen citizen) {
        String checkStm = "Select ID from representative where id=?;";
        String citizenStm = "update citizen set name = ?, address = ?, email = ?, phonenumber = ? where cpr = ?;";
        try (Connection conn = startConnection();
             PreparedStatement checkPre = conn.prepareStatement(checkStm);
             PreparedStatement citizenPre = conn.prepareStatement(citizenStm)) {

            if (citizen.getRepresentative() != null) {
                checkPre.setString(1, citizen.getRepresentative().getId().toString());
                ResultSet set = executeStm(checkPre).getData();
                if (set == null || !set.next())
                    updateRepresentative(citizen.getRepresentative());
            }
            citizenPre.setString(1, citizen.getCpr());
            citizenPre.setString(1, citizen.getName());
            citizenPre.setString(2, citizen.getAddress());
            citizenPre.setString(3, citizen.getEmail());
            citizenPre.setString(4, String.valueOf(citizen.getPhoneNumber()));

            System.out.println("executes citizen update");
            System.out.println(executeUpdate(citizenPre));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void updateRepresentative(Representative representative) {
        String stm = "update representative set contactinfo = ?, type = ? where id = ?;";
        try (Connection conn = startConnection(); PreparedStatement statement = conn.prepareStatement(stm)) {
            statement.setString(1, representative.getContactInfo());
            statement.setString(2, representative.getType().toString());
            statement.setString(3, representative.getId().toString());
            executeUpdate(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateSubmitter(Submitter submitter) {
        String subStm = "update submitter set submittertype = ?, contactinfo =? where id = ?;";
        try (Connection conn = startConnection(); PreparedStatement statement = conn.prepareStatement(subStm)) {
            statement.setString(1, submitter.getType().toString());
            statement.setString(2, submitter.getContactInfo());
            statement.setString(3, submitter.getId().toString());
            executeUpdate(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private TypeOfRepresentative castToRepresentativeType(String input) {


        switch (input) {
            case "Værge":
                return TypeOfRepresentative.LEGAL_GUARDIAN;
            case "Fuldmagt":
                return TypeOfRepresentative.POWER_OF_ATTORNEY;
            case "Partsrepræsentant":
                return TypeOfRepresentative.PART_REPRESENTATIVE;
        }

        return null;
    }

    private SubmitterType castToSubmittterType(String input) {
        switch (input) {
            case "Igangværende indsats":
                return SubmitterType.ONGOING_EFFORT;
            case "Andre kommuner":
                return SubmitterType.OTER_MUNICIPALITY;
            case "Andre":
                return SubmitterType.MISCELLANEOUS;
            case "Borger":
                return SubmitterType.CITIZIN;
            case "Pårørende":
                return SubmitterType.RELATIVE;
            case "Læge":
                return SubmitterType.DOCTOR;
            case "Hospital":
                return SubmitterType.HOSPITAL;
            case "Anden management":
                return SubmitterType.OTHER_MANAGEMENT;
        }
        return null;
    }

    private ConsentType castToConsentType(String input) {
        if (input == null)
            return null;
        switch (input) {
            case "Verbalt":
                return ConsentType.VERBAL;
            case "Skrevent":
                return ConsentType.WRITTEN;
        }

        return null;
    }

    private ConsentEntity castToConsentEntity(String input) {
        switch (input) {
            case "Personlig Læge":
                return ConsentEntity.PERSONAL_DOCTOR;
            case "Speciallæge":
                return ConsentEntity.SPECIAL_DOCTER;
            case "Hospital":
                return ConsentEntity.HOSPITAL;
            case "A-Kasse":
                return ConsentEntity.UNEMPLOYMENT_FUND;
            case "Tilbud":
                return ConsentEntity.OFFER;
            case "Arbejdsgiver":
                return ConsentEntity.EMPLOYER;
            case "Tidligere Kommune":
                return ConsentEntity.PREVIOUS_MUNICIPALITY;
            case "Anden forvaltning":
                return ConsentEntity.OTHER_MANAGEMENT;
            case "Andre":
                return ConsentEntity.OTHER;
        }
        return null;
    }

}
