package tests;

import Competition.Competitor;
import Competition.Name;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CompetitorCategoryTest {
    private Competitor competitor;
    @Before
    public void setUp() throws Exception {
        Name name = new Name("Dara", "Kolade");
        String[] score = new String[]{"2", "4", "6", "9"};
        this.competitor = new Competitor(100, name, "Pro", "UK", 25, score);
    }

    @Test
    public void getcID() {
        assertEquals(100, competitor.getcID());
    }

    @Test
    public void setcID() {
        competitor.setcID(200);
        assertEquals(200, competitor.getcID());
    }

    @Test
    public void getName() {
        Name expected = new Name("Dara","Kolade");
        assertEquals(expected, competitor.getName());
    }

    @Test
    public void setName() {
        Name expectedName = new Name("Danger", "Dash");
        competitor.setName(expectedName);
        assertEquals(expectedName, competitor.getName());
    }

    @Test
    public void getLevel() {
        assertEquals("Pro", competitor.getLevel());
    }

    @Test
    public void setLevel() {
        competitor.setLevel("Expert");
        assertEquals("Expert", competitor.getLevel());
    }

    @Test
    public void getCountry() {
        assertEquals("UK", competitor.getCountry());
    }

    @Test
    public void setCountry() {
        competitor.setCountry("Nig");
        assertEquals("Nig", competitor.getCountry());
    }

    @Test
    public void getAge() {
        assertEquals(25, competitor.getAge());
    }

    @Test
    public void setAge() {
        competitor.setAge(30);
        assertEquals(30, competitor.getAge());
    }


}