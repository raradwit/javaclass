package CommitReader;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 *
 Author: SACHIN
 Date: 3/3/2016.
 */
public class FileOperation {
    String tags[] = new String[]{"commit","Author","Date"};
    public boolean writeToFile(List<String> commitList){
        List<String> requiredLines = new ArrayList<>();
        Path file = Paths.get("CommitList.txt");
        requiredLines.add("------------------------------------------------------");
        for(String messages:commitList){
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
        try {
            Files.write(file, requiredLines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;

    }

    public boolean checkCommitId(String commitId){
        try {
            String content = new String(Files.readAllBytes(Paths.get("CommitList.txt")));
            if(content.contains(commitId)){
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


}
