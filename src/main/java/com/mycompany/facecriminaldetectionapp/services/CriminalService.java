/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.facecriminaldetectionapp.services;

import com.mycompany.facecriminaldetectionapp.model.Criminal;
import org.springframework.stereotype.Service;

/**
 *
 * @author user
 */
@Service
public interface CriminalService {

    public void saveCriminal(Criminal criminal);

}
