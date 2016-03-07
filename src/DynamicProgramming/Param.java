package DynamicProgramming;

/**
 * Author: SACHIN
 * Date: 3/4/2016.
 */
public class Param<T> {
    T elem;

    public Param(T elem){
        this.elem=elem;
    }

    public Param(){
        //this.elem=elem;
    }
    public void setElem(T elem) {
        this.elem = elem;
    }

    public T getElem() {
        return elem;
    }

    public static <E> E returnSame(E elem){
        return elem;
    }




}
