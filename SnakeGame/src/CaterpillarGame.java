/*
*Ahmed Hazein
*Mounjed Mousallam
*CIS 255
*OCT/18/2021
*Its a program that run a game which the caterpillar game (snake game) this has 2 players each player should
*take the points or number before the other player to get more scores
* */
import java.awt.*;
import java.awt.event.*;
import java.awt.event.WindowAdapter;
import java.util.Random;
import java.util.Enumeration;

public class CaterpillarGame extends Frame {
    // setup board dimensions
    final static int BoardWidth = 60;
    final static int BoardHeight = 40;
    final static int SegmentSize = 10;

    // Random Game Points variables
    private Point numberSquare = new Point(10, 10);
    private int numberSquarePoints = 0;

    // Player Scores
    private int playerOnePoints = 0;
    private int playerTwoPoints = 0;


    // setup the two Caterpillars in their starting positions
    private Caterpillar playerOne = new Caterpillar (Color.blue, new Point(20, 10));
    private Caterpillar playerTwo = new Caterpillar (Color.red, new Point(20, 30));
    // constructor
    public CaterpillarGame ( ) {
        setSize ((BoardWidth + 1) * SegmentSize, BoardHeight * SegmentSize + 30);
        setTitle ("Caterpillar Game");
        addKeyListener (new KeyReader( ));
        addWindowListener(new CloseQuit());

    }

    public static void main (String [ ] args) {
        CaterpillarGame world = new CaterpillarGame( );
        world.setVisible(true);
        world.newNumberSquare();
        world.run( );


    }

private void newNumberSquare (){
    Random randomGenerator = new Random();
    numberSquarePoints = randomGenerator.nextInt(9) + 1;
    numberSquare = new Point((int)(Math.random() * (BoardWidth - 2) + 2), (int)(Math.random() * (BoardHeight - 2) + 2));

}

    public void paint (Graphics g) {
        setBackground(Color.YELLOW);
        playerOne.paint(g);
        playerTwo.paint(g);
        g.setColor(Color.BLACK);
        g.drawString("Player One Score: " + playerOnePoints,  (BoardWidth + 5), (BoardHeight + 10));
        g.drawString("Player Two Score: " + playerTwoPoints,  (450), (BoardHeight + 10));
        g.drawString("Bonus Score : " + numberSquarePoints,  (250), (BoardHeight + 10));
        g.setColor(Color.GREEN);
        g.fillOval( CaterpillarGame.SegmentSize * numberSquare.x ,CaterpillarGame.SegmentSize * numberSquare.y ,CaterpillarGame.SegmentSize ,
                CaterpillarGame.SegmentSize);


    }

    public void movePieces ( ) { // Very Important Questions
        playerOne.move(this); // Please discuss on Canvas Discussion Forum!
        playerTwo.move(this); // What is ¡®this¡¯?
        if (playerOne.inPosition(numberSquare)) {
            playerOnePoints += numberSquarePoints;
            newNumberSquare();
        } else if (playerTwo.inPosition(numberSquare)) {
            playerTwoPoints += numberSquarePoints;
            newNumberSquare();
        }
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

    private class CloseQuit extends WindowAdapter {
        public  void windowClosing (WindowEvent e) {
            System.exit(0);
        }
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

