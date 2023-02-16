package CompetitorInterface;

import Competition.Competitor;
import Competition.CompetitorList;
import Competition.Name;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class ScoreFrequency extends JFrame {
    private CompetitorList competitorList;

    public ScoreFrequency(CompetitorList competitorList) {
        this.competitorList = competitorList;
        JPanel panel = new JPanel();
        Container container = getContentPane();
        JTextArea reportTextArea = new JTextArea();

        reportTextArea.setText(competitorList.getScoreFrequencyReport());
        container.add(reportTextArea, BorderLayout.CENTER);
        JLabel sizeLabel = new JLabel();
        sizeLabel.setText("Number of Competitors: " + competitorList.getSize());
        container.add(sizeLabel, BorderLayout.NORTH);

        setSize(400, 300);
        setVisible(true);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());
        panel.add(closeButton);
        add(panel, BorderLayout.SOUTH);
    }
}
