
class Color {
    String Clear = "\u001B[0m";
    String Black = "\u001B[30m";
    String Red = "\u001B[31m";
    String Green = "\u001B[32m";
    String Yellow = "\u001B[33m";
    String Blue = "\u001B[34m";
    String Purple = "\u001B[35m";
    String Cyan = "\u001B[36m";
    String White = "\u001B[37m";

    String Black(String text) {
        return Black + text + Clear;
    }
    String Red(String text) {
        return Red + text + Clear;
    }
    String Green(String text) {
        return Green + text + Clear;
    }
    String Yellow(String text) {
        return Yellow + text + Clear;
    }
    String Blue(String text) {
        return Blue + text + Clear;
    }
    String Cyan(String text) { return Cyan + text + Clear; }
    String Purple(String text) {
        return Purple + text + Clear;
    }
    String White(String text) {
        return White + text + Clear;
    }
}
