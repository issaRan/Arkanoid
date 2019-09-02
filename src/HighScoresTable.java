import java.io.File;
import java.io.Serializable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * @author ISSA 10/06/18
 */
public class HighScoresTable implements Serializable {
    private List<ScoreInfo> topScore;
    private int size;
    private static final int SIZE_OF_TABLE = 5;

    /**
     * // Create an empty high-scores table with the specified size.
     * // The size means that the table holds up to size top scores.
     *
     * @param size the size of the table.
     */
    public HighScoresTable(int size) {
        this.topScore = new ArrayList<>();
        this.size = size;
    }

    /**
     * // Add a high-score.
     *
     * @param score score which include score and name.
     */
    public void add(ScoreInfo score) {
        int rank = getRank(score.getScore());
        if (rank > size) {
            return;
        } else if (rank == size) {
            this.topScore.add(size - 1, score);
        } else {
            this.topScore.add(rank - 1, score);
        }
        if (this.topScore.size() > this.size) {
            for (int i = this.size; i < this.topScore.size(); i++) {
                this.topScore.remove(this.topScore.get(i));
            }
        }
    }

    /**
     * function the detriment if we want to put him in the table.
     *
     * @param score the score.
     * @return a rank.
     */
    public int getRank(int score) {
        int aviliableRanks = 1;
        for (int i = 0; i < topScore.size(); i++) {
            if (topScore.get(i).getScore() < score) {
                return aviliableRanks;
            }
            aviliableRanks++;
        }
        return aviliableRanks;
    }

    /**
     * return the size of the table.
     *
     * @return the size of the table.
     */
    public int size() {
        return this.size;
    }

    /**
     * // Return the current high scores.
     * // The list is sorted such that the highest
     * // scores come first.
     *
     * @return list of score info.
     */
    public List<ScoreInfo> getHighScores() {
        return this.topScore;
    }

    /**
     * // Clears the table.
     */
    public void clear() {
        this.topScore.clear();
    }

    /**
     * // Load table data from file.
     * // Current table data is cleared.
     *
     * @param filename the file.
     * @throws IOException if case of error.
     */
    public void load(File filename) throws IOException {
        this.clear();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
            String line = reader.readLine();
            while (line != null) {
                String name = line;
                line = reader.readLine();
                int scoreToBe;
                try {
                    scoreToBe = Integer.parseInt(line);
                } catch (Exception e) {
                    return;
                }
                ScoreInfo scoreInfo = new ScoreInfo(name, scoreToBe);
                this.add(scoreInfo);
                line = reader.readLine();
            }
        } catch (IOException fileExecption) {
            throw new IOException("problem in the file");
        }
    }

    /**
     * // Save table data to the specified file.
     *
     * @param filename the file.
     * @throws IOException if case of error.
     */
    public void save(File filename) throws IOException {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filename)));
            for (int i = 0; i < this.topScore.size(); i++) {
                writer.println(this.topScore.get(i).getName());
                writer.println(this.topScore.get(i).getScore());
            }
        } catch (IOException e) {
            System.err.println("error");
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * // Read a table from file and return it.
     * // If the file does not exist, or there is a problem with
     * // reading it, an empty table is returned.
     *
     * @param filename the file.
     * @return a table.
     */
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable highScoresTable = new HighScoresTable(SIZE_OF_TABLE);
        if (!filename.exists()) {
            return highScoresTable;
        }
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(filename)));

            // print each read line
            String line = reader.readLine();
            int flag = 0;
            int score = 0;
            String string = null;
            while (line != null) {
                if (flag == 0) {
                    string = line;
                }
                if (flag == 1) {
                    score = Integer.parseInt(line);
                }
                line = reader.readLine();
                if (string != null && flag == 1) {
                    ScoreInfo scoreInfo = new ScoreInfo(string, score);
                    highScoresTable.topScore.add(scoreInfo);
                    flag = -1;
                }
                flag++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("Unable to find file: " + filename.getName());
        } catch (IOException e) {
            System.err.println("Failed reading file: " + filename.getName()
                    + ", message:" + e.getMessage());
            e.printStackTrace(System.err);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename.getName());
            }
        }
        return highScoresTable;
    }
}