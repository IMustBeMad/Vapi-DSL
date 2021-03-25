## In a nutshell
* Expressive and self-documented logic
* Unified business layer validation style
* Encouraged single responsibility for each validated entity

## Examples
```java
public List<ValidationError> testTheGeneral(String name, String rank) {
    Validation.succeedIf("General Kenobi")
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

## Documentation
_under construction_
