package de.yannick.statcalculator.service;

import de.yannick.statcalculator.repository.CSVFileStatisticsRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import de.yannick.statcalculator.model.LabeledCSVFile;
import de.yannick.statcalculator.document.CSVFileStatistics;
import de.yannick.statcalculator.document.ColumnMedian;

@Service
public class CSVFileStatisticsCalculatorService {

    @Autowired
    private LabelGroupedColumnMediansService labelGroupedColumnMediansService;

    @Autowired
    private CSVFileStatisticsRepository csvFileStatisticsRepository;

    public CSVFileStatistics calculateStatistics(LabeledCSVFile labeledCSVFile) {
        CSVFileStatistics csvFileStatistics = new CSVFileStatistics(
                labeledCSVFile.getFileName(),
                labeledCSVFile.getLabels(),
                labeledCSVFile.getAttributes()
        );
        csvFileStatistics.setLabelToColumnMedians(
                labelGroupedColumnMediansService.getColumnMediansGroupedByLabel(labeledCSVFile)
        );

        csvFileStatisticsRepository.save(csvFileStatistics);

        return csvFileStatistics;
    }
}
