package story;

public class Space {
    public static final String DEFAULT_DESCRIPTION = "Космос";
    private String description;

    public Space() {
        this(DEFAULT_DESCRIPTION);
    }

    public Space(String description) {
        this.description = formatDescription(description);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = formatDescription(description);
    }

    private String formatDescription(String description) {
        if (description == null) {
            return DEFAULT_DESCRIPTION;
        }
        if (description.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        return description.trim();
    }
}
