package irsad.co.id.shootgamefirst;

/**
 * Created by LostTime-PC on 10/11/2017.
 */
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Character extends GameObject{
    private static final int ROW_TOP_TO_BUTTOM = 0;
    private static final int ROW_RIGHT_TO_LEFT = 1;
    private static final int ROW_LEFT_TO_RIGHT = 2;
    private static final int ROW_BUTTOM_TO_TOP = 3;

    private int rowUsing = ROW_LEFT_TO_RIGHT;

    private int colUsing;

    private Bitmap[] leftToRights;
    private Bitmap[] rightToLefts;
    private Bitmap[] topToButtoms;
    private Bitmap[] buttomToTops;

    public static final float VELOCITY = 0.1f;

    private int movingVectorX = 10;
    private int movingVectorY = 0;

    private long lastDrawNanoTime = -1;

    private GameSurface gameSurface;

    public Character(GameSurface gameSurface,Bitmap image,int x,int y){
        super(image,4,3,x,y);

        this.gameSurface = gameSurface;

        this.topToButtoms = new Bitmap[colCount];
        this.leftToRights = new Bitmap[colCount];
        this.rightToLefts = new Bitmap[colCount];
        this.buttomToTops = new Bitmap[colCount];

        for(int col = 0;col < this.colCount; col++){
            this.topToButtoms[col] = this.createSubImangeAt(ROW_TOP_TO_BUTTOM,col);
            this.leftToRights[col] = this.createSubImangeAt(ROW_LEFT_TO_RIGHT,col);
            this.rightToLefts[col] = this.createSubImangeAt(ROW_RIGHT_TO_LEFT,col);
            this.buttomToTops[col] = this.createSubImangeAt(ROW_BUTTOM_TO_TOP,col);

        }
    }
    public Bitmap[] getMoveBitmap(){
        switch (rowUsing){
            case ROW_BUTTOM_TO_TOP:
                return this.buttomToTops;
            case ROW_LEFT_TO_RIGHT:
                return this.leftToRights;
            case ROW_RIGHT_TO_LEFT:
                return this.rightToLefts;
            case ROW_TOP_TO_BUTTOM:
                return this.topToButtoms;
            default:
                return null;
        }
    }

    public Bitmap getCurrentMoveBitmap(){
        Bitmap[] bitmaps = this.getMoveBitmap();
        return bitmaps[this.colUsing];
    }
    public void update(){
        this.colUsing++;
        if(colUsing >= colCount){
            this.colUsing = 0;
        }
        long now = System.nanoTime();

        if(lastDrawNanoTime == -1){
            lastDrawNanoTime = now;
        }

        int deltaTime = (int)((now-lastDrawNanoTime)/1000000);

        float distance = VELOCITY * deltaTime;
        double movingVectorLength = Math.sqrt(movingVectorX*movingVectorX+movingVectorY*movingVectorY);

        this.x = x+(int)(distance*movingVectorX/movingVectorLength);
        this.y = y+(int)(distance*movingVectorY/movingVectorLength);

        if(this.x < 0){
            this.x = 0;
            this.movingVectorX = -this.movingVectorX;
        }else if(this.x > this.gameSurface.getWidth() - width){
            this.x = this.gameSurface.getWidth()-width;
            this.movingVectorX = -this.movingVectorX;
        }

        if(this.y < 0){
            this.y = 0;
            this.movingVectorY = -this.movingVectorY;
        }else if(this.x > this.gameSurface.getHeight() - height){
            this.x = this.gameSurface.getHeight()-height;
            this.movingVectorY = -this.movingVectorY;
        }

        if( movingVectorX > 0 )  {
            if(movingVectorY > 0 && Math.abs(movingVectorX) < Math.abs(movingVectorY)) {
                this.rowUsing = ROW_TOP_TO_BUTTOM;
            }else if(movingVectorY < 0 && Math.abs(movingVectorX) < Math.abs(movingVectorY)) {
                this.rowUsing = ROW_BUTTOM_TO_TOP;
            }else  {
                this.rowUsing = ROW_LEFT_TO_RIGHT;
            }
        } else {
            if(movingVectorY > 0 && Math.abs(movingVectorX) < Math.abs(movingVectorY)) {
                this.rowUsing = ROW_TOP_TO_BUTTOM;
            }else if(movingVectorY < 0 && Math.abs(movingVectorX) < Math.abs(movingVectorY)) {
                this.rowUsing = ROW_BUTTOM_TO_TOP;
            }else  {
                this.rowUsing = ROW_RIGHT_TO_LEFT;
            }
        }
    }
    public void draw(Canvas canvas){
        Bitmap bitmap = this.getCurrentMoveBitmap();
        canvas.drawBitmap(bitmap,x,y,null);
        this.lastDrawNanoTime = System.nanoTime();
    }
    public void setMovingVector(int movingVectorX,int movingVectorY){
        this.movingVectorX = movingVectorX;
        this.movingVectorY = movingVectorY;
    }
}
