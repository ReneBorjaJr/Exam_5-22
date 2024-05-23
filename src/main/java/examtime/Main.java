package examtime;

public class Main {
    public static void main(String[] args) {
        SocialHandler twitter = new SocialHandler();

        twitter.addHandleToFile("ReneBorja");
        twitter.addHandleToFile("mikejones");
        twitter.addHandleToFile("mikejones");
        twitter.addHandleToFile("daddy");

        twitter.printHandlesFromFile();

    }
}
