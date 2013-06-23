package net.lampetty.samples.lombok;

public class LombokSample {

    public static void main(String[] args) {
        User user = new User();
        user.setId(1);
        user.setName("oinume");
        user.setEmail("oinume@gmail.com");
        System.out.println("user = " + user);
    }
}
