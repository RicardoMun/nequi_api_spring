package com.accenture.nequiApi.controller;


import com.accenture.nequiApi.model.Ofice;
import com.accenture.nequiApi.service.OficeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ofices")
public class OficeController {

    private final OficeService oficeService;

    public OficeController(OficeService oficeService) {
        this.oficeService = oficeService;
    }

    @PostMapping
    public ResponseEntity<?> createOfice(@RequestBody Ofice ofice) {
        Ofice savedOfice = oficeService.saveOfice(ofice);
        return ResponseEntity.ok(savedOfice);
    }
}
