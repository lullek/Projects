import java.util.*;

public class Factorial {

public Factorial() {

}

public long factorial(long digit) { // method to calculate the factorial of the number passed as argument
  long startDigit = digit;

  if(digit > 1) {
    return startDigit*factorial(digit-1);
  } else {
    return 1;
  }

}

public ArrayList<Long> intToArray(long number) { // a method to split up a larger number to several single digits that are placed in a list
  long temp = number;
  ArrayList<Long> list = new ArrayList<Long>();

  while(temp > 0) {
    list.add(temp % 10);
    temp /= 10;
  }

  for(int i = 0; i < list.size(); i++) { //removes zeroes from the list
    if(list.get(i) == 0) {
      list.remove(i);
      i -= 1;
    }
  }

  return list;
}

public long sumList(ArrayList<Long> listTwo) { //takes the elements of a list and sums them together
  long sum = 0;

  for(int i = 0; i < listTwo.size(); i++) {
    sum += listTwo.get(i);
  }
  return sum;
}

public long sumAll(long number) { //takes a number, calls the other functions, and returns the sum of the separate digits
  ArrayList<Long> numberList = new ArrayList<Long>();
  numberList = intToArray(number);
  long sum = 0;
  if(numberList.size() > 1) {
    while(numberList.size() > 1) {
      sum = sumList(numberList);
      numberList = intToArray(sum);
    }
  } else {
    sum = number;
  }
  return sum;
}

public static void main(String[] args) {
  Factorial calculator = new Factorial();
  int maxValue = 20;
  for(int i = 1; i <= maxValue; i++){
  long result = calculator.factorial(i);
  long sumOfAll = calculator.sumAll(result);
  System.out.println("The factorial of " + i + " is " + result + " sumAll is " + sumOfAll);
  }
}

}
