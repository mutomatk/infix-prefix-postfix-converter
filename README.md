# Infix 
→
 Prefix & Postfix Converter
A Java program that converts infix expressions to **Postfix** (Reverse Polish Notation) and **Prefix** (Polish
Notation) using the **Shunting-Yard Algorithm**.--
## Features- Converts infix expressions to both Postfix and Prefix- Supports operators: `+` `-` `*` `/` `^`- Handles parentheses `( )`- Respects operator precedence and associativity (`^` is right-associative)- Validates mismatched parentheses and throws descriptive errors- Interactive command-line interface with colour output--
## Project Structure
```
infix-prefix-postfix-converter/
├──
 src/
│  
├──
 Main.java           
# Entry point — interactive CLI
│  
├──
 Converter.java      # Core algorithm (toPostfix, toPrefix)
│  
└──
 ConverterTest.java  # JUnit 5 unit tests
└──
 README.md
```--
## How to Compile & Run
```bash
# Compile
javac src/Converter.java src/Main.java
# Run
java -cp src Main
```
### Example Session
```
╔══════════════════════════════════════╗
║  Infix 
→
 Prefix & Postfix Converter 
║
╚══════════════════════════════════════╝
Enter infix expression: A+B*C-D
┌─────────────────────────────────────┐
│
Results                            
│
├─────────────────────────────────────┤
│
Infix   : A+B*C-D                  
│
Postfix : ABC*+D-                  
│
Prefix  : -+A*BCD                  
│
│
│
└─────────────────────────────────────┘
```--
## Expression Examples
| Infix              
| Postfix        
| Prefix         
|--------------------|----------------|----------------|
| `A+B`              
| `AB+`          
| `A+B*C`            
| `ABC*+`        
| `+AB`          
|
| `+A*BC`        
| `A+B*C-D`          
|
|
| `ABC*+D-`      | `-+A*BCD`      |
| `(A+B)*C`          
| `A^B^C`            
| `AB+C*`        
| `ABC^^`        
| `*+ABC`        
| `^A^BC`        
|
|
| `((A+B)*(C-D))`    | `AB+CD-*`      | `*+AB-CD`      |--
## Algorithm
### Infix 
→
 Postfix (Shunting-Yard)
1. Scan tokens left to right
2. **Operand** 
→
 append to output
3. **Operator** 
→
 pop operators of higher/equal precedence from stack to output, then push
4. **`(`** 
→
 push onto stack
5. **`)`** 
→
 pop to output until `(` is found
6. **End** 
→
 pop remaining stack to output
### Infix 
→
 Prefix
1. Reverse the infix expression
2. Swap `(` 
↔
 `)`
3. Apply the Postfix algorithm
4. Reverse the result
**Time Complexity:** O(n) &nbsp;|&nbsp; **Space Complexity:** O(n)--
## Running Tests
```bash
# Requires JUnit 5 on classpath
javac -cp .:junit-platform-console-standalone.jar src/*.java
java  -cp .:junit-platform-console-standalone.jar org.junit.platform.console.ConsoleLauncher --scan-classpath
```--
## Author
Mutoma — [github.com/mutomatk](https://github.com/mutomatk)
