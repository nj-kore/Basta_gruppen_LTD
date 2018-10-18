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