public class Point {
    int x;
    int y;
    Point(int newX,int newY) {
        x = newX;
        y = newY;
    }
    String Print() {
        return "(" + x + ',' + y + ')';
    }
}
