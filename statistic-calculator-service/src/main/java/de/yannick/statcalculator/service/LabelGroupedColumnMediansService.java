package de.yannick.statcalculator.service;


import lombok.AllArgsConstructor;
import lombok.Getter;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import de.yannick.statcalculator.document.ColumnMedian;
import de.yannick.statcalculator.model.LabeledCSVFile;

@Service
public class LabelGroupedColumnMediansService {

    public Map<String, List<ColumnMedian>> getColumnMediansGroupedByLabel(LabeledCSVFile labeledCSVFile) {
        Map<String, List<ColumnMedian>> columnMedians = labeledCSVFile.getLabels().stream()
                .map(label -> getColumnMediansForLabel(label, labeledCSVFile))
                .collect(Collectors.toMap(ColumnMediansByLabel::getLabel, ColumnMediansByLabel::getColumnMedians));

        return columnMedians;
    }

    private ColumnMediansByLabel getColumnMediansForLabel(String label, LabeledCSVFile labeledCSVFile) {
        List<ColumnMedian> columnMedians = new LinkedList<>();
        List<Map<String, Object>> relevantRows = labeledCSVFile.getRows().stream()
                .filter(row -> row.get("label").equals(label))
                .collect(Collectors.toList());

        labeledCSVFile.getAttributes().forEach(attribute -> {
            List<Object> attributeValues = relevantRows.stream()
                    .map(row -> row.get(attribute))
                    .collect(Collectors.toList());
            ColumnMedian columnMedian = new ColumnMedian(attribute, getMedian(attributeValues));
            columnMedians.add(columnMedian);
        });

        return new ColumnMediansByLabel(label, columnMedians);
    }

    // TODO handle average in case of even length and number values
    private Object getMedian(List<Object> objects) {
        if (objects.size() == 0) return null;
        List<Object> sorted = objects.stream()
                .sorted()
                .collect(Collectors.toList());
        return isEven(sorted.size()) ?
                sorted.stream().skip(sorted.size() / 2 - 1).findFirst().get() :
                sorted.stream().skip(sorted.size() / 2).findFirst().get();
    }

    private boolean isEven(int n) {
        return n % 2 == 0;
    }

    @Getter
    @AllArgsConstructor
    class ColumnMediansByLabel {
        String label;
        List<ColumnMedian> columnMedians;
    }
}
