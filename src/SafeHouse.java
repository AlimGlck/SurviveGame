public class SafeHouse extends NormalLoc{
    public SafeHouse(Player player){
     super(player,"Güvenli Ev",1);
    }

    @Override
    public boolean onLocation() {
        System.out.println("Güvenli evdesiniz!");
        System.out.println("Canınız yenilendi!");
        this.getPlayer().setHealth(this.getPlayer().getOriginalHealt());
        return true;
    }
}
