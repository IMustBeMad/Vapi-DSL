package validation.api.demo.validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

//@RequiredArgsConstructor(staticName = "assertThat")
public class BaseChainFunction<P> extends AbstractChainFunction<P> {

    private BaseChainFunction(P param) {
        super();
        this.param = param;
    }

    public static <P> BaseChainFunction<P> assertThat(P param) {
        return new BaseChainFunction<>(param);
    }

    @Override
    public BaseChainFunction<P> and() {
        return mySelf();
    }

    @Override
    public BaseChainFunction<P> or() {
        this.cluster = new PredicateCluster();

        return mySelf();
    }

    private BaseChainFunction<P> mySelf() {
        return this;
    }

    public BaseChainFunction<P> matches(String pattern) {
        this.cluster.push(param -> ((String) param).matches(pattern), "error.pattern.not.matched");

        return mySelf();
    }

    public BaseChainFunction<P> isDate(String byPattern) {
        this.cluster.push(param -> isDate((String) param, byPattern), "error.is.not.a.date.by.pattern");

        return mySelf();
    }

    public BaseChainFunction<P> isDate(Function<P, P> mutator, String byPattern) {
        this.cluster.push(param -> isDate(((String) mutator.apply(param)), byPattern), "error.is.not.a.date.by.pattern");

        return mySelf();
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
