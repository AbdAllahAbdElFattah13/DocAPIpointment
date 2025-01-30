---
status: "confirmed"
date: 2024-01-30
decision-makers: AbdAllah AbdElFattah
consulted: Claude 3.5 Sonnet
---


# New Time Handling Approach

## Status
Accepted

## Context
The system initially handled dates using `LocalDateTime` with string-based storage in a specific format ("d/M/yyyy H:mm"). This approach presented several challenges:

### Previous Implementation
```kotlin
data class FutureDate(
    val dateTimeString: String,
    private val now: LocalDateTime = LocalDateTime.now(),
) {
    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy H:mm")
    val dateTime: LocalDateTime = dateTimeString.toDateTimeWithFormat(formatter)

    init {
        require(dateTime.isAfter(now)) { "Date must be in the future: $dateTimeString" }
    }
}
```

### Issues with Previous Approach
1. **Timezone Ambiguity**: `LocalDateTime` doesn't carry timezone information, leading to potential confusion and bugs
2. **Storage Inefficiency**: String-based storage is less efficient than numeric timestamps
3. **Performance**: Constant string parsing/formatting overhead

## Decision
We will change our time handling approach to:

1. Use `Instant` as the core time representation
2. Store dates as epoch milliseconds
3. Maintain timezone awareness through explicit UTC handling
4. Provide clean factory methods for creation
5. Remove string-based storage

### New Implementation
```kotlin
class FutureDate private constructor(
    private val instant: Instant
) {
    companion object {
        private val UTC_ZONE = ZoneId.of("UTC")
        
        fun fromInstant(instant: Instant, clock: Clock = Clock.systemUTC()): FutureDate {
            require(instant.isAfter(clock.instant())) { 
                "Date must be in the future: $instant" 
            }
            return FutureDate(instant)
        }

        fun fromEpochMillis(epochMillis: Long): FutureDate
        fun parse(dateStr: String, pattern: String = "d/M/yyyy H:mm"): FutureDate
        fun stringToEpochMillis(dateStr: String): Long
    }

    fun toEpochMillis(): Long
    fun format(pattern: String = "d/M/yyyy H:mm", zone: ZoneId = UTC_ZONE): String
}
```

## Consequences

### Positive
1. **Improved Data Integrity**:
   - Unambiguous time representation
   - No timezone-related bugs
   - Consistent handling across system boundaries

2. **Better Performance**:
   - Efficient numeric storage
   - Reduced parsing overhead
   - Faster comparisons and calculations

3. **Enhanced API Design**:
   - Clear contractual representation of time
   - Easy integration with frontend (JavaScript uses milliseconds)
   - Simplified database operations

4. **Better Testing**:
   - Clock injection for time-based testing
   - No string parsing in core logic
   - Deterministic behavior

### Negative
1. **Migration Effort**:
   - Existing data needs conversion
   - API clients need updating

2. **Complexity**:
   - More complex initial setup
   - Need for explicit timezone handling in UI layer
   - Additional conversion logic for human-readable formats

## Alternatives Considered

### 1. Keep String-Based Storage with Better Validation
- Rejected due to performance and integrity concerns
- Would not solve timezone issues

### 2. Use LocalDateTime with Explicit Zone
- Rejected due to unnecessary complexity
- Still requires string parsing/formatting

### 3. Use ZonedDateTime
- Rejected as too heavy for our needs
- Instant provides same benefits with less overhead

## Implementation Notes

### Database Migration

### API Changes
```json
// Old format
{
    "slotTime": "22/1/2024 12:00"
}

// New format
{
    "slotTime": 1705921200000
}
```
