package nl.saxion.jm.zeldasite.model;

public class Item {

    private String name;
    private int id;
    private static int idList = 0;
    private String type;
    private String imageName;
    private String description;

    public Item(String name, String type, String description)
    {
        id = idList;
        idList++;

        this.name = name;
        this.type = type;
        String imageName = name.replaceAll("\\s+","");
        this.imageName =  "../" + imageName + ".png";
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

    public void setType(String type) {
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

    public String getType() {
        return type;
    }

    public String getImage() {
        return imageName;
    }
}
