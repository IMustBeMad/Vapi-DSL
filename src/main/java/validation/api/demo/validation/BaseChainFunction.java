package validation.api.demo.validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

public class BaseChainFunction<P> extends AbstractChainFunction<P> implements StringChainFunctionHolder<P> {

    private BaseChainFunction(P param) {
        super();
        this.param = param;
    }

    public static <P> BaseChainFunction<P> assertThat(P param) {
        return new BaseChainFunction<>(param);
    }

    @Override
    public BaseChainFunction<P> and() {
        return this;
    }

    @Override
    public BaseChainFunction<P> or() {
        this.cluster = new PredicateCluster();
        this.clusters.add(this.cluster);

        return this;
    }

    @Override
    public BaseChainFunction<String> matches(String pattern) {
        this.cluster.push(param -> ((String) param).matches(pattern), "error.pattern.not.matched");

        return mySelf();
    }

    @Override
    public BaseChainFunction<String> isDate(String byPattern) {
        this.cluster.push(param -> isDate((String) param, byPattern), "error.is.not.a.date.by.pattern");

        return mySelf();
    }

    @Override
    public BaseChainFunction<P> isDate(Function<P, String> mutator, String byPattern) {
        this.cluster.push(param -> isDate(mutator.apply(param), byPattern), "error.is.not.a.date.by.pattern");

        return this;
    }

    private <T> BaseChainFunction<T> mySelf() {
        return (BaseChainFunction<T>) this;
    }

    private boolean isDate(String param, String byPattern) {
        try {
            LocalDate.parse(param, DateTimeFormatter.ofPattern(byPattern));
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
