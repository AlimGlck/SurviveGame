import javax.print.DocFlavor;
import java.util.Scanner;

public class Player {
    Scanner scanner = new Scanner(System.in);
    private int damage;
    private int health;
    private int originalHealt;
    private int money;
    private String name;
    private String charName;
    private Inventory inventory;

    public Player(String name){
        this.name = name;
        this.inventory = new Inventory();
    }

    public void selectChar(){
        GameChar[] charList = {new Samurai(),new Archer(),new Knight()};
        System.out.println("\n\nKarakterler");
        System.out.println("------------------------------");
        for (GameChar gameChar : charList){
            System.out.println("ID: "+gameChar.getId()+
                               "\t Karakter: "+gameChar.getName() +
                               "\t Hasar: "+gameChar.getDamage() +
                               "\t Sağlık: "+gameChar.getHealth() +
                               "\t Para: "+gameChar.getMoney());
        }
        System.out.println("------------------------------");
        System.out.println();
        System.out.print("Lütfen bir karakter seçiniz:");
        int selectChar = scanner.nextInt();

        switch (selectChar){
            case 1:
                initPlayer(new Samurai());
                break;
            case 2:
                initPlayer(new Archer());
                break;
            case 3:
                initPlayer(new Knight());
                break;
            default:
                initPlayer(new Samurai());
        }
        /*System.out.println("Karakter: "+this.getCharName()+", Hasar: " + getDamage()+", Sağlık: "+this.getHealth()+", Para: "+this.getMoney());*/
    }

    public void printInfo(){
        System.out.println("\nSilahınız : "+this.getInventory().getWeapon().getName()+", Zırhınız: "+this.getInventory().getArmor().getName()+", Bloklama: "+this.getInventory().getArmor().getBlock()+ ", Hasarınız : "+this.getTotalDamage()+", Sağlık : "+this.getHealth()+", Para : "+this.getMoney());
    }

    public void initPlayer(GameChar gameChar){
        this.setDamage(gameChar.getDamage());
        this.setHealth(gameChar.getHealth());
        this.setMoney(gameChar.getMoney());
        this.setCharName(gameChar.getName());
        this.setOriginalHealt(gameChar.getHealth());
    }

    public int getTotalDamage(){
        return this.damage + this.getInventory().getWeapon().getDamage();
    }
    public int getDamage(){
        return this.damage;
    }

    public void setDamage(int damage){
        this.damage = damage;
    }

    public int getHealth(){
        return this.health;
    }
    public void setHealth(int health){
        if (health<0){
            health = 0;
        }
        this.health = health;
    }

    public int getMoney() {
        return this.money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharName() {
        return this.charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getOriginalHealt(){
        return this.originalHealt;
    }

    public void setOriginalHealt(int originalHealt){
        this.originalHealt = originalHealt;
    }
}
