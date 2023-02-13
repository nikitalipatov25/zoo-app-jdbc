package zoo_animals;

import java.io.Serializable;

public class Animals implements Serializable {
    public String name;
    public int legsNumber;
    public boolean isPredator;
    public String color;
    public String area;

    public Animals(String name, int legsNumber, boolean isPredator, String color, String area) {
        this.name = name;
        this.legsNumber = legsNumber;
        this.isPredator = isPredator;
        this.color = color;
        this.area = area;
    }

    @Override
    public String toString() {
        return name + ", " + legsNumber + ", " + isPredator + ", " + color + ", " + area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLegsNumber() {
        return legsNumber;
    }

    public void setLegsNumber(int legsNumber) {
        this.legsNumber = legsNumber;
    }

    public boolean isPredator() {
        return isPredator;
    }

    public void setPredator(boolean predator) {
        isPredator = predator;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
