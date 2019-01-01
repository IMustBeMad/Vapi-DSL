package validation.api.demo.validation;

public interface ChainFunction<P> {

   ChainFunction<P> and();

   ChainFunction<P> or();

    void validate();

    void failFast();

    void computeFails();
}
