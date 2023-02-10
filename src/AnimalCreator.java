import animals.*;

import java.io.IOException;
import java.util.ArrayList;

public class AnimalCreator {

    public static ArrayList<Animals> create(ArrayList<String> stringsAnimals, ArrayList<Animals> animals) throws IOException {
        String[] newAnimal;
        for (String stringsAnimal : stringsAnimals) {
            newAnimal = stringsAnimal.split(", ");
            switch (newAnimal[0]) {
                case ("Tiger") -> animals.add(new Tiger(
                        newAnimal[1],
                        Integer.parseInt(newAnimal[2]),
                        Boolean.parseBoolean(newAnimal[3]),
                        newAnimal[4],
                        newAnimal[5]
                ));
                case ("Wolf") -> animals.add(new Wolf(
                        newAnimal[1],
                        Integer.parseInt(newAnimal[2]),
                        Boolean.parseBoolean(newAnimal[3]),
                        newAnimal[4],
                        newAnimal[5]
                ));
                case ("Penguin") -> animals.add(new Penguin(
                        newAnimal[1],
                        Integer.parseInt(newAnimal[2]),
                        Boolean.parseBoolean(newAnimal[3]),
                        newAnimal[4],
                        newAnimal[5]
                ));
                case ("Bear") -> animals.add(new Bear(
                        newAnimal[1],
                        Integer.parseInt(newAnimal[2]),
                        Boolean.parseBoolean(newAnimal[3]),
                        newAnimal[4],
                        newAnimal[5]
                ));
                case ("Kangaroo") -> animals.add(new Kangaroo(
                        newAnimal[1],
                        Integer.parseInt(newAnimal[2]),
                        Boolean.parseBoolean(newAnimal[3]),
                        newAnimal[4],
                        newAnimal[5]
                ));
            }
        }
        Main.saveAnimals(animals);
        return animals;
    }

}
