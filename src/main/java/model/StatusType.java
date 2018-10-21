/**
 * An enum for the different statuses a User can have
 * responsibility:  To represent the current status of a User
 * used by:         User
 * @author Gustav Häger
 */
package model;

public enum StatusType {
    Available, Busy, Do_not_disturb;

    @Override
    public String toString() {
        switch (this){
            case Busy:              return "Busy";
            case Do_not_disturb:    return "Do not disturb";
            default:                return "Available";
        }
    }
}