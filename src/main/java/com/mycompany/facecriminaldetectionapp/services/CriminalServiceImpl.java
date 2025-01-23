/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.facecriminaldetectionapp.services;

import com.mycompany.facecriminaldetectionapp.model.Criminal;
import com.mycompany.facecriminaldetectionapp.repositories.CriminalRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author user
 */
@Service
//@NoArgsConstructor
public class CriminalServiceImpl implements CriminalService {

    private CriminalRepository criminalRepository;

    @Autowired
    public CriminalServiceImpl(CriminalRepository criminalRepository) {
        this.criminalRepository = criminalRepository;

    }

    @Override
    public void saveCriminal(Criminal criminal) {
        criminalRepository.save(criminal);
        

    }

}
