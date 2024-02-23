package com.example.Crud.Service;

import com.example.Crud.Entity.Employee;
import com.example.Crud.Entity.Projects;
import com.example.Crud.Repository.Employee_repo;
import com.example.Crud.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class EmployeeServiceIMP implements EmployeeService{
    @Autowired
    Employee_repo emp;
    @Autowired
    ProjectRepository pro;
    @Override
    public int addEmployee(Employee employee) {
        emp.save(employee);
        return employee.getEid();

    }

    @Override
    public String RemoveEmployee(int eid) {

        Employee e=emp.findById(eid).orElse(null);
        if (e != null) {
            List<Projects> projectsList= e.getProjects();
            for (Projects projectobject : projectsList) {
                List<Employee> EmployeeList = projectobject.getEmployees();
                if(EmployeeList.contains(e))
                {
                    EmployeeList.remove(e);
                    projectobject.setEmployees(EmployeeList);
                    pro.save(projectobject);
                }
            }
            e.setProjects(new ArrayList<>());
            emp.deleteById(eid);
            System.out.println("Deleted successfully");
            return "Deleted successfully";
        }
        return "Unable to find project";
    }





//    }
    public String saveProjects(Projects projects,int EmployeeId)
    {
       Employee employee= emp.findById(EmployeeId).orElse(null);
        List<Projects>p=employee.getProjects();
        List<Employee>e=projects.getEmployees();
        e.add(employee);
        p.add(projects);
        employee.setProjects(p);
        projects.setEmployees(e);
        pro.save(projects);
        emp.save(employee);
        return "succesfully added";

    }

    @Override
    public List<Projects> displayProjects(int eid) {
        Employee employee=emp.findById(eid).orElse(null);
        return employee.getProjects();
    }

    public boolean checking(Employee employee)
    {

        if(employee.getEname().isBlank()|| employee.getEdesignation().isBlank() || employee.getEsalary().isBlank())
        {
            return false;
        }
        else
        {
            System.out.println("METHOD IS CALLED");

        }
        return true;



    }

    @Override
    public String RemoveProject(int pid) {
        Projects projects = pro.findById(pid).orElse(null);
        if (projects != null) {
            List<Employee> employees = projects.getEmployees();
            for (Employee employee : employees) {
                List<Projects> projectsList = employee.getProjects();
               if(projectsList.contains(projects))
                {
                    projectsList.remove(projects);
                    employee.setProjects(projectsList);
                    emp.save(employee);
                }
            }
            projects.setEmployees(new ArrayList<>());
            pro.deleteById(pid);
            System.out.println("Deleted successfully");
            return "Deleted successfully";
        }
        return "Unable to find project";
    }


    @Override
    public Projects SingleProject1(int projectId) {
        return pro.findById(projectId).orElse(null);

    }

    @Override
    public String updateProject(Projects projects) {
        Projects p= pro.findById(projects.getProjectId()).orElse(null);
        p.setProjectId(projects.getProjectId());
        p.setProjectName(projects.getProjectName());
        p.setSubmitionDate(projects.getSubmitionDate());


        pro.save(p);
        return "Successfully Updated";

    }

    @Override
    public String saveProjectEmployee(Employee employee, int ProjectId) {

        Projects projects=pro.findById(ProjectId).orElse(null);
        System.out.println("Another method is called");
        System.out.println("Project name is"+projects.getProjectId());
        List<Employee>e=projects.getEmployees();
        e.add(employee);
        projects.setEmployees(e);
        List<Projects>p=employee.getProjects();
        p.add(projects);
        employee.setProjects(p);
        pro.save(projects);
        emp.save(employee);


        return "Succesfully added project";

    }

    @Override
    public List<Projects> viewAllProjects() {
       return pro.findAll();
    }

    @Override
    public List<Employee> displayEmployee(int projectId) {
        Projects  projects=pro.findById(projectId).orElse(null);
        return projects.getEmployees();
    }

    @Override
    public String SaveProjectsfree(Projects projects) {
        pro.save(projects);
        return "succesfully saved";
    }


    @Override
    public Employee SearchEmployee(int eid) {
       return emp.findById(eid).orElse(null);

    }
    public Employee SingleEmployee(int eid)
    {
        return emp.findById(eid).orElse(null);
    }

    @Override
    public List<Employee> DisplayEmployess() {

        return emp.findAll();
    }
    public String UpdateEmployee(Employee e) {

        Employee employee= emp.findById(e.getEid()).orElse(null);
        employee.setEid(e.getEid());
        employee.setEname(e.getEname());
        employee.setEdesignation(e.getEdesignation());
        employee.setEsalary(e.getEsalary());

        emp.save(employee);
        return "Successfully Updated";

    }



}
