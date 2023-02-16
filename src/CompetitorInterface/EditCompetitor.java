package CompetitorInterface;

import Competition.Competitor;
import Competition.CompetitorList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class EditCompetitor extends JFrame {
    private CompetitorList competitorList;
    private final JTable table;
    private Iterable<? extends Competitor> competitors;

    public EditCompetitor(CompetitorList competitorList, JTable table) {
        this.competitorList = competitorList;
        this.table = table;

        setTitle("Edit Competitor Details");

        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            // Get the competitor ID from the selected row
            int id = (int) table.getValueAt(selectedRow, 0);
            Competitor competitor = competitorList.findById(id);


            // Create text fields for the new information
            JTextField firstNameField = new JTextField(competitor.getName().getFirstName(), 20);
            JTextField lastNameField = new JTextField(competitor.getName().getLastName(), 20);
            JComboBox levelBox = new JComboBox(new String[] {"Novice", "Amateur", "Expert", "Pro"});
            JTextField countryField = new JTextField(competitor.getCountry(), 20);
            JTextField ageField = new JTextField(Integer.toString(competitor.getAge()), 20);
            JTextField scoreField = new JTextField(Arrays.toString(competitor.getScoreArray()), 20);

            // Create the input dialog
            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new GridLayout(0, 2, 10, 10));
            inputPanel.add(new JLabel("First Name:"));
            inputPanel.add(firstNameField);
            inputPanel.add(new JLabel("Last Name:"));
            inputPanel.add(lastNameField);
            inputPanel.add(new JLabel("Level:"));
            inputPanel.add(levelBox);
            inputPanel.add(new JLabel("Country:"));
            inputPanel.add(countryField);
            inputPanel.add(new JLabel("Age:"));
            inputPanel.add(ageField);
            inputPanel.add(new JLabel("Score:"));
            inputPanel.add(scoreField);

            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(e -> dispose());
            inputPanel.add(closeButton);

            JButton saveButton = new JButton("Save");
            saveButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Update the competitor's information
                    competitor.getName().setFirstName(firstNameField.getText());
                    competitor.getName().setLastName(lastNameField.getText());
                    competitor.setLevel((String) levelBox.getSelectedItem());
                    competitor.setCountry(countryField.getText());
                    competitor.setAge(Integer.parseInt(ageField.getText()));
                    competitor.setScoreArray(scoreField.getText().replace("[", "").
                            replace("]", "").split(","));

                    // Update the table
                    table.setValueAt(competitor.getcID(), selectedRow, 0);
                    table.setValueAt(competitor.getName().getFullName(), selectedRow, 1);
                    table.setValueAt(competitor.getLevel(), selectedRow, 2);
                    table.setValueAt(competitor.getCountry(), selectedRow, 3);
                    table.setValueAt(competitor.getAge(), selectedRow, 4);
                    table.setValueAt(Arrays.toString(competitor.getScoreArray()), selectedRow, 5);
                    table.setValueAt(competitor.getOverallScore(), selectedRow, 6);
                    table.setValueAt(competitor.getAverageScore(), selectedRow, 7);
                    writeToFile();

                    dispose();
                }
            });
            inputPanel.add(saveButton);

            add(inputPanel, BorderLayout.CENTER);
            setVisible(true);
        }
        else {
            // Show a dialog to indicate that a row must be selected
            JOptionPane.showMessageDialog(null, "Please select a row to edit");
        }
    }

    public void writeToFile() {
        try (FileWriter writer = new FileWriter("list.csv")) {
            for (Competitor competitor : this.competitorList.getCompetitorList()) {
                writer.write(competitor.getcID() + "," + competitor.getName().getFullName() + ","
                        + competitor.getAge() + "," + competitor.getLevel() + "," + competitor.getCountry() + ","
                        + Arrays.toString(competitor.getScoreArray()).replace
                        ("[", "").replace("]", "").replace(" ", "") + "\n");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




