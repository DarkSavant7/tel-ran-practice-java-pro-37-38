package de.telran.practice5;

//T(ype), E(lement), K(ey), V(alue), N(umber)
//public class BoxGeneric<TYPE, ELEMENT, TYPE3> {
public class BoxGeneric<TYPE> {
    //    private static TYPE someStatic = 10;
    private TYPE obj;
//    private TYPE[] arr;

    public TYPE getObj() {
        return obj;
    }

    public void setObj(TYPE obj) {
        this.obj = obj;
    }

    public BoxGeneric(TYPE obj /*, TYPE[] arr*/) {
        this.obj = obj;
//        this.arr = arr;
//        TYPE some = new TYPE();
//        TYPE[] arr = new TYPE[10];
//        TYPE[] arr = (TYPE[]) new Object[10];
    }
}
