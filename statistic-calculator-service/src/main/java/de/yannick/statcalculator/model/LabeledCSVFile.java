package de.yannick.statcalculator.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class LabeledCSVFile {

    private String fileName;
    private List<String> attributes;
    private List<String> labels;
    private List<Map<String, Object>> rows;
}
