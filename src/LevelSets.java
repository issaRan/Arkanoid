import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.LineNumberReader;
/**
 * @author ISSA  10/6/18
 */
public class LevelSets {
    /**
     * return a string of selection.
     * @param reader java reader.
     * @return selection as strings.
     */
    public static List<Selection<String>> fromReader(Reader reader) {
        List<Selection<String>> checking = new ArrayList<>();
        String line = null;
        String path = null;
        Selection<String> transfer;
        String[] parse = null;
        try {
            LineNumberReader theText = new LineNumberReader(reader);
            while ((line = theText.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }
                if (theText.getLineNumber() % 2 != 0) {
                    parse = line.split(":");
                } else {
                    path = line;
                    transfer = new Selection<>(parse[0], parse[1], path);
                    checking.add(transfer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return checking;
    }
}