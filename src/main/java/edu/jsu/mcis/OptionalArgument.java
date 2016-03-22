package edu.jsu.mcis;
import java.util.*;

public class OptionalArgument extends Argument{
    public OptionalArgument(String n, String d, DataType t){
        super(n,d,t); 
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

	public String getDescription(){
		return description;
	}
}