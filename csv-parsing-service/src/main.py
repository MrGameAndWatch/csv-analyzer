import click
import csv

from services import csv_to_json_service

@click.command()
@click.option('--filepath', default='.', help='CSV file to parse')
@click.option('--delimiter', default=';', help='Delimiter in CSV file')
def parse(filepath, delimiter):
  with open(filepath) as csv_file:
    csv_reader = csv.DictReader(csv_file, delimiter=delimiter)
    parsed = csv_to_json_service.transform(csv_reader)
    print(parsed)

if __name__ == '__main__':
  parse()