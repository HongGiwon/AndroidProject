package com.example.keonjukim.sheepfarm10;

/**
 * Created by keonjukim on 2016-06-05.
 */
public class sheep {
    public static int growSpeed = 0;
    public int bornAt;
    public boolean touched;
    public int pur;
    public double xpos;
    public double ypos;
    public double xvel;
    public double yvel;
    public double winx;
    public double winy;
    public int color = 0;
    public int value;
    public sheep(double winx, double winy, int bornAt){
        this.bornAt =bornAt;
        pur=1;
        touched=false;
        xpos = winx/2;
        ypos = 3*winx/4;
        this.winx =winx;
        this.winy = winy;
        this.turn(Math.random()*10%6.283184);
        value=5;
        color=0;
    }
    public sheep(double winx, double winy, int bornAt,int color){
        this.color=color;
        this.bornAt =bornAt;
        pur=1;
        touched=false;
        xpos = winx/2;
        ypos = 3*winx/4;
        this.winx =winx;
        this.winy = winy;
        this.turn(Math.random()*10%6.283184);
        if(color==1)
            value=10;
        else if(color==2)
            value=2;
    }
    public void turn(double angle){
        xvel=Math.cos(angle)*winx/600;
        yvel=Math.sin(angle)*winy/900;
    }
    public void move(){
        xpos+=xvel;
        ypos+=yvel;
        if(xpos>winx-50||xpos<0) xvel=-xvel;
        if(ypos>winy||ypos<0) yvel=-yvel;
    }
    public void attacked(){
        pur=1;
    }
    public boolean growth(){
        if(pur<3){
            if(growSpeed==0) pur++;
            else;
            return true;
        }
        else{
            return false;
        }
    }
}