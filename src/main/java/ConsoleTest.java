import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ConsoleTest {
    public static void main(String[] args) {
        DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
        Date date = new Date();

        String[] modelNumberSplit = "123-dsfsdf-123".split("-");
        System.out.println(modelNumberSplit[1]);
    }
}
