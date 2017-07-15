package com.example.keonjukim.sheepfarm10;

/**
 * Created by keonjukim on 2016-06-05.
 */
public class alian extends thief{
    public alian(double winx, double winy,int bornAt){
        super(winx,winy,bornAt);
        hp=1;
        this.turn(Math.random()*10%6.283184);
    }
    public void turn(double angle){
        xvel=Math.cos(angle)*winx/50;
        yvel=Math.sin(angle)*winy/200;
    }
    public void attacked(){
        hp--;
    }
}
