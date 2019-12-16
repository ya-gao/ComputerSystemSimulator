package Instruction;

import Memory.Memory;
import Registers.Registers;
import Utilities.Decode;
import cs.simulator.simulator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * @author Ya
 */

public class IO_Operation {
    Memory mem;
    Registers R;
    simulator GUI;
    String address;
    int intGPR;
    int DEVID;
    String str;
    String s;
    String op;
    public static String convertWordToInt(String word){
        char[] Word=word.toCharArray();
        int[] array=new int[word.length()];
        StringBuffer str=new StringBuffer();
        for(int i=0;i<Word.length;i++){
            array[i]=(int)Word[i];
            str=str.append(array[i]);
        }
        String str1=str.toString();
        return str1;
    }

    public void input() throws Exception {
        //get data from the window ,and assume the value is str
        
        if (DEVID == 1 || DEVID == 0) {
            R.setGPR(intGPR, str);
            
            switch (intGPR) {
                case 0: {        
                     if((Character.isLetter(str.charAt(0))==true))
            {
                 cs.simulator.simulator.r0.setText(convertWordToInt(str));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + intGPR + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + convertWordToInt(str));
            }
                     else{
                    cs.simulator.simulator.r0.setText(R.getGPR(intGPR));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + intGPR + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(intGPR));
                     }break;
                }
                case 1: {
                             if((Character.isLetter(str.charAt(0))==true))
            {
                 cs.simulator.simulator.r1.setText(convertWordToInt(str));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + intGPR + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + convertWordToInt(str));
            }
                     else{
                    cs.simulator.simulator.r1.setText(R.getGPR(intGPR));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + intGPR + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(intGPR));
                     }break;
                
                }
                case 2: {
                            if((Character.isLetter(str.charAt(0))==true))
            {
                 cs.simulator.simulator.r2.setText(convertWordToInt(str));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + intGPR + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + convertWordToInt(str));
            }
                     else{
                    cs.simulator.simulator.r2.setText(R.getGPR(intGPR));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + intGPR + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(intGPR));
                     }break;
                
                }
                case 3: {
                             if((Character.isLetter(str.charAt(0))==true))
            {
                 cs.simulator.simulator.r3.setText(convertWordToInt(str));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + intGPR + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + convertWordToInt(str));
            }
                     else{
                    cs.simulator.simulator.r3.setText(R.getGPR(intGPR));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + intGPR + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(intGPR));
                     }break;
                
                }
                default:
                    break;
            }
            R.incrementPC();
        } else if (DEVID == 2) {
            File file = new File("./paragraph.txt");
            BufferedReader reader = null;
            // store the whole paragraph as one single string
            String paraStr = new String();
            try {
                reader = new BufferedReader(new FileReader(file));
                String tempString;

                if ((tempString = reader.readLine()) != null) {
                    paraStr = tempString;
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e1) {
                    }
                }
            }

            
            System.out.println(paraStr);
            R.setGPR(intGPR, paraStr);
            String[] arrOfSent = paraStr.split("[.?!]");
            int i = 0;
            while (i < arrOfSent.length) {
                arrOfSent[i] = arrOfSent[i].trim();
                System.out.println(arrOfSent[i]);
                i++;
            }

            System.out.println(arrOfSent.length);
            if (arrOfSent.length != 6) {
                System.out.println("ERROR: WRONG SENTENCE NUMBER!!!");
            }

            // store words from mem[100], store start of sentence from mem[500]
            String wordAddress = "100";
            String sentAddress = "500";

            for (String sent : arrOfSent) {
                // remove special characters in sentence

                sent = sent.replaceAll(",", "");
                sent = sent.replaceAll("\\(", "");
                sent = sent.replaceAll("\\)", "");
                System.out.println(sent);
                // now words in sent are only separated by whitespace

                // split sent by whitespace, now arrOfWord.length = # of words in this sentence
                String[] arrOfWord = sent.split(" ");
                System.out.println(arrOfWord.length);
                // Store start address of this sentence
                mem.setMemValue(sentAddress, wordAddress);
                System.out.println("sentAddress is:" + sentAddress);
                System.out.println("Memory stored" + mem.getMemValue(sentAddress));
                sentAddress = String.valueOf(Integer.parseInt(sentAddress) + 1);
                // System.out.println("updated sentAddress is:"+sentAddress);


                // store each word in mem
                for (String word : arrOfWord) {
                    System.out.println(" wordAddress:" + wordAddress);
                    System.out.println("word:" + word);
                    // System.out.println("wordAddress:"+wordAddress);
                    mem.setMemValue(wordAddress, word);
                    System.out.println("Memory stored:" + mem.getMemValue(wordAddress));
                    wordAddress = String.valueOf(Integer.parseInt(wordAddress) + 1);

                    // System.out.println(mem.getMemValue(wordAddress));
                }
            } // Done. Now mem[100 ...] store word by word, mem[500...505] store start address of each sentence


            System.out.println("hi");
            s = op + Integer.toBinaryString(intGPR) + Integer.toBinaryString(DEVID);
            //  System.out.println(Integer.toBinaryString(RY));
            System.out.println(s);
            // System.out.println("hi");
            int dec = Integer.parseInt(s, 2);
            System.out.println("dec:" + dec);
            //reg.setMAR(reg.getPC());                        //setting MAR value
            System.out.println(R.getPC());
            R.incrementPC();
            cs.simulator.simulator.pc.setText(R.getPC());
            R.setMAR(R.getPC());

            cs.simulator.simulator.mar.setText(R.getMAR());                    //Displaying MAR value
            R.setMBR(String.valueOf(dec));              //setting MBR value
            cs.simulator.simulator.mbr.setText(R.getMBR());                    //Displaying MBR value
            R.setIR(String.valueOf(dec));               //setting IR value
            cs.simulator.simulator.ir.setText(R.getIR());                      //Displaying IR value
            

            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "MAR value:");
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getMAR());
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "MBR value:");
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getMBR());
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "IR value:");
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getIR());
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "PC value:");
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getPC());
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");

        }
    }

    public void output() {
        //output data into the window ,and assume the value is str

        if (DEVID == 0 || DEVID == 1) {
            str = R.getGPR(intGPR);
            //do something to output in the window
            if (str.length() < 10) {
                JOptionPane.showMessageDialog(null, "Register(" + intGPR + "):" + str);
                
            } else {
                JOptionPane.showMessageDialog(null, str);
            }
        } else {
        }
        System.out.println("hi");
        s = op + Integer.toBinaryString(intGPR) + Integer.toBinaryString(DEVID);
        //  System.out.println(Integer.toBinaryString(RY));
        System.out.println("S:OUT" + s);
        // System.out.println("hi");
        int dec = Integer.parseInt(s, 2);
        System.out.println("dec:" + dec);
        //reg.setMAR(reg.getPC());       //setting MAR value

        System.out.println(R.getPC());
        R.setMAR(R.getPC());

        cs.simulator.simulator.mar.setText(R.getMAR());                    //Displaying MAR value
        R.setMBR(String.valueOf(dec));              //setting MBR value
        cs.simulator.simulator.mbr.setText(R.getMBR());                    //Displaying MBR value
        R.setIR(String.valueOf(dec));               //setting IR value
        cs.simulator.simulator.ir.setText(R.getIR());                      //Displaying IR value
        //Whenever the the next instruction is ready pc value will increment by one
        R.incrementPC();
        //reg.setPC(String.valueOf(t));
        // cs.simulator.simulator.pc.setText(R.getPC());


        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "MAR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getMAR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "MBR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getMBR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "IR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getIR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "PC value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getPC());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");


    }
    public void CHK()
    {
        if (DEVID == 0 || DEVID == 1) {
        R.setGPR(intGPR,"valid");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "MAR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getMAR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "MBR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getMBR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "IR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getIR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "PC value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getPC());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        
    }
        else
        {
            R.setGPR(intGPR,"invalid");
             cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "MAR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getMAR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "MBR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getMBR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "IR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getIR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "PC value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getPC());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        }
            
    }

    public IO_Operation(Registers register, Memory memory, String[] instruction) {
        R = register;
        mem = memory;
        op = instruction[0];
        intGPR = Integer.parseInt(instruction[1]);
        DEVID = Integer.parseInt(instruction[2]);
        if (instruction.length > 3 && instruction[3] == " ") {
            System.out.println("STR:" + str);
        } else if (instruction.length > 3 && instruction[3] != " ") {
            str = instruction[3];

        }

    }

}
