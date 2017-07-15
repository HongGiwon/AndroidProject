package com.example.keonjukim.sheepfarm10;

/**
 * Created by 2014313366 on 2016-06-21.
 */
public class mission {
    double due_time;
    int re_money;
    int re_sheep;

    public double timepass ()
    {
        due_time = due_time - 25;
        return due_time;
    }
    public mission(double time, int money, int sheep)
    {
            due_time = time;
            re_money = money;
            re_sheep = sheep;
    }
    public boolean timeup(int cur_money, int cur_sheep)
    {
        if(cur_money < 0 || cur_sheep < re_sheep) {
            //게임오버
            return false;
        }
        else{
            //미션성공
            return true;
        }
    }
    public int getRe_money(){
        return re_money;
    }

    public int getRe_sheep(){
        return re_sheep;
    }
    public double getDue_time(){
        return due_time;
    }
}
