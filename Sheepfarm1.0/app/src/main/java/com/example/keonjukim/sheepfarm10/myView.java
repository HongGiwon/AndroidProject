package com.example.keonjukim.sheepfarm10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by keonjukim on 2016-06-04.
 */
public class myView extends View{
    public ArrayList<alian> alians = new ArrayList<>();
    public ArrayList<thief> wolfs= new ArrayList<>();
    public ArrayList<sheep> sheeps= new ArrayList<>();
    int time;
    int money;
    int sheepnum;
    int color;
    double rand;
    public boolean isrunning;
    public static int RUN = 1;
    public static int PAUSE = 2;
    public static int MISSIONLONG=24000;
    public int mMode = RUN;
    Bitmap deadbyalian;
    Bitmap deadbywolf;
    Bitmap wolf1;
    Bitmap wolf2;
    Bitmap wolf3;
    Bitmap background;
    Bitmap sheep1;
    Bitmap sheep2;
    Bitmap sheep3;
    Bitmap gold1;
    Bitmap gold2;
    Bitmap gold3;
    Bitmap dong1;
    Bitmap dong2;
    Bitmap dong3;
    Bitmap fight1;
    Bitmap fight2;
    Bitmap fight3;
    Bitmap alian;
    Bitmap btn_sheep;
    Bitmap btn_color_sheep;
    Bitmap btn_mission;
    Bitmap btn_collection;
    Bitmap popup;
    Bitmap sheepHead;
    Bitmap moneyImg;
    Bitmap boss;
    Bitmap tracter;
    Bitmap collector_white;
    Bitmap collector_gold;
    Bitmap collector_dong;
    Bitmap collector_fight;
    double alianWidth;
    double alianHeight;
    double wolfWidth;
    double wolfHeight;
    double sheepWidth;
    double sheepHeight;
    int displayHeight;
    int displayWidth;
    Vibrator vibrator;
    Paint moneyPaint, sheepPaint, talkPaint;
    mission missiontemp= new mission(MISSIONLONG,60,2);
    double missionprocess;
    int missioncount=1;
    int missioncheck = 0;
    int collector=0;
    int get_gold=0;
    int get_dong=0;
    int get_fight=0;
    MediaPlayer sheepSound;
    MediaPlayer cashSound;

