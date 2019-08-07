package dao.daointerfaces;

import entity.Address;

import java.sql.SQLException;
import java.util.List;

public interface AddressDAO {
    // create
    void add(Address address) throws SQLException;

    //read
    List<Address> getAll() throws SQLException;

    Address getById(long id) throws SQLException;

    //update
    void update(Address address) throws SQLException;

    //remove
    void remove(Address address);

}
