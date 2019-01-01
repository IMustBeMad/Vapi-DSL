package validation.api.demo.validation;

import java.util.function.Function;

public interface StringChainFunctionHolder<P> {

    BaseChainFunction<String> matches(String pattern);

    BaseChainFunction<String> isDate(String byPattern);

    BaseChainFunction<P> isDate(Function<P, String> mutator, String byPattern);
}
