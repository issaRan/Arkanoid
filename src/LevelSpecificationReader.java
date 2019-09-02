import javax.imageio.ImageIO;
import java.io.InputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.Image;
import java.util.ArrayList;
import java.awt.Color;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author ISSA.
 */
public class LevelSpecificationReader {
    /**
     * return a level list.
     *
     * @param reader file to read from.
     * @return return a level list.
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        List<LevelInformation> theLevels = new ArrayList<>();
        Image newImage;
        boolean enterd = false;
        BlocksFromSymbolsFactory blocksFromSymbolsFactory = null;
        int blockStartXV1 = 0;
        int blockSrartYV1 = 0;
        int skipList = 0;
        int rowHeightV1 = 0;
        int paddleSpeed = 0, paddleWidth = 0, numbersBall = 0,
                numberOfBlocks = 0, blockStartX = 0, blockStartY = 0, rowHeight = 0, keep = 0, howManyThings = 0;
        BufferedReader theReader = new BufferedReader(reader);
        CreatingLevels creatingLevels = new CreatingLevels();
        String lineFromFile = null, levelName;
        String[] parts;
        try {
            lineFromFile = theReader.readLine();
            while ((lineFromFile = theReader.readLine()) != null) {
                parts = lineFromFile.split(" ");
                lineFromFile = lineFromFile.trim();
                if (lineFromFile.startsWith("#") || lineFromFile.length() == 0) {
                    continue;
                } else if (lineFromFile.equals("START_LEVEL")) {
                    howManyThings = 0;
                    keep++;
                } else if (lineFromFile.contains("level_name")) {
                    howManyThings++;
                    parts = lineFromFile.split(":");
                    creatingLevels.setlevelName(parts[1]);
                } else if (lineFromFile.contains("ball_velocities")) {
                    List<Velocity> theVelocity = new ArrayList<>();
                    howManyThings++;
                    parts = lineFromFile.split(":");
                    String[] velocityInString = parts[1].split(" ");
                    String[] sendToVelocity;
                    for (int i = 0; i < velocityInString.length; i++) {
                        sendToVelocity = velocityInString[i].split(",");
                        Velocity velocity = Velocity.fromAngleAndSpeed(Double.parseDouble(sendToVelocity[0])
                                , Double.parseDouble(sendToVelocity[1]));
                        theVelocity.add(velocity);
                    }
                    creatingLevels.setInitialBallVelocities(theVelocity);
                } else if (lineFromFile.contains("paddle_speed")) {
                    howManyThings++;
                    parts = lineFromFile.split(":");
                    creatingLevels.setPaddleSpeed(Integer.parseInt(parts[1]));
                } else if (lineFromFile.contains("paddle_width")) {
                    howManyThings++;
                    parts = lineFromFile.split(":");
                    creatingLevels.setPaddleWidth(Integer.parseInt(parts[1]));
                } else if (lineFromFile.contains("blocks_start_x")) {
                    howManyThings++;
                    parts = lineFromFile.split(":");
                    blockStartXV1 = Integer.parseInt(parts[1]);
                    blockStartX = blockStartXV1;
                } else if (lineFromFile.contains("blocks_start_y")) {
                    howManyThings++;
                    parts = lineFromFile.split(":");
                    blockSrartYV1 = Integer.parseInt(parts[1]);
                    //blockStartY = blockSrartYV1;

                } else if (lineFromFile.contains("row_height")) {
                    howManyThings++;
                    parts = lineFromFile.split(":");
                    rowHeightV1 = Integer.parseInt(parts[1]);
                } else if (lineFromFile.contains("num_blocks")) {
                    howManyThings++;
                    parts = lineFromFile.split(":");
                    int numBlock = Integer.parseInt(parts[1]);
                    creatingLevels.setNumberOfblocks(numBlock);
                } else if (lineFromFile.contains("background")) {
                    howManyThings++;
                    parts = lineFromFile.split(":");
                    String[] param = parts[1].split("\\(");
                    if (parts[1].contains("image")) {
                        String image = param[1].split("\\)")[0];
                        Image theImage = fillImage(image);
                        creatingLevels.setGetBackground(new ImageForBackground(theImage));
                    }
                    if (parts[1].contains("color")) {
                        String[] theDivide = parts[1].split("color");
                        String color = theDivide[1];
                        Color theColor = fromStringToColor(color);
                        creatingLevels.setGetBackground(new ColorForBackground(theColor));
                    }

                } else if (lineFromFile.contains("block_definitions")) {
                    howManyThings++;
                    parts = lineFromFile.split(":");
                    //File file = new File(parts[1]);
                    InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(parts[1]);
                    Reader theSecondReader = new InputStreamReader(is);
                    BufferedReader br = new BufferedReader(theSecondReader);
                    blocksFromSymbolsFactory = BlocksDefinitionReader.fromReader(br);
                    //readerBlock.close();
                } else if (lineFromFile.contains("START_BLOCKS")) {
                    howManyThings++;
                    List<Block> block = new ArrayList<>();
                    boolean omg = false;
                    while (!lineFromFile.contains("END_BLOCKS")) {
                        lineFromFile = theReader.readLine();
                        for (int i = 0; i < lineFromFile.length(); i++) {
                            String blockOrSymbol = String.valueOf(lineFromFile.charAt(i));
                            if (blocksFromSymbolsFactory.isBlockSymbol(blockOrSymbol)) {
                                Block theBlock = blocksFromSymbolsFactory.getBlock(blockOrSymbol,
                                        blockStartXV1, blockSrartYV1);
                                block.add(theBlock);
                                blockStartXV1 += theBlock.getCollisionRectangle().getWidth();
                                omg = true;
                            } else if (blocksFromSymbolsFactory.isSpaceSymbol(blockOrSymbol)) {
                                blockStartXV1 += blocksFromSymbolsFactory.getSpaceWidth(blockOrSymbol);
                                omg = true;
                            }
                        }
                        blockStartXV1 = blockStartX;
                        if (omg) {
                            blockSrartYV1 += rowHeightV1;
                        }
                        omg = false;
                    }
                    creatingLevels.setBlocks(block);
                } else if (lineFromFile.startsWith("END_LEVEL")) {
                    if (howManyThings != 11) {
                        throw new Exception("some parts are missing");
                    }
                    theLevels.add(creatingLevels);
                    creatingLevels = new CreatingLevels();
                }
            }
        } catch (
                Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return theLevels;
    }

    /**
     * function to convert string to Image.
     *
     * @param image string path.
     * @return image.
     */
    public Image fillImage(String image) {
        Image imageV1 = null;
        BufferedReader reader = null;
        try {
            imageV1 = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(image));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageV1;
        /*
        String[] spliter = image.split("\\/");
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(spliter[1]);
        System.out.println(spliter[1]);
        try {
            //InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(spliter[1]);
            imageV1 = ImageIO.read(is);
        } catch (IOException e) {
            System.out.println("Error: failed to load image");
        }
        return imageV1;
        */
    }

    /**
     * convert string to color.
     *
     * @param s string path.
     * @return Color.
     */
    public Color fromStringToColor(String s) {
        if (s.contains("red")) {
            return Color.RED;
        }
        if (s.contains("blue")) {
            return Color.BLUE;
        }
        if (s.contains("green")) {
            return Color.GREEN;
        }
        if (s.contains("yellow")) {
            return Color.YELLOW;
        }
        if (s.contains("cyan")) {
            return Color.CYAN;
        }
        if (s.contains("orange")) {
            return Color.ORANGE;
        }
        if (s.contains("pink")) {
            return Color.PINK;
        }
        if (s.contains("gray")) {
            return Color.GRAY;
        }
        if (s.contains("lightGray")) {
            return Color.LIGHT_GRAY;
        }
        if (s.contains("black")) {
            return Color.BLACK;
        }
        if (s.contains("white")) {
            return Color.WHITE;
        }
        String[] first = s.split("\\(");
        String[] second = first[2].split("\\,");
        String[] third = second[2].split("\\)");
        int r = Integer.parseInt(second[0]);
        int g = Integer.parseInt(second[1]);
        int b = Integer.parseInt(third[0]);
        return (new Color(r, g, b));
    }
}