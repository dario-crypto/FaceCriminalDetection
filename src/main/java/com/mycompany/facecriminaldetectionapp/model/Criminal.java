/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.facecriminaldetectionapp.model;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author user
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Criminal {

    private Long id;
    private String name;
    private LocalDate dob;
    private String gender;
    private String nationality;
    private String pathImage;
    private float heigth;
    private float weight;
    private String eyesColor;
    private String hairColor;
    private String distinctiveSigns;
    private List<CriminalEvent> criminalEvents;

}
