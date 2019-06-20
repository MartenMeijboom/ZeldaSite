package nl.saxion.jm.zeldasite.controller;

import nl.saxion.jm.zeldasite.ApplicationManager;

abstract class controller {

    static private ApplicationManager manager;

    ApplicationManager myManager()
    {
        if(manager == null)
        {
            manager = new ApplicationManager();
        }
        return manager;
    }

}
