package de.yannick.statcalculator.service;

import de.yannick.statcalculator.document.CSVFileStatistics;
import de.yannick.statcalculator.document.ColumnMedian;
import de.yannick.statcalculator.model.LabeledCSVFile;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CSVFileStatisticsTest {

    private CSVFileStatisticsCalculatorService csvFileStatisticsCalculatorService;
    private LabelGroupedColumnMediansService labelGroupedColumnMediansService;

    @BeforeEach
    public void initService() {
       csvFileStatisticsCalculatorService = new CSVFileStatisticsCalculatorService();
       labelGroupedColumnMediansService = new LabelGroupedColumnMediansService();
    }

    @Test
    public void medianService_shouldReturn_correctColumnMediansGroupedByLabel() {
        LabeledCSVFile labeledCSVFile = new LabeledCSVFile();
        labeledCSVFile.setFileName("file");
        labeledCSVFile.setAttributes(Arrays.asList("a1", "a2"));
        labeledCSVFile.setLabels(Arrays.asList("l1", "l2", "l3"));
        List<Map<String, Object>> rows = Arrays.asList(
                Map.ofEntries(
                        new AbstractMap.SimpleEntry<String, Object>("a1", 1),
                        new AbstractMap.SimpleEntry<String, Object>("a2", "a"),
                        new AbstractMap.SimpleEntry<String, Object>("label", "l1")
                ),
                Map.ofEntries(
                        new AbstractMap.SimpleEntry<String, Object>("a1", 2),
                        new AbstractMap.SimpleEntry<String, Object>("a2", "b"),
                        new AbstractMap.SimpleEntry<String, Object>("label", "l1")
                ),
                Map.ofEntries(
                        new AbstractMap.SimpleEntry<String, Object>("a1", 3),
                        new AbstractMap.SimpleEntry<String, Object>("a2", "c"),
                        new AbstractMap.SimpleEntry<String, Object>("label", "l1")
                ),
                Map.ofEntries(
                        new AbstractMap.SimpleEntry<String, Object>("a1", 10),
                        new AbstractMap.SimpleEntry<String, Object>("a2", "male"),
                        new AbstractMap.SimpleEntry<String, Object>("label", "l3")
                ),
                Map.ofEntries(
                        new AbstractMap.SimpleEntry<String, Object>("a1", 5),
                        new AbstractMap.SimpleEntry<String, Object>("a2", "female"),
                        new AbstractMap.SimpleEntry<String, Object>("label", "l3")
                )
        );
        labeledCSVFile.setRows(rows);
        Map<String, List<ColumnMedian>> labelToColumnMedians = labelGroupedColumnMediansService.getColumnMediansGroupedByLabel(labeledCSVFile);

        Map<String, List<ColumnMedian>> expectedLabelToColumnMedians = Map.ofEntries(
                new AbstractMap.SimpleEntry<String, List<ColumnMedian>>(
                        "l1",
                        Arrays.asList(new ColumnMedian("a1", 2), new ColumnMedian("a2", "b"))
                ),
                new AbstractMap.SimpleEntry<String, List<ColumnMedian>>(
                        "l2",
                        Arrays.asList(new ColumnMedian("a1", null), new ColumnMedian("a2", null))
                ),
                new AbstractMap.SimpleEntry<String, List<ColumnMedian>>(
                        "l3",
                        Arrays.asList(new ColumnMedian("a1", 5), new ColumnMedian("a2", "female"))
                )
        );
        assertTrue(labelToColumnMedians.size() == 3);
        assertEquals(expectedLabelToColumnMedians.get("l1"), labelToColumnMedians.get("l1"));
        assertEquals(expectedLabelToColumnMedians.get("l2"), labelToColumnMedians.get("l2"));
        assertEquals(expectedLabelToColumnMedians.get("l3"), labelToColumnMedians.get("l3"));
    }
}
