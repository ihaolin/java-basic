package me.hao0.basic.script;

import org.junit.Test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;

public class TestScript {
	private static ScriptEngineManager manager = new ScriptEngineManager();
	private static ScriptEngine jsEngine = manager.getEngineByName("JavaScript");
	
	@Test
	public void testScript1() throws ScriptException {
		System.out.println(jsEngine.getFactory().getParameter("THREADING"));
		jsEngine.eval("n = 1728");
		System.out.println(jsEngine.eval("n+1"));
	}
	
	@Test
	public void testScriptMethod() throws ScriptException{
		jsEngine.eval(buildMethod());
		Greeter g = ((Invocable)jsEngine).getInterface(Greeter.class);
		System.out.println(g.greet("Lin Hao"));
	}
	
	@Test
	public void testScriptOO() throws FileNotFoundException, ScriptException{
		//String jsPath = TestScript.class.getResource("SimpleGreeter.js").toString();
		//System.out.println(jsPath);
		//FileReader reader = 
		//		new FileReader(new File(jsPath));
		jsEngine.eval("function SimpleGreeter(salutation){this.salutation = salutation;}SimpleGreeter.prototype.greet = function(whom){return whom+','+this.salutation;}");
		Object goodbyeGreeter = jsEngine.eval("new SimpleGreeter('Good Bye')");
		Greeter g = ((Invocable)jsEngine).getInterface(goodbyeGreeter, Greeter.class);
		System.out.println(g.greet("Lin Hao"));
	}

	private String buildMethod() {
		return "function greet(x) {return 'Hello, '+x;}";
	}
}
