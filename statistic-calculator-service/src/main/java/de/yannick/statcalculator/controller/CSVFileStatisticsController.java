package de.yannick.statcalculator.controller;

import de.yannick.statcalculator.document.CSVFileStatistics;
import de.yannick.statcalculator.service.CSVFileStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CSVFileStatisticsController {

    @Autowired
    private CSVFileStatisticsService csvFileStatisticsService;

    @GetMapping("/fileStatistics")
    public List<CSVFileStatistics> getAllCSVFileStatistics() {
        return csvFileStatisticsService.getAllFileStatistics();
    }
}
