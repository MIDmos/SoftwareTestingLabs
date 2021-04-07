package story;

public class Air {
    private final String description;
    private final boolean hasOxygen;

    public Air(String description, boolean hasOxygen) {
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }
        if (description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        this.description = description.trim();
        this.hasOxygen = hasOxygen;
    }

    public boolean hasOxygen() {
        return hasOxygen;
    }

    public String getDescription() {
        return description;
    }
}
