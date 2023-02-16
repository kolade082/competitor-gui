package Competition;

public class Name {
    private String firstName;
    private String middleName;
    private String lastName;

    public Name(String fName, String lName) {
        firstName = fName;
        middleName = "";
        lastName = lName;
    }

    public Name(String fName, String mName, String lName) {
        firstName = fName;
        middleName = mName;
        lastName = lName;
    }

    public Name(String fullName) {
        int spacePos1 = fullName.indexOf(' ');
        firstName = fullName.substring(0, spacePos1);
        int spacePos2 = fullName.lastIndexOf(' ');
        if (spacePos1 == spacePos2)
            middleName = "";
        else
            middleName = fullName.substring(spacePos1 + 1, spacePos2);
        lastName = fullName.substring(spacePos2 + 1);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fn) {
        firstName = fn;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String mn) {
        middleName = mn;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String ln) {
        lastName = ln;
    }

    public String getFirstAndLastName() {
        return firstName + " " + lastName;
    }

    public String getLastCommaFirst() {
        return lastName + ", " + firstName;
    }


    public String getFullName() {
        String result = firstName + " ";
        if (!middleName.equals("")) {
            result += middleName + " ";
        }
        result += lastName;
        return result;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Name)) return false;
        Name name = (Name) o;
        return firstName.equals(name.firstName) &&
                lastName.equals(name.lastName);
    }


}
