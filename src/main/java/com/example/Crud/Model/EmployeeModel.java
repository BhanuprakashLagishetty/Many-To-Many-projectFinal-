package com.example.Crud.Model;

import com.example.Crud.Entity.Projects;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {






    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Employee {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int eid;

        private String ename;

        private String edesignation;

        private  String esalary;
        //    @OneToMany(mappedBy = "employee" ,cascade = CascadeType.ALL)
        private List<Projects> projects= new ArrayList<>();



    }

}
