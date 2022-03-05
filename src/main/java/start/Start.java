package start;

import java.sql.SQLException;
import java.util.logging.Logger;

import presentation.ControllerClient;
import presentation.ControllerGeneral;
import presentation.ViewGeneral;

/**
 * The type Start.
 *
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class Start {
    /**
     * The constant LOGGER.
     */
    protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws SQLException the sql exception
     */
    public static void main(String[] args) throws SQLException {
		ViewGeneral v = new ViewGeneral();
		ControllerGeneral c = new ControllerGeneral(v);


		//Client c = new Client("Makai Alex", "alex.alex@yahoo.com", 25);
		/*
		ClientBLL clientBLL = new ClientBLL();
		List<Client> list = clientBLL.findAllClients();
		for(Client c : list){
			System.out.println(c.toString());
		}
		*/

		//clientBLL.updateClientName(new Client(9, "geani", "orice", 45), "Geoarge Alex");
		//clientBLL.insertClient(c);
		//clientBLL.deleteClient(new Client(6, "geani", "makai", 34));
		//System.out.println("Client id is " + c.getId());
		//System.out.println("Client with id 2 is: " + clientBLL.findClientById(5).toString());
		/*
		Student student = new Student("dummy name", "dummy address", "dummy@address.co", 12);

		StudentBLL studentBll = new StudentBLL();
		int id = studentBll.insertStudent(student);
		if (id > 0) {
			studentBll.findStudentById(id);
		}
		
		*//*
		// Generate error
		try {
			studentBll.findStudentById(8);
		} catch (Exception ex) {
			LOGGER.log(Level.INFO, ex.getMessage());
		}


		 *//*

		
		
		//obtain field-value pairs for object through reflection
		ReflectionExample.retrieveProperties(student);
		*/

	}
	
	

}
