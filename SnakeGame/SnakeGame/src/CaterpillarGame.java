import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;

public class CaterpillarGame extends Frame {
    // setup board dimensions
    final static int BoardWidth = 60;
    final static int BoardHeight = 40;
    final static int SegmentSize = 10;

    // setup the two Caterpillars in their starting positions
    private Caterpillar playerOne = new Caterpillar (Color.blue, new Point(20, 10));
    private Caterpillar playerTwo = new Caterpillar (Color.red, new Point(20, 30));
    // constructor
    public CaterpillarGame ( ) {
        setSize ((BoardWidth+1)*SegmentSize, BoardHeight*SegmentSize + 30);
        setTitle ("Caterpillar Game");
        addKeyListener (new KeyReader( ));
    }

    public static void main (String [ ] args) {
        CaterpillarGame world = new CaterpillarGame( );
        world.setVisible(true);
        world.run( );
    }
//    public void newNumberSquare(){
//        Random().nextInt(max - min + 1) + min;
//
    //}
    public void paint (Graphics g) {
        playerOne.paint(g);
        playerTwo.paint(g);
        Point p = new Point(20, 10);
            g.fillOval(5 + CaterpillarGame.SegmentSize * p.x,
                    15 + CaterpillarGame.SegmentSize * p.y,
                    CaterpillarGame.SegmentSize,
                    CaterpillarGame.SegmentSize);

    }

    public void movePieces ( ) { // Very Important Questions
        playerOne.move(this); // Please discuss on Canvas Discussion Forum!
        playerTwo.move(this); // What is ¡®this¡¯?
    } // Why do Caterpillars need ¡®this¡¯?

    public void run ( ) {
        // now start the game
        while (true) {
            movePieces( );
            repaint( );
            try {
                Thread.sleep(100); // create animation illusion
            } catch (Exception e) { } // must be in try-catch
        }
    }

    public boolean canMove (Point np) {
        // get x, y coordinate
        int x = np.x;
        int y = np.y;

        // test playing board boundaries
        if ((x <= 0) || (y <= 0)) return false;
        if ((x >= BoardWidth) || (y >= BoardHeight)) return false;

        // test caterpillars: can¡¯t move through self or other Caterpillar
        if (playerOne.inPosition(np)) return false;
        if (playerTwo.inPosition(np)) return false;

        // ok, safe square
        return true;
    }

    private class KeyReader extends KeyAdapter {
        public void keyPressed (KeyEvent e) {
            char c = e.getKeyChar( );
            switch (c) {

                // player One keys
                case 'q': playerOne.setDirection('Z'); break;
                case 'a': playerOne.setDirection('W'); break;
                case 'd': playerOne.setDirection('E'); break;
                case 'w': playerOne.setDirection('N'); break;
                case 's': playerOne.setDirection('S'); break;

                // player Two keys
                case 'p': playerTwo.setDirection('Z'); break;
                case 'j': playerTwo.setDirection('W'); break;
                case 'l': playerTwo.setDirection('E'); break;
                case 'i': playerTwo.setDirection('N'); break;
                case 'k': playerTwo.setDirection('S'); break;

                // ignore all other keys
            } // end switch
        } // end keyPressed
    } // end KeyReader private inner (nested) class
} // public class CaterpillarGame

