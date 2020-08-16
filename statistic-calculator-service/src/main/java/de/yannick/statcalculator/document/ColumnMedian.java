package de.yannick.statcalculator.document;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ColumnMedian {

    private String columnName;
    private Object median;
}
