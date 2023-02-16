package CompetitorInterface;

import Competition.Competitor;
import Competition.CompetitorList;
import Competition.Name;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.Arrays;


public class AddCompetitor extends JFrame {
    private final CompetitorList competitorList;
    private final JTable table;

    public AddCompetitor(CompetitorList competitorList, JTable table) {
        this.competitorList = competitorList;
        this.table = table;
        JPanel panel = new JPanel(new GridLayout(0, 2));


        setTitle("Add Competitor");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JTextField idField = new JTextField(20);
        JTextField firstNameField = new JTextField(20);
        JTextField lastNameField = new JTextField(20);
        JTextField ageField = new JTextField(20);
        JComboBox levelBox = new JComboBox(new String[] {"Novice", "Amateur", "Expert", "Pro"});
        JTextField countryField = new JTextField(20);
        JTextField scoreField = new JTextField(20);

        JLabel idLabel = new JLabel("ID:");
        JLabel firstNameLabel = new JLabel("First Name:");
        JLabel lastNameLabel = new JLabel("Last Name:");
        JLabel ageLabel = new JLabel("Age:");
        JLabel levelLabel = new JLabel("Level:");
        JLabel countryLabel = new JLabel("Country:");
        JLabel scoreLabel = new JLabel("Score:");

        panel.add(firstNameLabel);
        panel.add(firstNameField);
        panel.add(lastNameLabel);
        panel.add(lastNameField);
        panel.add(ageLabel);
        panel.add(ageField);
        panel.add(levelLabel);
        panel.add(levelBox);
        panel.add(countryLabel);
        panel.add(countryField);
        panel.add(scoreLabel);
        panel.add(scoreField);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());
        panel.add(closeButton);

        JButton performActionButton = new JButton("Add");
        performActionButton.addActionListener(e -> {
            // Validate the input fields
            if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() ||
                    ageField.getText().isEmpty() || countryField.getText().isEmpty() || scoreField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are mandatory. Please resubmit the form.");
                return;
            }

            int id = competitorList.generateID();
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String level = (String) levelBox.getSelectedItem();
            String country = countryField.getText();
            String[] score = scoreField.getText().split(",");

            Name name = new Name(firstName, lastName);
            Competitor competitor = new Competitor(id, name, level, country, age, score);

            competitorList.add(competitor);

            // Update the table with the new competitor
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.addRow(new Object[]{competitor.getcID(), competitor.getName().getFullName(),
                    competitor.getLevel(), competitor.getCountry(), competitor.getAge(),
                    Arrays.toString(competitor.getScoreArray()), competitor.getOverallScore(), competitor.getAverageScore()});

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("list.csv", true))) {
                bw.write(competitor.getcID() + "," + competitor.getName().getFullName() + "," + competitor.getAge() + ","
                        + competitor.getLevel() + "," + competitor.getCountry() + ","
                        + Arrays.toString(competitor.getScoreArray()).replace("[", "").replace("]", "").replace(" ", "") + "\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            dispose();
        });

        panel.add(performActionButton);

        add(panel, BorderLayout.SOUTH);
        setVisible(true);

        }
}

