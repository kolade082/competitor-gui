package tests;

import Competition.Competitor;
import Competition.Name;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

public class CompetitorTest {
    private Competitor competitor;
    @Before
    public void setUp() throws Exception {
        Name name = new Name("Dara", "Kolade");
        String[] score = new String[]{"2", "4", "6", "9"};
        this.competitor = new Competitor(100, name, "Pro", "UK", 25, score);
    }

    @Test
    public void getAverageScore() {
        double expectedAverage = 5.25;
        double actualAverage = competitor.getAverageScore();
        assertEquals(expectedAverage, actualAverage, 0.01);
    }
    @Test
    public void getOverallScore() {
        double expected = 21;
        double actual = competitor.getOverallScore();
        assertEquals(actual, expected, 0.01);
    }
    @Test
    public void getMaxScore() {
        assertEquals(9, competitor.getMaxScore());
    }

    @Test
    public void getMinScore() {
        assertEquals(2, competitor.getMinScore());
    }


    @Test
    public void getFullDetails() {
        String expectedDetails = "\nCompetitor number 100,Dara Kolade from UK . " +
                "Dara is an Pro aged 25 and has an overall score of 21.0";
        assertEquals(competitor.getFullDetails(), expectedDetails);
    }

    @Test
    public void getShortDetails() {
        String expectedResult = "\nCN 100,Dara has an overall score of 21.0";
        String actualResult = competitor.getShortDetails();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getScoreArray() {
        int[] expected = new int[]{2, 4, 6, 9};
        int[] actual = competitor.getScoreArray();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void setScoreArray() {
        String[] newScore = new String[]{"3", "5", "7", "8"};
        competitor.setScoreArray(newScore);
        int[] expected = new int[]{3, 5, 7, 8};
        int[] actual = competitor.getScoreArray();
        assertArrayEquals(expected, actual);
    }
}