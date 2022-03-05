package bll;

import bll.validators.EmailValidator;
import bll.validators.ClientAgeValidator;
import bll.validators.Validator;
import dao.AbstractDAO;
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The type Client bll.
 */
public class ClientBLL {
    private List<Validator<Client>> validators;
    private AbstractDAO abstractDAO;

    /**
     * Instantiates a new Client bll.
     */
    public ClientBLL() {
        validators = new ArrayList<>();
        validators.add(new EmailValidator());
        validators.add(new ClientAgeValidator());

        abstractDAO = new AbstractDAO(Client.class);
    }

    /**
     * Delete client.
     *
     * @param c the c
     */
    public void deleteClient(Client c){
        abstractDAO.deleteByID(c.getId());
    }

    /**
     * Update client.
     *
     * @param c      the c
     * @param field  the field
     * @param newVal the new val
     */
    public void updateClient(Client c, String field, Object newVal){
        abstractDAO.update(c, field, newVal);
    }

    /**
     * Find all clients list.
     *
     * @return the list
     */
    public List<Client> findAllClients(){
        return abstractDAO.findAll();
    }

    /**
     * Find client by id client.
     *
     * @param id the id
     * @return the client
     */
    public Client findClientById(int id) {
        Client c = (Client)abstractDAO.findById(id);
        if (c == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return c;
    }

    /**
     * Insert client int.
     *
     * @param client the client
     * @return the int
     */
    public int insertClient(Client client) {
        for (Validator<Client> v : validators) {
            v.validate(client);
        }
        return abstractDAO.insert(client);
    }
}
