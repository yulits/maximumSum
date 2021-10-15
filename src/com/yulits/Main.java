package com.yulits;

import javax.xml.crypto.dsig.keyinfo.KeyName;
import java.io.IOException;
import java.util.*;

public class Main {

    public static  Integer[] mergeArrays(Integer[] a, Integer[] b) {
        Integer[] c = new Integer[a.length + b.length];

        for (int i = 0; i < a.length; i++)
            c[i] = a[i];

        for (int i = 0; i < b.length; i++)
            c[a.length + i] = b[i];

        return b;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = scanner.nextInt();
        System.out.print("Enter k: ");
        int k = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter n numbers: ");
        String line = scanner.nextLine();

        int initialSum = 0;
        int newSum = 0;
        int count = 0;
        List<String> maxLengthNumbersList = new ArrayList<String>();
        List<String> redundantNumbersList = new ArrayList<String>();

        List<String> numbersStrList = new ArrayList<String>(Arrays.asList(line.split(" ")));

        int maxLength = 0;
        while ((count < k) && (!numbersStrList.isEmpty())) {
            Collections.sort(numbersStrList, Comparator.comparingInt(String::length));
            Collections.reverse(numbersStrList);
            maxLength = numbersStrList.get(0).length();

            //split the list to maximum length list and the redundant list
            maxLengthNumbersList.clear();
            redundantNumbersList.clear();
            // find all elements of the specific length, sort them in ascending order
            for (int i = 0; i < numbersStrList.size(); i++) {
                if (numbersStrList.get(i).length() == maxLength)
                    maxLengthNumbersList.add(numbersStrList.get(i));
                else
                    redundantNumbersList.add(numbersStrList.get(i));
            }

            Collections.sort(maxLengthNumbersList);

            for (String number : maxLengthNumbersList) {
                if (count < k) {
                    if (number.charAt(0) != '9') {
                        String initialNumber = number.charAt(0) + "0".repeat(maxLength - 1);
                        initialSum += Integer.parseInt(initialNumber);
                        String changedNumber = "9" + "0".repeat(maxLength - 1);
                        newSum += Integer.parseInt(changedNumber);
                        count++;
                    }
                    // add to the redundant numbers list the initial number without the first symbol
                    if (number.substring(1).length() > 0)
                        redundantNumbersList.add(number.substring(1));
                } else
                    break;
            }
            numbersStrList.clear();
            numbersStrList.addAll(redundantNumbersList);
        }

        System.out.print("The difference is: ");
        System.out.println(newSum - initialSum);

    }
}
