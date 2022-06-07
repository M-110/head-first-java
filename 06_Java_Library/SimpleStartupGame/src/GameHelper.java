import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class GameHelper {
    static final String ALPHABET = "ABCEFG";
    static final int GRID_HEIGHT= 7;
    static final int GRID_WIDTH = 7;
    static final int STARTUP_LENGTH = 3;
    static final int MAX_ATTEMPTS = 200;

    final boolean[] grid = new boolean[GRID_WIDTH * GRID_HEIGHT];
    final Random random = new Random();
    int startupCount = 0;

    public void placeStartup(Startup startup) {
        int[] locations = new int[3];
        ArrayList<String> coordinates = new ArrayList<>();

        boolean success = false;
        while (!success) {
            boolean isVertical = random.nextInt(2) == 0;
            int xRange = isVertical ? GRID_WIDTH : GRID_WIDTH - STARTUP_LENGTH;
            int yRange = isVertical ? GRID_HEIGHT - STARTUP_LENGTH : GRID_HEIGHT;
            int x = random.nextInt(xRange);
            int y = random.nextInt(yRange);

            boolean fit = true;
            for (int i = 0; i < STARTUP_LENGTH; i++) {
                int xOffset = isVertical ? 0 : i;
                int yOffset = isVertical ? i : 0;
                locations[i] = (y + yOffset) * GRID_HEIGHT + (x + xOffset);
                if (grid[(locations[i])]) {
                    fit = false;
                    break;
                }
            }
            success = fit;
        }

        for (int i = 0; i < STARTUP_LENGTH; i++) {
            grid[locations[i]] = true;
            int x = locations[i] % GRID_WIDTH + 1;
            int y = locations[i] / GRID_HEIGHT;

            coordinates.add(ALPHABET.charAt(y) + String.valueOf(x));
        }
        startup.setLocations(coordinates);
        startupCount++;
    }

    public String getUserInput(String prompt) {
        String inputLine = null;
        System.out.print(prompt + " ");
        try {
            BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            inputLine = is.readLine();
            if (inputLine.length() == 0)
                return null;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        return inputLine;
    }
}
