package dao.daointerfaceimpl;

import dao.daointerfaces.AddressDAO;
import db.DBH2ConnectionService;
import entity.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressDAOImpl implements AddressDAO {
    private Connection connection;
    private Logger logger = LoggerFactory.getLogger(AddressDAOImpl.class);

    @Override
    public void add(Address address){
        connection = DBH2ConnectionService.getConnection();
        PreparedStatement statement = null;
        String sqlQuery = "INSERT INTO PUBLIC.ADDRESS (ID,COUNTRY,CITY,STREET,POST_CODE) VALUES (?,?,?,?,?);";

        try {
            statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, address.getId());
            statement.setString(2, address.getCountry());
            statement.setString(3, address.getCity());
            statement.setString(4, address.getStreet());
            statement.setLong(5, address.getPostCode());
            int rowAddCount = statement.executeUpdate();
            logger.info("Добавлена строка: " + address + " " + rowAddCount);
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        }finally {
            DBH2ConnectionService.connClose(statement, connection);
        }

    }

    /*ID,COUNTRY,CITY,STREET,POST_CODE*/
    @Override
    public List<Address> getAll()  {
        connection = DBH2ConnectionService.getConnection();
        List<Address> addressList = new ArrayList<>();
        PreparedStatement statement = null;
        String sqlQuery = "SELECT * FROM PUBLIC.ADDRESS";

        try {
            statement = connection.prepareStatement(sqlQuery);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()){
                Address address = new Address();
                address.setId(resultSet.getLong("ID"));
                address.setCountry(resultSet.getString("COUNTRY"));
                address.setCity(resultSet.getString("CITY"));
                address.setStreet(resultSet.getString("STREET"));
                address.setPostCode(resultSet.getLong("POST_CODE"));
                logger.info("Получен адрес: " + address);
                addressList.add(address);
            }

        } catch (SQLException e) {
            logger.error(e.getMessage(),e);

        }finally {
            DBH2ConnectionService.connClose(statement, connection);
        }
        return addressList;
    }

    @Override
    public Address getById(long id)  {
        connection = DBH2ConnectionService.getConnection();
        PreparedStatement statement = null;
        String sqlQuery = "SELECT * FROM PUBLIC.ADDRESS WHERE ID = ?";
        Address address = null;

        try {
            statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1,id);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
                address = new Address();
                address.setId(resultSet.getLong("ID"));
                address.setCountry(resultSet.getString("COUNTRY"));
                address.setCity(resultSet.getString("CITY"));
                address.setStreet(resultSet.getString("STREET"));
                address.setPostCode(resultSet.getLong("POST_CODE"));
                logger.info("Получен адрес: " + address);
            }

        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        }finally {
            DBH2ConnectionService.connClose(statement, connection);
        }
        if (address == null) logger.error("По введеному ID адреса в базе не существует");

        return address;
    }

    @Override
    public void update(Address address){
        //проверяем на наличие адреса в базе по ID. Если aдрес по текущему ID есть то изменяем
        if (getById(address.getId()) != null){
            connection = DBH2ConnectionService.getConnection();
            PreparedStatement statement = null;
            String sqlQuery = "UPDATE PUBLIC.ADDRESS SET COUNTRY=?, CITY=?, STREET=?, POST_CODE=? WHERE ID=?";
            try {
                statement = connection.prepareStatement(sqlQuery);

                statement.setString(1,address.getCountry());
                statement.setString(2,address.getCity());
                statement.setString(3,address.getStreet());
                statement.setLong(4, address.getPostCode());
                statement.setLong(5, address.getId());
                statement.executeUpdate();
                logger.info("Обновлена строка с ID: "+address.getId());
            } catch (SQLException e) {
                logger.error(e.getMessage(),e);
            }finally {
                DBH2ConnectionService.connClose(statement, connection);
            }
        //Если записи по текущему ID нет то добавляем новый
        }else {
            add(address);
        }
    }

    @Override
    public void remove(Address address) {
        connection = DBH2ConnectionService.getConnection();
        PreparedStatement statement = null;
        String sqlQuery = "DELETE FROM PUBLIC.ADDRESS WHERE ID=?";
        try {
            statement = connection.prepareStatement(sqlQuery);
            long addressId = address.getId();
            statement.setLong(1, addressId);
            statement.execute();
            logger.info("Удалена строка с ID: " + addressId);
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        }finally {
            DBH2ConnectionService.connClose(statement, connection);
        }
    }
}
