package de.yannick.statcalculator.service;

import de.yannick.statcalculator.document.CSVFileStatistics;
import de.yannick.statcalculator.repository.CSVFileStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CSVFileStatisticsService {

    @Autowired
    private CSVFileStatisticsRepository csvFileStatisticsRepository;

    public List<CSVFileStatistics> getAllFileStatistics() {
        return csvFileStatisticsRepository.findAll();
    }
}
