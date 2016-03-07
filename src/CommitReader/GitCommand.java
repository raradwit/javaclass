package CommitReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SACHIN on 3/3/2016.
 */
public class GitCommand {

    public List<String> getMessage(String commitId){

        List<String> commitList = new ArrayList<>();//4d7f6c057465157e9f892497ddba8e61d769fe46

        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "cd \"C:\\Users\\deerwalk\\IdeaProjects\\javaclass\" && git show "+commitId);
        builder.redirectErrorStream(true);
        Process p = null;
        try {
            p = builder.start();
            BufferedReader r = null;
            r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            while (true) {
                try {
                    line = r.readLine();
                    commitList.add(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (line == null) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return commitList;
    }
}