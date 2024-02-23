package com.example.Crud.Service;

import com.example.Crud.Entity.Employee;
import com.example.Crud.Entity.Projects;

import java.util.List;

public interface EmployeeService {
    int addEmployee(Employee employee);
    String RemoveEmployee(int eid);
    String UpdateEmployee(Employee e);
    Employee SearchEmployee(int eid);
    Employee SingleEmployee(int eid);
    List<Employee> DisplayEmployess();
    String saveProjects(Projects projects,int cartId);
    List<Projects> displayProjects(int eid);

    public boolean checking(Employee employee);
    String RemoveProject(int pid);
    Projects SingleProject1(int projectId);
    String updateProject(Projects projects);
    String saveProjectEmployee(Employee employee,int ProjectId);
    List<Projects> viewAllProjects();
    List<Employee>displayEmployee(int projectId);
    String SaveProjectsfree(Projects projects);


}
