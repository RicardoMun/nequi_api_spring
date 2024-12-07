package com.accenture.nequiApi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document (collection = "freanchises")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Franchise {

    @Id
    private String id;
    private String name;
    private List<Ofice> ofices = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ofice> getOfices() {
        return ofices;
    }

    public void setOfices(List<Ofice> ofices) {
        this.ofices = ofices;
    }
}
