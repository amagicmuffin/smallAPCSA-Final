package Logic;

public class ClearScreen {
    public static void main (String[] args)
    {
//        try {
//            final String os = System.getProperty("os.name");
//            if (os.contains("Windows")) {
//                Runtime.getRuntime().exec("cls");
//            }
//        } catch (final Exception e) {
//            e.printStackTrace();
//        }

        System.out.println("skdlfjsdlkf");
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}