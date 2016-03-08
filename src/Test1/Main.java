package Test1;

import java.util.List;
import java.util.function.Supplier;

/**
 * Author: SACHIN
 * Date: 3/8/2016.
 */
public class Main {

    public static void main(String[] args) {
//        Student obj1 = new Student();
        Supplier<Student> obj = Student::new;
        Student student = obj.get();

        Supplier<List<Student>> s = Student::studentList;

        List<Student> stdList = s.get();

        Result result = Result.getResultInstance();


        for(Student std:stdList){
            System.out.println("Result Of Student");
            if(result.checkResult(std.getMarks())){
                result.printStudent(std,"Pass");
            }else{
                result.printStudent(std,"Fail");
            }
        }


    }
}
