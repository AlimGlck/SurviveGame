import java.util.Random;

public abstract class BattleLoc extends Location{
    private Monster monster;
    private String award;
    private int maxMonster;
    public BattleLoc(Player player, String name,int id,Monster monster,String award,int maxMonster){
        super(player,name,id);
        this.monster = monster;
        this.award = award;
        this.maxMonster = maxMonster;
    }

    @Override
    public boolean onLocation(){
        if (this.getPlayer().getInventory().isCaveLocAward() == true && this.getLocationName() =="Mağara"){
            System.out.println("Buradaki ödülü zaten almışsınız. Tekrar giriş yapamazsınız.");
            return true;
        }
        if (this.getPlayer().getInventory().isForestLocAward() == true && this.getLocationName() =="Orman"){
            System.out.println("Buradaki ödülü zaten almışsınız. Tekrar giriş yapamazsınız.");
            return true;
        }
        if (this.getPlayer().getInventory().isRiverLocAward() == true && this.getLocationName() =="Nehir"){
            System.out.println("Buradaki ödülü zaten almışsınız. Tekrar giriş yapamazsınız.");
            return true;
        }
        int randomMonsternumb = this.randomMonsterNumber();
        System.out.println("\nŞuan buradasınız : "+this.getLocationName());
        System.out.println("Dikkatli Ol! Burada "+ randomMonsternumb+ " tane " +this.getMonster().getName() +" yaşıyor.");
        System.out.print("<S>avaş veya <K>aç:");
        String selectedCase = scanner.nextLine();
        selectedCase = selectedCase.toUpperCase();
        if (selectedCase.equals("S") && combat(randomMonsternumb)){

            System.out.println(this.getLocationName()+ " mekanındaki tüm düşmanları yendiniz!");
            if (this.getMonster().getName() == "Zombi"){
                System.out.println("\"Yemek\" ödülünü kazandınız!");
                this.getPlayer().getInventory().setCaveLocAward(true);
            }
            if (this.getMonster().getName() == "Ayı"){
                System.out.println("\"Su\" ödülünü kazandınız!");
                this.getPlayer().getInventory().setRiverLocAward(true);
            }
            if (this.getMonster().getName() == "Vampir"){
                System.out.println("\"Odun\" ödülünü kazandınız!");
                this.getPlayer().getInventory().setForestLocAward(true);
            }
                return true;
        }

        if (this.getPlayer().getHealth()<=0){
            System.out.println("Mekanik center oldunuz.");
            return false;
        }
        return true;
    }

