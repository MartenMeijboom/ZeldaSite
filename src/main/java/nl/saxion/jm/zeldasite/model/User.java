package nl.saxion.jm.zeldasite.model;

import java.util.ArrayList;

public class User {

    private String userName;
    private int id;
    private static int idList = 0;
    private String fullName;
    private String mailAddress;
    private String password;
    private ArrayList<Item> items;

    private ArrayList<Boss> defeatedBosses;

    public User(String userName, String fullName, String mailAddress, String password)
    {
        id = idList;
        idList++;

        this.userName = userName;
        this.fullName = fullName;
        this.mailAddress = mailAddress;
        this.password = password;

        items = new ArrayList<>();
        defeatedBosses = new ArrayList<>();
    }

    public boolean correctPassword(String password)
    {
        if(this.password.equals(password))
        {
            return true;
        }
        return false;
    }

    public void addItem(Item item)
    {
        items.add(item);
    }

    public void removeItem(Item item)
    {
        items.remove(item);
    }

    public void addBoss(Boss boss)
    {
        defeatedBosses.add(boss);
    }

    public void removeBoss(Boss boss)
    {
        defeatedBosses.remove(boss);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getPassword() {
        return password;
    }

    public int getId()
    {
        return id;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<Boss> getDefeatedBosses() {
        return defeatedBosses;
    }


    public void setPassword(String password) {
        this.password = password;
    }
}
