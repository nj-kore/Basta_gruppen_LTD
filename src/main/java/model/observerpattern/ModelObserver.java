package model.observerpattern;

import model.MainModel;

/**
 * This is the applications equivalent of the java.util.Observer interface
 *
 * @author Filip Andréasson
 * @author Jonathan Köre
 *
 * @version 1.0
 * @since 2018-10-18
 */

public interface ModelObserver {
    /**
     *  This method is called whenever the any ModelObservable object calls the method 'notifyObservers'.
     *
     * @param   updateType     is the type of task the update method will perform
     */
    void update(MainModel.UpdateTypes updateType);
}
