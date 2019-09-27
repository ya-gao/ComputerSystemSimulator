package Instrustion;

import Memory.Memory;
import Registers.Registers;

public abstract  class Instruction {
    private String instruction; //the whole instruction in 16 bits
    public abstract void execute(String instruction, Registers register, Memory memory);

}
