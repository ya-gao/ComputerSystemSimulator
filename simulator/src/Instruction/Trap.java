package Instruction;

import Memory.Memory;
import Registers.Registers;
import Utilities.Decode;
import Utilities.MachineFaultException;
import cs.simulator.simulator;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Trap {


    /**
     *
     *         Traps to memory address 0, which contains the address of a table in
     *         memory. Stores the PC+1 in memory location 2. The table can have a
     *         maximum of 16 entries representing 16 routines for user-specified
     *         instructions stored elsewhere in memory. Trap code contains an index
     *         into the table, e.g. it takes values 0 – 15. When a TRAP instruction
     *         is executed, it goes to the routine whose address is in memory
     *         location 0, executes those instructions, and returns to the
     *         instruction stored in memory location 2. The PC+1 of the TRAP
     *         instruction is stored in memory location 2.
     *
     */

        Memory mem;
        Registers reg;
        int trapCode;

        public Trap(Registers register, Memory memory, String[] instruction) {
            String trapcode = instruction[2];
            trapCode = Integer.parseInt(trapcode);
        }

        public void trap() throws Exception {
            if (trapCode > 15 || trapCode < 0) {
                System.out.println("Wrong trapCode!");
                throw new MachineFaultException(MachineFaultException.FaultCode.ILL_TRPC.getValue(),
                        MachineFaultException.FaultCode.ILL_TRPC.getMessage());
            }
            // store pc+1 into memory 2
            reg.setMAR("2");
            reg.setMBR(reg.getPC() + 1);
            mem.setMemValue(reg.getMAR(), reg.getMBR());
            // goes to the routine in memory location 0
            reg.setMAR("0");
            reg.setMBR(mem.getMemValue(reg.getMAR()));
            String tableAddress = reg.getMBR();

            reg.setMAR(trapCode + tableAddress);
            reg.setMBR(mem.getMemValue(reg.getMAR()));
            String routine = reg.getMBR();
            reg.setPC(routine);
            try {
                do {
                    reg.setMAR(reg.getPC());
                    reg.setMBR(mem.getMemValue(reg.getMAR()));
                    reg.setIR(reg.getMBR());

                    Decode dc=new Decode(reg,mem);
                    String ins=reg.getIR();
                    String opcode=ins.substring(0,3);
                    if(opcode.equals("LDR")||opcode.equals("STR")||opcode.equals("LDA")||opcode.equals("LDX")||opcode.equals("STX"))
                    {
                        try {

                            dc.decodeForLoadStore(ins);
                        } catch (Exception ex) {
                            Logger.getLogger(simulator.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                    else if(opcode.equals("AMR")||opcode.equals("SMR")||opcode.equals("AIR")||opcode.equals("SIR"))
                    {
                        try {

                            dc.decodeForArithmetic(ins);
                        } catch (Exception ex) {
                            Logger.getLogger(simulator.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }

                    else if(opcode.equals("JZ ")||opcode.equals("JNE")||opcode.equals("SOB")||opcode.equals("JGE")||opcode.equals("JMA")||opcode.equals("JSR")||opcode.equals("RFS")||opcode.equals("JCC"))
                    {
                        try {

                            dc.decodeForTransfer(ins);
                        } catch (Exception ex) {
                            Logger.getLogger(simulator.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else if(opcode.equals("MLT")||opcode.equals("DVD")||opcode.equals("TRR")||opcode.equals("AND")||opcode.equals("ORR")||opcode.equals("NOT"))
                    {
                        try {

                            dc.decodeForLogical(ins);
                        } catch (Exception ex) {
                            Logger.getLogger(simulator.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else if(opcode.equals("SRC")||opcode.equals("RRC"))
                    {
                        System.out.println("HI SRC");
                        try {

                            dc.decodeForShiftRotate(ins);
                        } catch (Exception ex) {
                            Logger.getLogger(simulator.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    else
                    {
                        if(opcode.equals("IN "))
                        {
                            //JFrame frame1=new JFrame();
                            System.out.println("IN");
                            String inputValue = JOptionPane.showInputDialog("Please input a value");

                            try {

                                dc.decodeForIO(ins,inputValue);
                            } catch (Exception ex) {
                                Logger.getLogger(simulator.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        else if(opcode.equals("OUT")){
                            System.out.println("OUT");
                            try {

                                dc.decodeForIO(ins," ");
                            } catch (Exception ex) {
                                Logger.getLogger(simulator.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    }

                } while (reg.getIR() != "0");
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            // return to the instruction
            reg.setMAR("2");
            reg.setMBR(mem.getMemValue(reg.getMAR()));
            reg.setPC(reg.getMBR());
        }
    }