    public myView(Context context){
        super(context);
        cashSound = MediaPlayer.create(getContext(),R.raw.cash);
        cashSound.setVolume((float)0.8,(float)0.8);
        sheepSound = MediaPlayer.create(getContext(),R.raw.sheep);
        sheepSound.setVolume(1,1);
        isrunning = true;
        moneyPaint = new Paint();
        sheepPaint = new Paint();
        talkPaint = new Paint();
        money=150;
        sheepnum=0;
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        time=0;
        Display display = ((WindowManager)context.getSystemService(context.WINDOW_SERVICE)).getDefaultDisplay();
        displayWidth = display.getWidth();
        displayHeight = display.getHeight();
        moneyImg = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.money),displayHeight/15,displayHeight/15,true);
        background = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.background),displayWidth,displayHeight,true);
        wolf1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.wolf1),displayHeight/14,displayHeight/12,true);
        wolf2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.wolf2),displayHeight/14,displayHeight/12,true);
        wolf3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.wolf3),displayHeight/14,displayHeight/12,true);
        deadbywolf = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.deadbywolf),displayHeight/9,displayHeight/9,true);
        sheep1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.sheep1),displayHeight/9,displayHeight/9,true);
        sheep2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.sheep2),displayHeight/9,displayHeight/9,true);
        sheep3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.sheep3),displayHeight/9,displayHeight/9,true);
        gold1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.gold1),displayHeight/9,displayHeight/9,true);
        gold2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.gold2),displayHeight/9,displayHeight/9,true);
        gold3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.gold3),displayHeight/9,displayHeight/9,true);
        dong1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.dong1),displayHeight/9,displayHeight/9,true);
        dong2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.dong2),displayHeight/9,displayHeight/9,true);
        dong3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.dong3),displayHeight/9,displayHeight/9,true);
        fight1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.fight1),displayHeight/9,displayHeight/9,true);
        fight2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.fight2),displayHeight/9,displayHeight/9,true);
        fight3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.fight3),displayHeight/9,displayHeight/9,true);
        collector_white =Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.sheep2),displayHeight/6,displayHeight/6,true);
        collector_gold = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.gold2),displayHeight/6,displayHeight/6,true);
        collector_dong = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.dong2),displayHeight/6,displayHeight/6,true);
        collector_fight = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.fight2),displayHeight/6,displayHeight/6,true);
        deadbyalian = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.deadbyalian),displayHeight/6,displayHeight/3,true);
        alian = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.alian),displayHeight/9,displayHeight/9,true);
        btn_sheep = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.btn_sheep),displayWidth/5,displayWidth/5,true);
        btn_color_sheep = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.btn_color_sheep),displayWidth/5,displayWidth/5,true);
        btn_mission = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.btn_mission),displayWidth/5,displayWidth/5,true);
        btn_collection = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.btn_collection),displayWidth/5,displayWidth/5,true);
        popup = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.popup),(displayWidth/25)*24,displayHeight*14/15-(displayWidth/25)*8,true);
        sheepHead = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.sheephead),displayHeight/15,displayHeight/15,true);
        boss = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.boss),displayHeight*3/10,displayHeight*3/10,true);
        tracter = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.tracter),displayHeight*1/10,displayHeight*1/10,true);

        wolfWidth=wolf1.getWidth();
        wolfHeight=wolf1.getHeight();
        sheepWidth=sheep1.getWidth();
        sheepHeight=sheep1.getHeight();
        alianHeight = alian.getHeight();
        alianWidth = alian.getWidth();
        sheeps.add(new sheep(displayWidth-sheepWidth, displayHeight - (displayWidth / 25) * 7- sheepHeight, time));

        sheepPaint.setTextSize(displayWidth/10);
        sheepPaint.setColor(Color.rgb(30,0,0));
        moneyPaint.setTextSize(displayWidth/12);
        moneyPaint.setColor(Color.rgb(20,20,0));
        talkPaint.setTextSize(displayWidth/20);
        talkPaint.setColor(Color.rgb(20,20,0));
        myThread thread1 = new myThread(this);
        thread1.start();
    }

    @Override
    public void onDraw(Canvas canvas) {
        time++;
        canvas.drawBitmap(background, 0, 0, null);
        canvas.drawBitmap(btn_sheep, displayWidth / 25, displayHeight - (displayWidth / 25) * 6, null);
        canvas.drawBitmap(btn_color_sheep, (displayWidth / 25) * 7, displayHeight - (displayWidth / 25) * 6, null);
        canvas.drawBitmap(btn_mission, (displayWidth / 25) * 13, displayHeight - (displayWidth / 25) * 6, null);
        canvas.drawBitmap(btn_collection, (displayWidth / 25) * 19, displayHeight - (displayWidth / 25) * 6, null);
        if (mMode == RUN) {
            for (int i = 0; i < wolfs.size(); i++) {
                switch (wolfs.get(i).hp) {
                    case 1:
                        canvas.drawBitmap(wolf3, (float) wolfs.get(i).xpos, (float) wolfs.get(i).ypos, null);
                        break;
                    case 2:
                        canvas.drawBitmap(wolf2, (float) wolfs.get(i).xpos, (float) wolfs.get(i).ypos, null);
                        break;
                    case 3:
                        canvas.drawBitmap(wolf1, (float) wolfs.get(i).xpos, (float) wolfs.get(i).ypos, null);
                        break;
                    case 4:
                        canvas.drawBitmap(deadbywolf, (float) wolfs.get(i).xpos, (float) wolfs.get(i).ypos, null);
                        break;
                }
            }
            for (int i = 0; i < alians.size(); i++) {
                switch (alians.get(i).hp) {
                    case 1:
                        canvas.drawBitmap(alian, (float) alians.get(i).xpos, (float) alians.get(i).ypos, null);
                        break;
                    case 4:
                        canvas.drawBitmap(deadbyalian, (float) alians.get(i).xpos, (float) alians.get(i).ypos, null);
                        break;
                }
            }
            for (int i = 0; i < sheeps.size(); i++) {
                if(sheeps.get(i).color==0) {
                    switch (sheeps.get(i).pur) {
                        case 1:
                            canvas.drawBitmap(sheep1, (float) sheeps.get(i).xpos, (float) sheeps.get(i).ypos, null);
                            break;
                        case 2:
                            canvas.drawBitmap(sheep2, (float) sheeps.get(i).xpos, (float) sheeps.get(i).ypos, null);
                            break;
                        case 3:
                            canvas.drawBitmap(sheep3, (float) sheeps.get(i).xpos, (float) sheeps.get(i).ypos, null);
                    }
                }else  if(sheeps.get(i).color==1) {
                    switch (sheeps.get(i).pur) {
                        case 1:
                            canvas.drawBitmap(gold1, (float) sheeps.get(i).xpos, (float) sheeps.get(i).ypos, null);
                            break;
                        case 2:
                            canvas.drawBitmap(gold2, (float) sheeps.get(i).xpos, (float) sheeps.get(i).ypos, null);
                            break;
                        case 3:
                            canvas.drawBitmap(gold3, (float) sheeps.get(i).xpos, (float) sheeps.get(i).ypos, null);
                    }
                }else  if(sheeps.get(i).color==2) {
                    switch (sheeps.get(i).pur) {
                        case 1:
                            canvas.drawBitmap(dong1, (float) sheeps.get(i).xpos, (float) sheeps.get(i).ypos, null);
                            break;
                        case 2:
                            canvas.drawBitmap(dong2, (float) sheeps.get(i).xpos, (float) sheeps.get(i).ypos, null);
                            break;
                        case 3:
                            canvas.drawBitmap(dong3, (float) sheeps.get(i).xpos, (float) sheeps.get(i).ypos, null);
                    }
                }else if(sheeps.get(i).color==3) {
                    switch (sheeps.get(i).pur) {
                        case 1:
                            canvas.drawBitmap(fight1, (float) sheeps.get(i).xpos, (float) sheeps.get(i).ypos, null);
                            break;
                        case 2:
                            canvas.drawBitmap(fight2, (float) sheeps.get(i).xpos, (float) sheeps.get(i).ypos, null);
                            break;
                        case 3:
                            canvas.drawBitmap(fight3, (float) sheeps.get(i).xpos, (float) sheeps.get(i).ypos, null);
                    }
                }
            }
        }
        if(collector==1&&mMode==PAUSE){
            canvas.drawBitmap(popup, (displayWidth/25),displayHeight*1/10, null);
            canvas.drawBitmap(collector_white, (displayWidth/6),displayHeight/5, null);
            if(get_gold==1){
                canvas.drawBitmap(collector_gold, (displayWidth*3/5),displayHeight/5, null);
            }if(get_dong==1){
                canvas.drawBitmap(collector_dong, (displayWidth/6),displayHeight*3/5, null);
            }if(get_fight==1){
                canvas.drawBitmap(collector_fight, (displayWidth*3/5),displayHeight*3/5, null);
            }
            collector=0;
        }
        Paint rect = new Paint();
        rect.setColor(Color.rgb((int)(255-255*missiontemp.due_time/MISSIONLONG),(int)(200*missiontemp.due_time/MISSIONLONG),0));
        canvas.drawRect((float)(displayWidth*0.9),(float)((displayHeight*0.8 - missiontemp.due_time*displayHeight*0.6/MISSIONLONG)),(float)(displayWidth*0.95),(float)(displayHeight*0.8),rect);
        canvas.drawBitmap(tracter, (float)((displayWidth*0.9-tracter.getWidth()/2)), (float)((displayHeight*0.8-tracter.getHeight()/2 - missiontemp.due_time*displayHeight*0.6/MISSIONLONG)), null);

        if(mMode==PAUSE && missioncheck==1){
            //미션확인
            canvas.drawBitmap(popup, (displayWidth/25),displayHeight*1/10, null);
            canvas.drawBitmap(boss, (displayWidth/8),displayHeight*1/8, null);
            canvas.drawText("한 달안에 "+missiontemp.getRe_money() + "만큼의 돈을 준비하고",displayWidth/15,displayHeight*1/2,talkPaint);
            canvas.drawText("적어도"+missiontemp.getRe_sheep()+"마리의 양을 키우게.",displayWidth/15,displayHeight*9/16,talkPaint);
            canvas.drawText("그렇게 하지 못하면 자네는 해고야 해고!!!!",displayWidth/15,displayHeight*10/16,talkPaint);
            canvas.drawText("남은시간:"+(int)(missiontemp.getDue_time()/(MISSIONLONG/30))+"일",displayWidth/15,displayHeight*11/16,talkPaint);
        }
        if(mMode==PAUSE && missioncheck == 2){
            //미션부여
            canvas.drawBitmap(popup, (displayWidth/25),displayHeight*1/10, null);
            canvas.drawBitmap(boss, (displayWidth/8),displayHeight*1/8, null);
            canvas.drawText("훌륭해! 돈은 잘 가져가겠어..",displayWidth/15,displayHeight*1/2,talkPaint);
            canvas.drawText("다음달까지 "+missiontemp.getRe_money() + "만큼의 돈을 준비하고",displayWidth/15,displayHeight*9/16,talkPaint);
            canvas.drawText("적어도"+missiontemp.getRe_sheep()+"마리의 양을 키우게.",displayWidth/15,displayHeight*10/16,talkPaint);
            canvas.drawText("그렇게 하지 못하면 자네는 해고야 해고!!!! ",displayWidth/15,displayHeight*11/16,talkPaint);
        }
        if(mMode==PAUSE && missioncheck == 3){
            //미션부여
            canvas.drawBitmap(popup, (displayWidth/25),displayHeight*1/10, null);
            canvas.drawBitmap(boss, (displayWidth/8),displayHeight*1/8, null);

            canvas.drawText("지금 장난이라도 하자는거야 뭐야!!!!",displayWidth/15,displayHeight*9/16,talkPaint);
            canvas.drawText("당장 나가. 월급 못줘!! 당장 나가아!!!!!,",displayWidth/15,displayHeight*10/16,talkPaint);
            canvas.drawText("자네는 해고야 해고!!!! ",displayWidth/15,displayHeight*11/16,talkPaint);
        }


        canvas.drawBitmap(sheepHead, (displayWidth*16/25),(displayWidth/25), null);
        canvas.drawText(""+sheeps.size(),(int)(displayWidth*16/25+sheepHead.getWidth()*1.3),(int)(displayWidth/25+sheepHead.getHeight()),sheepPaint);
        canvas.drawBitmap(moneyImg, (displayWidth*1/25),(displayWidth/25), null);
        canvas.drawText(""+money,(int)(displayWidth*1/25+moneyImg.getWidth()*1.2),(int)(displayWidth/25+moneyImg.getHeight()*0.9),moneyPaint);
        if(!isrunning) {
            canvas.drawText("Game Over",(int)displayWidth*11/40,(int)displayHeight/2,sheepPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
       // if(event.getAction()==MotionEvent.ACTION_DOWN) {
            int ax = (int) event.getX();
            int ay = (int) event.getY();
            boolean temp = false;
            if (mMode == PAUSE) {
                mMode = RUN;
                missioncheck = 0;
            } else {
                if ((ay >= displayHeight - (displayWidth / 25) * 6 && ay <= displayHeight - (displayWidth / 25))) {
                    if (displayWidth / 25 <= ax && ax <= (displayWidth / 25) * 6) {
                        if (!temp && sheeps.size() < 12&&money>=100){
                            sheeps.add(new sheep(displayWidth-sheepWidth, displayHeight - (displayWidth / 25) * 7- sheepHeight, time));
                            money-=100;
                            cashSound.start();
                        }
                    }
                    else if ((displayWidth / 25) * 7 <= ax && ax <= (displayWidth / 25) * 12) {
                        if (!temp && sheeps.size() < 12&&money>=300){
                            if((rand=Math.random())<0.3) {
                                color = 1;
                                get_gold=1;
                            }
                            else if(rand<0.6) {
                                color = 2;
                                get_dong=1;
                            }
                            else    {
                                color=3;
                                sheep.growSpeed++;
                                get_fight=1;
                            }
                            sheeps.add(new sheep(displayWidth-sheepWidth, displayHeight - (displayWidth / 25) * 7- sheepHeight, time, color));
                            money-=300;
                            cashSound.start();
                        }
                    }
                    else if ((displayWidth / 25) * 13 <= ax && ax <= (displayWidth / 25) * 18) {
                        mMode = PAUSE;
                        missioncheck = 1;
                    } else if ((displayWidth / 25) * 19 <= ax && ax <= (displayWidth / 25) * 24) {
                        mMode = PAUSE;
                        collector = 1;
                    }
                } else {
                    int size=wolfs.size();
                    for (int i = 0; i < size; i++) {
                        if (wolfs.get(i).xpos <= ax && ax <= wolfs.get(i).xpos + wolfWidth && wolfs.get(i).ypos <= ay && wolfs.get(i).ypos + wolfHeight >= ay) {
                            wolfs.get(i).attacked();
                            if (wolfs.get(i).hp < 1) {
                                wolfs.remove(i);
                                size--;
                                i--;
                            }
                            temp = true;
                        }
                    }
                    size=alians.size();
                    for (int i = 0; i < size; i++) {
                        if (alians.get(i).xpos <= ax && ax <= alians.get(i).xpos + alianWidth && alians.get(i).ypos <= ay && alians.get(i).ypos + wolfHeight >= ay) {
                                alians.remove(i);
                                size--;
                                i--;
                            }
                            temp = true;
                        }

                    size=sheeps.size();
                    for (int i = 0; i < size; i++) {
                        if (sheeps.get(i).xpos <= ax && ax <= sheeps.get(i).xpos + sheepWidth && sheeps.get(i).ypos <= ay && sheeps.get(i).ypos + sheepHeight >= ay) {
                            if (sheeps.get(i).pur == 1) {
                                size--;
                                sheepdead(i);
                                i--;
                                temp = true;
                                continue;
                            }
                            money+=Math.pow(sheeps.get(i).pur,2)*sheeps.get(i).value;
                            sheeps.get(i).attacked();
                            temp = true;
                        }
                    }

                }
            }
  //      }
        invalidate();
        return false;
    }
    public void sheepdead(int i){
        Log.i("sheep","dead");
        if(sheeps.get(i).color==3) sheep.growSpeed--;
        sheeps.remove(i);
        sheepSound.start();
        vibrator.vibrate(100);
    }

}


