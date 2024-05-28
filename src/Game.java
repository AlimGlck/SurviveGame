import java.util.Scanner;

public class Game {
    Scanner scanner = new Scanner(System.in);
    public void start(){
        System.out.println();
        System.out.println("Macera Oyununa Hoşgeldiniz.");
        System.out.println();
        System.out.print("Lütfen bir isim giriniz:");
        String playerName = scanner.nextLine();
        Player player = new Player(playerName);
        System.out.println();
        System.out.print("Sayın " + player.getName()+ " lütfen bir karakter seçiniz.");
        player.selectChar();
        Location myLocation = null;
        while (true){
            player.printInfo();
            System.out.println();
            Location [] locations ={new SafeHouse(player),new ToolStore(player),new Cave(player),new Forest(player),new River(player),new Cliffs(player)};
            System.out.println("Bölgeler");
            System.out.println("------------------------------");
            for(Location location : locations){
                System.out.println(+location.getId()+". "+location.getLocationName() );
            }
            System.out.println("0 - Çıkış Yap --> Oyunu Sonlandır.");
            System.out.println("------------------------------");
            System.out.println();
            System.out.print("Lütfen gitmek istediğiniz bölgeyi seçiniz:");
            int selectLoc = scanner.nextInt();
            switch (selectLoc){
                case 0:
                    myLocation = null;
                    break;
                case 1:
                    myLocation = new SafeHouse(player);
                    break;
                case 2:
                    myLocation = new ToolStore(player);
                    break;
                case 3:
                    myLocation = new Cave(player);
                    break;
                case 4:
                    myLocation = new Forest(player);
                    break;
                case 5:
                    myLocation = new River(player);
                    break;
                case 6:
                    myLocation = new Cliffs(player);
                    break;
                default:
                    myLocation = new SafeHouse(player);
            }
            if (myLocation == null){
                break;
            }
            if (myLocation.getLocationName() == "Güvenli Ev" && myLocation.getPlayer().getInventory().isCaveLocAward() == true && myLocation.getPlayer().getInventory().isForestLocAward() == true && myLocation.getPlayer().getInventory().isRiverLocAward() == true ){
                System.out.println("Oyunu kazandınız ve adadan kurtuldunuz!");
                break;

            }
            if (!myLocation.onLocation()){
                System.out.println("Game Over.");
                break;
            }


        }
    }
}
