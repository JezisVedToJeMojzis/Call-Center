package callcenter.cli;
import java.io.*;
import java.util.UUID;

public class KeyboardInput {
    public static String readString(String string_for_user)
    {
        String s = "";

        BufferedReader keyboardInput = new BufferedReader(new InputStreamReader(System.in));
        try
        {	System.out.println(string_for_user);
            s = keyboardInput.readLine();
            //System.out.println("Nacital som " + s);
        }
        catch (Exception e)
        {
            System.out.println("Wrong input. Try again.");
            s = readString(string_for_user);
        }

        return s;
    }

    public static int readInt(String string_for_user)
    {
        int n = 0;
        String s;

        BufferedReader keyboardInput = new BufferedReader(new InputStreamReader(System.in));
        try
        {	System.out.println(string_for_user);
            s = keyboardInput.readLine();
            n = Integer.parseInt(s);
        }
        catch (Exception e)
        {
            System.out.println("Wrong input. Try again.");
            n = readInt(string_for_user);
        }

        return n;
    }

    public static UUID readUUID(String prompt) {
        UUID uuid = null;
        String input;

        BufferedReader keyboardInput = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println(prompt);
            input = keyboardInput.readLine();
            uuid = UUID.fromString(input);
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Wrong input or invalid UUID format. Try again.");
            uuid = readUUID(prompt);
        }

        return uuid;
    }
}
