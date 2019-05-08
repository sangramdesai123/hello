import java.util.*;
import java.io.*;
/*
S->Bb/Cd,
B->aB/#,
C->cC/#

Ans:
Var  first      follow
S->  {a,b,c,d}  {$}
B->  {a,$}      {b}
C->  {c,$}      {d}
*/

class Main{
 static ArrayList<Character> done = new ArrayList<Character>(); 
 static ArrayList<String> calcfirst = new ArrayList<String>();
 static ArrayList<String> calcfollow = new ArrayList<String>();
 static ArrayList<String> productions = new ArrayList<String>();
 static String follow = "", first = "";

 public static void main(String[] args) throws Exception{
   BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
 System.out.println("Enter the number of productions");
 int no = Integer.parseInt(br.readLine());
 System.out.println("Enter the productions");
 for (int i=0; i<no; i++) {
  productions.add(br.readLine()+'\0');
 }

   //First Set
   for (int i=0; i<productions.size(); i++) {
     first = "";
     char c = productions.get(i).charAt(0);
     if (done.contains(c)) {
       continue;      
     }
     String firstnew = findfirst(c,0,0);
     calcfirst.add(c + firstnew + '\0');
     done.add(c);
   }

   System.out.println("First Set");
   for (int j=0; j<calcfirst.size(); j++) {
     System.out.println(done.get(j) + "  ->  " + calcfirst.get(j).substring(1));
   }

   done.clear();
   //Follow Set
   for (int i=0; i<productions.size(); i++) {
     follow = "";
     char c = productions.get(i).charAt(0);
     if (done.contains(c)) {
       continue;      
     }
     String follownew = follow(c);
     calcfollow.add(follownew);   
     done.add(c);
   }
   System.out.println("Follow Set");
   for (int j=0; j<calcfollow.size(); j++) {
     System.out.println(done.get(j) + "  ->  " + calcfollow.get(j));
   }
 }

 public static String findfirst(char c, int q1, int q2)
 {
   int j;
  
   // The case where we
   // encounter a Terminal
   if(!(Character.isUpperCase(c))) {
     first = first + c;
   }
   for(j = 0; j < productions.size(); j++)
   {
     if(productions.get(j).charAt(0) == c)
     {
       if(productions.get(j).charAt(2) == '#')
       {
         if(productions.get(q1).charAt(q2) == '\0')
           first += '#';
         else if(productions.get(q1).charAt(q2) != '\0' && (q1 != 0 || q2 != 0))
         {
           // Recursion to calculate First of New
           // Non-Terminal we encounter after epsilon
           findfirst(productions.get(q1).charAt(q2), q1, (q2+1));
         }
         else
           first += '#';
       }
       else if(!Character.isUpperCase(productions.get(j).charAt(2)))
       {
         first += productions.get(j).charAt(2);
       }
       else
       {
         // Recursion to calculate First of
         // New Non-Terminal we encounter
         // at the beginning
         findfirst(productions.get(j).charAt(2), j, 3);
       }
     }
   }
   return first;
 }

 public static String follow(char c)
 {
   int i, j;
  
   // Adding "$" to the follow
   // set of the start symbol
   if(productions.get(0).charAt(0) == c) {
     follow += '$';
   }
   for(i = 0; i < productions.size(); i++)
   {
     for(j = 2;j < productions.get(i).length(); j++)
     {
       if(productions.get(i).charAt(j) == c)
       {
         if(productions.get(i).charAt(j+1) != '\0')
         {
           // Calculate the first of the next
           // Non-Terminal in the production
           followfirst(productions.get(i).charAt(j+1), i, (j+2));
         }
        
         if(productions.get(i).charAt(j+1)=='\0' && c!=productions.get(i).charAt(0))
         {
           // Calculate the follow of the Non-Terminal
           // in the L.H.S. of the production
           follow(productions.get(i).charAt(0));
         }
       }
     }
   }
   return follow;
 }

