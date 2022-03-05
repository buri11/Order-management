package presentation;


import bll.ClientBLL;
import model.Client;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The type View client.
 */
public class ViewClient {
    private JFrame frameClient = new JFrame("Client operations");
    private JPanel pGeneralClient = new JPanel();
    private JPanel pSelectOpClient = new JPanel();

    private JLabel selectOp = new JLabel("Select operation:");
    private JRadioButton addClient = new JRadioButton("Add new client");
    private JRadioButton editClient = new JRadioButton("Edit a client");
    private JRadioButton deleteClient = new JRadioButton("Delete a client");
    private ButtonGroup radioClient = new ButtonGroup();
    private JPanel bttnGroup = new JPanel();
    private String columnsClient[] = {"id", "name", "email", "age"};
    private JTable tabel;
    private JScrollPane scrollPane;
    private JPanel pTabel = new JPanel();
    private ClientBLL clientBLL = new ClientBLL();

    private JPanel pAddClient = new JPanel();
    //add client panel
    private JLabel nameLabel = new JLabel("Name:");
    private JTextField nameTF = new JTextField(15);
    private JLabel emailLabel = new JLabel("Email:");
    private JTextField emailTF = new JTextField(15);
    private JLabel ageLabel = new JLabel("Age:");
    private JTextField ageTF = new JTextField(10);
    private JButton addClientButton = new JButton("ADD");

    private JPanel pEditClient = new JPanel();
    //edit client panel
    private JLabel clientToEditLabel = new JLabel("Type the client's id you want to edit");
    private JTextField clientEditIdTF = new JTextField(5);
    private JLabel selectLabel = new JLabel("Select field to edit");
    private String []cbOptions = {"name", "email", "age"};
    private JComboBox cb = new JComboBox(cbOptions);
    private JLabel newValLabel = new JLabel("New value:");
    private JTextField newValTF = new JTextField(15);
    private JButton editClientButton = new JButton("EDIT");

    private JPanel pDeleteClient = new JPanel();
    //delete client panel
    private JLabel clientToDelLabel = new JLabel("Type the client's id you want to delete");
    private JTextField clientDelIdTF = new JTextField(5);
    private JButton delClientButon = new JButton("DELETE");

    /**
     * Instantiates a new View client.
     */
    public ViewClient(){
        frameClient.getContentPane().setLayout(new BorderLayout());
        //frameClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameClient.setMinimumSize(new Dimension(200,200));
        frameClient.setLocationRelativeTo(null);

        pSelectOpClient.setLayout(new GridLayout(0,1));
        selectOp.setHorizontalAlignment(SwingConstants.CENTER);
        pSelectOpClient.add(selectOp);

        radioClient.add(addClient);
        radioClient.add(editClient);
        radioClient.add(deleteClient);

        bttnGroup.setLayout(new GridLayout(0, 3));
        bttnGroup.add(addClient);
        bttnGroup.add(editClient);
        bttnGroup.add(deleteClient);
        pSelectOpClient.add(bttnGroup);

        pGeneralClient.add(pSelectOpClient);
        tabel = this.populateJTable();
        scrollPane = new JScrollPane(tabel);
        scrollPane.setPreferredSize(new Dimension(500, 250));
        pTabel.add(scrollPane);
        pGeneralClient.add(pTabel);
        pGeneralClient.setLayout(new BoxLayout(pGeneralClient, BoxLayout.Y_AXIS));

        frameClient.setContentPane(pGeneralClient);
        frameClient.setVisible(true);
        frameClient.pack();

        //set-up add client panel
        pAddClient.setLayout(new GridLayout(1,0));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pAddClient.add(nameLabel);
        pAddClient.add(nameTF);
        emailLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pAddClient.add(emailLabel);
        pAddClient.add(emailTF);
        ageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pAddClient.add(ageLabel);
        pAddClient.add(ageTF);
        pAddClient.add(addClientButton);

        //set-up edit client panel
        pEditClient.setLayout(new GridLayout(1,0));
        pEditClient.add(clientToEditLabel);
        pEditClient.add(clientEditIdTF);
        selectLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pEditClient.add(selectLabel);
        pEditClient.add(cb);
        newValLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pEditClient.add(newValLabel);
        pEditClient.add(newValTF);
        pEditClient.add(editClientButton);

        //set-up del client panel
        pDeleteClient.setLayout(new GridLayout(1,0));
        clientToDelLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pDeleteClient.add(clientToDelLabel);
        pDeleteClient.add(clientDelIdTF);
        pDeleteClient.add(delClientButon);
    }

