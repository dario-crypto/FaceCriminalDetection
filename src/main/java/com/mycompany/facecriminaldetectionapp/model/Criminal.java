/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.facecriminaldetectionapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Array;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;


/**
 *
 * @author user
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "criminals")
public class Criminal {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    private LocalDate dob;
    private String gender;
    private String nationality;
    private String imageName;
    private float heigth;
    private float weight;
    @Column(name = "eyes_color")
    private String eyesColor;
    @Column(name = "hair_color")
    private String hairColor;
    @Column(name = "distinctive_signs")
    private String distinctiveSigns;
    @JdbcTypeCode(SqlTypes.VECTOR)
    @Array(length = 5)
    private float[] embedding;

    //private List<CriminalEvent> criminalEvents;
}
