package com.example.Crud.Model;

import com.example.Crud.Entity.Employee;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectModel {
    @Id
    @GeneratedValue
    private int projectId;
    private String ProjectName;
    private String submitionDate;

    private Employee employee;
}