    /**
     * Populate j table j table.
     *
     * @return the j table
     */
    protected JTable populateJTable(){
        List<Client> list = clientBLL.findAllClients();
        String [][]data = new String[list.size()][4];
        int index = 0;
        for(Client c : list){
            data[index][0] = String.valueOf(c.getId());
            data[index][1] = c.getName();
            data[index][2] = c.getEmail();
            data[index][3] = String.valueOf(c.getAge());
            index++;
        }

        JTable table = new JTable(data,columnsClient);
        return table;
    }

    /**
     * Gets scroll pane.
     *
     * @return the scroll pane
     */
    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    /**
     * Sets scroll pane.
     *
     * @param scrollPane the scroll pane
     */
    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    /**
     * Gets frame client.
     *
     * @return the frame client
     */
    public JFrame getFrameClient() {
        return frameClient;
    }

    /**
     * Gets general client.
     *
     * @return the general client
     */
    public JPanel getpGeneralClient() {
        return pGeneralClient;
    }

    /**
     * Gets select op client.
     *
     * @return the select op client
     */
    public JPanel getpSelectOpClient() {
        return pSelectOpClient;
    }

    /**
     * Gets add client.
     *
     * @return the add client
     */
    public JPanel getpAddClient() {
        return pAddClient;
    }

    /**
     * Gets edit client.
     *
     * @return the edit client
     */
    public JPanel getpEditClient() {
        return pEditClient;
    }

    /**
     * Gets delete client.
     *
     * @return the delete client
     */
    public JPanel getpDeleteClient() {
        return pDeleteClient;
    }

    /**
     * Gets add client.
     *
     * @return the add client
     */
    public JRadioButton getAddClient() {
        return addClient;
    }

    /**
     * Gets edit client.
     *
     * @return the edit client
     */
    public JRadioButton getEditClient() {
        return editClient;
    }

    /**
     * Gets delete client.
     *
     * @return the delete client
     */
    public JRadioButton getDeleteClient() {
        return deleteClient;
    }

    /**
     * Gets bttn group.
     *
     * @return the bttn group
     */
    public JPanel getBttnGroup() {
        return bttnGroup;
    }

    /**
     * Gets name tf.
     *
     * @return the name tf
     */
    public JTextField getNameTF() {
        return nameTF;
    }

    /**
     * Gets email tf.
     *
     * @return the email tf
     */
    public JTextField getEmailTF() {
        return emailTF;
    }

    /**
     * Gets age tf.
     *
     * @return the age tf
     */
    public JTextField getAgeTF() {
        return ageTF;
    }

    /**
     * Gets add client button.
     *
     * @return the add client button
     */
    public JButton getAddClientButton() {
        return addClientButton;
    }

    /**
     * Gets cb.
     *
     * @return the cb
     */
    public JComboBox getCb() {
        return cb;
    }

    /**
     * Gets new val tf.
     *
     * @return the new val tf
     */
    public JTextField getNewValTF() {
        return newValTF;
    }

    /**
     * Gets edit client button.
     *
     * @return the edit client button
     */
    public JButton getEditClientButton() {
        return editClientButton;
    }

    /**
     * Gets tabel.
     *
     * @return the tabel
     */
    public JPanel getpTabel() {
        return pTabel;
    }

    /**
     * Gets tabel.
     *
     * @return the tabel
     */
    public JTable getTabel() {
        return tabel;
    }

    /**
     * Sets tabel.
     *
     * @param tabel the tabel
     */
    public void setTabel(JTable tabel) {
        this.tabel = tabel;
    }

    /**
     * Gets client edit id tf.
     *
     * @return the client edit id tf
     */
    public JTextField getClientEditIdTF() {
        return clientEditIdTF;
    }

    /**
     * Gets client del id tf.
     *
     * @return the client del id tf
     */
    public JTextField getClientDelIdTF() {
        return clientDelIdTF;
    }

    /**
     * Gets del client buton.
     *
     * @return the del client buton
     */
    public JButton getDelClientButon() {
        return delClientButon;
    }
}
