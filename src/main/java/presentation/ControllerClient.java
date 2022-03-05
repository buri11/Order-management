package presentation;

import bll.ClientBLL;
import model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The type Controller client.
 */
public class ControllerClient {
    private ViewClient v;
    private ClientBLL clientBLL;

    /**
     * Instantiates a new Controller client.
     *
     * @param v the v
     */
    public ControllerClient(ViewClient v){
        this.v = v;
        clientBLL = new ClientBLL();
        v.getAddClient().addActionListener(new AddClientListener());
        v.getEditClient().addActionListener(new EditClientListener());
        v.getDeleteClient().addActionListener(new DeleteClientListener());

        v.getAddClientButton().addActionListener(new AddClientToDBListener());
        v.getEditClientButton().addActionListener(new EditClientDBListener());
        v.getDelClientButon().addActionListener(new DelClientDBListener());
    }

    private class DelClientDBListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int clientID = Integer.parseInt(v.getClientDelIdTF().getText());
            Client c = clientBLL.findClientById(clientID);
            clientBLL.deleteClient(c);

            JScrollPane scrollPane;
            //update view
            v.getpTabel().remove(v.getScrollPane());
            v.setTabel(v.populateJTable());
            scrollPane = new JScrollPane(v.getTabel());
            scrollPane.setPreferredSize(new Dimension(500, 250));
            v.setScrollPane(scrollPane);
            v.getpTabel().add(v.getScrollPane());
            v.getFrameClient().pack();
            v.getFrameClient().setLocationRelativeTo(null);
        }
    }

    private class EditClientDBListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int clientID = Integer.parseInt(v.getClientEditIdTF().getText());
            Client c = clientBLL.findClientById(clientID);

            String fieldToUpdate = (String)v.getCb().getSelectedItem();
            Object newVal = v.getNewValTF().getText();
            if(fieldToUpdate.equals("age")){
                newVal = Integer.parseInt((String)newVal);
            }

            clientBLL.updateClient(c, fieldToUpdate, newVal);

            JScrollPane scrollPane;
            //update view
            v.getpTabel().remove(v.getScrollPane());
            v.setTabel(v.populateJTable());
            scrollPane = new JScrollPane(v.getTabel());
            scrollPane.setPreferredSize(new Dimension(500, 250));
            v.setScrollPane(scrollPane);
            v.getpTabel().add(v.getScrollPane());
            v.getFrameClient().pack();
            v.getFrameClient().setLocationRelativeTo(null);
        }
    }

    private class AddClientToDBListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = v.getNameTF().getText();
            String email = v.getEmailTF().getText();
            int age = Integer.parseInt(v.getAgeTF().getText());
            Client client = new Client(name, email, age);
            clientBLL.insertClient(client);

            JScrollPane scrollPane;
            //update view
            v.getpTabel().remove(v.getScrollPane());
            v.setTabel(v.populateJTable());
            scrollPane = new JScrollPane(v.getTabel());
            scrollPane.setPreferredSize(new Dimension(500, 250));
            v.setScrollPane(scrollPane);
            v.getpTabel().add(v.getScrollPane());
            v.getFrameClient().pack();
            v.getFrameClient().setLocationRelativeTo(null);
        }
    }

    private class AddClientListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton buton = v.getAddClient();
            JPanel pGeneral = v.getpGeneralClient();
            if(buton.isSelected()){
                pGeneral.remove(v.getpEditClient());
                pGeneral.remove(v.getpDeleteClient());

                pGeneral.add(v.getpAddClient(), 1);
            }
            v.getFrameClient().pack();
            v.getFrameClient().setLocationRelativeTo(null);
        }
    }

    private class EditClientListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton buton = v.getEditClient();
            JPanel pGeneral = v.getpGeneralClient();
            if(buton.isSelected()){
                pGeneral.remove(v.getpAddClient());
                pGeneral.remove(v.getpDeleteClient());

                pGeneral.add(v.getpEditClient(), 1);
            }
            v.getFrameClient().pack();
            v.getFrameClient().setLocationRelativeTo(null);
        }
    }

    private class DeleteClientListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton buton = v.getDeleteClient();
            JPanel pGeneral = v.getpGeneralClient();
            if(buton.isSelected()){
                pGeneral.remove(v.getpAddClient());
                pGeneral.remove(v.getpEditClient());

                pGeneral.add(v.getpDeleteClient(), 1);
            }
            v.getFrameClient().pack();
            v.getFrameClient().setLocationRelativeTo(null);
        }
    }

}
