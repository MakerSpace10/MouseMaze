import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.*; 

public class MyGame extends Game  {
    public static final String TITLE = "Mouse Maze";
    public static final int SCREEN_WIDTH = 1000;
    public static final int SCREEN_HEIGHT = 860;
    

    // declare variables here
    //The x and y will directly corilate to the rows and cols of the maze
    public int mouseX;
    public int mouseY;
    public int playerrow = 1;
    public int playercol = 0;
    public int[][] maze = new int[8][8];
    //Board origin
    public int ogx = 175;
    public int ogy = 10;


    public MyGame() {
        // initialize variables here
        //Read from the file to create the maze format: 1 is free space, 0 is blocked space.
        //Doesn't work without try{}
        try {
            File file = new File("level1.txt");   
            BufferedReader br = new BufferedReader(new FileReader(file)); 
            String st;
            int r = 0; 
            //Take each line, make sure it's not null
            while ((st = br.readLine()) != null){
                String[] words = st.split("");
                for(int c = 0; c < st.length(); c++){
                    maze[r][c] = Integer.parseInt(words[c]); 
                }
                r++;
            }
        //To avoid issues:
            br.close(); 
        }catch(IOException e) {
            System.out.println("Oops, you can't open this file!");
        }
        //The ultimate goal is to get to maze[maze.length][maze[0].length]!
            
    }
    
    public void update() {
        // updating logic
    }
    
    public void draw(Graphics pen) {
        
        //The board itself: Try not to tamper.
        //Square's variables:
        int sqx = ogx;
        int sqy = ogy;
        for(int r = 0; r < 8; r++){
            for(int c = 0; c < 8; c++){
                //Draw the squares
                if(maze[r][c] == 0){
                    pen.setColor(Color.BLACK);   
                    pen.drawRect(sqx, sqy, 100, 100);
                    pen.fillRect(sqx, sqy, 100, 100);
                }else if(maze[r][c] == 1){
                    pen.setColor(Color.RED);   
                    pen.drawRect(sqx, sqy, 100, 100);
                    pen.fillRect(sqx, sqy, 100, 100);
                }
                //Alter the x value every time to incrament:
                sqx += 100;
            }
            //X needs to reset every time
            sqx = ogx;
            //Y need to increment but never go back
            sqy += 100;
        }
        //The rest of drawings:
        //Will need some counters to find the nearet square origin and replace it
        int playerX = ogx;
        int playerY = ogy;
        //Setting x value
        if(mouseX >= 175 && mouseX < 275){
            playerX = 175;
        }else if(mouseX >= 275 && mouseX < 375){
            playerX = 275;
        }else if(mouseX >= 375 && mouseX < 475){
            playerX = 375;
        }else if(mouseX >= 475 && mouseX < 575){
            playerX = 475;
        }else if(mouseX >= 575 && mouseX < 675){
            playerX = 575;
        }else if(mouseX >= 675 && mouseX < 775){
            playerX = 675;
        }else if(mouseX >= 775 && mouseX < 875){
            playerX = 775;
        }else if(mouseX >= 875 && mouseX < 975){
            playerX = 875;
        }
        //Setting y value:
        if(mouseY >= 10 && mouseY < 110){
            playerY = 10;
        }else if(mouseY >= 110 && mouseY < 210){
            playerY = 110;
        }else if(mouseY >= 210 && mouseY < 310){
            playerY = 210;
        }else if(mouseY >= 310 && mouseY < 410){
            playerY = 310;
        }else if(mouseY >= 410 && mouseY < 510){
            playerY = 410;
        }else if(mouseY >= 510 && mouseY < 610){
            playerY = 510;
        }else if(mouseY >= 610 && mouseY < 710){
            playerY = 610;
        }else if(mouseY >= 710 && mouseY < 810){
            playerY = 710;
        }
 
        //Blue (Movable character)
        pen.setColor(Color.BLUE);
        pen.drawRect(playerX, playerY, 100, 100);
        pen.fillRect(playerX, playerY, 100, 100);
    }
        
    @Override
    public void keyTyped(KeyEvent ke) {}

    @Override
    public void keyPressed(KeyEvent ke) {
        //https://www.educative.io/answers/how-to-listen-to-and-take-action-on-keyboard-strokes-in-java
        //Remember to add && statments to avoid going out of bounds
        System.out.println(ke.getKeyCode() + "");
        if (ke.getKeyCode() == KeyEvent.VK_UP && mouseY - 100 >= ogy && maze[playerrow - 1][playercol] != 0) {
            mouseY -= 100;
            playerrow--;
        }else if (ke.getKeyCode() == KeyEvent.VK_DOWN && mouseY + 100 < ogy + 800) {
            mouseY += 100;
        }else if (ke.getKeyCode() == KeyEvent.VK_LEFT && mouseX - 100 >= ogx) {
            mouseX -= 100;
        }else if (ke.getKeyCode() == KeyEvent.VK_RIGHT && mouseX + 100 < ogx + 800) {
            mouseX += 100;
        }

    }

    @Override
    public void keyReleased(KeyEvent ke) {}

    @Override
    public void mouseClicked(MouseEvent me) {
        mouseX = me.getX()-8;
        mouseY = me.getY()-32;
    }

    @Override
    public void mousePressed(MouseEvent me) {}
    
    @Override
    public void mouseReleased(MouseEvent me) {}

    @Override
    public void mouseEntered(MouseEvent me) {}

    @Override
    public void mouseExited(MouseEvent me) {}
        
        
    //Launches the Game
    public static void main(String[] args) { new MyGame().start(TITLE, SCREEN_WIDTH,SCREEN_HEIGHT); }
}