package websearchengine;

import java.applet.*;
import java.awt.event.*;
import java.awt.*;

public class welcome extends Applet implements ActionListener {
   TextArea input;
   TextArea output;
   //String Add, Subtract;
   //int i = 10, j = 20, sum = 0, Sub = 0;
   
   public void init() {
      input = new TextArea(10,20);
      input.setEditable(false);
      add(input,"center");
      Button b = new Button("Search WebPage");
      Button c = new Button("Search Synonym");
      b.addActionListener(this);
      c.addActionListener(this);
      add(b);
      add(c);
   }
   public void actionPerformed(ActionEvent e) {
      /*
	   sum = i + j;
      input.setText("");
      input.append("i = "+ i + "\t" + "j = " + j + "\n");
      Button source = (Button)e.getSource();
      
      if(source.getLabel() == "Add") {
         input.append("Sum : " + sum + "\n");
      }
      if(i >j) { 
         Sub = i - j;
      } else {
         Sub = j - i;
      }
      if(source.getLabel() == "Subtract") {
         input.append("Sub : " + Sub + "\n");
      }
   }
   */
   }
}


/*
import java.applet.*;

import java.awt.*;
import java.awt.event.*;


public class welcome extends Applet {

  private TextField input;
  private TextField output;
  
  public void init () {
   
     // Construct the TextFields
     this.input = new TextField(40);
     this.output = new TextField(40);
     this.output.setEditable(false);
     Button b = new Button("Capitalize");

     // add the button to the layout
     this.add(input);
     this.add(b);
     this.add(output);

     // specify that action events sent by the
     // button or the input TextField should be handled 
     // by the same CapitalizerAction object
     CapitalizerAction ca = new CapitalizerAction(input, output);
     b.addActionListener(ca);
     this.input.addActionListener(ca);
     // notice that ActionEvents produced by output are ignored.
   
   }

}


class CapitalizerAction implements ActionListener {

  private TextField in;
  private TextField out;

  public CapitalizerAction(TextField in, TextField out) {
    this.in = in;
    this.out = out;
  }

  public void actionPerformed(ActionEvent ae) {

    String s = in.getText();
    out.setText(s.toUpperCase());

  }

}
*/