package CompetitorInterface;

import Competition.Competitor;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Summary extends JFrame {
    private JTextArea textArea;
    private Competitor competitor;

    public Summary(String text) {
        this.competitor = competitor;
        setTitle("Competitor Details");
        setSize(new Dimension(700, 150));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setText(text);
        textArea.setEditable(false);
        add(textArea, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panel.add(closeButton);
        add(panel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
