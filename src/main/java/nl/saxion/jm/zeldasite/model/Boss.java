package nl.saxion.jm.zeldasite.model;

import nl.saxion.jm.zeldasite.model.helper.Game;

import java.util.ArrayList;

public class Boss {

    private String name;
    private int id;
    private static int idList = 0;
    private Game seenIn;
    private ArrayList<Item> weapons;
    private ArrayList<Item> spoils;
    private String imageName;
    private String description;

    public Boss(String name, Game seenIn, String imageName, String description)
    {
        id = idList;
        idList++;

        this.name = name;
        this.seenIn = seenIn;
        this.imageName = imageName;
        this.description = description;

        weapons = new ArrayList<>();
        spoils = new ArrayList<>();
    }

    public void removeSpoil(Item spoil)
    {
        spoils.remove(spoil);
    }

    public void addSpoil(Item spoil)
    {
        spoils.add(spoil);
    }

    public void removeWeapon(Item weapon)
    {
        weapons.remove(weapon);
    }

    public void addWeapon(Item weapon)
    {
        weapons.add(weapon);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Game getSeenIn() {
        return seenIn;
    }

    public void setSeenIn(Game seenIn) {
        this.seenIn = seenIn;
    }

    public ArrayList<Item> getWeapons() {
        return weapons;
    }

    public void setWeapons(ArrayList<Item> weapons) {
        this.weapons = weapons;
    }

    public ArrayList<Item> getSpoils() {
        return spoils;
    }

    public void setSpoils(ArrayList<Item> spoils) {
        this.spoils = spoils;
    }

    public String getImage() {
        return imageName;
    }

    public void setImage(String image) {
        this.imageName = imageName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
