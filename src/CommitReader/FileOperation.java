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
    String tags[] = new String[]{"commit","Author","Date"};
    public boolean writeToFile(Map<String,List<String>> allList){
        List<String> requiredLines = new ArrayList<>();
        Path file = Paths.get("Commit.txt");
        requiredLines.add("------------------------------------------------------");
        for(String messages:allList.get("commitInfo")){
            System.out.println(messages);
            if(messages!=null) {
                if(messages.contains(tags[0])){
                    if(checkCommitId(messages)){ //Checking for if commit id already exists in file.
                       return false;
                    }
                }
                if (messages.contains(tags[0]) || messages.contains(tags[1]) || messages.contains(tags[2])) {
                    requiredLines.add(messages);
                }
            }
        }

        requiredLines.addAll(allList.get("commitMessage").stream().filter(message -> message != null).collect(Collectors.toList()));

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
