package dao;

import entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    //create
    void add (Employee employee);

    //read
    List<Employee> getAll();
    Employee getbyId(long id);

    //update
    void update(Employee employee);

    //remove

    void remove(Employee employee);
}
