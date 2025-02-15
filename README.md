# DocAPIpointment

A modular monolith application for managing doctor appointments, built with Kotlin and Spring Boot.

## Overview

DocAPIpointment is a backend system that handles doctor appointment booking and management. The system is designed with a modular monolith architecture, consisting of four modules each implementing different architectural patterns:

- **Doctor Availability Module**: Traditional Layered Architecture
- **Appointment Booking Module**: Clean Architecture
- **Appointment Confirmation Module**: Simple Architecture
- **Doctor Management Module**: Hexagonal Architecture

## Technical Stack

- Kotlin
- Spring Boot
- In-memory data storage
- MapStruct for object mapping
- UTC-based time handling using epoch milliseconds

## Getting Started

### Prerequisites

- JDK 17 or higher
- Kotlin 1.9.x

### Running the Application

1. Clone the repository
2. Navigate to the project directory
3. Run the application:
   ```bash
   ./gradlew bootRun
   ```

The application will start on `http://localhost:8080`

## Default Test Data

The system currently uses default test objects for the doctor and patient:

### Default Doctor
- ID: `de99fd73-b66b-4853-8ca7-ce5766319f14`
- Name: Ibrahim El-Masry

### Default Patient
- ID: `6c26cc8b-6f02-4e06-8c02-a070388934c5`
- Name: Mohamed El-Shazly

## Testing Guide

### Available APIs

1. Create Slot
```http
POST http://localhost:8080/api/v1/slots/{doctorId}
Content-Type: application/json

{
  "slotStartDateTime": "4106676000000",
  "cost": "100"
}
```

2. Get Doctor's Slots
```http
GET http://localhost:8080/api/v1/slots/{doctorId}
```

3. Get Available Slots
```http
GET http://localhost:8080/api/v1/availableSlots
```

4. Create Appointment
```http
POST http://localhost:8080/api/v1/appointment
Content-Type: application/json

{
  "patientId": "6c26cc8b-6f02-4e06-8c02-a070388934c5",
  "slotId": "d3550f7f-3cb6-4e9e-a67a-4a7a4e00dbfe"
}
```

5. Get Doctor's Upcoming Appointments
```http
GET http://localhost:8080/api/v1/doctor/{doctorId}/upcoming_appointments
```

6. Update Appointment Status
```http
POST http://localhost:8080/api/v1/appointment/status
Content-Type: application/json

{
  "appointmentId": "bb7c89b0-3895-4583-baf7-0a41b78b894c",
  "appointmentStatus": "COMPLETED"
}
```

### Step-by-Step Testing Flow

1. **Create Test Slots**
    - Use the default doctor ID (`de99fd73-b66b-4853-8ca7-ce5766319f14`)
    - Create 4 slots with different times:
      ```json
      {
        "slotStartDateTime": "4106676000000",  // January 1, 2100, 12:00 PM UTC
        "cost": "100"
      }
      ```
      ```json
      {
        "slotStartDateTime": "4106683200000",  // January 1, 2100, 2:00 PM UTC
        "cost": "100"
      }
      ```
      ```json
      {
        "slotStartDateTime": "4106690400000",  // January 1, 2100, 4:00 PM UTC
        "cost": "100"
      }
      ```
      ```json
      {
        "slotStartDateTime": "4106697600000",  // January 1, 2100, 6:00 PM UTC
        "cost": "100"
      }
      ```

2. **Verify Slot Creation**
    - Call GET `/api/v1/slots/{doctorId}` with the default doctor ID
    - Verify all 4 slots are listed
    - Note the generated slot IDs for the next steps

3. **Create Appointments**
    - Create 3 appointments using the default patient ID and different slot IDs
    - Use POST `/api/v1/appointment`
    - Save the returned appointment IDs

4. **Verify Appointments**
    - Call GET `/api/v1/doctor/{doctorId}/upcoming_appointments`
    - Verify all 3 appointments are listed

5. **Test Status Updates**
    - Update status for first appointment to COMPLETED
    - Update status for second appointment to CANCELLED
    - Update status for third appointment to CANCELLED, then to REOPENED
    - Note: Attempting to set status to OPENED will return 400 Bad Request

### Important Notes

- All slots are created with a fixed duration of 2 hours
- Time is handled in UTC using epoch milliseconds
- Appointment statuses can be:
    - OPENED (default, cannot be set via API)
    - COMPLETED
    - CANCELLED
    - REOPENED

## Error Handling

The API will return appropriate HTTP status codes:
- 20x: Success
- 400: Bad Request (e.g., invalid appointment status)
- 500: Internal Server Error

## Future Improvements

- Refactor the Adaptor in `Doctor Management Module` to include test coverage.
- Update API Endpoint to better match RESTful conventions.
- Standardize API responses.
- Add test coverage reports.
- Add integration tests.
- Add create/update APIs for doctors and patients
- Make slot duration configurable
- Add pagination for listing endpoints
- Add more comprehensive error handling
- Add proper database integration
- Unify assertion libraries used in tests for more consistency
