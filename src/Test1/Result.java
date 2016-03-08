package Test1;

import java.util.function.Predicate;

/**
 * Author: SACHIN
 * Date: 3/8/2016.
 */
public class Result {

    private static Result result;

    public static Result getResultInstance(){
        if(result==null){
            return new Result();
        }else {
            return result;
        }
    }

    public boolean checkResult(Marks marks){

        Predicate<Double> isPass = m->m>=40.0;

        if(isPass.test(marks.getSub1())&&isPass.test(marks.getSub2())&&isPass.test(marks.getSub3())&&isPass.test(marks.getSub4())){
            Add<Double> doSum = (double p1,double p2,double p3,double p4)->p1+p2+p3+p4;
            if(doSum.sum(marks.getSub1(),marks.getSub2(),marks.getSub3(),marks.getSub4())>170) {
                return true;
            }
        }
        return false;
    }

    public interface Add<Double>{
        public Double sum(double p1,double p2,double p3,double p4);
    }

    public void printStudent(Student student,String result){
        System.out.println("Name: "+student.getName());
        System.out.println("Age: "+student.getAge());
        Add<Double> doSum = (double p1,double p2,double p3,double p4)->p1+p2+p3+p4;
        Marks marks = student.getMarks();
        System.out.println("Total Marks Obtained:"+doSum.sum(marks.getSub1(),marks.getSub2(),marks.getSub3(),marks.getSub4()));
        System.out.println("Subject 1: "+marks.getSub1());
        System.out.println("Subject 2: "+marks.getSub2());
        System.out.println("Subject 3: "+marks.getSub3());
        System.out.println("Subject 4: "+marks.getSub4());
        System.out.println("Overall Status: "+result);
        System.out.println("--------------------------------------------");
    }


}
