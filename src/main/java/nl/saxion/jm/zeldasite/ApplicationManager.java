package nl.saxion.jm.zeldasite;

import nl.saxion.jm.zeldasite.model.Boss;
import nl.saxion.jm.zeldasite.model.Item;
import nl.saxion.jm.zeldasite.model.User;

import java.util.ArrayList;

public class ApplicationManager {

    private ArrayList<User> users;
    private ArrayList<Boss> bosses;
    private ArrayList<Item> items;

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

    public String[] getBossNames()
    {
        String[] itemNames = new String[bosses.size()];
        for (int i = 0; i < bosses.size(); i++) {
            itemNames[i] = bosses.get(i).getName();
        }
        return itemNames;
    }

}
