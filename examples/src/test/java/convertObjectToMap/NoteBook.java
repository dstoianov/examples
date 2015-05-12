package convertObjectToMap;

/**
 * Created by dstoianov on 2014-11-24, 3:09 PM
 */
public class NoteBook {

    private double numberOfSheets;
    private String description;

    public NoteBook(double numberOfSheets, String description) {
        super();
        this.numberOfSheets = numberOfSheets;
        this.description = description;
    }

    public double getNumberOfSheets() {
        return numberOfSheets;
    }

    public String getDescription() {
        return description;
    }

}

