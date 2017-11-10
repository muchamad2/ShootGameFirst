package irsad.co.id.shootgamefirst;

/**
 * Created by LostTime-PC on 10/11/2017.
 */
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.View;

public class GameThread extends Thread{
    private boolean running;
    private GameSurface gameSurface;
    private SurfaceHolder surfaceHolder;

    public GameThread(GameSurface gameSurface,SurfaceHolder surfaceHolder){
        this.gameSurface = gameSurface;
        this.surfaceHolder = surfaceHolder;
    }
    @Override
    public void run(){
        long startTime = System.nanoTime();
        while (running) {
            Canvas canvas = null;
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (canvas) {
                    this.gameSurface.update();
                    this.gameSurface.draw(canvas);
                }
            } catch (InterruptedException ie) {

            } finally {
                if (canvas != null) {
                    this.surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            long now = System.nanoTime();

            long waitTime = (now - startTime) / 1000000;
            if (waitTime < 0) {
                waitTime = 10;
            }
            System.out.print("Wait time = " + waitTime);
            try {
                this.sleep(waitTime);
            } catch (InterruptedException e) {

            }
            startTime = System.nanoTime();
            System.out.print(".");
        }
    }
    public void setRunning(boolean running){
        this.running = running;
    }
}
