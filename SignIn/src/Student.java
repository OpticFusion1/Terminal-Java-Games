import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Student {
    String name;
    private boolean in = false;
    private Date arrived;
    private Date departed;

    public Student(String nName) {
        name = nName;
    }

    public boolean check() {
        //Check time
        if(!in) arrived = new Date();
        else departed = new Date();
        in = !in;
        return in;
    }

    public String print() {
        //Ready time stamps
        DateFormat dateFormat = new SimpleDateFormat("hh:mm");
        String arrival = "";
        if(arrived != null) arrival = dateFormat.format(arrived);
        String departure = "";
        if(departed != null) departure = dateFormat.format(departed);
        return name + ',' + arrival + ',' + departure + ',';
    }
}
