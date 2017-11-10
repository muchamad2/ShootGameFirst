package irsad.co.id.shootgamefirst;

/**
 * Created by LostTime-PC on 10/11/2017.
 */
import  android.graphics.Bitmap;

public abstract class GameObject {
    protected Bitmap image;
    protected final int rowCount;
    protected final int colCount;

    protected final int WIDTH;
    protected final int HEIGHT;

    protected final int width;
    protected final int height;

    protected int x;
    protected int y;

    public GameObject(Bitmap image,int rowCount,int colCcount,int x,int y){
        this.image = image;
        this.rowCount = rowCount;
        this.colCount = colCcount;
        this.x = x;
        this.y = y;

        this.WIDTH = image.getWidth();
        this.HEIGHT = image.getHeight();

        this.width = this.WIDTH / colCcount;
        this.height = this.WIDTH /  rowCount;
    }

    protected Bitmap createSubImangeAt(int row,int col){
        Bitmap subimage = Bitmap.createBitmap(image,col*width,row*width,width,height);
        return subimage;
    }

    public int getX(){return this.x;}
    public int getY(){return this.y;}

    public int getWidth(){return this.width;}
    public int getHeight(){return this.height;}
}
