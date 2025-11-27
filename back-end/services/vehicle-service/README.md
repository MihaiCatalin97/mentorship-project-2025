## Build
```bash
mvn install -DskipTests 

```
## API
### POST @ / vehicles/types
#### Description
This endpoint allows the creation of a new vehicle type in the memory for now.
#### Validations
No validations
#### Request examples
```
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "name": "Dacia Logan 2022",
  "hourlyRate": 25.5,
  "capacity": 5,
  "createdAt": "2025-11-24T14:35:00+02:00",
  "updatedAt": "2025-11-24T15:00:00+02:00"
}

```

#### Response examples
```
{
    "id": "9021d196-a545-4025-8e40-6c32305658a9",
    "name": "Dacia Logan 2022",
    "hourlyRate": 25.5,
    "capacity": 5,
    "createdAt": "2025-11-25T09:56:34.6146665Z",
    "updatedAt": null
}
```