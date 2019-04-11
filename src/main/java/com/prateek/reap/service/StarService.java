package com.prateek.reap.service;

import com.prateek.reap.entity.Star;
import com.prateek.reap.repository.StarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StarService{


    @Autowired
    private StarRepository starRepository;

    public Iterable<Star> findAll() {
        return starRepository.findAll();
    }

    public void save(Star star) {
        starRepository.save(star);
    }

    public Star findByName(String starType)
    {
       return starRepository.findByName(starType.toUpperCase());
    }
}
