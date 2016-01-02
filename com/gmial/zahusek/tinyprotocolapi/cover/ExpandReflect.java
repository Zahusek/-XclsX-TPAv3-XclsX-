package com.gmail.zahusek.tinyprotocolapi.reflect;

import com.gmail.zahusek.tinyprotocol.Reflection;
import com.gmail.zahusek.tinyprotocol.Reflection.Accessor;

public abstract class ExpandReflect {
	
	private Object object;
	private Accessor[] accessor;
	
	public ExpandReflect(Type type, String claz){
		this.object = Reflection.getConstructor(String.format("{%s}.%s", type.name().toLowerCase(), claz)).invoke();
		this.accessor = Reflection.getField(this.object.getClass());
	}
	
	public ExpandReflect(Type type, String claz, int constructor, Object... obs){
		this.object = Reflection.getConstructor(String.format("{%s}.%s", type.name().toLowerCase(), claz), Reflection.getClass(String.format("{%s}.%s", type.name().toLowerCase(), claz)).getConstructors()[constructor].getParameterTypes()).invoke(obs);
		this.accessor = Reflection.getField(this.object.getClass());
	}
	
	protected void setParameter(int id, Object value) {
		accessor[id].set(object, value);
	}

	@SuppressWarnings("unchecked")
	protected <T> T getParameter(int id) {
		return (T) accessor[id].get(object);
	}
	
	protected void setParameters(Object... values) {
		for(int i = 0; i < values.length; i++) 
			accessor[i].set(object, values[i]);
	}
	
	public Object getReturned() {
		return object;
	}
	protected enum Type {
		NMS, OBC;
	}
}
