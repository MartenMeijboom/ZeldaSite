package nl.saxion.jm.zeldasite.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Boss {

    private String name;
    private int id;
    private static int idList = 0;
    private String seenin;
    private ArrayList<Item> weapons;
    private ArrayList<Item> spoils;
    private String imageName;
    private String description;

    public Boss(String name, String seenin, String description) {
        id = idList;
        idList++;

        this.name = name;
        this.seenin = seenin;
        String imageName = name.replaceAll("\\s+", "");
        this.imageName = "../" + imageName + ".png";
        this.description = description;

        weapons = new ArrayList<>();
        spoils = new ArrayList<>();
    }

    public Boss(JSONObject jsonObject) {
        try {
            this.name = jsonObject.getString("name");
            this.seenin = jsonObject.getString("seenin");
            this.description = jsonObject.getString("description");

            String imageName = name.replaceAll("\\s+", "");
            this.imageName = "../" + imageName + ".png";

            id = idList;
            idList++;

            weapons = new ArrayList<>();
            spoils = new ArrayList<>();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void removeSpoil(Item spoil) {
        spoils.remove(spoil);
    }

    public void addSpoil(Item spoil) {
        spoils.add(spoil);
    }

    public void removeWeapon(Item weapon) {
        weapons.remove(weapon);
    }

    public void addWeapon(Item weapon) {
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

    public String getSeenin() {
        return seenin;
    }

    public void setSeenIn(String seenIn) {
        this.seenin = seenIn;
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
