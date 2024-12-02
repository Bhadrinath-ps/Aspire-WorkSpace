package com.aspiresys;

import java.util.ArrayList;
import java.util.List;

public class AutoboxingUnboxing {

    int primitiveInt = 25;
    Integer boxedInt = primitiveInt;

    Integer anotherBoxedInt = new Integer(30);
    int unboxedInt = anotherBoxedInt;

    List<Integer> numberList = new ArrayList<>();

    // Method to perform autoboxing, unboxing, and list operations
    public void performAutoboxingUnboxing() {
        numberList.add(100);
        numberList.add(200);

        int firstNumber = numberList.get(0);
        int secondNumber = numberList.get(1);

        int sum = firstNumber + secondNumber;

        System.out.println("Boxed Integer: " + boxedInt);
        System.out.println("Unboxed int: " + unboxedInt);
        System.out.println("First number from the list: " + firstNumber);
        System.out.println("Second number from the list: " + secondNumber);
        System.out.println("Sum of the two numbers: " + sum);
    }

    public static void main(String[] args) {
        AutoboxingUnboxing example = new AutoboxingUnboxing();
        example.performAutoboxingUnboxing();
    }
}
