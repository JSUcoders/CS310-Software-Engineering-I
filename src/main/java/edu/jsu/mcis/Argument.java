package edu.jsu.mcis;
import java.util.*;

public class Argument {
	public enum DataType{INT, FLOAT, BOOL, STRING};

    private String name;
    private String value;
    private DataType type;
    
    public Argument(String n, DataType t){
        name = n;
        type = t;
    }
    
    public void addValue(String v){
        value = v;
    }
    
    public String getName(){
        return name;
    }
    
    public String getValue(){
        return value;
    }
    
    public DataType getType(){
        return type;
    }
}