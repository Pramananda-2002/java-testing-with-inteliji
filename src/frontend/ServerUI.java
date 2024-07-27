package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerUI implements UI {
    static JTextField textField;
    static JButton button;
    static JButton createClient;
    static JLabel title;
    static JLabel label;
    static JFrame frame;
    UIProvider uiProvider;
    ServerUI(){
        uiProvider= UIProvider.getInstance();
        textField = new JTextField(40);
        button = new JButton("Add");
        createClient = new JButton("Create Client");
        label =new JLabel("");
        title = new JLabel("Server");
        frame = new JFrame("Server Site");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String sentence = textField.getText();
                if(uiProvider.addSentence(sentence)){
                    label.setText(" \""+sentence+ "\" add to the database successfully");
                }
                else{
                    label.setText("something went wrong");
                }

                textField.setText(" ");
            }
        });

        createClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new ClientUIHandler().run();
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(650,300);
        frame.setResizable(true);
        frame.setLayout(new GridLayout(4,1));
        JPanel titlePanel = new JPanel();
        JPanel inputPanel = new JPanel();
        JPanel outputPanel = new JPanel();
        JPanel clientPanel = new JPanel();




        titlePanel.add(title);
        inputPanel.add(textField);
        inputPanel.add(button);
        outputPanel.add(label);
        clientPanel.add(createClient);
        frame.add(titlePanel);
        frame.add(inputPanel);
        frame.add(outputPanel);
        frame.add(clientPanel);
        frame.setSize(600,500);
        frame.setVisible(true);
    }



}
