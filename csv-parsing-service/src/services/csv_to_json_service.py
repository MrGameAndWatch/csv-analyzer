from typing import List, Dict
from csv import DictReader

def transform(csv_reader: DictReader) -> List[Dict]:
  fieldnames = csv_reader.fieldnames
  parsed_rows = list()
  for row in csv_reader:
    parsed = {}
    for field in fieldnames:
      parsed[field] = row[field]
    parsed_rows.append(parsed)

  return {
    'attributes': fieldnames,
    'rows': parsed_rows
  }