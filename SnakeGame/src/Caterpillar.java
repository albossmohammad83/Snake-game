import java.awt.*;
import java.util.*;
class Caterpillar {
    private Color color; // Tricky! Notice how position is accessed
    public Point position; // by different methods in this class...
    private char direction = 'E';
    //private Queue body = new << queue type/interface >>;
    Queue <Point> body = new LinkedList<> ();
    //private Queue commands = new << queue type/interface >>;
    Queue <Character> commands = new LinkedList<> ();
    private int caterpillarLength = 10;
    public Caterpillar (Color c, Point sp) {
        color = c;
        for (int i = 0; i < 10; i++) { // each caterpillar is
            position = new Point(sp.x + i, sp.y);
            body.add (position);
        }
    }

    public void setDirection (char d) {
        commands.add(d);
    }

    public void move (CaterpillarGame game) {
        // first see if we should change direction
        if (commands.size( ) > 0) {
            Character c = (Character) commands.peek( ); // just peek
            commands.remove( );
            direction = c; // Character wrapper to char
            if (direction == 'Z') return;
        }

        // then find new position
        Point np = newPosition( );
        if (game.canMove(np)) {
            // erase one segment, add another
            body.remove( );
            body.add(np);
            position = np;
        }

    }

    private Point newPosition ( ) {
        int x = position.x;
        int y = position.y;

        if (direction == 'E') x++;
        else if (direction == 'W') x--;
        else if (direction == 'N') y--;
        else if (direction == 'S') y++;
        return new Point(x, y);
    }

    public boolean inPosition (Point np) {
        //Enumeration e = body.elements();
        Iterator<Point> e = body.iterator();
        while (e.hasNext()) {
            Point location = (Point) e.next();
            if (np.equals(location)) return true;
        }
        return false;
    }

    public void paint (Graphics g) {
        g.setColor(color);
        //Enumeration e = body.elements();
        Iterator<Point> e = body.iterator();
        // iterator stuff
        while (e.hasNext( )) {
            Point p = (Point) e.next();
            g.fillOval(5 + CaterpillarGame.SegmentSize * p.x,
                    15 + CaterpillarGame.SegmentSize * p.y,
                    CaterpillarGame.SegmentSize,
                    CaterpillarGame.SegmentSize);
        }
    }
}