    public boolean combat(int monsterNum){
        System.out.println();
        for (int i = 0; i<monsterNum; i++){
            if (this.getMonster().getName()=="Yılan"){
                int snakeDam = randomShot();
                this.getMonster().setDamage(snakeDam);
            }
            this.getMonster().setHealth(this.getMonster().getOriginalHealth());
            int firstShot = firstShot();
            playerStats();
            System.out.println();
            monsterStats(i);


            if (firstShot == 1) {
                while (this.getPlayer().getHealth() > 0 && this.getMonster().getHealth() > 0) {
                    System.out.println();
                    System.out.print("<V>ur veya <K>aç: ");
                    String selectCombat = scanner.nextLine().toUpperCase();
                    if (selectCombat.equals("V")) {
                        System.out.println();
                        System.out.println("Siz vurudunuz!");
                        this.getMonster().setHealth(this.getMonster().getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                        if (this.getMonster().getHealth() > 0) {
                            System.out.println();
                            System.out.println("Canavar size vurdu!");
                            System.out.println();
                            int monsterDamage = this.getMonster().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                            if (monsterDamage < 0) {
                                monsterDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - monsterDamage);
                            afterHit();
                        }
                    } else {
                        return false;
                    }
                }
            }

            if (firstShot == 2) {
                while (this.getPlayer().getHealth() > 0 && this.getMonster().getHealth() > 0) {
                    System.out.println();
                    System.out.print("<V>ur veya <K>aç: ");
                    String selectCombat = scanner.nextLine().toUpperCase();
                    if (selectCombat.equals("V")) {
                        System.out.println();
                        if (this.getMonster().getHealth() > 0) {
                            System.out.println();
                            System.out.println("Canavar size vurdu!");
                            System.out.println();
                            int monsterDamage = this.getMonster().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                            if (monsterDamage < 0) {
                                monsterDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - monsterDamage);
                            afterHit();
                        }
                        System.out.println("Siz vurudunuz!");
                        this.getMonster().setHealth(this.getMonster().getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();

                    } else {
                        return false;
                    }
                }
            }
            if (this.getMonster().getName()!="Yılan") {
                if (this.getMonster().getHealth() < this.getPlayer().getHealth()) {
                    System.out.println("\nDüşmanı yendiniz!");
                    System.out.println(this.getMonster().getAwardMoney() + " madeni para kazandınız!");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getMonster().getAwardMoney());
                    System.out.println("Güncel paranız: " + this.getPlayer().getMoney());
                    System.out.println();
                }
            }
            if (this.getMonster().getName()=="Yılan"){
                if (this.getMonster().getHealth() < this.getPlayer().getHealth()) {
                    System.out.println("\nDüşmanı yendiniz!");
                    int firstRatio = randomAward();
                    int secondRatio = randomAward();
                    if (firstRatio>=1 && firstRatio<=15){
                        if (secondRatio>=1 && secondRatio<=20){
                            System.out.println("Tüfek kazandınız!");
                            this.getPlayer().getInventory().setWeapon(new Weapon("Tüfek",3,7,45));
                        }
                        if (secondRatio>20 && secondRatio<=50){
                            System.out.println("Kılıç kazandınız!");
                            this.getPlayer().getInventory().setWeapon(new Weapon("Kılıç",2,3,35));
                        }
                        if (secondRatio>50 && secondRatio<=100){
                            System.out.println("Tabanca kazandınız!");
                            this.getPlayer().getInventory().setWeapon(new Weapon("Tabanca",1,2,15));
                        }
                    }
                    if (firstRatio>15 && firstRatio<=30){
                        if (secondRatio>=1 && secondRatio<=20){
                            System.out.println("Hafif zırh kazandınız!");
                            this.getPlayer().getInventory().setArmor(new Armor(1,1,15,"Hafif"));
                        }
                        if (secondRatio>20 && secondRatio<=50){
                            System.out.println("Orta zırh kazandınız!");
                            this.getPlayer().getInventory().setArmor(new Armor(2,3,25,"Orta"));
                        }
                        if (secondRatio>50 && secondRatio<=100){
                            System.out.println("Ağır zırh kazandınız!");
                            this.getPlayer().getInventory().setArmor(new Armor(3,5,40,"Ağır"));
                        }
                    }
                    if (firstRatio>30 && firstRatio<=55){
                        if (secondRatio>=1 && secondRatio<=20){
                            System.out.println("10 madeni para kazandınız!");
                            this.getPlayer().setMoney(this.getPlayer().getMoney() + 10);
                        }
                        if (secondRatio>20 && secondRatio<=50){
                            System.out.println("5 madeni para kazandınız!");
                            this.getPlayer().setMoney(this.getPlayer().getMoney() + 5);
                        }
                        if (secondRatio>50 && secondRatio<=100){
                            System.out.println("1 madeni para kazandınız!");
                            this.getPlayer().setMoney(this.getPlayer().getMoney() + 1);
                        }
                    }
                    if (firstRatio>55 && firstRatio<=100){
                        System.out.println("Hiçbir şey kazanamadınız.");
                    }

                    System.out.println("Güncel paranız: " + this.getPlayer().getMoney());
                    System.out.println();
                }
            }
            else {
                return false;
            }
        }
        return true;
    }

    public void afterHit(){
        System.out.println("Canınız: " +this.getPlayer().getHealth());
        System.out.println(this.getMonster().getName() + " Canı: " + this.getMonster().getHealth());
    }

    public void playerStats(){
        System.out.println("Oyuncu Değerleri");
        System.out.println("-----------------");
        System.out.println("Sağlık: "+this.getPlayer().getHealth());
        System.out.println("Silah: "+this.getPlayer().getInventory().getWeapon().getName());

        System.out.println("Zırh: "+this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Bloklama: "+this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Para: "+this.getPlayer().getMoney());
    }

    public void monsterStats(int i){
        System.out.println(i+1 +". "+this.getMonster().getName() + " Değerleri");
        System.out.println("-----------------------------------------");
        System.out.println("Sağlık: "+this.getMonster().getHealth());
        System.out.println("Hasar: "+this.getMonster().getDamage());
        System.out.println("Ödül: "+ this.getMonster().getAwardMoney());

    }
    public int randomMonsterNumber(){
        Random r = new Random();
        return r.nextInt(this.getMaxMonster())+1;
    }

    public int firstShot(){
        Random r = new Random();
        return r.nextInt(2)+1;
    }

    public int randomAward(){
        Random r = new Random();
        return r.nextInt(100)+1;
    }
    public int randomShot(){
        Random r = new Random();
        return r.nextInt(6)+3;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxMonster() {
        return maxMonster;
    }

    public void setMaxMonster(int maxMonster) {
        this.maxMonster = maxMonster;
    }
}
