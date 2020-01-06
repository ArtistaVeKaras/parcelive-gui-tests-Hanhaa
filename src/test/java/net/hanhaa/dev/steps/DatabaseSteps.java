package net.hanhaa.dev.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import net.hanhaa.dev.database.DatabaseConnector;
import net.hanhaa.dev.database.DatabaseOperations;
import net.hanhaa.dev.pages.BasePage;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DatabaseSteps {

    @Autowired
    BasePage basePage;

    private DatabaseOperations db = new DatabaseOperations();

    @Given("^Delete \"([^\"]*)\" distributor$")
    public void deleteDistributor(String distributorName) throws Throwable {
        List<Long> companyId = db.getIdFromCompanyByCompanyName(distributorName);
        if (companyId != null) {
            for (int i = 0; i < companyId.size(); i++) {
                db.deleteFromCompanyTidByDistributorId(companyId.get(i));
                db.updatePrevCompanyIdFromDeviceByCustomerId(db.selectCustomerIdsFromCompanyByCompanyId(companyId.get(i)));
                db.deleteFromCustomerOrderShipmentItemByCustomerOrderShipmentId(db.selectIdsFromCustomerOrderShipmentByCustomerID(
                        db.selectCustomerIdsFromCompanyByCompanyId(companyId.get(i))
                ));
                db.deleteFromRemindByCustomerID(db.selectCustomerIdsFromCompanyByCompanyId(companyId.get(i)));
                db.deleteFromCustomerOrderShipmentByCustomerID(db.selectCustomerIdsFromCompanyByCompanyId(companyId.get(i)));
                db.deleteFromCompanyAddressInfoByCustomerId(db.selectCustomerIdsFromCompanyByCompanyId(companyId.get(i)));
                db.deleteFromCompanyContactByCustomerId(db.selectCustomerIdsFromCompanyByCompanyId(companyId.get(i)));
                db.deleteFromCompanyAddressByCustomerId(db.selectCustomerIdsFromCompanyByCompanyId(companyId.get(i)));
                db.deleteFromPricingPlanDayCostByPricingPlanId(companyId.get(i));
                db.deleteFromPricingPlanByCompanyId(companyId.get(i));
                db.deleteFromUserCodeByUserId(db.selectIdFromUserByCompanyId(companyId.get(i)));
                db.deleteFromUserRoleByUserId(db.selectIdFromUserByCompanyId(companyId.get(i)));
                db.deleteFromCustomerOrderByCustomerId(companyId.get(i));
//                db.deleteFromUserByCompanyId(companyId.get(i));
                db.deleteFromCompanyBillingEventByCompanyId(companyId.get(i));
                db.deleteFromOauthClientDetailsByCompanyId(db.selectCustomerIdsFromCompanyByCompanyId(companyId.get(i)));
                db.deleteFromUserCodeByUserId(db.selectIdFromUserByCompanyId(db.selectCustomerIdsFromCompanyByCompanyId(companyId.get(i))));
                db.deleteFromUserRoleByUserId(db.selectIdFromUserByCompanyId(db.selectCustomerIdsFromCompanyByCompanyId(companyId.get(i))));
                db.deleteFromUserByCompanyId(db.selectCustomerIdsFromCompanyByCompanyId(companyId.get(i)));
                db.deleteFromUserByCompanyId(companyId.get(i));
                db.deleteFromTrackByDistributorId(companyId.get(i));
                db.deleteFromTrackIdByCompanyId(db.selectCustomerIdsFromCompanyByCompanyId(companyId.get(i)));
                db.deleteFromDeviceCompanyHistoryByCompanyId(db.selectCustomerIdsFromCompanyByCompanyId(companyId.get(i)));
                db.deleteFromCompanyByDistributorId(companyId.get(i));
                db.deleteFromCompanyById(companyId.get(i));
                // no foreign key for this table
                db.deleteFromCompanyContractByCompanyIdNotInCompany();
            }
        }
    }

    DatabaseConnector databaseConnector = new DatabaseConnector();
    private Statement connection = databaseConnector.createConnection();
    @Given("^Delete \"([^\"]*)\" distributor2$")
    public void deleteDistributor2(String distributorName) throws Throwable {
        String query = "select id from company where company_name = '" + distributorName + "'";
        ResultSet rs = connection.executeQuery(query);
        rs.next();
        System.out.println("666 rs = " + rs.getLong("id"));
        Long companyId = Long.valueOf(123);
        if (companyId != null) {

        }
    }

    @Given("^Distributor \"([^\"]*)\" exists$")
    public void distributorExists(String company) throws Throwable {
        db.checkDistributorExistsInDB(company);
    }

    @Given("^Customer \"([^\"]*)\" exists$")
    public void customerExists(String customer) throws Throwable {
        db.checkCustomerExistsInDB(customer);
    }

    @Given("^Account Manager \"([^\"]*)\" exists$")
    public void acountManagerExists(String manager) throws Throwable {
        db.checkManagerExistsInDB(manager);
    }

    @Given("^Confirmed leasing contract for \"([^\"]*)\" exists$")
    public void confirmedLeasingContractForExists(String distributorName) throws Throwable {
        db.checkConfirmedLeasingContractExistsForDistributor(distributorName);
    }

    @Given("^Customer order for \"([^\"]*)\" exists$")
    public void customerOrderForExists(String distributorName) throws Throwable {
        db.checkCustomerOrderExistsForDistributor(distributorName);
    }

    @Given("^Address for \"([^\"]*)\" customer exists$")
    public void addressForCustomerExists(String customerName) throws Throwable {
        db.checkAddressForCustomerExists(customerName);
    }

    @Given("^Contact for \"([^\"]*)\" customer exists$")
    public void contactForCustomerExists(String customerName) throws Throwable {
        db.checkContactForCustomerExists(customerName);
    }

    @Given("^Shipment for \"([^\"]*)\" exists with require state$")
    public void shipmentForExistsWithRequireState(String customerName) throws Throwable {
        db.checkShipmentForCustomerWithRequireStateExists(db.getIdFromCompanyByCompanyName(customerName));
    }

    @Given("^Delete \"([^\"]*)\" device$")
    public void deleteDevice(String serialNumber) throws Throwable {
        try {
            long deviceId = db.selectIdFromDeviceBySerialNumber(serialNumber);
            db.deleteFromCompanyTidByTids(db.selectIdsFromTrackIdByDeviceId(deviceId));
            db.deleteFromTrackIdByDeviceId(deviceId);
            db.deleteFromTrackByDeviceId(deviceId);
            db.deleteFromDeviceStatusHistoryByDeviceId(deviceId);
            db.deleteFromDeviceCompanyHistoryByDeviceId(deviceId);
            db.deleteFromCustomerOrderShipmentItemByDeviceId(deviceId);
            db.deleteFromProDuctionOrderDeviceByDeviceId(deviceId);
            db.deleteFromDeviceById(deviceId);
        } catch (SQLDataException e) {
            e.printStackTrace();
        }
    }

    @Given("^Order with \"([^\"]*)\" number exists$")
    public void orderWithNumberExists(String orderNumber) throws Throwable {
        db.checkOrderNumberForCustomerExists(orderNumber);
    }
}
