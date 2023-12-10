public class Animal {
    private int id;
    protected String name;
    private String type;

    public Animal(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public String getDescription() {
        String description = "This is a " + type + " called " + name + ".";
        return description;
    }

    public void makeNoise() {
        System.out.println("The " + name + " makes a noise.");
    }

    public String getAnimalHistory() {
        String history = "This is the history of the " + name + ".";
        return history;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

class Mammal extends Animal {
    public Mammal(int id, String name) {
        super(id, name, "Mammal");
    }

    @Override
    public String getAnimalHistory() { 
        return "Mammals are warm-blooded vertebrates with hair or fur.";
    }

    @Override
    public void makeNoise() {
        if ("Elephant".equals(name)) {
            System.out.println("The elephant trumpets.");
        } else if ("Monkey".equals(name)) {
            System.out.println("The monkey chatters.");
        } else {
            super.makeNoise();  
        }
    }
}

class Amphibian extends Animal {
    public Amphibian(int id, String name) {
        super(id, name, "Amphibian");
    }

    @Override
    public String getAnimalHistory() {
         return "Amphibians are cold-blooded vertebrates that live both in water and on land.";
    }

    @Override
    public void makeNoise() {
        if ("Frog".equals(name)) {
            System.out.println("The frog croaks.");
        } else if ("Salamander".equals(name)) {
            System.out.println("The salamander makes a chirping sound.");
        }else {
            super.makeNoise();  
        }
    }
}

class Reptile extends Animal {
    public Reptile(int id, String name) {
        super(id, name, "Reptile");
    }

    @Override
    public String getAnimalHistory() { 
        return "Reptiles are cold-blooded vertebrates covered in scales.";
    }

    @Override
    public void makeNoise() {
        if ("Snake".equals(name)) {
            System.out.println("The snake hisses.");
        } else if ("Turtle".equals(name)) {
            System.out.println("The turtle makes a hissing sound.");
        }else {
            super.makeNoise();  
        }
    }
}


// Elephant
// Monkey

// Frog
// Salamander

// Snake
// Turtle;