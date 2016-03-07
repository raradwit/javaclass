package CommitReader;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 Author: SACHIN
 Date: 3/3/2016.
 */
public class FileOperation {
    public boolean writeToFile(Map<String,List<String>> allList){
        if(checkCommitId(allList.get("commitInfo").get(allList.get("commitInfo").size()-1))){
            return false;
        }
        List<String> requiredLines = new ArrayList<>();
        Path file = Paths.get("Commit.txt");
        requiredLines.add("------------------------------------------------------\n");
        requiredLines.addAll(allList.get("commitInfo").stream().filter(messages -> messages != null).collect(Collectors.toList()));

        requiredLines.add("\n");

        requiredLines.addAll(allList.get("commitMessage").stream().filter(message -> message != null).collect(Collectors.toList()));

        requiredLines.add("Files Modified/Deleted/Changes:");

        for(String message:allList.get("filesChange")){
            if(message!=null) {
                if (String.valueOf(message.charAt(0)).equalsIgnoreCase("M")) {
                    message = message.substring(1, message.length());
                    requiredLines.add("Modified: " + message.trim());

                }else if(String.valueOf(message.charAt(0)).equalsIgnoreCase("D")){
                    message = message.substring(1, message.length());
                    requiredLines.add("Deleted: " + message.trim());
                }else if(String.valueOf(message.charAt(0)).equalsIgnoreCase("A")){
                    message = message.substring(1, message.length());
                    requiredLines.add("Added: " + message.trim());
                }
            }
        }
        try {
            Files.write(file, requiredLines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;

    }

    public boolean checkCommitId(String commitId){
        try {
            String content = new String(Files.readAllBytes(Paths.get("Commit.txt")));
            if(content.contains(commitId)){
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


}
