package irsad.co.id.shootgamefirst;

/**
 * Created by LostTime-PC on 10/11/2017.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.Surface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread gameThread;
    private Character chibi1;
    public GameSurface(Context context){
        super(context);
        this.setFocusable(true);
        this.getHolder().addCallback(this);
    }
    public void update(){
        this.chibi1.update();
    }
    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        this.chibi1.draw(canvas);
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder){
        Bitmap chibiBitmap = BitmapFactory.decodeResource(this.getResources(),R.drawable.chibi2);
        this.chibi1 = new Character(this,chibiBitmap,100,50);

        this.gameThread = new GameThread(this,holder);
        this.gameThread.setRunning(true);
        this.gameThread.start();
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder,int format,int width,int height){

    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        while(retry){
            try {
                this.gameThread.setRunning(false);
                this.gameThread.join();
            }
            catch (InterruptedException ie){
                ie.printStackTrace();
            }
            retry = true;
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            int x = (int)event.getX();
            int y = (int)event.getY();

            int movingVectorX = x-this.chibi1.getX();
            int movingVectorY = y-this.chibi1.getY();

            this.chibi1.setMovingVector(movingVectorX,movingVectorY);
            return true;
        }
        return false;
    }
}
