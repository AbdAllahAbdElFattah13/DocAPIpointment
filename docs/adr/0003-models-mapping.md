---
status: "confirmed"
date: 2024-01-31
decision-makers: AbdAllah AbdElFattah
consulted: Claude 3.5 Sonnet
---

# Using MapStruct for Object Mapping

## Context

We needed a solution for mapping between domain objects and DTOs/response objects in our application. Previously, we were using hand-written mappers like:

```kotlin
@Component
class SlotMapper {
    fun toResponseSlot(slot: Slot): ResponseSlot {
        return ResponseSlot(
            id=slot.id.value,
            time=slot.time.epochMillis.toString(),
            durationInMinutes = slot.durationInMinutes,
            isReserved = slot.isReserved,
            cost = slot.cost.value,
        )
    }

    fun toResponseSlots(slots: List<Slot>): List<ResponseSlot> {
        return slots.map { toResponseSlot(it) }
    }
}
```

This approach, while straightforward, had several limitations:
- Required manual maintenance of mapping code
- Prone to errors when object properties change
- Inconsistent mapping implementations across different mappers
- No compile-time validation of mappings
- Test coverage needed for each mapper implementation

## Decision

We have decided to use MapStruct for object mapping with the following configuration:

```kotlin
@Mapper(
    componentModel = "spring",
    uses = [SharedModelsMapping::class, CostMapper::class],
    injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
interface SlotMapper {
    @Mapping(target = "time", source = "time", qualifiedByName = ["mapTimeToEpoch"])
    fun toResponseSlot(slot: Slot): ResponseSlot
    
    fun toResponseSlots(slots: List<Slot>): List<ResponseSlot>
}
```

## Consequences

### Positive

1. **Reduced Boilerplate**
   - Mapping code is generated automatically
   - Less manual code to maintain
   - Reduced chance of mapping errors

2. **Compile-time Safety**
   - Mapping errors are caught during compilation
   - Type-safe mappings
   - Early detection of breaking changes

3. **Performance**
   - Generated code is optimized
   - No reflection used at runtime
   - Better performance than manual mapping in most cases

4. **Consistency**
   - Uniform mapping approach across the application
   - Standardized way to handle custom type conversions
   - Clear separation between mapping logic and business logic

5. **Testing**
   - Less need for unit testing mapping code
   - Generated code is already tested by MapStruct
   - Focus tests on custom mapping logic only

### Negative

1. **Learning Curve**
   - Team needs to learn MapStruct annotations and conventions
   - Understanding generated code when debugging
   - Additional project setup required

2. **Build Time**
   - Increased build time due to code generation
   - Additional annotation processing step

3. **Kotlin Integration Challenges**
   - Some Kotlin features don't work well with MapStruct out of the box
   - Special handling needed for Boolean properties starting with 'is'
   - Need to use kapt instead of annotationProcessor

### Boolean Property Naming Issue

We encountered an issue with Boolean properties named with 'is' prefix (e.g., `isReserved`). MapStruct couldn't properly detect the getter method due to Kotlin's special handling of Boolean properties.

```kotlin
// Problem
data class Slot(
    val isReserved: Boolean = false  // Generates isReserved() getter
)

// Solution
data class Slot(
    val reserved: Boolean = false    // Generates getReserved() getter
)
```

This was resolved by:
1. Renaming Boolean properties to not start with 'is'
2. Using explicit mappings when 'is' prefix is necessary
3. Documenting this convention for the team

## Alternatives Considered

### 1. Manual Mapping with Extension Functions

```kotlin
fun Slot.toResponse() = ResponseSlot(
    id = id.value,
    time = time.epochMillis.toString(),
    // ...
)
```

Rejected due to:
- No compile-time safety
- Scattered mapping logic
- Harder to maintain consistency

### 2. Reflection-based Mapping (like ModelMapper)

Rejected due to:
- Runtime performance overhead
- No compile-time validation
- Magic behavior leading to subtle bugs

### 3. Keep Hand-written Mappers

Rejected due to:
- Maintenance overhead
- Inconsistent implementations
- More code to test

## Implementation Notes

1. Build Configuration:
```kotlin
plugins {
    kotlin("kapt")
}

dependencies {
    implementation("org.mapstruct:mapstruct:1.6.3")
    kapt("org.mapstruct:mapstruct-processor:1.6.3")
}

kapt {
    arguments {
        arg("mapstruct.defaultComponentModel", "spring")
        arg("mapstruct.nullValueCheckStrategy", "ALWAYS")
    }
}
```

2. Testing Approach:
```kotlin
class SlotMapperTest {
    private val sharedModelsMapping = SharedModelsMapping()
    private val costMapper = CostMapper()
    private val slotMapper = SlotMapperImpl(sharedModelsMapping, costMapper)
    
    @Test
    fun `should map slot to response slot`() {
        // test code
    }
}
```

## References

- [MapStruct Documentation](https://mapstruct.org/documentation/stable/reference/html/)
- [MapStruct with Kotlin](https://mapstruct.org/documentation/stable/reference/html/#using-mapstruct-with-kotlin)
- [Spring Boot Integration](https://mapstruct.org/documentation/stable/reference/html/#using-dependency-injection)