 public static void followfirst(char c, int c1, int c2)
 {
   int k;
  
   // The case where we encounter
   // a Terminal
   if(!(Character.isUpperCase(c)))
     follow += c;
   else
   {
     int i = 0, j = 1;
     for(i = 0; i < calcfirst.size(); i++)
     {
       if(calcfirst.get(i).charAt(0) == c)
         break;
     }
    
     //Including the First set of the
     // Non-Terminal in the Follow of
     // the original query
     while(calcfirst.get(i).charAt(j) != '\0')
     {
       if(calcfirst.get(i).charAt(j) != '#')
       {
         follow += calcfirst.get(i).charAt(j);
       }
       else
       {
         if(productions.get(c1).charAt(c2) == '\0' )
         {
           // Case where we reach the
           // end of a production
           follow(productions.get(c1).charAt(0));
         }
         else
         {
           System.out.println("In else");
           // Recursion to the next symbol
           // in case we encounter a "#"
           followfirst(productions.get(c1).charAt(c2), c1, c2+1);
         }
       }
       j++;
     }
   }
 }
}




/******************************************************/

import java.io.*;
import java.util.*;

class map {
    String reg,val;

    public map(String reg, String val) {
        this.reg = reg;
        this.val = val;
    }
}

class Main {
    static int counter = -1;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of registers to use");
        int N = sc.nextInt();
        ArrayList<map> rx = new ArrayList<>();

        for(int i=0;i<N;i++) {rx.add(new map("R"+i, "-"));}
        
        //File file = new File("mcg.txt");
        String input ="a=a+b b=a+c d=a+b"; //readFile("", file);

        System.out.println(input);
        String[] lInput = input.split("[\\s]+");

        for(int i=0;i<lInput.length;i++) {
            String lhs = lInput[i].substring(0,lInput[i].indexOf("=")).trim();
            String rhs = lInput[i].substring(lInput[i].indexOf("=")+1).trim();
            System.out.println(lhs+" "+rhs);
            StringTokenizer st = new StringTokenizer(rhs," +-/*");
            String[] op = new String[2];
            int j=0;
            while(st.hasMoreTokens()) {op[j++] = st.nextToken();}
            
            int pos = getReg(rx, op[0]);
            checkop(rhs);
            int pos2 = check(rx, op[1]);
            if(pos2==-1) {System.out.print(op[1]+","+rx.get(pos).reg+"\n");}
            else {System.out.print(rx.get(pos2).reg+","+rx.get(pos).reg+"\n");}
            rx.get(pos).val = lhs;
        }

        sc.close();
    }
    
    public static int check(ArrayList<map> rx, String op1) {
        for(int i=0;i<rx.size();i++) {
            if(rx.get(i).val.equals(op1)) {return i;}
        }
        return -1;
    }

    public static void checkop(String rhs) {
        if(rhs.contains("+")) {System.out.print("ADD ");}
        else if(rhs.contains("-")) {System.out.print("SUB ");}
        else if(rhs.contains("/")) {System.out.print("DIV ");}
        else if(rhs.contains("*"))  {System.out.print("MUL ");}
    }

    public static int getReg(ArrayList<map> rx, String op1) {
        int N = rx.size();

        for(int i=0;i<N;i++) {
            if(rx.get(i).val.equals("-")) {
                System.out.println("MOV "+op1+","+rx.get(i).reg);
                rx.get(i).val = op1;
                return i;
            }
        }
        counter=(counter+1)%N;
        System.out.println("MOV "+rx.get(counter).reg+","+rx.get(counter).val);
        rx.get(counter).val = op1;
        System.out.println("MOV "+op1+","+rx.get(counter).reg);
        return counter;
    }

    public static String readFile(String input, File file) {
        try {
            int c;
            FileInputStream in = new FileInputStream(file);
            while((c=in.read())!=-1) {
                input = input + (char) c;
            }
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        return input;
    }
}

