package gameloader;

public interface Rules {
    public String getName();
    public void setup();
    public void clear();
    public void remove(Place place);
}
