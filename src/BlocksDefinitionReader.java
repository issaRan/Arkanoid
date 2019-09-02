import java.awt.Color;
import java.io.BufferedReader;
import java.util.HashMap;

/**
 * @author ISSA 10/6/18
 * takes information from file.
 */
public class BlocksDefinitionReader {
    /**
     * Constructor.
     *
     * @param reader reader
     * @return factory  returns factory
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        HashMap<String, Integer> spacer = new HashMap<>();
        HashMap<String, BlockCreator> symbolAndBlock = new HashMap<>();
        BufferedReader fromFile = new BufferedReader(reader);
        String lineFromFile, sdefLeft, sdefRight;
        String[] checkLine;
        int hieghtBlockFromDefualt = 0, widthBlockFromDefualt = 0, defualtHit = 0;
        int bdefHitPoints = 0;
        String symbol = null;
        Color stroke = null;
        try {
            while ((lineFromFile = fromFile.readLine()) != null) {
                lineFromFile = lineFromFile.trim();
                HashMap<Integer, ColorOrImage> fillingBdef = new HashMap<>();
                if (lineFromFile.startsWith("#")) {
                    continue;
                } else if (lineFromFile.startsWith("default")) {        //default
                    checkLine = lineFromFile.split(" ");
                    for (int i = 1; i < checkLine.length; i++) {
                        String[] theFirstOfTheString = checkLine[i].split(":");
                        if (theFirstOfTheString[0].contains("height")) {
                            hieghtBlockFromDefualt = Integer.parseInt(theFirstOfTheString[1]);
                        }
                        if (theFirstOfTheString[0].contains("width")) {
                            widthBlockFromDefualt = Integer.parseInt(theFirstOfTheString[1]);
                        }
                        if (theFirstOfTheString[0].contains("hit_points")) {
                            defualtHit = Integer.parseInt(theFirstOfTheString[1]);
                        }
                        if (theFirstOfTheString[0].contains("stroke")) {
                            stroke = ColorOrImage.colorFromString(theFirstOfTheString[1]);
                        }
                    }
                } else if (lineFromFile.startsWith("bdef")) {
                    checkLine = lineFromFile.split(" ");
                    bdefHitPoints = defualtHit;
                    for (int i = 1; i < checkLine.length; i++) {
                        String[] theFirstOfString = checkLine[i].split(":");
                        if (theFirstOfString[0].contains("symbol")) {
                            symbol = theFirstOfString[1];
                        }
                        if (theFirstOfString[0].contains("height")) {
                            hieghtBlockFromDefualt = Integer.parseInt(theFirstOfString[1]);
                        }
                        if (theFirstOfString[0].contains("width")) {
                            widthBlockFromDefualt = Integer.parseInt(theFirstOfString[1]);
                        }
                        if (theFirstOfString[0].contains("hit_points")) {
                            bdefHitPoints = Integer.parseInt(theFirstOfString[1]);
                        }
                        if (theFirstOfString[0].contains("stroke")) {
                            stroke = ColorOrImage.colorFromString(theFirstOfString[1]);
                        }
                        if (theFirstOfString[0].contains("fill-")) {
                            if (theFirstOfString[1].contains("image")) {
                                String[] divide = theFirstOfString[1].split("\\(");
                                String whatWeWant = divide[1].split("\\)")[0];
                                int num = Integer.parseInt(theFirstOfString[0].split("\\-")[1]);
                                fillingBdef.put(num, new ColorOrImage(ColorOrImage.parseImage(whatWeWant)));
                            }
                            if (theFirstOfString[1].contains("color")) {
                                int num = Integer.parseInt(theFirstOfString[0].split("\\-")[1]);
                                fillingBdef.put(num,
                                        new ColorOrImage(ColorOrImage.colorFromString(theFirstOfString[1])));
                            }
                        }
                        if (theFirstOfString[0].equals("fill")) {
                            if (theFirstOfString[1].contains("image")) {
                                String[] divide = theFirstOfString[1].split("\\(");
                                String whatWeWant = divide[1].split("\\)")[0];
                                fillingBdef.put(1,
                                        new ColorOrImage(ColorOrImage.parseImage(whatWeWant)));
                            }
                            if (theFirstOfString[1].contains("color")) {
                                fillingBdef.put(1,
                                        new ColorOrImage(ColorOrImage.colorFromString(theFirstOfString[1])));
                            }
                        }
                    }
                    if (fillingBdef.isEmpty() || hieghtBlockFromDefualt == 0 || widthBlockFromDefualt == 0
                            || bdefHitPoints == 0 || symbol == null) {
                        System.out.println("missig parts");
                        System.exit(1);
                    } else {
                        MakeBlock block = new MakeBlock();
                        block.setColorOrImage(fillingBdef);
                        block.setHeight(hieghtBlockFromDefualt);
                        block.setWidth(widthBlockFromDefualt);
                        block.setHitPoint(bdefHitPoints);
                        if (stroke != null) {
                            block.setStroke(stroke);
                        }
                        symbolAndBlock.put(symbol, block);
                    }
                } else if (lineFromFile.startsWith("sdef")) {
                    checkLine = lineFromFile.split(" ");
                    sdefLeft = checkLine[1].split(":")[1];
                    sdefRight = checkLine[2].split(":")[1];
                    spacer.put(sdefLeft, Integer.parseInt(sdefRight));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fromFile.close();
            } catch (Exception k) {
                k.printStackTrace();
            }
        }
        return new BlocksFromSymbolsFactory(spacer, symbolAndBlock);
    }
}
