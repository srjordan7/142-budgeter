import java.util.*;

public class Budgeter {
   public static final int DAYS_IN_MONTH = 31;
   
   public static void main(String[] args) {
      Scanner console = new Scanner(System.in);
      introMessage();
      int incomeCat = categories(console, "income");
      double totalIn = amounts(console, incomeCat, "income");
      double monthDay = dailyMonthly(console);
      int expenseCat = categories(console, "expense");
      double totalEx = amounts(console, expenseCat, "expense");
      double monthlyEx = totals(console, totalIn, monthDay, totalEx, expenseCat);
      spenderSaver(totalIn, monthlyEx);
   }
   
   // prints the intro message
   public static void introMessage() {
      System.out.println("This program asks for your monthly income and");
      System.out.println("expenses, then tells you your net monthly income.");
      System.out.println();
   }
   
   // asks for number of categories
   // returns number of categories
   // parameters:
   //    Scanner console - the scanner for user input
   //    String incomeExpense - if asking for income or expense
   public static int categories(Scanner console, String incomeExpense) {
      System.out.print("How many categories of " + incomeExpense + "? ");
      int categories = console.nextInt();
      return categories;
   }
   
   // asks for individual amounts
   // returns total amounts
   // parameters:
   //    Scanner console - the scanner for user input
   //    int categories - the number of categories
   //    String incomeExpense - if asking for income or expense
   public static double amounts(Scanner console, int categories, String incomeExpense) {
      double total = 0;
      for(int i = 1; i <= categories; i++) {
         System.out.print("    Next " + incomeExpense + " amount? $ ");
         double single = console.nextDouble();
         total += single;
      }
      System.out.println();
      return total;
   }
   
   // asks for daily or monthly expenses
   // returns if expenses are daily or monthly
   // parameters:
   //    Scanner console - the scanner for user input
   public static double dailyMonthly(Scanner console) {
      System.out.print("Enter 1) monthly or 2) daily expenses? ");
      double monthDay = console.nextDouble();
      return monthDay;
   }
   
   // prints totals and daily amounts
   // returns monthly expenses
   // parameters:
   //    Scanner console - the scanner for user input
   //    double totalIn - the total income
   //    double monthDay - if expenses are monthly or daily
   //    double totalEx - the total expenses
   //    int expenseCat - the number of expense categories
   public static double totals(Scanner console, double totalIn, double monthDay,
                               double totalEx, int expenseCat) {
      double dailyIn = totalIn / DAYS_IN_MONTH;
      dailyIn = round2(dailyIn);
      System.out.println("Total income = $" + totalIn + " ($" + dailyIn + "/day");
      double monthlyEx = 0;
      double dailyEx = 0;
      if(monthDay == 1) {
         monthlyEx = totalEx;
         monthlyEx = round2(monthlyEx);
         dailyEx = totalEx / DAYS_IN_MONTH;
         dailyEx = round2(dailyEx);
      } else {
         dailyEx = totalEx;
         dailyEx = round2(dailyEx);
         monthlyEx = dailyEx * DAYS_IN_MONTH;
         monthlyEx = round2(monthlyEx);
      }
      System.out.println("Total expenses = $" + monthlyEx + " ($" + dailyEx + "/day)");
      System.out.println();
      return monthlyEx;
   }
   
   // prints if user is a spender or saver
   // parameters:
   //    double totalIn - the total income
   //    double monthlyEx - the total expenses
   public static void spenderSaver(double totalIn, double monthlyEx) {
      double difference = totalIn - monthlyEx;
      difference = round2(difference);
      
      if(difference > 0) {
         System.out.println("You earned $" + difference + " more than you spent this month.");
         if(difference > 250) {
            System.out.println("You're a big saver.");
            System.out.println("Treat yourself a little next month! You deserve it.");
         } else {
            System.out.println("You're a saver.");
            System.out.println("Nice work! Keep it up.");
         }
      } else {
         difference = Math.abs(difference);
         System.out.println("You spent $" + difference + " more than you earned this month.");
         if(difference > -250) {
            System.out.println("You're a spender.");
            System.out.println("Make sure to watch your budget!");
         } else {
            System.out.println("You're a big spender.");
            System.out.println("You should try to save a bit more next month.");
         }
      }
   }
   
   // Rounds the given value to two decimal places and returns the result.
   //
   // double num - the number to round
   public static double round2(double num) {
      return Math.round(num * 100.0) / 100.0;
   }
}