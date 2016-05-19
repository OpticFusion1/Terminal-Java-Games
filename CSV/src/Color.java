
class Color {
    String Clear = "\u001B[0m";
    String Black = "\u001B[30m";
    String Red = "\u001B[31m";
    String Green = "\u001B[32m";
    String Yellow = "\u001B[33m";
    String Blue = "\u001B[34m";
    String Purple = "\u001B[35m";
    String Cyan = "\u001B[36m";
    String White = "\u001B[36m";

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
    String Cyan(String text) {
        return Cyan + text + Clear;
    }
    String Purple(String text) {
        return Purple + text + Clear;
    }
    String White(String text) {
        return White + text + Clear;
    }

    int length(String text) {
        if(text.contains(Clear)) text = text.replace(Clear,"");
        if(text.contains(Black)) text = text.replace(Black,"");
        if(text.contains(Red)) text = text.replace(Red,"");
        if(text.contains(Green)) text = text.replace(Green,"");
        if(text.contains(Yellow)) text = text.replace(Yellow,"");
        if(text.contains(Blue)) text = text.replace(Blue,"");
        if(text.contains(Cyan)) text = text.replace(Cyan,"");
        if(text.contains(Purple)) text = text.replace(Purple,"");
        if(text.contains(White)) text = text.replace(White,"");
        return text.length();
    }
}
