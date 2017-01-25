
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    private static List<Student> all = new ArrayList<>();
    private static List<Integer> id = new ArrayList<>();

    public static void main(String[] args) {
        //Startup
        System.out.println("Robotics Auto Sign-in System");
        DateFormat dateFormat = new SimpleDateFormat("MM.dd");
        Date date = new Date();
        System.out.println(dateFormat.format(date));

        //Load Roster <--
        File roster = new File("Roster");
        try {
            Scanner scanner = new Scanner(roster);
            String[] file = encrypt(scanner.nextLine(),-5).split("\n");
            //For each line
            for(String line : file) {
                String[] string = line.split(",");
                all.add(new Student(string[0]));
                id.add(Integer.valueOf(string[1]));
            }
            scanner.close();
            System.out.println("Roster Loaded");
        } catch(FileNotFoundException e) {
            System.out.println("No Roster Found");
        }

        //Prepare outputs
        Scanner scanner = new Scanner(System.in);
        File attendance = new File("Attendance " + dateFormat.format(date) + ".csv");
        boolean newStudent = false;


        while(true) {
            //Get ID
            System.out.println("Scan Card");
            try {
                int find = scanner.nextInt();
                scanner.nextLine();
                //Locate student
                if(id.contains(find)) {
                    Student student = all.get(id.indexOf(find));
                    if(student.check()) System.out.println("Welcome, " + student.name);
                    else System.out.println("Goodbye, " + student.name);
                } else {
                    //Set up new student
                    System.out.println("Enter full name");
                    Student student = new Student(scanner.nextLine());
                    student.check();
                    if(student.name.length() > 3) {
                        //Add student to lists
                        all.add(student);
                        id.add(find);
                        newStudent = true;
                    } else System.out.println("Cancelled");
                }

                try {
                    //Update Attendance sheet <--
                    FileWriter writer = new FileWriter(attendance);
                    writer.write("Name,Arrival,Departure,\n");
                    for(Student student : all) writer.write(student.print() + "\n");
                    writer.close();

                    if(newStudent) {
                        //Update student roster
                        writer = new FileWriter(roster);
                        for(int i = 0; i < all.size(); i++) writer.write(encrypt(all.get(i).name + ',' + id.get(i) + "\n",5));
                        writer.close();
                        System.out.println("Roster Updated");
                        newStudent = false;
                    }
                } catch(IOException e) {
                    System.out.println("Output Error: " + e);
                }
            } catch(InputMismatchException e) {
                System.out.println("Error: NAN");
                scanner.nextLine();
            }
        }
    }

    private static String encrypt(String string, int degree) {
        char[] line = string.toCharArray();
        string = "";
        for(char c : line) string += (char)(c + degree);
        return string;
    }
}
