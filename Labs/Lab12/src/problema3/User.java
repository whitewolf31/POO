package problema3;

public class User implements Observer {

    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(String notification) {
        System.out.println(name + " was notified the following: " + notification);
    }
}
