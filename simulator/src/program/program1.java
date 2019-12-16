/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program;

import Memory.Memory;
import Registers.Registers;
import Utilities.Decode;
import cs.simulator.simulator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author dilipvarma
 */
public class program1 {
     Memory mem = new Memory();
    Registers R = new Registers();
    Decode dc = new Decode(R, mem);
    simulator GUI;
    String address;
    int intGPR;
    int RX;
    int RY;

    String s;
    String opcode;
    String ins;

    public void readFile() throws Exception {
        File file = new File("./program1.txt");
        System.out.println("program1");
        BufferedReader reader = null;
        // store instructions from mem[600]
        try {
            reader = new BufferedReader(new FileReader(file));
            mem.setMemValue("35", "-1");
            String tempString;
            R.setPC("600");
            int i = 600;
            while ((tempString = reader.readLine()) != null) {
                mem.setMemValue(String.valueOf(i), tempString);
                System.out.println("Memory at" + i + ": " + mem.getMemValue(String.valueOf(i)));
               // System.out.println("PC: " + R.getPC());
               System.out.println("i:"+i);
               System.out.println("pc:"+R.getPC());
                // if if PC == i, execute the instruction; otherwise skip execution, just read and store next line of txt file
                if (Integer.parseInt(R.getPC()) == i) {
                    System.out.println("I'm executing " + R.getPC() + "instruction!!!");
                    System.out.println("pc:"+R.getPC());

                    try {
                        System.out.println(mem.getMemValue(R.getPC()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        ins = mem.getMemValue(R.getPC());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(ins);
                    opcode = ins.substring(0, 3);

                    if (opcode.equals("LDR") || opcode.equals("STR") || opcode.equals("LDA") || opcode.equals("LDX") || opcode.equals("STX")) {
                        try {

                            dc.decodeForLoadStore(ins);
                        } catch (Exception ex) {
                            Logger.getLogger(simulator.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else if (opcode.equals("AMR") || opcode.equals("SMR") || opcode.equals("AIR") || opcode.equals("SIR")) {
                        System.out.println("SMR");
                        try {

                            dc.decodeForArithmetic(ins);
                        } catch (Exception ex) {
                            Logger.getLogger(simulator.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else if (opcode.equals("JZ ") || opcode.equals("JNE") || opcode.equals("JCC") || opcode.equals("SOB") || opcode.equals("JGE") || opcode.equals("JMA") || opcode.equals("JSR") || opcode.equals("RFS"))//||opcode.equals("JCC"))
                    {
                        try {

                            dc.decodeForTransfer(ins);
                        } catch (Exception ex) {
                            Logger.getLogger(simulator.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (opcode.equals("MLT") || opcode.equals("RDF") || opcode.equals("MIN") || opcode.equals("DVD") || opcode.equals("TRR") || opcode.equals("AND") || opcode.equals("ORR") || opcode.equals("NOT")||opcode.equals("MUL")) {
                        try {

                            dc.decodeForLogical(ins);
                        } catch (Exception ex) {
                            Logger.getLogger(simulator.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        if (opcode.equals("IN ")) {

                            if (ins.substring(6, 7).equals("2")) {
                                try {

                                    dc.decodeForIO(ins, " ");
                                } catch (Exception ex) {
                                    Logger.getLogger(simulator.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {
                                //JFrame frame1=new JFrame();
                                System.out.println("IN");
                                String inputValue = JOptionPane.showInputDialog("Please input a value");

                                try {
                                    dc.decodeForIO(ins, inputValue);
                                } catch (Exception ex) {
                                    Logger.getLogger(simulator.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        } else if (opcode.equals("OUT")) {
                            System.out.println("OUT");
                            try {
                                dc.decodeForIO(ins, " ");
                            } catch (Exception ex) {
                                Logger.getLogger(simulator.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
                System.out.println("execution of this instruction done");

            

                i++;
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

    }

}
    
    

