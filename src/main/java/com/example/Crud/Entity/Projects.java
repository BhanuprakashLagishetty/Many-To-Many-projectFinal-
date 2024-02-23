package com.example.Crud.Entity;

import com.example.Crud.Model.EmployeeModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Projects {

    @Id
    @GeneratedValue
        private int projectId;
    private String ProjectName;
    private String submitionDate;

//@ManyToMany
//@JoinTable(
//        name = "employee_project",
//        joinColumns = @JoinColumn(name = "project_id"),
//        inverseJoinColumns = @JoinColumn(name = "employee_id")
//)
@ManyToMany(mappedBy = "projects")
    private List<Employee> employees=new ArrayList<>();
}
