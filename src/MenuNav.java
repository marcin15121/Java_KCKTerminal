import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.terminal.Terminal;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.charset.Charset;
import java.time.Year;
import java.util.Scanner;
import java.util.SplittableRandom;


public class MenuNav {

    private static Service service = new Service();

    // Define color constants
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_BLUE = "\u001B[34m";
    public String engine, state, type;

    Terminal terminal = TerminalFacade.createTerminal(Charset.forName("UTF8"));
    Screen screen = TerminalFacade.createScreen();

    public void displayMenu() {

        asciiArt();

        screen.startScreen();
        while (true) {
            screen.clear();

            screen.putString(3, 1, "            Car Company", Terminal.Color.RED, Terminal.Color.BLACK, ScreenCharacterStyle.Bold);
            screen.putString(3, 2, "-------PRESS-ENTER-TO-CONTINUE-------", Terminal.Color.WHITE, Terminal.Color.BLACK, ScreenCharacterStyle.Blinking);
            screen.refresh();

            boolean stop = false;
            while (!stop) {
                Key key = screen.readInput();
                while (key == null) {
                    key = screen.readInput();
                }
                switch (key.getKind()) {
                    case Escape:
                        stop = true;
                        break;
                    case Enter:
                        mainMenu();
                }
            }

            screen.refresh();
            Thread.currentThread();
            while (screen.readInput() == null) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void asciiArt() {
        int width = 130;
        int height = 30;

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bufferedImage.getGraphics();
        graphics.setFont(new Font("SansSerif", Font.BOLD, 14));

        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.drawString("Car Company", 10, 15);

        for (int y = 0; y < height; y++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int x = 0; x < width; x++) {
                stringBuilder.append(bufferedImage.getRGB(x, y) == -16777216 ? " " : "#");
            }
            if (stringBuilder.toString().trim().isEmpty()) {
                continue;
            }
            System.out.println(stringBuilder);
        }
    }

    public void mainMenu() {

        screen.startScreen();
        int highlight = 1;

        boolean stop = true;
        while (stop) {
            screen.clear();

            screen.putString(4, 1, "1. Add vehicle", Terminal.Color.BLUE, Terminal.Color.BLACK, ScreenCharacterStyle.Bold);
            screen.putString(4, 2, "2. Show vehicles", Terminal.Color.BLUE, Terminal.Color.BLACK, ScreenCharacterStyle.Bold);
            screen.putString(4, 3, "3. Repair vehicle", Terminal.Color.BLUE, Terminal.Color.BLACK, ScreenCharacterStyle.Bold);
            screen.putString(4, 4, "4. Remove vehicle", Terminal.Color.BLUE, Terminal.Color.BLACK, ScreenCharacterStyle.Bold);
            screen.putString(4, 5, "To exit press Escape or click here", Terminal.Color.BLUE, Terminal.Color.BLACK, ScreenCharacterStyle.Bold);

            for (int i = 1; i < 6; i++) {
                if (i == highlight) {
                    screen.putString(0, i, "--->", Terminal.Color.RED, Terminal.Color.BLACK, ScreenCharacterStyle.Bold);
                }
            }
            screen.refresh();

            Key key = screen.readInput();
            while (key == null) {
                key = screen.readInput();
            }
            switch (key.getKind()) {
                case Escape:
                    System.exit(0);
                    break;
                case ArrowUp:
                    if (highlight == 1) {
                        highlight = 5;
                    } else {
                        highlight--;
                    }
                    break;
                case ArrowDown:
                    if (highlight == 5) {
                        highlight = 1;
                    } else {
                        highlight++;
                    }
                    break;
                case Enter:
                    switch (highlight) {
                        case 1:
                            addVehicle();
                            break;
                        case 2:
                            showVehicle();
                            break;
                        case 3:
                            repairVehicle();
                            break;
                        case 4:
                            removeVehicle();
                            break;

                        case 5:
                            System.exit(0);
                    }

            }
            screen.refresh();

        }

    }

    public void addVehicle() {

        int highlightType = 2;
        boolean stop = true;
        while (stop) {
            screen.startScreen();
            screen.clear();
            screen.putString(4, 1, "Choose among these types of vehicle: car,truck,motorbike", Terminal.Color.BLUE, Terminal.Color.BLACK, ScreenCharacterStyle.Bold);
            screen.putString(4, 2, "Car", Terminal.Color.BLUE, Terminal.Color.BLACK);
            screen.putString(4, 3, "Truck", Terminal.Color.BLUE, Terminal.Color.BLACK);
            screen.putString(4, 4, "Motorbike", Terminal.Color.BLUE, Terminal.Color.BLACK);

            for (int i = 2; i < 5; i++) {
                if (i == highlightType) {
                    screen.putString(0, i, "--->", Terminal.Color.RED, Terminal.Color.BLACK, ScreenCharacterStyle.Bold);
                }
            }
            screen.refresh();
            Key key = screen.readInput();
            while (key == null) {
                key = screen.readInput();
            }
            switch (key.getKind()) {
                case Escape:
                    break;
                case ArrowUp:
                    if (highlightType == 2) {
                        highlightType = 4;
                    } else {
                        highlightType--;
                    }
                    break;
                case ArrowDown:
                    if (highlightType == 4) {
                        highlightType = 2;
                    } else {
                        highlightType++;
                    }
                    break;
                case Enter:
                    if (highlightType == 2) {
                        type = "CAR";
                    } else if (highlightType == 3) {
                        type = "TRUCK";
                    } else if (highlightType == 4) {
                        type = "MOTORBIKE";
                    }
                    addVehicleCondition();
                    break;
            }
        }
    }


    public void addVehicleCondition() {
        int highlight = 2;
        boolean stop = true;
        while (stop) {
            screen.startScreen();
            screen.clear();
            screen.putString(4, 1, "Specify condition:", Terminal.Color.BLUE, Terminal.Color.BLACK, ScreenCharacterStyle.Bold);
            screen.putString(4, 2, "New", Terminal.Color.BLUE, Terminal.Color.BLACK);
            screen.putString(4, 3, "Little Damaged", Terminal.Color.BLUE, Terminal.Color.BLACK);
            screen.putString(4, 4, "Destroyed", Terminal.Color.BLUE, Terminal.Color.BLACK);

            for (int i = 2; i < 5; i++) {
                if (i == highlight) {
                    screen.putString(0, i, "--->", Terminal.Color.RED, Terminal.Color.BLACK, ScreenCharacterStyle.Bold);
                }
            }
            screen.refresh();
            Key key = screen.readInput();
            while (key == null) {
                key = screen.readInput();
            }
            switch (key.getKind()) {
                case Escape:
                    break;
                case ArrowUp:
                    if (highlight == 2) {
                        highlight = 4;
                    } else {
                        highlight--;
                    }
                    break;
                case ArrowDown:
                    if (highlight == 4) {
                        highlight = 2;
                    } else {
                        highlight++;
                    }
                    break;
                case Enter:
                    if (highlight == 2) {
                        state = "NEW";
                    } else if (highlight == 3) {
                        state = "LITTLE_DAMAGED";
                    } else if (highlight == 4) {
                        state = "DESTROYED";
                    }
                    addVehicleEngine();
            }

        }
    }

    public void addVehicleEngine() {

        int highlight = 2;
        Boolean stop = true;
        while (stop) {
            screen.startScreen();
            screen.clear();
            screen.putString(4, 1, "Choose Engine:", Terminal.Color.BLUE, Terminal.Color.BLACK);
            screen.putString(4, 2, "1.0", Terminal.Color.BLUE, Terminal.Color.BLACK);
            screen.putString(4, 3, "1.9", Terminal.Color.BLUE, Terminal.Color.BLACK);
            screen.putString(4, 4, "3.5", Terminal.Color.BLUE, Terminal.Color.BLACK);
            for (int i = 2; i < 5; i++) {
                if (i == highlight) {
                    screen.putString(0, i, "--->", Terminal.Color.RED, Terminal.Color.BLACK, ScreenCharacterStyle.Bold);
                }
            }

            screen.refresh();
            Key keyEngine = screen.readInput();
            while (keyEngine == null) {
                keyEngine = screen.readInput();
            }
            switch (keyEngine.getKind()) {
                case Escape:
                    break;
                case ArrowUp:
                    if (highlight == 2) {
                        highlight = 4;
                    } else {
                        highlight--;
                    }
                    break;
                case ArrowDown:
                    if (highlight == 4) {
                        highlight = 2;
                    } else {
                        highlight++;
                    }
                    break;
                case Enter:
                    if (highlight == 2) {
                        engine = "1.0";
                    } else if (highlight == 3) {
                        engine = "1.9";
                    } else if (highlight == 4) {
                        engine = "3.5";
                    }
                    service.addVeh1(VehType.valueOf(type), State.valueOf(state), engine);
                    while (true) {
                        screen.clear();
                        screen.putString(4, 2, "             SUCCED!", Terminal.Color.YELLOW, Terminal.Color.BLACK, ScreenCharacterStyle.Bold);
                        screen.putString(3, 4, "------PRESS-ENTER-TO-CONTINUE------", Terminal.Color.WHITE, Terminal.Color.BLACK, ScreenCharacterStyle.Blinking);
                        screen.refresh();
                        boolean wait = true;
                        while (wait) {
                            Key key = screen.readInput();
                            while (key == null) {
                                key = screen.readInput();
                            }
                            switch (key.getKind()) {
                                case Escape:
                                    wait = true;
                                    break;
                                case Enter:
                                    mainMenu();
                            }
                        }
                    }
            }
        }

    }

    public void removeVehicle() {
        int highlight = 4;

        boolean stop = true;
        while (stop) {
            screen.startScreen();
            screen.clear();
            screen.putString(4, 1, "Delete your vehicle ", Terminal.Color.BLUE, Terminal.Color.BLACK, ScreenCharacterStyle.Bold);
            screen.putString(4, 2, service.showVehicle().toString(), Terminal.Color.BLUE, Terminal.Color.BLACK);
            screen.putString(4, 3, " ", Terminal.Color.BLUE, Terminal.Color.BLACK, ScreenCharacterStyle.Bold);
            screen.putString(6, 4, "Delete", Terminal.Color.BLUE, Terminal.Color.BLACK);
            screen.putString(6, 5, "Go Back", Terminal.Color.BLUE, Terminal.Color.BLACK);
            screen.refresh();

            for (int i = 4; i < 6; i++) {
                if (i == highlight) {
                    screen.putString(0, i, "--->", Terminal.Color.RED, Terminal.Color.BLACK, ScreenCharacterStyle.Bold);
                }
            }

            screen.refresh();

            Key key = screen.readInput();
            while (key == null) {
                key = screen.readInput();
            }
            switch (key.getKind()) {
                case Escape:
                    break;
                case ArrowUp:
                    if (highlight == 4) {
                        highlight = 5;
                    } else {
                        highlight--;
                    }
                    break;
                case ArrowDown:
                    if (highlight == 5) {
                        highlight = 4;
                    } else {
                        highlight++;
                    }
                    break;
                case Enter:
                    if (highlight == 4) {
                        service.removeVeh();
                        while (true) {
                            screen.clear();
                            screen.putString(4, 2, "             SUCCED!", Terminal.Color.YELLOW, Terminal.Color.BLACK, ScreenCharacterStyle.Bold);
                            screen.putString(3, 4, "------PRESS-ENTER-TO-CONTINUE------", Terminal.Color.WHITE, Terminal.Color.BLACK, ScreenCharacterStyle.Blinking);
                            screen.refresh();
                            boolean wait = true;
                            while (wait) {
                                Key keyExit = screen.readInput();
                                while (keyExit == null) {
                                    keyExit = screen.readInput();
                                }
                                switch (keyExit.getKind()) {
                                    case Escape:
                                        wait = true;
                                        break;
                                    case Enter:
                                        mainMenu();
                                }
                            }
                        }
                    } else {
                        mainMenu();
                        break;
                    }
            }
        }
    }

    public void showVehicle() {

        boolean stop = true;
        while (stop) {
            screen.startScreen();
            screen.clear();
            screen.putString(4, 1, "Your vehicle ", Terminal.Color.BLUE, Terminal.Color.BLACK, ScreenCharacterStyle.Bold);
            screen.putString(4, 2, service.showVehicle().toString(), Terminal.Color.BLUE, Terminal.Color.BLACK);
            screen.putString(3, 1, "PRESS ENTER TO EXIT TO MAIN MENU", Terminal.Color.WHITE, Terminal.Color.BLACK, ScreenCharacterStyle.Bold);
            screen.refresh();
            boolean wait = true;
            while (wait) {
                Key keyExit = screen.readInput();
                while (keyExit == null) {
                    keyExit = screen.readInput();
                }
                switch (keyExit.getKind()) {
                    case Escape:
                        wait = true;
                        break;
                    case Enter:
                        mainMenu();
                }
            }
        }

    }


    public void repairVehicle() {
        int highlight = 4;

        boolean stop = true;
        while (stop) {
            screen.startScreen();
            screen.clear();
            screen.putString(4, 1, "You want to repair your vehicle: ", Terminal.Color.BLUE, Terminal.Color.BLACK, ScreenCharacterStyle.Bold);
            screen.putString(4, 2, service.showVehicle().toString(), Terminal.Color.BLUE, Terminal.Color.BLACK);
            screen.putString(4, 3, " ", Terminal.Color.BLUE, Terminal.Color.BLACK, ScreenCharacterStyle.Bold);
            screen.putString(4, 4, "Confirm", Terminal.Color.BLUE, Terminal.Color.BLACK);
            screen.putString(6, 5, "Go Back", Terminal.Color.BLUE, Terminal.Color.BLACK);
            screen.refresh();

            for (int i = 4; i < 6; i++) {
                if (i == highlight) {
                    screen.putString(0, i, "--->", Terminal.Color.RED, Terminal.Color.BLACK, ScreenCharacterStyle.Bold);
                }
            }

            screen.refresh();

            Key key = screen.readInput();
            while (key == null) {
                key = screen.readInput();
            }
            switch (key.getKind()) {
                case Escape:
                    break;
                case ArrowUp:
                    if (highlight == 4) {
                        highlight = 5;
                    } else {
                        highlight--;
                    }
                    break;
                case ArrowDown:
                    if (highlight == 5) {
                        highlight = 4;
                    } else {
                        highlight++;
                    }
                    break;
                case Enter:
                    if (highlight == 4) {
                        service.repairVeh();
                        while (true) {
                            screen.clear();
                            screen.putString(4, 2, "             SUCCED!", Terminal.Color.YELLOW, Terminal.Color.BLACK, ScreenCharacterStyle.Bold);
                            screen.putString(3, 4, "------PRESS-ENTER-TO-CONTINUE------", Terminal.Color.WHITE, Terminal.Color.BLACK, ScreenCharacterStyle.Blinking);
                            screen.refresh();
                            boolean wait = true;
                            while (wait) {
                                Key keyExit = screen.readInput();
                                while (keyExit == null) {
                                    keyExit = screen.readInput();
                                }
                                switch (keyExit.getKind()) {
                                    case Escape:
                                        wait = true;
                                        break;
                                    case Enter:
                                        mainMenu();
                                }
                            }
                        }
                    } else {
                        mainMenu();
                        break;
                    }
            }
        }
    }

}









