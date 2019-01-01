package validation.api.demo.validation;

public interface ChainFunction<P> {

   ChainFunction<P> and();

   ChainFunction<P> or();

    void failFast();

    void computeAndFail();
}
