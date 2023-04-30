/**
 * Order class to keep track of clients orders with linked list
 */
package com.example.maman13part2real;

import java.util.LinkedList;

public class Order {
        private String dish;
        private int amount;
        private int price;
        private int sum;
        private static LinkedList<Order> linkedList = new LinkedList<>();

        /**
         * build function with no arguments
         */
        public Order(){
            dish = "Nothing";
            amount = 0;
            this.price = 0;
            this.sum = 0;
        }

    /**
     * build function with three arguments
     * @param price - price of dish
     * @param amount - amount of dishes
     * @param dish - dish name
     */
        public Order(int price, int amount,String dish) {
            this.dish = dish;
            this.amount = amount;
            this.price = price;
            this.sum = price * amount;

        }

    /**
     * delete linked list function for when client terminates or finishes order
     */
    public void deleteLinkedList() {
        linkedList = new LinkedList<>();
    }

    /**
     * add to linked list of dishes ordered by client
     * @param order - the order to add
     */
    public void AddToLinkedList(Order order) {
            linkedList.add(new Order(order.getPrice(), order.getAmount(), order.getDish()));
        }

    /**
     * string function
     * @return the full order
     */
    @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < linkedList.size(); i++) {
                Order line = linkedList.get(i);
                sb.append("\t\nThe item ordered is: ").append(line.getDish())
                        .append("\t\nThe price is: ").append(line.getPrice())
                        .append("\t\nThe quantity is: ").append(line.getAmount())
                        .append("\t\nThe sum is: ").append(line.getSum())
                        .append("\n");
            }
            return sb.toString();
        }

    /**
     * get functions
     * @return amount of dish ordered
     */
    public int getAmount() {
        return amount;
    }

    /**
     *
     * @return price of dish
     */
    public int getPrice() {
        return price;
    }

    /**
     *
     * @return sum - amount*price
     */
    public int getSum() {
        return sum;
    }

    public String getDish() {
        return dish;
    }
}

