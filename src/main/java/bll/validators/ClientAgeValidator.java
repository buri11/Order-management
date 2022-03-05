package bll.validators;

import model.Client;

/**
 * The type Client age validator.
 *
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class ClientAgeValidator implements Validator<Client> {
	private static final int MIN_AGE = 17;
	private static final int MAX_AGE = 40;

	public void validate(Client c) {

		if (c.getAge() < MIN_AGE || c.getAge() > MAX_AGE) {
			throw new IllegalArgumentException("The Student Age limit is not respected!");
		}

	}

}
