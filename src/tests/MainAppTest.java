package tests;

import Competition.MainApp;
import org.junit.Before;
import org.junit.Test;

import Competition.Competitor;
import Competition.CompetitorList;
import Competition.Name;

import java.io.*;

import static org.junit.Assert.*;

public class MainAppTest {
    private CompetitorList competitorList;
    private Competitor competitor1;
    private Competitor competitor2;
    private Competitor competitor3;

    @Before
    public void setUp() throws Exception {
        Name name1 = new Name("Dara", "Kolade");
        String[] score1 = new String[]{"2", "4", "2", "9"};
        this.competitorList = new CompetitorList();
        this.competitor1 = new Competitor(100, name1, "Pro", "UK", 25, score1);

        Name name2 = new Name("Dara", "Kolade");
        String[] score2 = new String[]{"4", "5", "2", "9"};
        this.competitor2 = new Competitor(101, name2, "Expert", "US", 28, score2);

        Name name3 = new Name("Jo", "Borg");
        String[] score3 = new String[]{"4", "5", "2", "9"};
        this.competitor3 = new Competitor(102, name3, "Novice", "US", 28, score3);
    }


    @Test
    public void main() throws FileNotFoundException, UnsupportedEncodingException {
        competitorList.add(competitor1);
        competitorList.add(competitor2);
        competitorList.add(competitor3);

        InputStream testInput = new ByteArrayInputStream("100\nyes\n101\nno\n".getBytes("UTF-8"));
        System.setIn(testInput);

        ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOutput));

        MainApp.main(null);

        String expectedOutput = "Enter a competitor number: \n" + competitor1.getShortDetails() +
                "\nDo you want to enter another competitor ID? (yes/no)\n" +
                "Enter a competitor number: \n" + competitor2.getShortDetails() +
                "\nDo you want to enter another competitor ID? (yes/no)\n";
        String actualOutput = testOutput.toString();

        assertEquals(expectedOutput, actualOutput);
    }
}