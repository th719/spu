package org.example.spu;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;

import java.util.Date;

import javax.validation.constraints.NotNull;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@Entity
@AllArgsConstructor
public class Employer {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(length=200, nullable=false, unique=false)
    private String name;

    private Date birthDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "department")
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;
}
