package CommitReader;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by SACHIN on 3/3/2016.
 */
public class Main {
    public static void main(String[] args) {
        GitCommand command = new GitCommand();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Enter the Commit Id:");
        String commitId = scanner.nextLine();
        Map<String,List<String>> allList = command.getMessage("d8954e3c167e58882b7318a75d4a593978f2ce49");//fa7c71a80496c594e0dc544cabbca1969ea6fc84
        FileOperation operation = new FileOperation();
        for(String message:allList.get("commitInfo")){
            if(message.contains(GitMessage.errorMessagePatternOne)|| message.contains(GitMessage.errorMessagePatternTwo)){
                System.out.println("The Commit Id is Incorrect.");
                break;
            }else{
                if(operation.writeToFile(allList)){
                    System.out.println("Saved Successfully.");
                }else{
                    System.out.println("Detail with this Commit Id Already Exists.");
                }
                break;
            }

        }

    }
}
