package irsad.co.id.shootgamefirst;

import android.graphics.Rect;
import android.view.MotionEvent;

import java.util.ArrayList;

/**
 * Created by LostTime-PC on 10/11/2017.
 */

public class HUD {
    Rect left;
    Rect right;
    Rect shoot;

    public ArrayList<Rect> currentButtonList = new ArrayList<>();
    HUD(int screenWidth,int screenHeight){
        int buttonWidth = screenWidth/8;
        int buttonHeight = screenHeight/7;
        int buttonPadding = screenWidth/80;

        left = new Rect(buttonPadding,
                screenHeight-buttonHeight-buttonPadding,
                buttonWidth,
                screenHeight-buttonPadding);
        right = new Rect(buttonWidth+buttonPadding,
                screenHeight-buttonHeight-buttonPadding,
                buttonWidth+buttonPadding+buttonWidth,
                screenHeight-buttonPadding);
        shoot = new Rect(screenWidth-buttonWidth-buttonPadding,
                screenHeight-buttonHeight-buttonPadding,
                screenWidth-buttonPadding,
                screenHeight-buttonPadding);
        currentButtonList.add(left);
        currentButtonList.add(right);
        currentButtonList.add(shoot);
    }
    public void handleInput(MotionEvent motionEvent){
        int x = (int)motionEvent.getX(0);
        int y = (int)motionEvent.getY(0);
        switch (motionEvent.getAction() && MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN:
                if(left.contains(x,y)){
                    
                }
        }
    }
}
