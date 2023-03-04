
import java.util.Scanner;
import  java.lang.String;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

class UserInput{
    Scanner scanner;
    String[] numbers,Operators;
    public String input;
    public UserInput(){
        scanner = new Scanner(System.in);
        System.out.println("enter input:");
        input = scanner.nextLine();
        numbers = input.split("[^\\d.]+");
        Operators = input.replaceAll("[\\d.]+", "").split("");
    }
    public String[] InputSymbol(){
        return Operators;
    }
    public String[] InputInteger(){
        return numbers;
    }

}

class CheckValidation{
    Boolean flag = true;
    public CheckValidation(String[] numbers, String[] Operators){
        for (String n : numbers) {
            int number = Integer.parseInt(n);
            if (number < 1 || number > 10) {
                flag = false;
                throw new IllegalArgumentException("Invalid number");
            }
        }
        for (String character : Operators) {
            if (!"+-*/".contains(character)) {
                flag = false;
                throw new IllegalArgumentException("Invalid string");
            }
        }
    }
    public boolean result(){
        return flag;
    }
}

class Output{
    public Output(Boolean flag, String input){
        if(flag){
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("js");
            try {
                Object result = engine.eval(input);
                System.out.println("Result: " + result);
            } catch (ScriptException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("failed by CheckValidation Object\n");
        }
    }
}
public class Main {
    public static void main(String[] args) {

        UserInput InputObject = new UserInput();

        String[] numbers = InputObject.InputInteger();
        String[] Characters = InputObject.InputSymbol();

        String input = InputObject.input;

        CheckValidation CheckObject = new CheckValidation(numbers, Characters);
        Output Result = new Output(CheckObject.result(), input);

    }
}