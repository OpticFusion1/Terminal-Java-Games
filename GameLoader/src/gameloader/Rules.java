package gameloader;

import gameloader.Point;

public interface Rules {
    public String getName();
    public void setup();
    public void select(Point p);
}
