package Extra;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by zephyr on 3/9/16.
 */

public class GitCommitMessage {
    public static void main(String[] args) throws IOException, InterruptedException {
        String[] msgs;
        boolean furtherProcess = true;
        List<String> messages;
        List<String> extractedMessage=new LinkedList<String>();
        List<String> files = new LinkedList<String>();
        List<String> fileOperation=new LinkedList<String>();
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter commit ID: ");
        String commitID= scan.nextLine();
        String path;
        while(true){
            System.out.println("Enter path of git repo clone: ");
            path=scan.nextLine();
            if (new File(path).exists()){
                break;
            }
            else {
                System.out.println("Invalid Path!");
            }
        }

        extractedMessage.add(commitID);
        String[] cmd = {"git","branch","--contains",commitID};
        String[] cmd1 = {"git","show","--name-status","--format=%B%an <%ae>,%ar,%N ",commitID};

        messages=runGitCommand(cmd,path);
        if (!messages.isEmpty()){
            for (String message:messages){
                message= message.replace("* ","");
                extractedMessage.add(message);
            }

            messages=runGitCommand(cmd1,path);
            for (int i = 0; i <messages.size() ; i++) {
                String message=messages.get(i);
                if (message.contains(GitErrorMessages.errorMessageOne)||message.contains(GitErrorMessages.errorMessageTwo)
                        ||message.contains(GitErrorMessages.errorMessageThree)){
                    System.out.println("Commit ID is incorrect or Directory is not a git repository!");
                    furtherProcess=false;
                    break;
                }
                if (i<2){
                    msgs = message.split(",");
                    Collections.addAll(extractedMessage, msgs);
                }
                else if (i>2){
                    message=message.replaceAll("\\s+", " ");
                    msgs= message.split(" ");
                    fileOperation.add(msgs[0]);
                    files.add(msgs[1]);
                }
            }
            if (furtherProcess){
                extractedMessage.add(commitID);
                if (generateReport(extractedMessage,files,fileOperation,path)){
                    System.out.println("report.txt successfully generated");
                }
                else {
                    System.out.println("Error in generating report");
                }
            }
        }else {
            System.out.println("Invalid Commit ID!!");
        }


    }

    public static List<String> runGitCommand(String[] cmd, String path) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder(cmd);

        pb.directory(new File(path));

        Process p =pb.start();

        p.waitFor();

        BufferedReader buf = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String message="";

        List<String> output  = new LinkedList<String>();

        while ((message=buf.readLine())!=null){
            output.add(message);
        }
        return output;
    }

    public static boolean generateReport(List<String> report,List<String> fileNames,List<String> fileOperation,String path){
        Report file = new Report();
        file.setBranch(report.get(1));
        file.setMessage(report.get(2) + " \n" + report.get(3).trim() + " \n" + report.get(4).trim());
        file.setAuthor(report.get(5));
        file.setDate(report.get(6));
        file.setNote(report.get(7));
        file.setRevisionNo(report.get(0));
        int fileNameIndex=0;
        for (String op:fileOperation){
            if (op.equals("A")) {
                file.setFileAdded(fileNames.get(fileNameIndex++));

            } else if (op.equals("D")) {
                file.setFileRemoved(fileNames.get(fileNameIndex++));

            } else {
                file.setFileModified(fileNames.get(fileNameIndex++));

            }
        }
        return file.writeFile(path);
    }
}
