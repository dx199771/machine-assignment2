/**
* TestTrolleyGen
* testing TrolleyGen
*/

import sheffield.*;
import java.util.*;
import java.math.*;


public class TestTrolleyGen
{
 public static void main(String[] arg) {
   // create objects for input and output
   EasyWriter scr = new EasyWriter();
   
   TrolleyGen tg = new TrolleyGen(12345,10);
   String[]trolley= tg.fillTrolley();
   
   for (int i=0;i<10;i++ ){ scr.println(trolley[i]);};
}}   
 