import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TgTestFileCreator {
    private static final String TG_TEST_PATH = "./src/test/resources/tg_test.csv";

    public static void main(String[] args) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(TG_TEST_PATH, false));
        writer.println("x,y");
        for (double x = -Math.PI/2+0.05; x <= Math.PI / 2; x += 0.1) {
            writer.print(x);
            writer.print(',');
            writer.println(Math.tan(x));
        }
        writer.close();
    }
}
