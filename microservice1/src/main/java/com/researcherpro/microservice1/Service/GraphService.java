package com.researcherpro.microservice1.Service;


import com.researcherpro.microservice1.Model.DailyDataUsage;
import com.researcherpro.microservice1.Repository.DailyDataUsageRepository;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

@Service
public class GraphService {
    @Autowired
    private DailyDataUsageRepository dailyDataUsageRepository;

    public byte[] getAnalyticsGraph() throws IOException {
        List<Date> dates = new ArrayList<>();
        List<Integer> usage = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        List<DailyDataUsage> allData = dailyDataUsageRepository.findAll();

        for(int i = 0; i < allData.size(); i++){
            LocalDate localDate = LocalDate.parse(allData.get(i).getDate(), formatter);
            Date date = java.sql.Date.valueOf(localDate);
            dates.add(date);
            usage.add(allData.get(i).getAmountOfRequests());
        }

        XYChart chart = new XYChartBuilder()
                .width(600)
                .height(400)
                .title("Daily Usage Graph")
                .xAxisTitle("Usage Days")
                .yAxisTitle("Amount of Requests")
                .build();

        chart.addSeries("Series", dates, usage);
        BufferedImage bufferedImage = BitmapEncoder.getBufferedImage(chart);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", outputStream);
        return outputStream.toByteArray();
    }
}
