/**
 * Class to read file and print contents
 * has get and read functions to deal with upload and putting content to arrays
 * has print statements for menu and for specific dish and price
 */

package com.example.maman13part2real;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MenuFromDoc {

    private Exception fileEmpty = new Exception("File is Null!");
    private static final int NUMDISHEDPERCOURSE = 3;//number of dish meals in every course
    //6 arrays - 2 arrays for each 3 courses,one for dish name and one for dish price
    static String[] nameOfDishFirstCourse = new String[NUMDISHEDPERCOURSE];
    static String[] nameOfDishMainCourse = new String[NUMDISHEDPERCOURSE];
    static String[] nameOfDishDessert = new String[NUMDISHEDPERCOURSE];

    static int[] priceFirstCourse = new int[NUMDISHEDPERCOURSE];
    static int[] priceMainCourse = new int[NUMDISHEDPERCOURSE];
    static int[] priceDessert = new int[NUMDISHEDPERCOURSE];

    public MenuFromDoc() throws Exception {
        read();
    }

    /**
     * get file function
     * @return - file contents
     */
    private File getFile() {
        String filePath = "src/main/java/com/example/maman13part2real/MenuTextMAMAN";
        File file = new File(filePath);
        return file;
    }

    /**
     * read the file and split into 6 arrays, 2 arrays for each 3 courses,one for dish name and one for dish price
     * @throws Exception  -if there's problems with file contents or upload
     */
    public void read() throws Exception {
        File file = getFile();
        if (file == null) {
            throw fileEmpty;
        }

        try (Scanner scanner = new Scanner(file)) {
            int firstCourseCount = 0;
            int mainCourseCount = 0;
            int dessertCount = 0;

            while (scanner.hasNextLine()) {
                String dish = scanner.nextLine().trim();
                String course = scanner.nextLine().trim();
                int price = Integer.parseInt(scanner.nextLine().trim());

                switch (course.toLowerCase()) {
                    case "first course":
                        nameOfDishFirstCourse[firstCourseCount] = dish;
                        priceFirstCourse[firstCourseCount] = price;
                        firstCourseCount++;
                        break;
                    case "main course":
                        nameOfDishMainCourse[mainCourseCount] = dish;
                        priceMainCourse[mainCourseCount] = price;
                        mainCourseCount++;
                        break;
                    case "dessert":
                        nameOfDishDessert[dessertCount] = dish;
                        priceDessert[dessertCount] = price;
                        dessertCount++;
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid course: " + course);
                }
            }

            System.out.println("File loaded successfully!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * print function to print contents of dish and price for each course
     * @param arrDish - array of dishes of a course
     * @param arrPrice  - array of dish prices corresponding with dish name array previously mentioned
     * @return - string  contents of dish and price for each course
     */
    private static String printArr(String[] arrDish, int[] arrPrice) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arrDish.length; i++) {
            sb.append("\n").append(" Dish is: ").append(arrDish[i]).append("\n Price is: ").append(arrPrice[i]).append("\n");
        }
        return sb.toString();
    }
    /**
     * get the dish price chosen by user
     * @param choiceCourse - course selected
     * @param choiceDishNum - number dish selected
     * @return - int price of dish name chosen
     */
    public int dishPressedPrice(int choiceCourse,int choiceDishNum){
        switch (choiceCourse){
            case 1:
                 return priceFirstCourse[choiceDishNum];
            case 2:
                return priceMainCourse[choiceDishNum];
            case 3:
                return priceDessert[choiceDishNum];
            default:
                return 0;
        }
    }

    /**
     * get the dish name chosen by user
     * @param choiceCourse - course selected
     * @param choiceDishNum - number dish selected
     * @return - string of dish name chosen
     */
    public String dishPressedName(int choiceCourse,int choiceDishNum){
        switch (choiceCourse){
            case 1:
                return nameOfDishFirstCourse[choiceDishNum];
            case 2:
                return nameOfDishMainCourse[choiceDishNum];
            case 3:
                return nameOfDishDessert[choiceDishNum];
            default:
                return "";
        }
    }

    /**
     * print menu of course once selected
     * @param chosenCourse - chosen course
     * @return - the dishes offered for the course selected
     */
    public static String printCourseMenu(int chosenCourse){
        switch(chosenCourse){
            case 1:
                return "First course: " + printArr(nameOfDishFirstCourse,priceFirstCourse);
            case 2:
                return "Main course: "+ printArr(nameOfDishMainCourse,priceMainCourse);
            case 3:
                return "Dessert: " + printArr(nameOfDishDessert,priceDessert);
            default:
                return "Hello and welcome!\n" +
                        "please choose from the menu\n" +
                        "you must select course->amount->dish\n" +
                        "\t!make sure to keep it in that order!";
        }
    }
}