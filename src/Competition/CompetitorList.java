package Competition;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

//demonstrates using an ArrayList
public class CompetitorList {

    private final ArrayList<Competitor> competitorList;

    //create an empty arraylist
    public CompetitorList() {
        competitorList = new ArrayList<Competitor>();
    }

    public void add(Competitor c) {
        competitorList.add(c);
    }

    public ArrayList<Competitor> getCompetitorList() {
        return competitorList;
    }


    //returns a report with one line per person
    //demonstrates traversing the array,
    //getting one element at a time
    public String getAllCompetitors() {
        String report = "";
        for (Competitor c : competitorList) {
            report += String.format("%-6s", c.getcID());
            report += String.format("%-20s", c.getName().getFullName());
            report += String.format("%-10s", c.getLevel());
            report += String.format("%-6s", c.getCountry());
            report += String.format("%-4d", c.getAge());
            report += String.format("%-10s", c.getOverallScore());
            report += String.format("%-10s", c.getAverageScore());
            report += String.format("%-4s", c.getMaxScore());
            report += String.format("%-4s", c.getMinScore());
            report += "\n";
        }
        return report;
    }

    //returns the number of elements in the list
    public int getSize() {
        return competitorList.size();
    }

    //returns the Staff object with a specified id
    //demonstrates searching through the array
    //and stopping by returning when a match is found
    public Competitor findById(int id) {
        for (Competitor c : competitorList) {
            if (c.getcID() == id) {
                return c;
            }
        }
        return null;
    }

    public int generateID() {
        int max = 0;
        for (Competitor c : competitorList) {
            if (c.getcID() > max) {
                max = c.getcID();
            }
        }
        return max + 1;
    }
    public int getMaxScore() {
        int maxScore = 0;
        for (Competitor c: competitorList) {
            double score = c.getOverallScore();
            if (score > maxScore) {
                maxScore= (int) score;
            }
        }
        return maxScore;
    }

    public String getScoreFrequencyReport() {
        // Create an array to store all scores
        ArrayList<Integer> allScores = new ArrayList<>();
        for (Competitor c : competitorList) {
            int[] scores = c.getScoreArray();
            for (int score : scores) {
                allScores.add(score);
            }
        }
        // Create the frequency map to store the frequency of each score
        Map<Integer, Integer> freqScores = new HashMap<>();
        for (int score : allScores) {
            if (!freqScores.containsKey(score)) {
                freqScores.put(score, 1);
            } else {
                freqScores.put(score, freqScores.get(score) + 1);
            }
        }
        // Create the frequency report string
        String report = "Score\tFrequency\n";
        for (Map.Entry<Integer, Integer> entry : freqScores.entrySet()) {
            report += entry.getKey() + "\t" + entry.getValue() + "\n";
        }
        return report;
    }


    /**
     * writes supplied text to file
     *
     * @param filename the name of the file to be written to
     * @param report   the text to be written to the file
     */
    public void writeToFile(String filename, String report) throws IOException {

        FileWriter fw;
        {
            fw = new FileWriter(filename);
            fw.write("The report\n");
            fw.write(report);
            fw.close();
        }

    }

    /**
     * reads file with given name, extracting Competition.Competitor data, creating Competition.Competitor objects
     * and adding them to the list of Competitors
     * Blank lines are skipped
     * Validation for integer year, missing items
     *
     * @param filename the name of the input file
     */
    public void readFile(String filename) throws FileNotFoundException {
            File f = new File(filename);
            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                //read first line and process it
                String inputLine = scanner.nextLine();
                if (inputLine.length() != 0) {//ignored if blank line
                    processLine(inputLine);
                }

            }

    }

    /**
     * Processes line, extracts data, creates Competition.Competitor object
     * and adds to list
     * Checks for non-numeric year and missing items
     * Will still crash if name entered without a space
     *
     * @param line the line to be processed
     */
    private void processLine(String line) {
        try {
            String[] parts = line.split(",");
            Name name = new Name(parts[1]);
            int cID = Integer.parseInt(parts[0]);
            String ageNum = parts[2];
            int age = Integer.parseInt(ageNum);
            String country = parts[4];
            String level = parts[3];


            //the qualifications are at the end of the line
            int qualLength = parts.length - 5;
            String[] score = new String[qualLength];

//            int score = Integer.parseInt(parts[qualLength]);
            System.arraycopy(parts, 5, score, 0, qualLength);

            //create Competition.Competitor object and add to the list
            Competitor c = new Competitor(cID, name, level, country, age, score);
            this.add(c);
        }

        //for these two formatting errors, ignore lines in error and try and carry on

        //this catches trying to convert a String to an integer
        catch (NumberFormatException nfe) {
            String error = "Number conversion error in '" + line + "'  - "
                    + nfe.getMessage();
            System.out.println(error);
        }
        //this catches missing items if only one or two items
        //other omissions will result in other errors
        catch (ArrayIndexOutOfBoundsException air) {
            String error = "Not enough items in  : '" + line
                    + "' index position : " + air.getMessage();
            System.out.println(error);
        }

    }

}
