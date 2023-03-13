# Discussion

**Document all error conditions you determined and why they are error
 conditions. Do this by including the inputs that you used to test your
  program and what error conditions they exposed:**

Error 1: One error I noticed was trying to call an integer operation when the stack has zero or 1 numbers.
This is an error because +,-,*,/, and % all require two numbers to perform the operation. I found this error by adding
10 to the stack, then calling +, which resulted in an Empty Exception since there was only one number in stack. 

Error 2: Dividing by zero and modulus division by zero. I called 10 0 / and got an arithmetic exception since we can't 
divide by zero. Thus I added in a check to make sure that the top value of the stack isn't zero when attempting to divide
or modulus divide.

Error 3: We can't call the . operator when the stack is empty since this results in a empty exception. Thus, I added
in a check to make sure the stack isn't empty when trying to call ".". I found this error by calling . as my first 
input. 

Error 4: Given errors of non ints and characters that don't match our operators or special commands. Easy tests to check
that these are errors is 1.0 2.0 * would result in a double and any other character input that isn't a valid operator 
or command character like bleh bleh + makes no sense under integer addition, and thus should be a caught error. 