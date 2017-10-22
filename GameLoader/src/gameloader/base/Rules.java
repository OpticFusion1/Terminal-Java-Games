package gameloader.base;

public interface Rules {
    String getName();
    void setup();
    void clear();
    void remove(Place place);
}
