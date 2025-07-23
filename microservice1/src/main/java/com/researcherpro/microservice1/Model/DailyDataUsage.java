package com.researcherpro.microservice1.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "daily-data-usage")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyDataUsage {
    @Id
    private ObjectId id;
    private String date;
    private int amountOfRequests;
    private List<String> listOfRequests;

    public DailyDataUsage(String date, int amountOfRequests){
        this.date = date;
        this.amountOfRequests = amountOfRequests;
        this.listOfRequests = new ArrayList<>();
    }

    public void addToListOfRequests(String newRequestMessage){
        this.listOfRequests.add(newRequestMessage);
    }

}
