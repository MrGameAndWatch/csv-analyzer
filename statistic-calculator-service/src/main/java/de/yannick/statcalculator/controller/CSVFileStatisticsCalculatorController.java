package de.yannick.statcalculator.controller;

import de.yannick.statcalculator.model.LabeledCSVFile;
import de.yannick.statcalculator.service.CSVFileStatisticsCalculatorService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CSVFileStatisticsCalculatorController {

    @Autowired
    private CSVFileStatisticsCalculatorService csvFileStatisticsCalculatorService;

    @PostMapping(path = "/calculateStatistics")
    public ResponseEntity<String> analyzeFile(@RequestParam("file") LabeledCSVFile labeledCSVFile) {
        csvFileStatisticsCalculatorService.calculateStatistics(labeledCSVFile);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}