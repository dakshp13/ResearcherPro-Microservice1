package com.researcherpro.microservice1.Service;


import com.researcherpro.microservice1.Repository.DailyDataUsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GraphService {
    @Autowired
    private DailyDataUsageRepository dailyDataUsageRepository;

    public byte[] getAnalyticsGraph(){
        return null;
    }
}
