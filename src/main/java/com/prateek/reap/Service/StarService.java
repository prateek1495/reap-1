package com.prateek.reap.Service;

import com.prateek.reap.Entity.Star;
import com.prateek.reap.Repository.StarRepository;
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

    public Star getStarByName(String starType) {
        return starRepository.findByName(starType.toUpperCase());
    }
}
