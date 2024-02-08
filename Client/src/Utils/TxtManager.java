package Utils;

import Commands.CommandBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class TxtManager {

    public static ArrayList<ArrayList<String>> readLines(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        StringBuilder result = new StringBuilder();
        int character;
        while ((character = fileReader.read()) != -1) {
            result.append((char) character);
        }
        String fileContent = result.toString().replaceAll("\r", "");
        return splitFileString(fileContent.split("\n"));
    }

    private static ArrayList<ArrayList<String>> splitFileString(String[] fileString) {
        ArrayList<String> result_i = new ArrayList<>();
        ArrayList<ArrayList<String>> result = new ArrayList<>();

        for (String fileLine: fileString) {
            String[] fileLineSplit = fileLine.split(" ");

            if (fileLineSplit.length>0 && CommandBuilder.MapOfCommands.containsKey(fileLineSplit[0])) {

                if (Arrays.asList("add", "addIfMin", "removeGreater", "removeLower", "updateId").contains(fileLineSplit[0])) {
                    if (result_i.isEmpty()) result_i.add(fileLine);
                    else {
                        result.add((ArrayList<String>) result_i.clone());
                        result_i.clear();
                        result_i.add(fileLine);
                    }
                } else {
                    if (result_i.isEmpty()) result_i.add(fileLine);
                    else {
                        result.add((ArrayList<String>) result_i.clone());
                        result_i.clear();
                        result_i.add(fileLine);
                    }
                }
            } else result_i.add(fileLine);
        }

        if (!result_i.isEmpty() && !result.contains(result_i)) result.add((ArrayList<String>) result_i.clone());
        return result;
    }


}
