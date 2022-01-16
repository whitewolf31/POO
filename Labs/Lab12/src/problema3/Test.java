package problema3;

public class Test {
    public static void main(String[] args) {
        Channel channel = new Channel();
        User user1 = new User("Radu");
        User user2 = new User("Laur");
        User user3 = new User("Andrei");
        channel.subscribe(user1);
        channel.subscribe(user2);
        channel.subscribe(user3);
        channel.notify("Hello there");
        channel.unsubscribe(user2);
        channel.notify("General Kenobi");
    }
}
