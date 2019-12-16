package Instruction;

import Memory.Memory;
import Registers.Registers;

public abstract class Instruction {
    Registers register = new Registers();
    Memory memory= new Memory();
    public abstract void Instruction(Registers register, Memory memory);
}
