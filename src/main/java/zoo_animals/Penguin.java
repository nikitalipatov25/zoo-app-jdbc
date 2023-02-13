package zoo_animals;

import java.io.Serializable;

public class Penguin extends Animals implements Serializable {
    public Penguin(String name, int legsNumber, boolean isPredator, String color, String area) {
        super(name, legsNumber, isPredator, color, area);
    }

    @Override
    public String toString() {
        return Penguin.class.getSimpleName() + ", " + super.toString();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public int getLegsNumber() {
        return super.getLegsNumber();
    }

    @Override
    public void setLegsNumber(int legsNumber) {
        super.setLegsNumber(legsNumber);
    }

    @Override
    public boolean isPredator() {
        return super.isPredator();
    }

    @Override
    public void setPredator(boolean predator) {
        super.setPredator(predator);
    }

    @Override
    public String getColor() {
        return super.getColor();
    }

    @Override
    public void setColor(String color) {
        super.setColor(color);
    }

    @Override
    public String getArea() {
        return super.getArea();
    }

    @Override
    public void setArea(String area) {
        super.setArea(area);
    }
}
