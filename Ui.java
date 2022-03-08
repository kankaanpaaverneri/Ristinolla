import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ui extends JFrame implements ActionListener
{
    private GameManager gameManager;
    private JButton[][] buttons;
    public Ui(final GameManager source)
    {
        this.gameManager = source;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Ristinolla");
        this.setSize(500, 500);
        this.setLocationRelativeTo(this);
        this.setLayout(new GridLayout(3, 3, 10, 10));

        //Setting up Ui Graphical buttons
        this.setButtonProperties();

        //Adding values from gameManagers Grid object
        this.setButtonsText();
        
        this.setVisible(true);
    }

    private void setButtonProperties()
    {
        buttons = new JButton[Grid.getGridHeight()][Grid.getGridWidth()];

        for(int column = 0; column < Grid.getGridHeight(); column++)
        {
            for(int row = 0; row < Grid.getGridWidth(); row++)
            {
                buttons[column][row] = new JButton();
                buttons[column][row].setFocusable(false);
                buttons[column][row].setFont(new Font("MV Boli", Font.BOLD, 60));
                buttons[column][row].addActionListener(this);
                this.add(buttons[column][row]);
            }
        }
    }

    private void setButtonsText()
    {
        //Copies values to this objects buttons from gameManagers Grid object
        for(int column = 0; column < Grid.getGridHeight(); column++)
        {
            for(int row = 0; row < Grid.getGridWidth(); row++)
            {
                char mark = gameManager.getGrid().getGridValue(column, row);
                buttons[column][row].setText(Character.toString(mark));
            }
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        for(int column = 0; column < Grid.getGridHeight(); column++)
        {
            for(int row = 0; row < Grid.getGridWidth(); row++)
            {
                if(e.getSource() == buttons[column][row] && buttons[column][row].getText().isBlank())
                {
                    //Update human objects currentColumn and currentRow values
                    gameManager.getHuman().setCurrentColumn(column);
                    gameManager.getHuman().setCurrentRow(row);
                    
                    //Pass Human object to the eventHandler
                    gameManager.eventHandler(gameManager.getHuman());
                    this.setButtonsText(); //Update the Ui

                    if(gameManager.getHuman().getIsWinner() == true)
                    {
                        String[] response = {"Kyllä"};
                        JOptionPane.showOptionDialog(null,
                                                    "Voittaja on IHMINEN",
                                                    "Voitto",
                                                    JOptionPane.YES_OPTION,
                                                    JOptionPane.INFORMATION_MESSAGE,
                                                    null, response, 0);
                        System.exit(0);
                    }
                    if(gameManager.getGrid().isFull())
                    {
                        String[] response = {"Kyllä"};
                        JOptionPane.showOptionDialog(null,
                                                    "Tasapeli",
                                                    "TASAPELI",
                                                    JOptionPane.YES_OPTION,
                                                    JOptionPane.INFORMATION_MESSAGE,
                                                    null, response, 0);
                        System.exit(0);
                    }
                }
            }
        }

        //Pass Computer object to the eventHandler
        gameManager.eventHandler(gameManager.getComputer());
        this.setButtonsText(); //Update the Ui

        if(gameManager.getComputer().getIsWinner() == true)
        {
            String[] response = {"Kyllä"};
            JOptionPane.showOptionDialog(null,
                                        "Voittaja on TIETOKONE",
                                        "Sinut on nöyryytetty",
                                        JOptionPane.YES_OPTION,
                                        JOptionPane.INFORMATION_MESSAGE,
                                        null, response, 0);
            System.exit(0);
        }
    }
}