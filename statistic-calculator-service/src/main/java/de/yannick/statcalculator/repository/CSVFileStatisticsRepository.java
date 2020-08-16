package de.yannick.statcalculator.repository;

import de.yannick.statcalculator.document.CSVFileStatistics;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CSVFileStatisticsRepository extends MongoRepository<CSVFileStatistics, String> { }
