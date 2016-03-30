package edu.jsu.mcis;
import java.util.*;

public class OptionalArgument extends Argument{
    public OptionalArgument(String n, String d, String v, DataType t){
        super(n,d,t);
		addValue(v);
    }
    
    public void setValue(String v){
        addValue(v);
    }
    /* public String getName(){
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
	} */
}