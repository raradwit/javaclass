package CommitReader;

import java.util.List;
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
        List<String> commitList = command.getMessage("fa7c71a80496c594e0dc544cabbca1969ea6fc84");//fa7c71a80496c594e0dc544cabbca1969ea6fc84
        FileOperation operation = new FileOperation();
        for(String message:commitList){
            if(message.contains(GitMessage.errorMessagePatternOne)|| message.contains(GitMessage.errorMessagePatternTwo)){
                System.out.println("The Commit Id is Incorrect.");
                break;
            }else{
                if(operation.writeToFile(commitList)){
                    System.out.println("Saved Successfully.");
                }else{
                    System.out.println("Detail with this Commit Id Already Exists.");
                }
                break;
            }

        }

    }
}
