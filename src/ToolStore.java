public class ToolStore extends NormalLoc{
    public ToolStore(Player player){
        super(player,"Eşya Dükkanı",2);
    }

    @Override
    public boolean onLocation() {
        System.out.println("Eşya dükkanına hoşgeldiniz!");
        boolean showMenu = true;
        while (showMenu){
            System.out.println("1 - Silahlar");
            System.out.println("2 - Zırhlar");
            System.out.println("3 - Eşya dükkanından çıkış yap");
            System.out.print("Seçiminiz:");
            int selectCase = scanner.nextInt();
            while (selectCase<1 || selectCase>3){
                System.out.println("Geçersiz değer, tekrar giriniz:");
                selectCase = scanner.nextInt();
            }
            switch (selectCase){
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmor();
                    buyArmor();
                    break;
                case 3:
                    System.out.println("Bir daha bekleriz...");
                    showMenu = false;
                    break;
            }
        }
        return true;
    }

    public void printWeapon(){
        System.out.println("----------- Silahlar --------------");
        for (Weapon w:Weapon.weapons()){
            System.out.println(w.getId()+". "+ w.getName()+ " <Para : "+ w.getPrice()+ ", Hasar: "+ w.getDamage()+">");
        }
        System.out.println("0 - Çıkış Yap");
    }

    public void buyWeapon(){
        System.out.println("Bir silah seçiniz:");
        int selectWeapon = scanner.nextInt();
        while (selectWeapon<0 || selectWeapon>Weapon.weapons().length){
            System.out.println("Geçersiz değer, tekrar giriniz:");
            selectWeapon = scanner.nextInt();
        }

        if (selectWeapon != 0){
            Weapon selectedWeapon = Weapon.getWeaponById(selectWeapon);
            if (selectedWeapon != null){
                if (selectedWeapon.getPrice() > this.getPlayer().getMoney()){
                    System.out.println("Yeterli paranız bulunmamaktadır.");
                }
                else {
                    System.out.println(selectedWeapon.getName()+" silahını satın aldınız.");
                    int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan paranız: "+this.getPlayer().getMoney() );
                    System.out.println("Önceki silahınız : "+this.getPlayer().getInventory().getWeapon().getName());
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                    System.out.println("Yeni silahınız : "+this.getPlayer().getInventory().getWeapon().getName());
                }
            }
        }



    }
    public void printArmor(){
        for (Armor a:Armor.armors()){
            System.out.println(a.getId()+". "+ a.getName()+ " <Para : "+ a.getPrice()+ ", Zırh: "+ a.getBlock()+">");
        }
        System.out.println("0 - Çıkış Yap");
    }

    public void buyArmor(){
        System.out.println("Bir zırh seçiniz:");

        int selectArmorId = scanner.nextInt();
        while (selectArmorId<0 || selectArmorId>Armor.armors().length){
            System.out.println("Geçersiz değer, tekrar giriniz:");
            selectArmorId = scanner.nextInt();
        }

        if (selectArmorId != 0){
            Armor selectedArmor = Armor.getArmorObjById(selectArmorId);

            if (selectedArmor.getPrice() > this.getPlayer().getMoney()){
                System.out.println("Yeterli paranız bulunmamaktadır.");
            }
            else {
                System.out.println(selectedArmor.getName()+ " zırhını satın aldınız !");
                int balance = this.getPlayer().getMoney() - selectedArmor.getPrice();
                this.getPlayer().setMoney(balance);
                this.getPlayer().getInventory().setArmor(selectedArmor);
                System.out.println("Kalan bakiye:"+ this.getPlayer().getMoney());
            }

        }
    }
}
