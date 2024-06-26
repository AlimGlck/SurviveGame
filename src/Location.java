import java.util.Scanner;

public abstract class Location {
    private Player player;
    private String locationName;
    private int id;
    protected static Scanner scanner = new Scanner(System.in);

    public Location(Player player,String locationName,int id){
        this.player = player;
        this.locationName = locationName;
        this.id = id;
    }

    public abstract boolean onLocation();

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
