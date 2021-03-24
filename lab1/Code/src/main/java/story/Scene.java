package story;

public class Scene {
    final private Person[] team;
    final private Rocket rocket;

    public Scene(Person[] team, Rocket rocket) {
        if (team == null || team.length < 1) {
            throw new IllegalArgumentException("Team must contain at least one person");
        }
        if (rocket == null) {
            throw new IllegalArgumentException("Rocket cannot be null");
        }
        this.team = team;
        this.rocket = rocket;
    }

    public String play() {
        if (!rocket.canWork()) {
            return rocket.doWork();
        } else {
            if (rocket.canGoToSpace()) {
                StringBuilder sb = new StringBuilder(rocket.doWork());
                sb.append(". ");
                for (Person p : team) {
                    sb.append(p.getName());
                    sb.append(", ");
                }
                if (team.length > 0) {
                    sb.setLength(sb.length() - 2);
                }
                sb.append(" вылетели как конфетти из хлопушки прямиком в ");
                sb.append(rocket.getTargetSpace().getDescription());
                sb.append(".");
                return sb.toString();
            } else {
                return rocket.doWork();
            }
        }
    }

    public Person[] getTeam() {
        return team;
    }

    public Rocket getRocket() {
        return rocket;
    }
}
