import click
import csv
import json
import os

from services import labeled_csv_to_json_service

@click.command()
@click.option('--filepath', default='.', help='Path to CSV file to parse')
@click.option('--filename', default='file.csv', help='Filename of file to parse')
@click.option('--delimiter', default=';', help='Delimiter in CSV file')
def parse(filepath, filename, delimiter):
  with open(os.path.join(filepath, filename)) as csv_file:
    csv_reader = csv.DictReader(csv_file, delimiter=delimiter)
    parsed = labeled_csv_to_json_service.transform(csv_reader, filename)
    print(json.dumps(parsed))

if __name__ == '__main__':
  parse()