/*********************************************************************/
/*lex yacc*/
/*PARSER FOR IF ELSE STATEMENT*/  
 //c-madeeasy.blogspot.com  
 /*YACC PROGRAM*/  
 %{  
 #include<stdio.h>  
 #include<stdlib.h>  
 %}  
 %token num alpha LT GT EQ LE GE NE AND OR INC DEC END  
 %left '+''-'  
 %left '*''/'  
 %right '^'  
 %right '='  
 %nonassoc UMINUS  
 %nonassoc IF  
 %nonassoc ELSE  
 %left GE NE LT GT LE EQ  
 %left AND OR  
 %%  
 S:ST END{printf("\n Accepted\n");exit(0);}  
 ST:IF'('F')''{'ST'}'%prec IF  
  |IF'('F')''{'ST'}'ELSE'{'ST'}'  
  |E';'  
  |E';'ST  
  F:C LO C  
  |C  
 LO:AND  
  |OR  
  C:E RELOP E  
  |E  
  E:alpha '='E  
  |E'+'E   
  |E'-'E   
  |E'*'E   
  |E'/'E  
  |E'^'E  
  |'('E')'   
  |'-'E %prec UMINUS  
  |alpha  
  |num  
  |alpha INC  
  |alpha DEC  
 RELOP:LT  
    |GT  
    |EQ  
    |LE  
    |GE  
    |NE  
   ;  
 %%  
 #include"lex.yy.c"  
 int main()  
 {   
 yyparse();  
 yylex();  
 return END;  
 }  
 yyerror(char *s)  
 {  
  printf("\nError");  
 }  
 /*LEX PROGRAM*/  
 %{  
 #include"pgm4.tab.h"  
 %}  
 %%  
 "if" {return IF;}  
 "else" {return ELSE;}  
 "&&" {return AND;}  
 "||" {return OR;}  
 "<=" {return LE;}  
 ">=" {return GE;}  
 ">" {return GT;}  
 "<" {return LT;}  
 "!=" {return NE;}  
 "++" {return INC;}  
 "--" {return DEC;}  
 "==" {return EQ;}  
 [0-9]+ {return num;}  
 [a-zA-Z]+ {return alpha;}  
 [\t];  
 [\n];   
 "$" {return END;}  
 .  {return yytext[0];}  
 %%  

 /****************************************************************/
 import  java.util.*;
public class Main{
 static String gra[] = {"a","(G)","G+G","G-G","G*G","G/G","G^G"};
 static List<String> grammer = Arrays.asList(gra);
 static String symbols[] = {"(",")","^","*","/","+","-","a","$"};
 static Stack<String> stack= new Stack<String>();
 static int current = 0;
 static String precedence[][]={
                 {"<","=","<","<","<","<","<","<","E"},
                 {"E",">",">",">",">",">",">","E",">"},
                 {"<",">","<",">",">",">",">","<",">"},
                 {"<",">","<","<",">",">",">","<",">"},
                 {"<",">","<",">","<",">",">","<",">"},
                 {"<",">","<","<","<",">",">","<",">"},
                 {"<",">","<","<","<",">",">","<",">"},
                 {"E",">",">",">",">",">",">","E",">"},
                 {"<","E","<","<","<","<","<","<","A"}
               };



 public static void main(String[] args) {
   printTable();
   Scanner scanner = new Scanner(System.in);
  System.out.println("add");
  genrate();
   System.out.println("Enter the expression");
   String input = scanner.nextLine()+"$";
   parse(input);
 }
public static void genrate(){
  String x[]={"(",")","^","*","/","+","-","a","$"};
  String y[]={"(",")","^","*","/","+","-","a","$"};
     int z[]={10,9,7,6,5,4,3,8,2};
  String pre[][]=new String[x.length][x.length];
  for(int i=0;i<x.length;i++){
    for(int j=0;j<y.length;j++){
      if(z[i]>z[j]){
        pre[i][j]=" > ";
      }else if(z[i]<z[j]){
        pre[i][j]=" < ";
      }else if(x[i].equals("(")&&y[j].equals("$")){
        pre[i][j]=" E ";
      }else if(x[i].equals("$")&&y[j].equals(")")){
        pre[i][j]=" E ";
      }else{
        pre[i][j]="  ";
      }
    }
  }
  /*print*/
  for(int i=0;i<x.length;i++){
    System.out.print(x[i]+" ");
    for(int j=0;j<y.length;j++){
      System.out.print(pre[i][j]+" ");
    }
    System.out.println();
  }
 }

