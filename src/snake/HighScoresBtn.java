package snake;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.FileReader;
import java.util.Collections;

public class HighScoresBtn extends Button {
    public HighScoresBtn(String n) {
        super(n);
    }

    public void action() {
        Menu menu = new Menu(new ArrayList<Button>(Arrays.asList(
                                        new DummyBtn("Sort by points"),
                                        new DummyBtn("Sort by date"),
                                        new DummyBtn("Sort by name"),
                                        new DummyBtn("Back"))));

        ArrayList<HighScore> hs = new ArrayList<>();
        try {
            hs = getHighScores();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        DateTimeFormatter dateF = DateTimeFormatter.ofPattern(
                                                        "yyyy-MM-dd HH:mm:ss");
        try {
            int choice = menu.select();
            while (choice != menu.getSize()-1) {
                if (choice == 0) {
                    hs.sort(Collections.reverseOrder(new PtsComparator()));
                } else if (choice == 1) {
                    hs.sort(Collections.reverseOrder(new DateComparator()));
                } else if (choice == 2) {
                    hs.sort(new NameComparator());
                }

                ArrayList<Button> hsBtns = new ArrayList<>();
                int i = 1;
                for (HighScore h : hs) {
                    hsBtns.add(new DummyBtn(i + ". " + h.getName() + ":" + "   "
                                            + h.getPts() + " pts" + "   "
                                            + " ("+ h.getDate().format(dateF)
                                            + ")" + "   "));
                    i += 1;
                }
                hsBtns.add(new DummyBtn("Back"));

                Menu hsMenu = new Menu(hsBtns);
                int hsChoice = hsMenu.select();
                while (hsChoice != hsMenu.getSize()-1) {
                    hsChoice = hsMenu.select();
                }
                choice = menu.select();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static ArrayList<HighScore> getHighScores() throws IOException {
        ArrayList<HighScore> highs = new ArrayList<>();
        File hs = new File("highscores.txt");
        if (!hs.exists()) {
            return highs;
        } else {
            BufferedReader in = new BufferedReader(
                                new FileReader("highscores.txt"));
            String line = in.readLine();
            while (line != null) {
                String name = line; line = in.readLine();
                int pts = Integer.parseInt(line); line = in.readLine();
                LocalDateTime date = LocalDateTime.parse(line);
                highs.add(new HighScore(name, pts, date));
                line = in.readLine();
                if (line != null && line.equals("")) {
                    line = in.readLine();
                }
            }
            return highs;
        }
    }

}
