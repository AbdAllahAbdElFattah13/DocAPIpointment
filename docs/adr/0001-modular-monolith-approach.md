---
status: "confirmed"
date: 2024-01-21
decision-makers: AbdAllah AbdElFattah
consulted: Claude 3.5 Sonnet
---

# Modular Monolith Implementation Approach Selection

## Context and Problem Statement

We need to implement a modular monolith architecture for our Spring Boot application using Kotlin.
The architecture needs to support clear module boundaries, event-based communication between modules, and be deployed as a single unit.
We need to decide between using Spring Modulith or Gradle Multi-Module approach for implementing this architecture.

## Decision Drivers

* Need for clear module boundaries and dependency management
* Deployment requirements (single deployment unit)
* Development experience and team productivity
* Future maintainability and potential evolution
* Build system complexity
* Testing and verification of architectural constraints

## Considered Options

* Spring Modulith with package-based modularity
* Gradle Multi-Module with physical module separation

## Decision Outcome

Chosen option: "Spring Modulith with package-based modularity", because it provides the necessary modularity benefits while maintaining simplicity in build and deployment processes, and offers built-in tools for ensuring architectural compliance.

## Consequences

* Project structure simplification leads to reduced cognitive load and faster onboarding
* Module boundary verification is automated through built-in tooling
* Build process efficiency increases due to single-module compilation
* Cross-module refactoring becomes more streamlined with package-based separation
* Module separation relies on disciplined development practices rather than physical boundaries, which requires careful code reviews
* Future microservices extraction may require additional architectural changes

### Confirmation

We can confirm the implementation of this decision through:

1. Using Spring Modulith's built-in testing capabilities (@ModulithTest)
2. Automatic documentation generation showing module dependencies
3. Code reviews ensuring proper package structure and dependencies

## Pros and Cons of the Options

### Spring Modulith with package-based modularity

#### Module Boundaries & Dependencies:

* Enforces boundaries through package structure and built-in verification tools
* Provides clear dependency visualization through automatic documentation
* Relies on convention and discipline rather than physical separation

#### Deployment Requirements:

* Naturally aligns with single-unit deployment goal
* Simplified deployment configuration and management

#### Development Experience:

* Reduces build configuration complexity
* Enables faster build cycles
* Facilitates easier cross-module refactoring
* Seamless integration with Spring's dependency injection

#### Testing & Verification:

* Built-in testing tools (@ModulithTest)
* Automatic architectural compliance verification
* Integrated documentation generation

#### Additional Considerations:

* Package-based organization might be less intuitive for new team members
* IDE support for boundary enforcement is less robust

### Gradle Multi-Module with physical module separation

#### Module Boundaries & Dependencies:

* Enforces strict physical boundaries through separate modules
* Makes dependencies explicit in build files
* Provides strong IDE support for module separation

#### Deployment Requirements:

* Still allows for single-unit deployment.
* Easier to convent to mirco-services in the future.

#### Development Experience:

* Familiar to teams with microservices background
* Requires more build configuration maintenance
* Longer build times due to module compilation

#### Testing & Verification:

* Requires manual setup for architectural testing
* Module boundaries enforced at compilation level
* Additional effort needed for cross-module testing

#### Additional Considerations:

* Better suited for future microservices migration
* Enables module sharing across different applications
* More boilerplate code required for module communication
* Increased build complexity
* Requires some knowledge of Gradle multi-module projects using either Kotlin or Groovy, since which an extra learning curve for the team.

## More Information

The decision is based on our current requirement to deploy as a single unit and the need for maintainable, clear module boundaries. The team has experience with Gradle multi-module projects, but the additional complexity isn't justified for our current needs.

This decision should be revisited if:

* We need to share modules across different applications
* We need to deploy modules independently
* We encounter significant issues with package-based boundaries

Related resources:

* Spring Modulith documentation
* Team's previous experience with Gradle multi-module projects
* Spring Framework modular architecture guidelines
