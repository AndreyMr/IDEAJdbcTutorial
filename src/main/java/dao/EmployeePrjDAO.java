package dao;

import entity.EmployeePRJ;

import java.util.List;

public interface EmployeePrjDAO {

    //create
    void add(EmployeePRJ employeePRJ);

    //read
    List<EmployeePRJ> getAll();
    EmployeePRJ getById(long employeeId, long projectId);

    //update
    void update(EmployeePRJ employeePRJ);

    //remove
    void remove (EmployeePRJ employeePRJ);


}
