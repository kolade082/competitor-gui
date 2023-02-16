package tests;

import Competition.Competitor;
import Competition.CompetitorList;
import Competition.Name;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.*;


import static org.junit.Assert.*;

public class CompetitorListTest {

    private CompetitorList competitorList;
    private Competitor competitor2;
    private Competitor competitor1;


    @Before
    public void setUp() throws Exception {
        Name name1 = new Name("Dara", "Kolade");
        String[] score1 = new String[]{"2", "4", "2", "9"};
        this.competitorList = new CompetitorList();
        this.competitor1 = new Competitor(100, name1, "Pro", "UK", 25, score1);

        Name name2 = new Name("Dara", "Kolade");
        String[] score2 = new String[]{"4", "5", "2", "9"};
        this.competitor2 = new Competitor(101, name2, "Expert", "US", 28, score2);
    }

    @Test
    public void add() {
        competitorList.add(competitor1);
        competitorList.add(competitor2);

        // Assert the size of the competitorList
        assertEquals(2, competitorList.getSize());
    }

    @Test
    public void getCompetitorList() {
        this.competitorList.add(competitor1);
        this.competitorList.add(competitor2);

        // Assert the size of the competitorList
        assertEquals(2, competitorList.getSize());
        // Assert that the first competitor in the list has the same ID as competitor1
        assertEquals(100, competitorList.getCompetitorList().get(0).getcID());
        // Assert that the second competitor in the list has the same ID as competitor2
        assertEquals(101, competitorList.getCompetitorList().get(1).getcID());
    }

    @Test
    public void getAllCompetitors() {
        competitorList.add(competitor1);
        competitorList.add(competitor2);

        assertEquals(2, competitorList.getSize());
        assertNotNull(competitorList.getAllCompetitors());
    }

    @Test
    public void getSize() {
        competitorList.add(competitor1);
        assertEquals(1, competitorList.getSize());
    }

    @Test
    public void findById() {
        competitorList.add(competitor1);
        // When
        Competitor foundCompetitor = competitorList.findById(100);
        // Then
        assertEquals(competitor1, foundCompetitor);

        // testing for a non-exisiting id
        Competitor notFoundCompetitor = competitorList.findById(102);
        assertNull(notFoundCompetitor);

    }

    @Test
    public void generateID() {
        competitorList.add(competitor1);
        competitorList.add(competitor2);
// generate id for the next applicant
        int generatedID = competitorList.generateID();

        assertEquals(102, generatedID);
    }
    @Test
    public void getMaxScore(){
        competitorList.add(competitor1);
        competitorList.add(competitor2);

        assertEquals(20, competitorList.getMaxScore());
    }
    @Test
    public void getScoreFrequencyReport() {
        // adds competitor
        competitorList.add(competitor1);
        competitorList.add(competitor2);
        //creates report based on the scores in the array
        String expectedReport = "Score\tFrequency\n" +
                "2\t3\n" +
                "4\t2\n" +
                "5\t1\n" +
                "9\t2\n";
        String actualReport = competitorList.getScoreFrequencyReport();

        assertEquals(expectedReport, actualReport);
    }

    @Test
    public void writeToFile() throws IOException {
        String report = competitorList.getAllCompetitors();
        File file = new File("test.txt");
        if (file.exists()) {
            file.delete();
        }
        competitorList.writeToFile("test.txt", report);
        assertTrue(file.exists());
    }
    @Test
    public void readFile() throws FileNotFoundException {
        competitorList.readFile("list.csv");
        assertEquals(29, competitorList.getSize());
    }
}