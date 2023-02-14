package animals;

public class Animals {

    private int id;
    private String name;
    private int legs;
    private String type;
    private boolean isPredator;
    private String color;
    private String area;

    public Animals(int id, String name, int legs, String type, boolean isPredator, String color, String area) {
        this.id = id;
        this.name = name;
        this.legs = legs;
        this.type = type;
        this.isPredator = isPredator;
        this.color = color;
        this.area = area;
    }

    public Animals(String name, int legs, String type, boolean isPredator, String color, String area) {
        this.name = name;
        this.legs = legs;
        this.type = type;
        this.isPredator = isPredator;
        this.color = color;
        this.area = area;
    }

    public Animals(String name, String type, String color) {
        this.name = name;
        this.type = type;
        this.color = color;
    }

    public Animals() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLegs() {
        return legs;
    }

    public void setLegs(int legs) {
        this.legs = legs;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
