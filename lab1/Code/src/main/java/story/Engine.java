package story;

public class Engine implements Mechanism {
    static final String OK_WORK = "Зажужжал мотор";
    static final String NO_WORK = "Мотор не зажжужжал";

    private boolean canWork;

    public Engine(boolean canWork) {
        this.canWork = canWork;
    }

    public void setCanWork(boolean canWork) {
        this.canWork = canWork;
    }

    @Override
    public boolean canWork() {
        return canWork;
    }

    @Override
    public String doWork() {
        if (canWork) {
            return OK_WORK;
        } else {
            return NO_WORK;
        }
    }
}
