package story;

public class Rocket implements Mechanism {

    static final String OK_WORK = "Тоненький свист перерос в рев воздуха";
    static final String USELESS_WORK = "Тоненький свист перерос в рев воздуха, ракета стоит на месте";
    static final String NO_WORK = "Ракета не может лететь";

    private Space targetSpace;
    private Engine engine;

    public Rocket() {
    }

    public Rocket(Space targetSpace, Engine engine) {
        this.targetSpace = targetSpace;
        this.engine = engine;
    }

    @Override
    public String doWork() {
        if (canWork()) {
            if (targetSpace != null) {
                return OK_WORK;
            } else {
                return USELESS_WORK;
            }
        } else {
            return NO_WORK;
        }
    }

    public boolean canGoToSpace() {
        return engine != null && engine.canWork() && targetSpace != null;
    }

    @Override
    public boolean canWork() {
        return engine != null && engine.canWork();
    }

    public Space getTargetSpace() {
        return targetSpace;
    }

    public void setTargetSpace(Space targetSpace) {
        this.targetSpace = targetSpace;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}
