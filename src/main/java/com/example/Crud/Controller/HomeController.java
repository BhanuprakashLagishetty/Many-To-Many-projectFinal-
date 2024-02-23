package com.example.Crud.Controller;

import com.example.Crud.Entity.Projects;
import com.example.Crud.Service.EmployeeServiceIMP;
import com.example.Crud.Entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private EmployeeServiceIMP service;

    public int EmployeeId=0;
    public int ProjectId=0;

    @RequestMapping("/")
    public String home(ModelMap model)
    {
        System.out.println("HOME PAGE IS CALLED");
        System.out.println("HOME PAGE IS CALLED");
        return "index";
    }


    @RequestMapping("/employeereport")
    public String employeereport()
    {
        return "employeereport";
    }


    @RequestMapping("/GenerateEmployee")
    public String GenerateEmployee(String ename, String edesignation, String esalary, Model model)
    {
        Employee employee=new Employee();
        employee.setEname(ename);
        employee.setEdesignation(edesignation);
        employee.setEsalary(esalary);
        System.out.println("IAMD BAHNU"+employee);
        if(service.checking(employee))
        {
            EmployeeId= service.addEmployee(employee);
            model.addAttribute("employee",employee);
            return "redirect:/viewEmployees";
        }
        else{
            String res="Please enter all fields";
            model.addAttribute("res",res);
            return "updateResult";
        }
    }

    //ADDING PROJECTS
    @RequestMapping("/AddProjects")
    public String AddProjects()
    {
        return "AddProjects";
    }
    @RequestMapping("/SaveProjects")
    public String SaveProjects(Projects projects,Model model)
    {
         service.saveProjects(projects,EmployeeId);
        Employee e2=service.SingleEmployee(EmployeeId);
        model.addAttribute("employee",e2);

        System.out.println("Attribute is called");

        System.out.println(e2.getEname());


        return "redirect:/DisplayProjects?eid="+EmployeeId;
    }


    @RequestMapping("/viewEmployees")
    public String viewEmployees(Model model)
    {
        List<Employee> a=service.DisplayEmployess();
        model.addAttribute("allEmployee",a);
        return "viewEmployees";
    }
    @RequestMapping("/viewAllProjects")
    public String viewAllProjects(Model model)
    {
        List<Projects> a=service.viewAllProjects();
        model.addAttribute("allProjects",a);
        return "viewAllProjects";
    }

    @RequestMapping("/ViewParticularEmployees")
    public String ViewParticularEmployees(int projectId,Model model)
    {
        ProjectId=projectId;
        List<Employee> e=service.displayEmployee(projectId);
        model.addAttribute("allEmployee",e);
        return "projectEmployeesView";


    }

    @RequestMapping("/AddingEmployeeProject")
    public String AddingEmployeeProject()
    {
        return "AddingEmployeeProject";
    }



    @RequestMapping("/DisplayProjects")
    public String DisplayProjects(int eid,Model model)
    {
        EmployeeId =eid;
        List<Projects> p=service.displayProjects(eid);
        model.addAttribute("projects",p);
        return "viewProjects";
    }



    @RequestMapping("/removeemployee1")
    public String removeemployee1()
    {
        return "removeemployee";
    }

    @RequestMapping("/removeemployee")
    public String removeemployee( int eid,Model model)
    {

        String res=service.RemoveEmployee(eid);
        model.addAttribute("result",res);

         return "redirect:/viewEmployees?reload=true";
    }


    @RequestMapping("/removeProject")
    public String removeProject( int projectId,Model model)
    {

        String res=service.RemoveProject(projectId);
        model.addAttribute("result",res);

        return "redirect:/DisplayProjects?eid="+EmployeeId;
    }
    @RequestMapping("/removeProjectfree")
    public String removeProjectfree(int projectId,Model model)
    {
        String res=service.RemoveProject(projectId);
        model.addAttribute("result",res);

        return "redirect:/viewAllProjects";
    }


    //update employee
    @RequestMapping("/updateEmployee")
    public String updateEmployee(int eid,Model model)
    {
        System.out.println("update method is called");
        if(service.SearchEmployee(eid)!=null)
        {
            model.addAttribute("singleEmployee",service.SingleEmployee(eid));
            return "updateEmployee";
        }
        else{
            String res="Employee not found";
            model.addAttribute("res",res);
            return "updateResult";
        }

    }

    //updateProject
    @RequestMapping("/updateProject")
    public String updateProject(int projectId,Model model)
    {
        System.out.println("update method is called");
            model.addAttribute("singleEmployee",service.SingleProject1(projectId));
            return "updateProject";
    }
    @RequestMapping("/SuccesfullProjectUpdate")
    public String SuccesfullProjectUpdate (Projects projects,Model model){
        String res=service.updateProject(projects);
        model.addAttribute("res",res);
        return "redirect:/DisplayProjects?eid="+EmployeeId;
    }

    @RequestMapping("/SuccesfullUpdate")
    public String SuccesfullUpdate(Employee employee,Model model)
    {
        String res=service.UpdateEmployee(employee);
        model.addAttribute("res",res);
        return "redirect:/viewEmployees?reload=true";

    }

   //searching employee
    @RequestMapping("/Singleemployee")
    public String Singleemployee()
    {
        System.out.println("Bhanuprakash");
        return "SearchEmployee";
    }


    @RequestMapping("/SearchEmployee")
    public String SearchEmployee( int eid,Model model)
    {
        System.out.println("METHOD CALLED");
        System.out.println(eid);
        Employee e2=service.SingleEmployee(eid);
        EmployeeId=e2.getEid();
        System.out.println(e2.getEname());
        model.addAttribute("employee",e2);
        return "singleEmployee";
    }

    //adding employee
    @RequestMapping("/AddEmployee")
    public String AddEmployee(int projectId)
    {
        System.out.println("Before acalle dproject id is"+projectId);
        ProjectId=projectId;
        return "AddingEmployeeProject";
    }

    @RequestMapping("/GenerateProjectEmplyee")
    public String GenerateProjectEmplyee(Employee employee)
    {
        service.saveProjectEmployee(employee,ProjectId);
        return "redirect:viewEmployees?reload=true";
    }
    @RequestMapping("/AddProjectsfree")
    public String AddProjectsfree()
    {
        return "AddProjectsfree";
    }

    @RequestMapping("/SaveProjectsfree")
    public String SaveProjectsfree(Projects projects,Model model)
    {
        service.SaveProjectsfree(projects);
        System.out.println("Bhanuprakash");
        return "redirect:/viewAllProjects";

    }

}