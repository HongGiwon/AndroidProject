package com.example.keonjukim.sheepfarm10;

/**
 * Created by keonjukim on 2016-06-04.
 */
public class thief {
    public int hp;
    public double xpos;
    public double ypos;
    public double xvel;
    public double yvel;
    public double winx;
    public double winy;
    public int bornAt;
    public thief(double winx, double winy,int bornAt){
        hp=3;
        this.bornAt = bornAt;
        xpos = Math.random()*winx%(winx/3)+winx/3;
        ypos = Math.random()*winy%(winy/3)+winy/3;
        this.winx =winx;
        this.winy = winy;
        this.turn(Math.random()*10%6.283184);
    }
    public void turn(double angle){
        xvel=Math.cos(angle)*winx/500;
        yvel=Math.sin(angle)*winy/700;
    }
    public void move(){
        xpos+=xvel;
        ypos+=yvel;
        if(xpos>winx-100||xpos<0) xvel=-xvel;
        if(ypos>winy||ypos<50) yvel=-yvel;
    }
    public void attacked(){
        if(hp<4) hp--;
    }
}