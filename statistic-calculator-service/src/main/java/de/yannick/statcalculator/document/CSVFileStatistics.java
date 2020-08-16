package de.yannick.statcalculator.document;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"id"})
@Document(collection = "CSVFileStatistics")
public class CSVFileStatistics {

    @Id
    private String id;
    private String fileName;
    private List<String> labels;
    private List<String> attributes;
    private Map<String, List<ColumnMedian>> labelToColumnMedians = new HashMap<>();

    public CSVFileStatistics(String fileName, List<String> labels, List<String> attributes) {
        this.fileName = fileName;
        this.labels = labels;
        this.attributes = attributes;
    }
}
