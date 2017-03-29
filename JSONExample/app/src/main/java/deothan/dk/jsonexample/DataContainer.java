package deothan.dk.jsonexample;

/**
 * Created by Mads Riisom on 29-03-2017.
 */

public class DataContainer {
    private int id;
    private String name;

    public DataContainer() {

    }

    public DataContainer(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
