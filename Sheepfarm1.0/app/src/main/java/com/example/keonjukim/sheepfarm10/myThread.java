package com.example.keonjukim.sheepfarm10;

import android.view.MotionEvent;
import android.widget.Toast;

/**
 * Created by keonjukim on 2016-06-04.
 */
public class myThread extends Thread{
    long startTime;
    public myView view;
    public int frame=0;
    public myThread(myView view){
        this.view = view;
    }
    public int getFrame(){
        return frame;
    }
    public void run(){
        while (view.isrunning){
            while(view.mMode==view.PAUSE);
            startTime=System.currentTimeMillis();
            frame++;
            update();
            view.postInvalidate();
            try {
                Thread.sleep(25-startTime+System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(){
        if((view.missionprocess=view.missiontemp.timepass())<0){
            //missionprocess가 미션의 남은시간. 이를 이용해 그림
            view.money=view.money-view.missiontemp.getRe_money();
            if(view.missiontemp.timeup(view.money,view.sheeps.size())){
               view.missioncount++;
               view.missiontemp = new mission(view.MISSIONLONG,80*view.missioncount,view.missioncount);
                view.missioncheck =2;
                view.mMode = view.PAUSE;
            }
            else{
                view.missioncheck = 3;
                view.mMode = view.PAUSE;
                view.isrunning=false;
                //게임오버
            }
        }
        int size;
        //늑대생성
        if(Math.random()*600%600<=1){
            view.wolfs.add(new thief(view.displayWidth-view.wolfWidth, view.displayHeight - (view.displayWidth / 25) * 7-view.wolfHeight,frame));
        }
        //늑대 움직임 및 잡아먹기
        size=view.wolfs.size();
        for(int i=0;i<size;i++){
            thief thiswolf=view.wolfs.get(i);
            thiswolf.move();
            if(frame-thiswolf.bornAt>60&&thiswolf.hp==4) {
                view.wolfs.remove(i);
                size--;
                i--;
            }
            //늑대가 잡아먹으면 sheepdead
            if(frame-thiswolf.bornAt>400)
                if(view.sheeps.size()>0) {
                    view.sheepdead(0);
                    thiswolf.hp=4;
                    thiswolf.xvel=0;
                    thiswolf.yvel=0;
                    thiswolf.bornAt=frame;
                }
        }
        //외계인
        if(Math.random()*600%600<=1){
            view.alians.add(new alian(view.displayWidth-view.alianWidth, view.displayHeight - (view.displayWidth / 25) * 7-view.alianHeight,frame));
        }
        //외계인 움직임 및 도난
        size=view.alians.size();
        for(int i=0;i<size;i++){
            thief thisalian=view.alians.get(i);
            thisalian.move();
            if(frame-thisalian.bornAt>60&&thisalian.hp==4) {
                view.alians.remove(i);
                size--;
                i--;
            }
            //외계인이 잡아먹으면 sheepdead
            if(frame-thisalian.bornAt>400)
                if(view.sheeps.size()>0) {
                    view.sheepdead(0);
                    thisalian.hp=4;
                    thisalian.xvel=0;
                    thisalian.yvel=0;
                    thisalian.bornAt=frame;
                }
        }
        //양 털 증가, 이미 최고단계이면 sheepdead
        size=view.sheeps.size();
        for(int i=0;i<size;i++){
            sheep thissheep = view.sheeps.get(i);
            thissheep.move();
            if((view.time-thissheep.bornAt)%240==239)
                if(!thissheep.growth()) {
                    view.sheepdead(i);
                    size--;
                    i--;
                }
        }
    }
}
