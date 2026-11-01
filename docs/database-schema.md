# SmartRoute Database Schema

## Overview

SmartRoute uses PostgreSQL as its relational database management system. The database stores locations that can be used as nodes in the route-finding system.

## Table: `locations`

| Column          | Data Type          | Constraints                           | Description                         |
| --------------- | ------------------ | ------------------------------------- | ----------------------------------- |
| `id`            | `integer`          | Primary Key, NOT NULL, auto-generated | Unique identifier for each location |
| `name`          | `varchar(100)`     | NOT NULL                              | Name of the location                |
| `latitude`      | `double precision` | NOT NULL                              | Geographic latitude                 |
| `longitude`     | `double precision` | NOT NULL                              | Geographic longitude                |
| `location_type` | `varchar(100)`     | NOT NULL, CHECK                       | Category of the location            |

## Constraints

### Primary Key

- `locations_pkey` ensures each location has a unique `id`.
- The ID is generated automatically using a PostgreSQL sequence.

### Required Fields

- `name`, `latitude`, `longitude`, and `location_type` cannot be `NULL`.

### Location Type Validation

The `valid_location_type` CHECK constraint restricts `location_type` to:

- `STATION`
- `AIRPORT`
- `RESTAURANT`
- `PARK`
- `UNIVERSITY`

## Example Record

| id  | name              | latitude | longitude | location_type |
| --- | ----------------- | -------- | --------- | ------------- |
| 1   | Updated Station A | 38.84    | -90.88    | STATION       |
