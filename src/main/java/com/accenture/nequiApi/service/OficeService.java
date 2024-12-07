package com.accenture.nequiApi.service;


import com.accenture.nequiApi.model.Ofice;
import com.accenture.nequiApi.repository.OficeRepository;
import org.springframework.stereotype.Service;

@Service
public class OficeService {

    private final OficeRepository oficeRepository;

    public OficeService(OficeRepository oficeRepository) {
        this.oficeRepository = oficeRepository;
    }

    public Ofice saveOfice(Ofice ofice){
        return oficeRepository.save(ofice);
    }

}
