package story;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

public class Scene {
    final private Person[] team;
    final private Engine engine;
    final private Air air;
    final private Air space;

    public Scene(Person[] team, Engine engine, Air air, Air space) {
        if (team == null || team.length < 1) {
            throw new IllegalArgumentException("Team must contain at least one person");
        }
        if (air == null) {
            throw new IllegalArgumentException("Air cannot be null");
        }
        if (!air.hasOxygen()) {
            throw new IllegalArgumentException("Air must have an oxygen");
        }
        this.team = team;
        this.engine = engine;
        this.air = air;
        this.space = space;
    }

    public String play() {
        StringBuilder sb = new StringBuilder();
        Air currentAir = air;
        if (engine == null) {
            sb.append(Engine.NO_WORK);
            sb.append(". ");
        } else if (engine.canWork()) {
            sb.append(engine.doWork());
            sb.append(", ");
            sb.append(currentAir.getDescription());
            sb.append(" зашумел");
            sb.append(". ");

            currentAir = space;
            for (Person p : team) {
                sb.append(p.getName());
                sb.append(", ");
            }
            if (team.length > 0) {
                sb.setLength(sb.length() - 2);
            }
            if(space==null){
                sb.append(" вылетели как конфетти из хлопушки прямиком в открытый космос");
            }else {
                sb.append(" перешли в пространство с воздухом ");
                sb.append(space.getDescription());
            }
            sb.append(". ");
        }
        for (Person p : team) {
            sb.append(p.getName());
            sb.append(p.canBreathe(currentAir) ? " может дышать" : " не может дышать");
            sb.append(", ");
        }
        if (team.length > 0) {
            sb.setLength(sb.length() - 2);
        }
        sb.append(".");
        return sb.toString();
    }
}
