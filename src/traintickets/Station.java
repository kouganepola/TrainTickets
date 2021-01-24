/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traintickets;

/**
 *
 * @author USER
 */
class Station {

    private String stcode, name;
    private int first_cls, second_cls, third_cls_A, third_cls_B, third_cls_C, total;

    public Station(String stcode, String name, int first_cls, int second_cls, int third_cls_A, int third_cls_B, int third_cls_C, int total) {
        this.stcode = stcode;
        this.name = name;
        this.first_cls = first_cls;
        this.second_cls = second_cls;
        this.third_cls_A = third_cls_A;
        this.third_cls_B = third_cls_B;
        this.third_cls_C = third_cls_C;
        this.total = total;

    }

    public String getstcode() {
        return stcode;
    }

    public String getname() {
        return name;
    }

    public int getfirst_cls() {
        return first_cls;
    }

    public int getsecond_cls() {
        return second_cls;
    }

    public int getthird_cls_A() {
        return third_cls_A;
    }

    public int getthird_cls_B() {
        return third_cls_B;
    }

    public int getthird_cls_C() {
        return third_cls_C;
    }

    public int gettotal() {
        return total;
    }

}
