package Competition;

public class Competitor extends CompetitorCategory {
    private String[] score;

    public Competitor(int cID, Name name, String level, String country, int age, String[] score) {
        super(cID, name, level, country, age);
        this.score = score;
    }


    public double getAverageScore() {
        double sum = 0;
        for (int i : this.getScoreArray()) {
            sum += i;
        }
        double average = (sum / this.getScoreArray().length);
        return average;
    }
    public double getOverallScore() {
        double sum = 0;
        int[] scores = this.getScoreArray();
        for (int i : scores) {
            sum += i;
        }
        return sum;
    }

    public int getMaxScore() {
        int maxScore = Integer.MIN_VALUE;
        for (int score : this.getScoreArray()) {
            if (score > maxScore) {
                maxScore = score;
            }
        }
        return maxScore;
    }
    public int getMinScore() {
        int minScore = Integer.MAX_VALUE;
        for (int score : this.getScoreArray()) {
            if (score < minScore) {
                minScore = score;
            }
        }
        return minScore;
    }

    public String getFullDetails() {
        return "\nCompetitor number " + this.getcID() + "," +
                this.getName().getFullName() + " from " + this.getCountry() + " . "
                + this.getName().getFirstName() + " is an " + this.getLevel() + " aged "
                + this.getAge() + " and has an overall score of " + this.getOverallScore();
    }


    public String getShortDetails() {
        return "\nCN " + this.getcID() + "," + this.getName().getFirstName()
                + " has an overall score of " + this.getOverallScore();
    }

    public int[] getScoreArray() {
        String[] strScore = this.score;
        int[] intScore = new int[strScore.length];

        for (int i = 0; i < strScore.length; i++) {
            intScore[i] = Integer.parseInt(strScore[i].trim());
        }
        return intScore;
    }

    @Override
    public void setScoreArray(String[] split) {
        this.score = split;
    }

}