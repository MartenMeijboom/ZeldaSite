package nl.saxion.jm.zeldasite.model;

import nl.saxion.jm.zeldasite.model.helper.Type;

public class Item {

    private String name;
    private int id;
    private static int idList = 0;
    private Type type;
    private String imageName;
    private String description;

    public Item(String name, Type type, String imageName, String description)
    {
        id = idList;
        idList++;

        this.name = name;
        this.type = type;
        this.imageName = imageName;
        this.description = description;
    }


    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void setIdList(int idList) {
        Item.idList = idList;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setImage(String image) {
        this.imageName = image;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public static int getIdList() {
        return idList;
    }

    public Type getType() {
        return type;
    }

    public String getImage() {
        return imageName;
    }
}
