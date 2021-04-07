package story;

public class Person {
    final private String name;

    public Person(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name.trim();
    }

    public String getName() {
        return name;
    }

    public boolean canBreathe(Air air){
        if(air==null){
            return false;
        }
        return air.hasOxygen();
    }
}
