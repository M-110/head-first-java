import java.util.ArrayList;

public class StartupBust {
    GameHelper helper = new GameHelper();
    ArrayList<Startup> startups = new ArrayList<>();
    int numberOfGuesses = 0;

    void setupGame() {
        startups = new ArrayList<>() {
            {
                add(new Startup("poniez"));
                add(new Startup("hacqi"));
                add(new Startup("cabista"));
            }
        };

        System.out.println("Your goal is to sink three Startups!");
        System.out.println("ponzies, hacqi, cabista");
        System.out.println("Try to sink them all in the fewest number of guesses");

        for (var startup: startups)
            helper.placeStartup(startup);
    }

    void startPlaying() {
        while (!startups.isEmpty()) {
            var input = helper.getUserInput("Guess a location: ");
            checkUserGuess(input);
        }
        finishGame();
    }

    void checkUserGuess(String input) {
        numberOfGuesses++;
        var result = "miss";
        for (var startup: startups) {
            result = startup.checkYourself(input);
            if (result.equals("destroy")) {
                startups.remove(startup);
                break;
            }
            else if (result.equals("hit"))
                break;
        }
        System.out.println(result);
    }

    void finishGame() {
        System.out.println("Game Over!");
        var message = numberOfGuesses < 20 ? "Great job!" : "Terrible job!";
        System.out.println(message);
        System.out.format("You took %d guesses", numberOfGuesses);
    }
}
