from typing import List, Dict
from csv import DictReader

def transform(csv_reader: DictReader, filename: str) -> List[Dict]:
  fieldnames = csv_reader.fieldnames
  labels = set()
  parsed_rows = list()
  for row in csv_reader:
    labels.add(row['label'])
    parsed = {}
    for field in fieldnames:
      parsed[field] = row[field]
    parsed_rows.append(parsed)

  return {
    "fileName": filename,
    "attributes": fieldnames,
    "labels": list(labels),
    "rows": parsed_rows
  }