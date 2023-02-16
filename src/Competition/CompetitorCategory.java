package Competition;

public abstract class CompetitorCategory {


    private int cID;
    private Name name;
    private String level;
    private String country;
    private int age;

    public CompetitorCategory(int cID, Name name, String level, String country, int age) {
        this.cID = cID;
        this.name = name;
        this.level = level;
        this.country = country;
        this.age = age;
    }

    public int getcID() {
        return cID;
    }

    public void setcID(int cID) {
        this.cID = cID;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public abstract double getAverageScore();

    public abstract double getOverallScore();

    public abstract int getMaxScore();

    public abstract int getMinScore();

    public abstract void setScoreArray(String[] split);
}
