package Extra;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zephyr on 3/11/16.
 */
public class Report {
    private String message="";
    private String author="";
    private String date="";
    private String note="";
    private String branch="";
    private String revisionNo="";
    private List<String> fileModified=new LinkedList<String>();
    private List<String> fileAdded= new LinkedList<String>();
    private List<String> fileRemoved=new LinkedList<String>();

    public Report() {
    }

    public Report(String message, String author, String date, String note,
                  String branch, String revisionNo, List<String> fileModified,
                  List<String> fileAdded, List<String> fileRemoved) {
        this.message = message;
        this.author = author;
        this.date = date;
        this.note = note;
        this.branch = branch;
        this.revisionNo = revisionNo;
        this.fileModified = fileModified;
        this.fileAdded = fileAdded;
        this.fileRemoved = fileRemoved;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getRevisionNo() {
        return revisionNo;
    }

    public void setRevisionNo(String revisionNo) {
        this.revisionNo = revisionNo;
    }

    public List<String> getFileModified() {
        return fileModified;
    }

    public void setFileModified(String fileModified) {
        this.fileModified.add(fileModified);
    }

    public List<String> getFileAdded() {
        return fileAdded;
    }

    public void setFileAdded(String fileAdded) {
        this.fileAdded.add(fileAdded);
    }

    public List<String> getFileRemoved() {
        return fileRemoved;
    }

    public void setFileRemoved(String fileRemoved) {
        this.fileRemoved.add(fileRemoved);
    }

    public boolean writeFile(String path){
        String formattedMessage=message+"\n"
                +"Author: "+author+"\n"+"Date: "+date+"\n"+"Note: "+note+"\n"+"Branch: "+branch+"\n"
                +"Revision No: "+revisionNo+"\n";
        formattedMessage+="File Modified: ";
        for (String file:fileModified){
            formattedMessage+=file+"\t";
        }
        formattedMessage+="\nFile Added: ";
        for (String file:fileAdded){
            formattedMessage+=file+"\t";
        }
        formattedMessage+="\nFile Removed: ";
        for (String file:fileRemoved){
            formattedMessage+=file+"\t";
        }
        path+="/report.txt";
        try {
            Files.write(Paths.get(path), formattedMessage.getBytes());
            return true;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }
}
