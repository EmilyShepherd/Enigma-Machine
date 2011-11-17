import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;

/**
 * PlugboardUIHandler
 * 
 * Handles the UI of the plugboard
 * 
 * Unfinished >:(
 * 
 * @author Emily Shepherd
 *
 */
public class PlugboardUIHandler extends UIHandler implements ActionListener
{
    private JSpinner end1;
    private JSpinner end2;
    
    private JButton create;
    
    final private static SpinnerListModel CHARS = new SpinnerListModel
    (
        new String[]
        {
            "A ", "B ", "C ", "D ", "E ", "F ",
            "G ", "H ", "I ", "J ", "K ", "L ",
            "M ", "N ", "O ", "P ", "Q ", "R ",
            "S ", "T ", "U ", "V ", "W ", "X ",
            "Y ", "Z "
        }
    );
    
    public PlugboardUIHandler()
    {
        draw();
    }

    protected void draw()
    {
        JPanel plugs = new JPanel();
        plugs.setLayout(null);
        plugs.setBounds(790, 250, 400, 400);
        form.add(plugs);
        
        JPanel creator = new JPanel();
        creator.setLayout(null);
        creator.setBounds(0, 0, 400, 40);
        plugs.add(creator);
        
        end1 = new JSpinner(CHARS);
        end1.setBounds(0, 0, 35, 25);
        creator.add(end1);
        
        end2 = new JSpinner(CHARS);
        end2.setBounds(50, 0, 35, 25);
        creator.add(end2);
        
        create = new JButton("Add Plug");
        create.setBounds(100, 0, 100, 25);
        create.addActionListener(this);
        creator.add(create);
        
        creator.updateUI();
        plugs.updateUI();
    }

    @Override
    public void actionPerformed(ActionEvent arg0)
    {
        // TODO Auto-generated method stub
        
    }
}
