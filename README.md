# Vapi-DSL
Fluent business validation DSL

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=IMustBeMad_Vapi-DSL&metric=alert_status)](https://sonarcloud.io/dashboard?id=IMustBeMad_Vapi-DSL)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=IMustBeMad_Vapi-DSL&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=IMustBeMad_Vapi-DSL)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=IMustBeMad_Vapi-DSL&metric=coverage)](https://sonarcloud.io/dashboard?id=IMustBeMad_Vapi-DSL)

## In a nutshell
* Provides expressive and self-documented logic
* Unifies business layer validation style
* Encourages single responsibility for each validated entity

## Examples

```java
public List<ValidationError> testTheGeneral(String name, String rank) {
    return Validation.succeedIf("General Kenobi")
                      .isEqualTo(String.format("%s %s", name, rank)).onError("do.not.try.it")
                      .or()
                      .startsWith(rank)
                      .contains(name)
                      .groupError("fine.addition.to.my.collection")
                      .examine();
}
```

```java
public void executeOrder66() {
    List<String> clones = List.of("CT-1509", "CT-2504", "CT-2110");

    Validation.failIf(clones)
              .contains("Anakin Skywalker").onError("you.were.the.chosen.one")
              .or()
              .hasNoDuplicates()
              .every(() -> StringConditions.startsWith("CT"))
              .satisfiesAny(ListConditions.isEmpty(), ListConditions.isEqualTo(List.of("Boba Fett")))
              .groupError("hello.there")
              .examine(ErrorMode.THROW);
}
```
