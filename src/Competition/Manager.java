package Competition;

import CompetitorInterface.GuiCompetitor;

import java.io.FileNotFoundException;
import java.util.Scanner;
public class Manager {

    public static void main(String[] args) throws FileNotFoundException {
        CompetitorList football = new CompetitorList();
        football.readFile("list.csv");
        new GuiCompetitor(football);

    }
}
