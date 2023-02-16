package CompetitorInterface;

import Competition.Competitor;
import Competition.CompetitorList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class GuiCompetitor extends JFrame {
    private final CompetitorList competitorList;
    private final JTable table;
    private final JTextField searchField;

    private final JComboBox<String> levelFilter;

    public GuiCompetitor(CompetitorList competitorList) {
        this.competitorList = competitorList;
        setTitle("Competition Table");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create buttons
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton searchButton = new JButton("Search");
        JButton frequencyButton = new JButton("Score Frequency");
        JButton reportButton = new JButton("Generate Report");
        levelFilter = new JComboBox<>(new String[]{"All", "Novice", "Amateur", "Expert", "Pro"});
        JButton fullSummary = new JButton("Full Summary");
        JButton shortSummary = new JButton("Short Summary");

        searchField = new JTextField(10);

        // Create a panel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(searchField);
        buttonPanel.add(searchButton);
        buttonPanel.add(frequencyButton);
        buttonPanel.add(reportButton);
        add(buttonPanel, BorderLayout.NORTH);

        JPanel buttonPanel2 = new JPanel();
        buttonPanel2.add(levelFilter);
        add(buttonPanel2, BorderLayout.SOUTH);
        buttonPanel2.add(fullSummary);
        add(buttonPanel2, BorderLayout.SOUTH);
        buttonPanel2.add(shortSummary);
        add(buttonPanel2, BorderLayout.SOUTH);


        String[] columnNames = {"ID", "Name", "Level", "Country", "Age", "Score", "Overall Score", "Average"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);


        Collections.sort(this.competitorList.getCompetitorList(), new Comparator<Competitor>() {
            @Override
            public int compare(Competitor c1, Competitor c2) {
                return Double.compare(c2.getOverallScore(), c1.getOverallScore());
            }
        });
        for (Competitor c : this.competitorList.getCompetitorList()) {
            Object[] rowData = {c.getcID(), c.getName().getFullName(),
                    c.getLevel(), c.getCountry(), c.getAge(), Arrays.toString(c.getScoreArray()), c.getOverallScore(), c.getAverageScore()};
            model.addRow(rowData);
        }

        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(sorter);

        int columnIndex = 6;
        sorter.setSortable(columnIndex, true);
        sorter.toggleSortOrder(columnIndex);

        // Add action listeners to buttons
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddCompetitor(competitorList, table);
            }
        });
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EditCompetitor(competitorList, table);
            }
        });
        levelFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filterText = levelFilter.getSelectedItem().toString(); // The text to filter by
                sorter.setRowFilter(RowFilter.regexFilter(filterText, 2));
                if (filterText == "All") {
                    sorter.setRowFilter(null);
                }
            }
        });
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String filterText = searchField.getText(); // The text to filter by
                sorter.setRowFilter(RowFilter.regexFilter(filterText, 0, 1, 2, 3, 4));
            }
        });
        shortSummary.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // Get the competitor ID from the selected row
                    int id = (int) table.getValueAt(selectedRow, 0);
                    Competitor competitor = competitorList.findById(id);
                    new Summary(competitor.getShortDetails());
                }
            }
        });
        fullSummary.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // Get the competitor ID from the selected row
                    int id = (int) table.getValueAt(selectedRow, 0);
                    Competitor competitor = competitorList.findById(id);
                    new Summary(competitor.getFullDetails());
                }
            }
        });
        frequencyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ScoreFrequency(competitorList);
            }
        });
        reportButton.addActionListener(e1 -> {
            try {
                competitorList.writeToFile("competitorReport/report.txt", competitorList.getAllCompetitors());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        setVisible(true);
    }
    public void closeGui() {
        this.dispose();
    }


}
