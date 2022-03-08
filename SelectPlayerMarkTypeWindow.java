import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SelectPlayerMarkTypeWindow extends JFrame implements ActionListener
{
    private JButton xMarkButton, oMarkButton;
    public SelectPlayerMarkTypeWindow()
    {
        this.setTitle("Valitse merkkityyppi");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(350, 100);
        this.setLocationRelativeTo(this);
        this.setLayout(new GridLayout(1, 2, 10, 10));

        //Adding buttons to JFrame
        this.setButtonProperties();
        this.add(xMarkButton);
        this.add(oMarkButton);

        this.setVisible(true);
    }

    private void setButtonProperties()
    {
        xMarkButton = new JButton("X");
        oMarkButton = new JButton("O");
        xMarkButton.setFont(new Font("MV Boli", Font.BOLD, 30));
        oMarkButton.setFont(new Font("MV Boli", Font.BOLD, 30));
        xMarkButton.setFocusable(false);
        oMarkButton.setFocusable(false);
        xMarkButton.addActionListener(this);
        oMarkButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        //Generating a local gameManager object
        //It´s value will be passed to the Ui object and copied to it´s member gameManager object
        GameManager gameManager = new GameManager();
        if(e.getSource() == xMarkButton)
        {
            gameManager.getHuman().setMarkType('X');
            gameManager.getComputer().setMarkType('O');
            this.dispose();
            new Ui(gameManager);
        }
        else if(e.getSource() == oMarkButton)
        {
            gameManager.getHuman().setMarkType('O');
            gameManager.getComputer().setMarkType('X');
            this.dispose();
            new Ui(gameManager);
        }
    }
}