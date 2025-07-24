package com.researcherpro.microservice1.Service;


import com.researcherpro.microservice1.Model.DailyDataUsage;
import com.researcherpro.microservice1.Repository.DailyDataUsageRepository;
import org.knowm.xchart.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
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
        List<String> dates = new ArrayList<>();
        List<Integer> usage = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        List<DailyDataUsage> allData = dailyDataUsageRepository.findAll();

        for(int i = 0; i < allData.size(); i++){
            dates.add(allData.get(i).getDate());
            usage.add(allData.get(i).getAmountOfRequests());
        }

        CategoryChart chart = new CategoryChartBuilder()
                .width(900)
                .height(500)
                .title("Daily Usage Graph")
                .xAxisTitle("Usage Days")
                .yAxisTitle("Amount of Requests")
                .build();


        chart.getStyler().setChartBackgroundColor(Color.DARK_GRAY);
        chart.getStyler().setPlotBackgroundColor(Color.LIGHT_GRAY);
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setChartFontColor(Color.WHITE);
        chart.getStyler().setAxisTickLabelsColor(Color.WHITE);
        chart.getStyler().setSeriesColors(new Color[]{ new Color(102, 204, 255) }); // pastel blue
        chart.getStyler().setAvailableSpaceFill(0.8);


        chart.addSeries("Dail Usage Data", dates, usage);
        BufferedImage bufferedImage = BitmapEncoder.getBufferedImage(chart);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", outputStream);
        return outputStream.toByteArray();
    }
}
