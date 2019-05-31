package nl.saxion.jm.zeldasite.model.helper;

public class Type {

    private String name;
    private int id;
    private static int idList = 0;

    public Type(String name)
    {
        this.name = name;

        id = idList;
        id++;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
