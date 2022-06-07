import java.util.ArrayList;

public class Startup
{
    String name;
    ArrayList<String> locationCells;

    public Startup(String name) {
        this.name = name;
    }
    public void setLocations(ArrayList<String> locs)
    {
        locationCells = locs;
        System.out.format("%s is located at:\n", name);
        for (var loc : locs) {
            System.out.println(loc);
        }
    }

  public String checkYourself(String stringGuess) {
      String result = "miss";
      int index = locationCells.indexOf(stringGuess);

      if (index >= 0) {
        locationCells.remove(index);
        result = locationCells.isEmpty() ? "destroy" : "hit";
      }
      return result;
  }
}