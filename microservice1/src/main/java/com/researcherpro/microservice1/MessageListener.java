package com.researcherpro.microservice1;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class MessageListener {

    @Autowired
    private DailyDataUsageRepository dailyDataUsageRepository;

    @RabbitListener(queues = RabbitMQConfig.DATA_QUEUE)
    public void listener(RabbitMQMessage rabbitMQMessage){
        updateMongoDailyDataUsage(rabbitMQMessage);
        System.out.println(rabbitMQMessage);

    }

    private void updateMongoDailyDataUsage(RabbitMQMessage rabbitMQMessage){
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDate = today.format(formatter);
        Optional<DailyDataUsage> presentDayData = dailyDataUsageRepository.findFirstByDate(formattedDate);
        if(presentDayData.isPresent()){
                DailyDataUsage oldPresentDayData = presentDayData.get();
                oldPresentDayData.setAmountOfRequests(oldPresentDayData.getAmountOfRequests()+1);
                oldPresentDayData.addToListOfRequests(rabbitMQMessage.getMessage());
                dailyDataUsageRepository.save(oldPresentDayData);
        }
        else{
            DailyDataUsage newPresentDayData = new DailyDataUsage(formattedDate, 1);
            newPresentDayData.addToListOfRequests(rabbitMQMessage.getMessage());
            dailyDataUsageRepository.insert(newPresentDayData);
            dailyDataUsageRepository.save(newPresentDayData);
        }
    }
}
