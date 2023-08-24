import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PingPongGame extends JPanel implements ActionListener {

    private int ballX = 150;
    private int ballY = 150;
    private int ballXDirection = 1;
    private int ballYDirection = 1;

    private int paddle1Y = 250;
    private int paddle2Y = 250;

    private Timer timer;

    public PingPongGame() {
        timer = new Timer(5, this);
        timer.start();
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    paddle2Y -= 20;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    paddle2Y += 20;
                }
            }
        });
        setFocusable(true);
    }


    public void actionPerformed(ActionEvent e) {
        ballX += ballXDirection;
        ballY += ballYDirection;

        // Check collision with walls
        if (ballX <= 0 || ballX >= 340) {
            ballXDirection = -ballXDirection;
        }
        if (ballY <= 0 || ballY >= 340) {
            ballYDirection = -ballYDirection;
        }

        // Check collision with paddles
        if (ballX <= 50 && ballY > paddle1Y && ballY < paddle1Y + 80) {
            ballXDirection = -ballXDirection;
        }
        if (ballX >= 300 && ballY > paddle2Y && ballY < paddle2Y + 80) {
            ballXDirection = -ballXDirection;
        }

        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 400, 400);
        g.setColor(Color.WHITE);
        g.fillRect(ballX, ballY, 15, 15);
        g.fillRect(50, paddle1Y, 10, 80);
        g.fillRect(340, paddle2Y, 10, 80);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ping Pong Game");
        PingPongGame pingPongGame = new PingPongGame();
        frame.add(pingPongGame);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
