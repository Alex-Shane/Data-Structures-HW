package hw4;


import java.util.Scanner;

/**
 * A program for an RPN calculator that uses a stack.
 */
public final class Calc {
  /**
   * The main function.
   *
   * @param args Not used.
   */
  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    LinkedStack<Integer> stack = new LinkedStack<>();
    String userInput = keyboard.next();
    while (!("!".equals(userInput))) {
      handleInput(userInput, stack);
      userInput = keyboard.next();
    }
  }

  private static void handleInput(String input, LinkedStack<Integer> stack) {
    if (inputIsInteger(input)) {
      stack.push(Integer.parseInt(input));
    } else if (inputIsValidOperator(input)) {
      if (!stackInStateToPerformOperation(stack)) {
        System.out.println("ERROR: Invalid operation with only one number");
      } else {
        performOperation(input, stack);
      }
    } else if (inputIsValidCommand(input)) {
      performCommand(input, stack);
    } else {
      System.out.println("ERROR: bad token");
    }
  }

  private static boolean inputIsInteger(String input) {
    try {
      Integer.parseInt(input);
      return true;
    } catch (NumberFormatException ex) {
      return false;
    }
  }

  private static boolean inputIsValidOperator(String input) {
    return "+".equals(input) || "-".equals(input) || "*".equals(input) || "/".equals(input) || "%".equals(input);
  }

  private static boolean inputIsValidCommand(String input) {
    return "?".equals(input) || ".".equals(input); // don't need to check ! because that is checked in while condition
  }

  private static void performOperation(String input, LinkedStack<Integer> stack) {
    int val2 = stack.top(); // val2 since it was added most recently
    stack.pop();
    int val1 = stack.top(); // val1 since it was added before val2
    stack.pop();
    if ("+".equals(input)) {
      stack.push(val1 + val2); // put result back into stack
    } else if ("-".equals(input)) {
      stack.push(val1 - val2); // put result back into stack
    } else if ("*".equals(input)) {
      stack.push(val1 * val2); // put result back into stack
    } else if ("/".equals(input)) {
      stack.push(val1 / val2); // put result back into stack
    } else {
      stack.push(val1 % val2); // put result back into stack
    }
  }

  private static void performCommand(String input, LinkedStack<Integer> stack) {
    if ("?".equals(input)) {
      System.out.println(stack.toString());
    } else {
      System.out.println(stack.top());
    }
  }

  private static boolean stackInStateToPerformOperation(LinkedStack<Integer> stack) {
    if (stack.empty()) { // if stack empty, can't perform operation
      return false;
    } else {
      int topVal = stack.top();
      stack.pop();
      // if stack empty after call, then can't perform operation because we need two numbers to perform operation
      if (stack.empty()) {
        return false;
      } else {
        stack.push(topVal); // revert to original stack state
        return true;
      }
    }
  }
}
