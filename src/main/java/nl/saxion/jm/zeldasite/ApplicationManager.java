package nl.saxion.jm.zeldasite;

import nl.saxion.jm.zeldasite.model.Boss;
import nl.saxion.jm.zeldasite.model.User;
import nl.saxion.jm.zeldasite.model.Item;
import nl.saxion.jm.zeldasite.model.helper.Type;

import java.util.ArrayList;

public class ApplicationManager {

    private ArrayList<User> users;
    private ArrayList<Boss> bosses;
    private ArrayList<Item> items;

    public ApplicationManager()
    {
        users = new ArrayList<>();
        bosses = new ArrayList<>();
        items = new ArrayList<>();

        generateTestData();
    }

    private void generateTestData()
    {
        User user = new User("yeet", "Marten Meijboom", "test@testmail.com", "12345");
        adduser(user);
        user = new User("testUseerNaemsfd", "Pieter Post", "sdfijbsadfjasijfbasi.com", "jajaneenee");
        adduser(user);

        Type type = new Type("testType");
        Type rings = new Type("Rings");

        Item item = new Item("Item1", type, "../stockimage.png", "");
        items.add(item);
        item = new Item("Blue Ring", rings, "../bluering.png", "In The Legend of Zelda, Link can equip the Blue Ring to take half the amount of damage he would normally.[4] In the First Quest, it can be purchased for 250 Rupees in a Shop within a Cave accessed through a staircase hidden beneath an Armos. In the Second Quest, it is sold for 250 Rupees in a different location in the northeastern portion of Hyrule. While Link has the Blue Ring equipped, his tunic is white. Certain characters, items, and enemies share colors with Link's tunic and will appear white while Link has the Blue Ring equipped, such as Princess Zelda's dress, the Arrow, and the whiskers of a Pols Voice. The Blue Ring can be replaced with the Red Ring found in Level-9, which reduces the amount of damage Link takes to a quarter of what he normally would.");
        items.add(item);
        item = new Item("TestItem12", type, "../stockimage.png", "");
        items.add(item);
    }

    public void adduser(User user)
    {
        users.add(user);
    }

    public void addBoss(Boss boss)
    {
        bosses.add(boss);
    }

    public void addItem(Item item)
    {
        items.add(item);
    }

    public User getUser(int id)
    {
        for (User u:users) {
            if(u.getId() == id)
            {
                return u;
            }
        }
        return null;
    }

    public User getUser(String userName)
    {
        for (User u:users) {
            if(u.getUserName().equals(userName))
            {
                return u;
            }
        }
        return null;
    }

    public Boss getBoss(int id)
    {
        for (Boss b:bosses) {
            if(b.getId() == id)
            {
                return b;
            }
        }
        return null;
    }

    public Item getItem(int id)
    {
        for (Item i:items) {
            if(i.getId() == id)
            {
                return i;
            }
        }
        return null;
    }

    public String[] getItemNames()
    {
        String[] itemNames = new String[items.size()];
        for (int i = 0; i < items.size(); i++) {
            itemNames[i] = items.get(i).getName();
        }
        return itemNames;
    }

    public ArrayList<Item> getItems()
    {
        return items;
    }

    public String[] getBossNames()
    {
        String[] itemNames = new String[bosses.size()];
        for (int i = 0; i < bosses.size(); i++) {
            itemNames[i] = bosses.get(i).getName();
        }
        return itemNames;
    }

}
