public class Monster {
    private int id;
    private int damage;
    private int health;
    private String name;
    private int awardMoney;
    private int originalHealth;

    public Monster(int id, int damage, int health,String name,int awardMoney) {
        this.id = id;
        this.damage = damage;
        this.health = health;
        this.name = name;
        this.awardMoney = awardMoney;
        this.originalHealth = health;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health<0){
            health = 0;
        }
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAwardMoney() {
        return awardMoney;
    }

    public void setAwardMoney(int awardMoney) {
        this.awardMoney = awardMoney;
    }

    public int getOriginalHealth() {
        return originalHealth;
    }

    public void setOriginalHealth(int originalHealth) {
        this.originalHealth = originalHealth;
    }
}
