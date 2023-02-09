import animals.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception {


        ArrayList<Animals> animals = new ArrayList<>();
        loadAnimals(animals);

        System.out.println("1 - Посмотреть всех животных " + "2 - Посмотреть конкретных животных");
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();

        switch (num) {
            case 1: System.out.println(animals);
                break;
          case 2: selectAnimals(animals);
        }



//        Tigers tiger = new Tigers("Fido", 4, true, "Orange with black stripes", "Eurasia, Africa" );
//
//        FileOutputStream fos = new FileOutputStream("temp.txt");
//        ObjectOutputStream oos = new ObjectOutputStream(fos);
//
//        ArrayList<Animals> animals = new ArrayList<>();
//
//        animals.add(tiger);
//
//        for (Animals animal : animals) {
//            oos.writeObject(animal);
//        }
//
//        oos.flush();
//        oos.close();



//        try {
//            FileInputStream fis = new FileInputStream("temp.txt");
//            ObjectInputStream ois = new ObjectInputStream(fis);
//            Animals filetiger = (Tigers) ois.readObject();
//            fis.close();
//            ois.close();
////            System.out.println(filetiger.name);
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }







//        for (int j = 0; j < stringsAnimals.size(); j++) {
//            stringsAnimals.s
//            System.out.println("j " + j + " text " + stringsAnimals.get(j));
//            switch (stringsAnimals.get(j)) {
//                case ("Tigers") : animalsArrayList.add(new Tigers(
//                        stringsAnimals.get(j + 1),
//                        Integer.parseInt(stringsAnimals.get(j + 2)),
//                        Boolean.parseBoolean(stringsAnimals.get(j + 3)),
//                        stringsAnimals.get(j + 4),
//                        stringsAnimals.get(j + 5)
//                        ));
//                case ("Wolfs") : animalsArrayList.add(new Wolfs(
//                        stringsAnimals.get(j + 1),
//                        Integer.parseInt(stringsAnimals.get(j + 2)),
//                        Boolean.parseBoolean(stringsAnimals.get(j + 3)),
//                        stringsAnimals.get(j + 4),
//                        stringsAnimals.get(j + 5)
//                ));
//                case ("Penguins") : animalsArrayList.add(new Penguins(
//                        stringsAnimals.get(j + 1),
//                        Integer.parseInt(stringsAnimals.get(j + 2)),
//                        Boolean.parseBoolean(stringsAnimals.get(j + 3)),
//                        stringsAnimals.get(j + 4),
//                        stringsAnimals.get(j + 5)
//                ));
//                case ("Bears") : animalsArrayList.add(new Bears(
//                        stringsAnimals.get(j + 1),
//                        Integer.parseInt(stringsAnimals.get(j + 2)),
//                        Boolean.parseBoolean(stringsAnimals.get(j + 3)),
//                        stringsAnimals.get(j + 4),
//                        stringsAnimals.get(j + 5)
//                ));
//                case ("Kangaroos") : animalsArrayList.add(new Kangaroos(
//                        stringsAnimals.get(j + 1),
//                        Integer.parseInt(stringsAnimals.get(j + 2)),
//                        Boolean.parseBoolean(stringsAnimals.get(j + 3)),
//                        stringsAnimals.get(j + 4),
//                        stringsAnimals.get(j + 5)
//                ));
//            }
//        }
//
//        System.out.println(animalsArrayList);
//
//        ArrayList<String> subList = new ArrayList<String>();
//        while (!stringsAnimals.isEmpty()){
//            subList = (ArrayList<String>) stringsAnimals.subList(0, 4);
//            animalsArrayList.add(new Animals(
//                    subList.get(0),
//                    Integer.parseInt( subList.get(1)),
//                    Boolean.parseBoolean( subList.get(2)),
//                    subList.get(3),
//                    subList.get(4)
//                    ));
//           subList.clear();
//            System.out.println(animalsArrayList);
//        }


    }

    public static ArrayList<Animals> loadAnimals(ArrayList<Animals> animals) throws Exception {

        FileReader fr = new FileReader("animals.txt");
        Scanner scanner = new Scanner(fr);
        scanner.useDelimiter(", *");
        ArrayList<String> stringsAnimals = new ArrayList<>();
        while (scanner.hasNext()) {
            stringsAnimals.add(scanner.nextLine());
        }
        //System.out.println(stringsAnimals);

        String[] newAnimal;
        for (int i = 0; i < stringsAnimals.size(); i++) {
            newAnimal = stringsAnimals.get(i).split(", ");
            switch (newAnimal[0]) {
                case ("Tigers") :animals.add(new Tigers(
                        newAnimal[1],
                        Integer.parseInt(newAnimal[2]),
                        Boolean.parseBoolean(newAnimal[3]),
                        newAnimal[4],
                        newAnimal[5]
                ));
                    break;
                case ("Wolfs") : animals.add(new Wolfs(
                        newAnimal[1],
                        Integer.parseInt(newAnimal[2]),
                        Boolean.parseBoolean(newAnimal[3]),
                        newAnimal[4],
                        newAnimal[5]
                ));
                    break;
                case ("Penguins") : animals.add(new Penguins(
                        newAnimal[1],
                        Integer.parseInt(newAnimal[2]),
                        Boolean.parseBoolean(newAnimal[3]),
                        newAnimal[4],
                        newAnimal[5]
                ));
                    break;
                case ("Bears") : animals.add(new Bears(
                        newAnimal[1],
                        Integer.parseInt(newAnimal[2]),
                        Boolean.parseBoolean(newAnimal[3]),
                        newAnimal[4],
                        newAnimal[5]
                ));
                    break;
                case ("Kangaroos") : animals.add(new Kangaroos(
                        newAnimal[1],
                        Integer.parseInt(newAnimal[2]),
                        Boolean.parseBoolean(newAnimal[3]),
                        newAnimal[4],
                        newAnimal[5]
                ));
                    break;
            }
        }

        fr.close();
        return animals;
    }


    public static void selectAnimals(ArrayList<Animals> animals) {
        System.out.println("1 - Посмотреть Тигров " + "2 - Посмотреть Волков " + "3 - Посмотреть Пингвинов " + "4 - Посмотреть Кенгуру " + "5 - Посмотреть Медведей");

        Scanner scanner = new Scanner(System.in);
        int animalNum = scanner.nextInt();

        Class questionMark;

        switch (animalNum) {
            case 1: questionMark = Tigers.class;
                break;
            case 2: questionMark = Wolfs.class;
                break;
            case 3: questionMark = Penguins.class;
                break;
            case 4: questionMark = Kangaroos.class;
                break;
            case 5: questionMark = Bears.class;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + animalNum);
        }
        animalsByType(animals, questionMark);
    }
    public static void animalsByType(ArrayList<Animals> animals, Class questionMark) {
        for (Animals animal : animals) {
            if (animal.getClass().equals(questionMark)) {
                System.out.println(animal);
            }
        }
    }
}