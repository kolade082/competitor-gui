package tests;

import Competition.Competitor;
import Competition.Name;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NameTest {
    private Competitor competitor;
    @Before
    public void setUp() throws Exception {
        Name name = new Name("Dara", "Sam","Kolade");
        Name name2 = new Name("Dara Kolade");
        Name name3 = new Name("Dara Sam Kolade");
        String[] score = new String[]{"2", "4", "6", "9"};
        this.competitor = new Competitor(100, name, "Pro", "UK", 25, score);
    }
    @Test
    public void getFirstName() {
        assertEquals("Dara", competitor.getName().getFirstName());
    }

    @Test
    public void setFirstName() {
        competitor.getName().setFirstName("Lothbrok");
        assertEquals("Lothbrok", competitor.getName().getFirstName());
    }

    @Test
    public void getMiddleName() {
        assertEquals("Sam", competitor.getName().getMiddleName());
    }

    @Test
    public void setMiddleName() {
        competitor.getName().setMiddleName("Drib");
        assertEquals("Drib", competitor.getName().getMiddleName());
    }

    @Test
    public void getLastName() {
        assertEquals("Kolade", competitor.getName().getLastName());
    }

    @Test
    public void setLastName() {
        competitor.getName().setLastName("Kora");
        assertEquals("Kora", competitor.getName().getLastName());
    }

    @Test
    public void getFirstAndLastName() {
        assertEquals("Dara Kolade", competitor.getName().getFirstAndLastName());
    }

    @Test
    public void getLastCommaFirst() {

        assertEquals("Kolade, Dara", competitor.getName().getLastCommaFirst());
    }

    @Test
    public void getFullName() {
        assertEquals("Dara Sam Kolade", competitor.getName().getFullName());
    }

    @Test
    public void testEquals() {
    }

    @Test
    public void setFullName() {
        Name expectedName = new Name("Bray", "Troy", "Lane");
        competitor.setName(expectedName);
        assertEquals("Bray Troy Lane", competitor.getName().getFullName());
    }
}