---
status: "confirmed"
date: 2024-02-01
decision-makers: AbdAllah AbdElFattah
consulted: Claude 3.5 Sonnet
---

# Notification Module Design Decisions

## Context

The system requires sending confirmation notifications when appointments are booked. Requirements specify:
- Must send notifications to both patient and doctor
- Must include patient name, appointment time, and doctor name
- Can be implemented as simple log messages
- Module should use "simplest architecture possible"

Key constraints:
- Limited development time
- Single doctor system
- Simplicity is prioritized

## Decision Points

### 1. Data Flow Architecture

#### Problem
The notification requires data owned by different modules:
- Appointment details (from Booking module)
- Doctor details (from Availability module)

#### Considered Options

1. **Pass doctor details through booking module**
   - Booking module fetches and passes doctor details during slot retrieval
   - Cons: Creates unnecessary coupling, leaks dependencies

2. **Fetch doctor details after booking**
   - Booking module fetches doctor details post-reservation
   - Cons: Adds complexity to booking module, violates separation of concerns

3. **Pass minimal data to notification module** ✅
   - Pass only essential IDs (slotId, patientId/Name, doctorId)
   - Let notification module fetch additional data as needed
   - Pros: Clean separation of concerns, maintains module independence

#### Decision
Chose option 3: Pass minimal data to notification module

### 2. Integration Method

#### Problem
Need to determine how the booking module will trigger notifications

#### Considered Options

1. **Event Bus/Queue System**
   - Implement message queue for notification events
   - Pros: Scalable, loose coupling
   - Cons: Complex, overhead, overkill for current needs

2. **Direct Module Calls** ✅
   - Simple synchronous calls between modules
   - Pros: Simple implementation, easy debugging, quick development
   - Cons: Tighter coupling

#### Decision
Chose direct calls due to:
- Alignment with "simplest architecture possible" requirement
- Limited development time
- Single doctor system
- Notification being just logging
- Easy debugging

## Consequences

### Positive
- Simple, straightforward implementation
- Quick to develop
- Easy to debug and maintain
- Clear separation of concerns
- Meets current requirements effectively

### Negative
- Less scalable if requirements grow significantly
- More coupled than event-based system
- May need refactoring if system scales

## Future Considerations
- Could be refactored to event-based system if scale increases
- Might need async processing if notification becomes more complex
- Could add retry mechanism if needed

## References
- Original requirements document
- Clean architecture decisions document
