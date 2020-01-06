package net.hanhaa.dev.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DatabaseOperations {


    DatabaseConnector databaseConnector = new DatabaseConnector();
    private Statement connection = databaseConnector.createConnection();
    private static final Logger logger = LoggerFactory.getLogger(DatabaseOperations.class);

    public String selectPasswordByUsername(String username) throws SQLException {
        String query = "select original_password from user where username like" + "'" + username + "'";
        ResultSet rs =  connection.executeQuery(query);
        rs.next();
        try {
            return rs.getString("original_password");
        } catch (SQLException sqlException) {
            logger.info("Password could not be found for user named: " + username);
            return null;
        }
    }

    public long checkDistributorExistsInDB(String company) throws SQLException {
        String query = "select * from company where company_name = " + "'" + company + "'" + " and type = 'DISTRIBUTOR'";
        ResultSet rs =  connection.executeQuery(query);
        rs.next();
        try {
            return rs.getLong("id");
        } catch (SQLException sqlException) {
            logger.info("There is no such distributor: " + company);
            throw new SQLDataException();
        }
    }

    public long checkCustomerExistsInDB(String customer) throws SQLException {
        String query = "select * from company where company_name = " + "'" + customer + "'" + " and type = 'CUSTOMER'";
        ResultSet rs =  connection.executeQuery(query);
        rs.next();
        try {
            return rs.getLong("id");
        } catch (SQLException sqlException) {
            logger.info("There is no such customer: " + customer);
            throw new SQLDataException();
        }
    }

    public long checkManagerExistsInDB(String manager) throws SQLException {
        String query = "select * from user where username = " + "'" + manager + "'";
        ResultSet rs =  connection.executeQuery(query);
        rs.next();
        try {
            return rs.getLong("id");
        } catch (SQLException sqlException) {
            logger.info("There is no such manager: " + manager);
            throw new SQLDataException();
        }
    }

    public List<Long> getIdFromCompanyByCompanyName(String distributorName) throws SQLException {
        String query = "select id from company where company_name = '" + distributorName + "'";
        ResultSet rs = connection.executeQuery(query);
        List<Long> companyIDs = new ArrayList<>();
        while (rs.next()) {
            try {
                companyIDs.add(rs.getLong("id"));
            } catch (SQLException sqlException) {
                logger.info("Could not found company ID using distributor name!");
                return null;
            }
        }
        return companyIDs;
    }

    public void deleteFromPricingPlanDayCostByPricingPlanId(Long companyId) throws SQLException {
        String query = "delete from pricing_plan_day_cost where pricing_plan_id in " +
                "(select id from pricing_plan where company_id =" + companyId + ")";
        connection.executeUpdate(query);
    }

    public void deleteFromPricingPlanByCompanyId(Long companyId) throws SQLException {
        String query = "delete from pricing_plan where company_id =" + companyId;
        connection.executeUpdate(query);
    }

    public List<Long> selectIdFromUserByCompanyId(Long companyId) throws SQLException {
        String query = "select id from user where company_id =" + companyId;
        ResultSet rs = connection.executeQuery(query);
        List<Long> usersIDs = new ArrayList<>();
        while (rs.next()) {
            try {
                usersIDs.add(rs.getLong("id"));
            } catch (SQLException sqlException) {
                logger.info("Could not found userID using the companyID!");
                return null;
            }
        }
        return usersIDs;
    }

    public List<Long> selectIdFromUserByCompanyId(List<Long> companyId) throws SQLException {
        List<Long> usersIDs = new ArrayList<>();
        for (int i = 0; i < companyId.size(); i++) {
            String query = "select id from user where company_id =" + companyId.get(i);
            ResultSet rs = connection.executeQuery(query);
            while (rs.next()) {
                try {
                    usersIDs.add(rs.getLong("id"));
                } catch (SQLException sqlException) {
                    logger.info("Could not found userID using the companyID!");
                    return null;
                }
            }
        }
        return usersIDs;
    }

    public List<Long> selectCustomerIdsFromCompanyByCompanyId(Long companyId) throws SQLException {
        String query = "select id from company where distributor_id =" + companyId;
        ResultSet rs = connection.executeQuery(query);
        List<Long> customersIDs = new ArrayList<>();
        while (rs.next()) {
            try {
                customersIDs.add(rs.getLong("id"));
            } catch (SQLException sqlException) {
                logger.info("Could not found customerID using the distributorID!");
                return null;
            }
        }
        return customersIDs;
    }

    public void deleteFromUserRoleByUserId(List<Long> selectIdFromUserByCompanyId) throws SQLException {
        String query;
        for (int i = 0; i < selectIdFromUserByCompanyId.size(); i++) {
            query = "delete from user_role where user_id = " + selectIdFromUserByCompanyId.get(i);
            connection.executeUpdate(query);
        }
    }

    public void deleteFromUserCodeByUserId(List<Long> companyList) throws SQLException {
        String query;
        for (int i = 0; i < companyList.size(); i++) {
            query = "delete from user_code where user_id = " + companyList.get(i);
            connection.executeUpdate(query);
        }
    }

//    public void deleteFromUserByCompanyId(Long companyId) throws SQLException {
//        String query = "delete from user where company_id =" + companyId;
//        connection.executeUpdate(query);
//    }

    public void deleteFromUserByCompanyId(Long companyId) throws SQLException {
        String query = "delete from user where company_id = " + companyId;
        connection.executeUpdate(query);
    }

    public void deleteFromUserByCompanyId(List<Long> selectCustomerIdsFromCompanyByCompanyId) throws SQLException {
        String query;
        for (int i = 0; i < selectCustomerIdsFromCompanyByCompanyId.size(); i++) {
            query = "delete from user where company_id = " + selectCustomerIdsFromCompanyByCompanyId.get(i);
            connection.executeUpdate(query);
        }
    }

    public void deleteFromCompanyBillingEventByCompanyId(Long companyId) throws SQLException {
        String query = "delete from company_billing_event where company_id =" + companyId;
        connection.executeUpdate(query);
    }

    public void deleteFromCustomerOrderByCustomerId(Long companyId) throws SQLException {
        String query = "delete from customer_order where customer_id in " +
                "(select id from company where distributor_id =" + companyId + ")";
        connection.executeUpdate(query);
    }

    public void deleteFromCompanyByDistributorId(Long companyId) throws SQLException {
        String query = "delete from company where distributor_id =" + companyId;
        connection.executeUpdate(query);
    }

    public void deleteFromCompanyById(Long companyId) throws SQLException {
        String query = "delete from company where id =" + companyId;
        connection.executeUpdate(query);
    }

    public void deleteFromCompanyContractByCompanyIdNotInCompany() throws SQLException {
        String query = "delete from company_contract where company_id not in (select id from company)";
        connection.executeUpdate(query);
    }

    public long checkConfirmedLeasingContractExistsForDistributor(String distributorName) throws SQLException {
        String query = "select id from company_contract where company_id = "
                + getIdFromCompanyByCompanyName(distributorName).get(0) + " and state = 'CONFIRMED'";
        ResultSet rs =  connection.executeQuery(query);
        rs.next();
        try {
            return rs.getLong("id");
        } catch (SQLException sqlException) {
            logger.info("There is no such contract for: " + distributorName);
            throw new SQLDataException();
        }
    }

    public long checkCustomerOrderExistsForDistributor(String distributorName) throws SQLException {
        String query = "select id from customer_order where distributor_id = "
                + getIdFromCompanyByCompanyName(distributorName).get(0);
        ResultSet rs =  connection.executeQuery(query);
        rs.next();
        try {
            return rs.getLong("id");
        } catch (SQLException sqlException) {
            logger.info("There is no customer order for: " + distributorName);
            throw new SQLDataException();
        }
    }

    public void deleteFromCompanyAddressByCustomerId(List<Long> selectCustomerIdFromCompanyByCompanyId) throws SQLException {
        for (int i = 0; i < selectCustomerIdFromCompanyByCompanyId.size(); i++) {
            String query = "delete from company_address where company_id = " + selectCustomerIdFromCompanyByCompanyId.get(i);
            connection.executeUpdate(query);
        }
    }

    public void deleteFromCompanyAddressInfoByCustomerId(List<Long> selectCustomerIdFromCompanyByCompanyId) throws SQLException {
        for (int i = 0; i < selectCustomerIdFromCompanyByCompanyId.size(); i++) {
            String query = "delete from company_address_info where company_id = " + selectCustomerIdFromCompanyByCompanyId.get(i);
            connection.executeUpdate(query);
        }
    }

    public long checkAddressForCustomerExists(String customerName) throws SQLException {
        String query = "select id from company_address where company_id = (select id from company where company_name = '"
                + customerName + "')";
        ResultSet rs =  connection.executeQuery(query);
        rs.next();
        try {
            return rs.getLong("id");
        } catch (SQLException sqlException) {
            logger.info("There is no customer order for: " + customerName);
            throw new SQLDataException();
        }
    }

    public void deleteFromCompanyContactByCustomerId(List<Long> selectCustomerIdFromCompanyByCompanyId) throws SQLException {
        for (int i = 0; i < selectCustomerIdFromCompanyByCompanyId.size(); i++) {
            String query = "delete from company_contact where company_id = " + selectCustomerIdFromCompanyByCompanyId.get(i);
            connection.executeUpdate(query);
        }
    }

    public long checkContactForCustomerExists(String customerName) throws SQLException {
        String query = "select id from company_contact where company_id = (select id from company where company_name = '"
            + customerName + "')";
        ResultSet rs =  connection.executeQuery(query);
        rs.next();
        try {
            return rs.getLong("id");
        } catch (SQLException sqlException) {
            logger.info("There is no customer order for: " + customerName);
            throw new SQLDataException();
        }
    }

    public void deleteFromCustomerOrderShipmentByCustomerID(List<Long> selectCustomerIdFromCompanyByCompanyId) throws SQLException {
        for (int i = 0; i < selectCustomerIdFromCompanyByCompanyId.size(); i++) {
            String query = "delete from customer_order_shipment where customer_id = " + selectCustomerIdFromCompanyByCompanyId.get(i);
            connection.executeUpdate(query);
        }
    }

    public void deleteFromRemindByCustomerID(List<Long> selectCustomerIdFromCompanyByCompanyId) throws SQLException {
        for (int i = 0; i < selectCustomerIdFromCompanyByCompanyId.size(); i++) {
            String query = "delete from remind where company_id = " + selectCustomerIdFromCompanyByCompanyId.get(i);
            connection.executeUpdate(query);
        }
    }

    public Long getDeviceIdBySerialNumber(String deviceSN) throws SQLException {
        String query = "select id from device where serial_number =" + "'" + deviceSN + "'";
        ResultSet rs =  connection.executeQuery(query);
        rs.next();
        try {
            return rs.getLong("id");
        } catch (SQLException sqlException) {
            logger.info("Device id was not found for serial number: " + deviceSN);
            return null;
        }
    }

    public long checkShipmentForCustomerWithRequireStateExists(List<Long> idFromCompanyByCompanyName) throws SQLException {
        String query = "select id from customer_order_shipment where customer_id = " + idFromCompanyByCompanyName.get(0) + " and  state = 'REQUIRE'";
        ResultSet rs =  connection.executeQuery(query);
        rs.next();
        try {
            return rs.getLong("id");
        } catch (SQLException sqlException) {
            logger.info("There is shipment in REQUIRE state for: " + idFromCompanyByCompanyName.get(0));
            throw new SQLDataException();
        }
    }

    public void updatePrevCompanyIdFromDeviceByCustomerId(List<Long> selectCustomerIdFromCompanyByCompanyId) throws SQLException {
        for (int i = 0; i < selectCustomerIdFromCompanyByCompanyId.size(); i++) {
            String query = "update device set prev_company_id = null where prev_company_id = " + selectCustomerIdFromCompanyByCompanyId.get(i);
            connection.executeUpdate(query);
        }
    }

    public long selectIdFromDeviceBySerialNumber(String serialNumber) throws SQLException {
        String query = "select id from device where serial_number = " + "'" + serialNumber + "'";
        ResultSet rs =  connection.executeQuery(query);
        rs.next();
        try {
            return rs.getLong("id");
        } catch (SQLException sqlException) {
            logger.info("Device wasn't found for serial number: " + serialNumber);
            throw new SQLDataException();
        }
    }

    public void deleteFromProDuctionOrderDeviceByDeviceId(long deviceId) throws SQLException {
        String query = "delete from production_order_device where device_id = " + deviceId;
        connection.executeUpdate(query);
    }

    public void deleteFromDeviceById(long deviceId) throws SQLException {
        String query = "delete from device where id = " + deviceId;
        connection.executeUpdate(query);
    }

    public void deleteFromCustomerOrderShipmentItemByDeviceId(long deviceId) throws SQLException {
        String query = "delete from customer_order_shipment_item where device_id =" + deviceId;
        connection.executeUpdate(query);
    }

    public void deleteFromDeviceCompanyHistoryByDeviceId(long deviceId) throws SQLException {
        String query = "delete from device_company_history where device_id = " + deviceId;
        connection.executeUpdate(query);
    }

    public void deleteFromDeviceStatusHistoryByDeviceId(long deviceId) throws SQLException {
        String query = "delete from device_status_history where device_id = " + deviceId;
        connection.executeUpdate(query);
    }

    public void deleteFromTrackByDeviceId(long deviceId) throws SQLException {
        String query = "delete from track where device_id = " + deviceId;
        connection.executeUpdate(query);
    }

    public void deleteFromTrackIdByDeviceId(long deviceId) throws SQLException {
        String query = "delete from track_id where device_id = " + deviceId;
        connection.executeUpdate(query);
    }

    public List<Long> selectIdsFromTrackIdByDeviceId(long deviceId) throws SQLException {
        String query = "select id from track_id where device_id = " + deviceId;
        ResultSet rs = connection.executeQuery(query);
        List<Long> trackIDs = new ArrayList<>();
        while (rs.next()) {
            try {
                trackIDs.add(rs.getLong("id"));
            } catch (SQLException sqlException) {
                logger.info("Could not found TID using device ID!");
                return null;
            }
        }
        return trackIDs;
    }

    public void deleteFromCompanyTidByTids(List<Long> selectIdFromTrackIdByDeviceId) throws SQLException {
        String query;
        for (int i = 0; i < selectIdFromTrackIdByDeviceId.size(); i++) {
            query = "delete from company_tid where tid_id = " + selectIdFromTrackIdByDeviceId.get(i);
            connection.executeUpdate(query);
        }
    }

    public void deleteFromCustomerOrderShipmentItemByCustomerOrderShipmentId(List<Long> idsFromCustomerOrderShipmentByCustomerID) throws SQLException {
        String query;
        for (int i = 0; i < idsFromCustomerOrderShipmentByCustomerID.size(); i++) {
            query = "delete from customer_order_shipment_item where customer_order_shipment_id = " + idsFromCustomerOrderShipmentByCustomerID.get(i);
            connection.executeUpdate(query);
        }
    }

    public List<Long> selectIdsFromCustomerOrderShipmentByCustomerID(List<Long> selectCustomerIdFromCompanyByCompanyId) throws SQLException {
        String query;
        ResultSet rs;
        List<Long> customerOrderShipmentIds = new ArrayList<>();
        for (int i = 0; i < selectCustomerIdFromCompanyByCompanyId.size(); i++) {
            query = "select id from customer_order_shipment where customer_id = " + selectCustomerIdFromCompanyByCompanyId.get(i);
            connection.executeQuery(query);
            rs = connection.executeQuery(query);
            while (rs.next()) {
                try {
                    customerOrderShipmentIds.add(rs.getLong("id"));
                } catch (SQLException sqlException) {
                    logger.info("Could not found customer_order_shipment ID using customer ID!");
                    return null;
                }
            }
        }
        return customerOrderShipmentIds;
    }

    public void deleteFromCompanyTidByDistributorId(Long companyId) throws SQLException {
        String query = "delete from company_tid where distributor_id = " + companyId;
        connection.executeUpdate(query);
    }

    public void deleteFromOauthClientDetailsByCompanyId(List<Long> selectCustomerIdsFromCompanyByCompanyId) throws SQLException {
        String query;
        for (int i = 0; i < selectCustomerIdsFromCompanyByCompanyId.size(); i++) {
            query = "delete from oauth_client_details where company_id = " + selectCustomerIdsFromCompanyByCompanyId.get(i);
            connection.executeUpdate(query);
        }
    }

    public void deleteFromDeviceCompanyHistoryByCompanyId(List<Long> selectCustomerIdsFromCompanyByCompanyId) throws SQLException {
        String query;
        for (int i = 0; i < selectCustomerIdsFromCompanyByCompanyId.size(); i++) {
            query = "delete from device_company_history where company_id = " + selectCustomerIdsFromCompanyByCompanyId.get(i);
            connection.executeUpdate(query);
        }
    }

    public void deleteFromTrackIdByCompanyId(List<Long> selectCustomerIdsFromCompanyByCompanyId) throws SQLException {
        String query;
        for (int i = 0; i < selectCustomerIdsFromCompanyByCompanyId.size(); i++) {
            query = "delete from track_id where company_id = " + selectCustomerIdsFromCompanyByCompanyId.get(i);
            connection.executeUpdate(query);
        }
    }

    public void deleteFromTrackByDistributorId(Long distributorId) throws SQLException {
        String query = "delete from track where tid_id in (select id from parcelive_dev.track_id where distributor_id = " + distributorId + ")";
        connection.executeUpdate(query);
    }

    public long checkOrderNumberForCustomerExists(String orderNumber) throws SQLException {
        String query = "select id from customer_order where order_number = " + orderNumber +
                " and customer_id = (select id from company where company_name = 'BML')";
        ResultSet rs =  connection.executeQuery(query);
        rs.next();
        try {
            return rs.getLong("id");
        } catch (SQLException sqlException) {
            logger.info("There is no order for BML customer with number: " + orderNumber);
            throw new SQLDataException();
        }
    }
}
