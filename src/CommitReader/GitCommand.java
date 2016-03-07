package CommitReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SACHIN on 3/3/2016.
 */
public class GitCommand {
    public Map<String,List<String>> getMessage(String commitId){
        String[] commandList = new String[]{"git log --format=%B -n 1","git diff-tree --no-commit-id --name-status -r","git --no-pager log -1 --pretty=format:\"%an <%ae>\"",
                "git branch --contains","git show -s --format=%ci","git show"};
        List<String> commitList = new ArrayList<>();//4d7f6c057465157e9f892497ddba8e61d769fe46
        List<String> commitMessage = new ArrayList<>();
        List<String> modiOrDelList = new ArrayList<>();
        Map<String,List<String>> allList=new HashMap<>();
        for(String eachCommand:commandList){
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "cd \"C:\\Users\\deerwalk\\IdeaProjects\\MorningClass\" && "+eachCommand+" "+commitId);
            builder.redirectErrorStream(true);
            Process p = null;
            try {
                p = builder.start();
                BufferedReader r = null;
                r = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line = null;
                boolean add = true;
                while (true) {
                    try {
                        line = r.readLine();
                        if(line!=null) {
                            if (eachCommand.equals(commandList[0])) {
                                commitMessage.add(line);
                            } else if (eachCommand.equals(commandList[1])) {
                                modiOrDelList.add(line);
                            } else if (eachCommand.equals(commandList[2])) {
                                commitList.add("Author: " + line);
                            } else if (eachCommand.equals(commandList[3])) {
                                commitList.add("Branch: " + line);
                            } else if (eachCommand.equals(commandList[4])) {
                                commitList.add("Date: " + line);
                            }else if(eachCommand.equals(commandList[5])){
                                commitList.add(line);
                                break;
                            }
                        }
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
        }
        allList.put("commitMessage",commitMessage);
        allList.put("commitInfo",commitList);
        allList.put("filesChange",modiOrDelList);
        return allList;
    }
}