 public static void parse(String input){
   char[] ib = input.toCharArray();
   stack.push("$");
   System.out.println();
   System.out.println();
   printStack(stack);
   printIB(ib,current+1);
   while(true){
     String top = stack.peek();
     ListIterator li = stack.listIterator(stack.size());
     while(li.hasPrevious()){
       top = (String)li.previous();
       if(!(top.equals("G")))
         break;
     }

     String currentInput = ib[current] + "";
     int topIndex = Arrays.asList(symbols).indexOf(top);
     int currentIndex = Arrays.asList(symbols).indexOf(currentInput);
     String op = precedence[topIndex][currentIndex];
     if(op.equals("E")){
       /* if error then print and quit */
       System.out.println("Error in given string");
       System.exit(0);
     }else if(op.equals("A")){
       /* if accept then print and quit */
       System.out.println("Accepted");
       System.exit(0);
     }else if(op.equals("<") || op.equals("=")){
       /* Shift current input buffer to stack */
       if(op.equals("<"))
         stack.add("<");
       stack.add(currentInput+"");
       current++;
       printStack(stack);
       printIB(ib,current+1);
     }else if(op.equals(">")){
       String handler = "";
       String popS = "";
       do{
         popS = stack.pop();
         handler += popS;
       }while(!(popS.equals("<")));
       handler = handler.replace("<","");
       String reverse = "";
       for(int i = handler.length() - 1; i >= 0; i--)
       {
         reverse = reverse + handler.charAt(i);
       }
       if(grammer.contains(reverse)){
         String top1 = stack.peek();
         String currentInput1 = ib[current] + "";
         int topIndex1 = Arrays.asList(symbols).indexOf(top1);
         int currentIndex1 = Arrays.asList(symbols).indexOf(currentInput1);
         String op1 = precedence[topIndex1][currentIndex1];
         if(op1.equals("E")){
           /* if error then print and quit */
           System.out.println("Error in given string");
           System.exit(0);
         }else if(op1.equals("A")){
           /* if accept then print and quit */
           System.out.println("Accepted");
           System.exit(0);
         }else if(op1.equals("<")){
           stack.add("<");
           stack.add("G");
           stack.add(currentInput1);
           current++;
           printStack(stack);
           printIB(ib,current+1);
         }else if(op1.equals(">")){
           stack.add("G");
           printStack(stack);
           printIB(ib,current+1);
         }else if(op1.equals("=")){
           stack.add("G");
           stack.add(currentInput1);
           current++;
           printStack(stack);
           printIB(ib,current+1);
         }
       }else{
         System.out.println("handler : " + reverse);
         System.out.println("Error");
         System.exit(0);
       }
     }

   }
 }

 public static void printStack(Stack<String> stack){
   System.out.println("Current stack is : " + stack);
 }

 public static void printIB(char[] ib,int current){
   String inputBuf = new String(ib);
   inputBuf = inputBuf.substring(current-1);
   System.out.println("Current input buffer is : " + inputBuf);
 }


 public static void printTable(){
   System.out.println("OPERATOR MATRIX");
   System.out.print("E");
   for(int i=0;i<symbols.length;i++){
     System.out.print("\t" + symbols[i]);
   }
   System.out.println();
  
   for(int i=0;i<symbols.length;i++){
     System.out.print(symbols[i]);
     for(int j=0;j<symbols.length;j++){
       System.out.print("\t" + precedence[i][j]);
     }
     System.out.println();
   }
 }
}

/*
a+(a*a)-a
*/

/****************************************************************************/