package bll.validators;

/**
 * The interface Validator.
 *
 * @param <T> the type parameter
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public interface Validator<T> {

    /**
     * Validate.
     *
     * @param t the t
     */
    public void validate(T t);
}
