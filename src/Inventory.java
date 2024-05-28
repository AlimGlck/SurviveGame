public class Inventory {
    private Weapon weapon;
    private Armor armor;
    private boolean caveLocAward;
    private boolean riverLocAward;
    private boolean forestLocAward;
    public Inventory() {
        this.weapon = new Weapon("Yumruk",-1,0,0);
        this.armor = new Armor(-1,0,0,"Paçavra");
        this.caveLocAward = false;
        this.riverLocAward = false;
        this.forestLocAward = false;
    }
    public Weapon getWeapon() {
        return weapon;
    }
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
    public Armor getArmor() {
        return armor;
    }
    public void setArmor(Armor armor) {
        this.armor = armor;
    }
    public boolean isCaveLocAward() {
        return caveLocAward;
    }
    public void setCaveLocAward(boolean caveLocAward) {
        this.caveLocAward = caveLocAward;
    }
    public boolean isRiverLocAward() {
        return riverLocAward;
    }
    public void setRiverLocAward(boolean riverLocAward) {
        this.riverLocAward = riverLocAward;
    }
    public boolean isForestLocAward() {
        return forestLocAward;
    }
    public void setForestLocAward(boolean forestLocAward) {
        this.forestLocAward = forestLocAward;
    }
}
