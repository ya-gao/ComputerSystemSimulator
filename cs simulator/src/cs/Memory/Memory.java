package Memory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class Memory {
    public static final int MEMORY_MAX_LENGTH= 2048; //Max size is 2048 words
    public static final int MaxValue = 65536; //Max value is 2^16
    static ArrayList<String> memory = new ArrayList<>(2048);
    static ArrayList<String> memory1 = new ArrayList<>(2048);


    public Memory(){
        //
        //Memory.initializeMemory();

    }

    public int getMemoryCapacity(){// get current memory capacity that has been used
        return memory.size();
    }

    public String getMemValue(String address) throws Exception{//get memory value by address
        int addr= Integer.parseInt(address);
        String result = "";
        if(addr>MEMORY_MAX_LENGTH-1 &&addr<MEMORY_MAX_LENGTH*2){
            result= memory1.get(addr);
        }
        else if(addr>getMemoryCapacity()){
            throw new Exception("Memory not defined");
        }
        else if(addr<MEMORY_MAX_LENGTH){
            result = memory.get(addr);
        }
        else {
            throw new Exception("Exceeds the MEMORY_MAX_LENGTH*2 words limit");
        }
        return result;
    }

    public void setMemValue(String address, String Value) throws Exception{// set memory value with address and data

        int value= Integer.parseInt(Value,2);
        int addr= Integer.parseInt(address);
        if (addr> MEMORY_MAX_LENGTH-1){
            throw new Exception("Exceeds the MEMORY_MAX_LENGTH limit");
        }
        else if(value>MaxValue){
            throw new Exception("Value Overflow");
        }
        else if(addr<5){
            throw new Exception("attempt to overwrite reserved memory");
        }
        else {
            memory.add(addr,Value);
        }
    }

    public static void initializeMemory(){
        File file = new File("memory.txt");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 5; //available memory address starts from 5
            while ((tempString = reader.readLine()) != null) {
                memory.set(line,tempString);
                line++;
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

    public void initializeMemory2(){
        for(int i=0;i<MEMORY_MAX_LENGTH;i++){
            memory.add("0");
        }
    }


}
