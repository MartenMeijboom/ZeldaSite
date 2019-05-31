package nl.saxion.jm.zeldasite.model;

import nl.saxion.jm.zeldasite.model.helper.Type;
import sun.jvm.hotspot.utilities.BitMap;

public class Item {

    private String name;
    private int id;
    private static int idList = 0;
    private Type type;
    private BitMap image;

    public Item(String name, Type type, BitMap image)
    {
        id = idList;
        idList++;

        this.name = name;
        this.type = type;
        this.image = image;
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

    public void setImage(BitMap image) {
        this.image = image;
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

    public BitMap getImage() {
        return image;
    }
}
