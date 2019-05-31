package nl.saxion.jm.zeldasite.model.helper;

public class Game {

    private String name;
    private int releaseYear;

    public Game(String name, int releaseYear)
    {
        this.name = name;
        this.releaseYear = releaseYear;
    }

    @Override
    public String toString()
    {
        return name + " (" + releaseYear + ")";
    }
}
