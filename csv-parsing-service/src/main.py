import click
import csv
import json

from services import labeled_csv_to_json_service

@click.command()
@click.option('--filepath', default='.', help='CSV file to parse')
@click.option('--delimiter', default=';', help='Delimiter in CSV file')
def parse(filepath, delimiter):
  with open(filepath) as csv_file:
    csv_reader = csv.DictReader(csv_file, delimiter=delimiter)
    parsed = labeled_csv_to_json_service.transform(csv_reader)
    print(json.dumps(parsed))

if __name__ == '__main__':
  parse()