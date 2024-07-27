package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientUI implements UI {
        static JTextField textField;
        static JButton button;
        static JLabel title;
        static JTextArea result;
        static JFrame frame;
        UIProvider uiProvider;

    ClientUI(){
        uiProvider = UIProvider.getInstance();
        textField = new JTextField(20);
        button = new JButton("Check");
        result = new JTextArea("Best matched words will be shown here", 10, 30);
        title = new JLabel("Client");
        frame = new JFrame("Client Site");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String word = textField.getText();
                if (!word.isEmpty()) result.setText(uiProvider.getSentences(word));
                textField.setText("");
            }
        });

        frame.setLocation(700, 400);
        frame.setResizable(true);
        frame.setLayout(new BorderLayout(10, 10));

        JPanel titlePanel = new JPanel();
        titlePanel.add(title);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel inputPanel = new JPanel();
        inputPanel.add(textField);
        inputPanel.add(button);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new BorderLayout());
        outputPanel.add(new JScrollPane(result), BorderLayout.CENTER);
        outputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(inputPanel, BorderLayout.CENTER);
        frame.add(outputPanel, BorderLayout.SOUTH);

        frame.setSize(500, 400);
        frame.setVisible(true);
    }





}
