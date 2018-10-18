package model.observerpattern;

import model.MainModel;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the applications equivalent of the java.util.Observable class
 *
 * @author Filip Andréasson
 * @author Jonathan Köre
 *
 * @version 1.0
 * @since 2018-10-18
 */

public class ModelObservable {

    private List<ModelObserver> observerList = new ArrayList<>();


    /**
     * This method is used to add ModelObserver objects to the observerList
     *
     * @param   observer    is the observer that gets added to observerList
     */
    public void addObserver(ModelObserver observer) {
        observerList.add(observer);
    }

    /**
     * This method is called by any ModelObservable object and will call the
     * ModelObserver.update(updateType) function which will update any class
     * implementing the ModelObserver interface
     *
     * @param   updateType  is the type of task the update method will perform
     */
    public void notifyObservers(MainModel.UpdateTypes updateType) {

        for (ModelObserver modelObserver : observerList) {
            modelObserver.update(updateType);
        }

    }
}
