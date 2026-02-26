
import java.util.Scanner;

class Question {

    static Scanner sc = new Scanner(System.in);

    void startQuiz() {
        int ans;
        int score = 0;
        //Question 1
        System.out.println("----------lets get started--------");

        System.out.println("1)which is right way to define a string in java?");
        System.out.println("\n 1->string\t2->STRING\t3->String\t4->char");
        System.out.print("ANSWER:");
        ans = sc.nextInt();
        if (ans == 3) {
            System.out.println("CORRECT ANSWER");
            score++;
        } else {
            System.out.println("the answer is String");
        }
        System.out.println("-------------------------------");
        //Question 2
        System.out.println("2) what is method overriding in java?");
        System.out.println("1)same method name");
        System.out.println("\n 2->same method name with same implementation");
        System.out.println("\n 3->same method name with different parameter");
        System.out.println("\n4->same method name with diffrent implementation");
        System.out.print("ANSWER:");
        ans = sc.nextInt();
        if (ans == 4) {
            System.out.println(" CORRECT ANSWER");
            score++;
        } else {
            System.out.println("\n the answer is same method name with diffrent implementation");
        }
        System.out.println("-------------------------------");
        //Question 3
        System.out.println("\n 3)which one IS java oops principle?");
        System.out.println("\n 1->INHERITANCE");
        System.out.println("2->POLYMORPHISM");
        System.out.println("3->ENCAPSULATION");
        System.out.println("4->ALL OF THE ABOVE");
        System.out.print("ANSWER:");
        ans = sc.nextInt();
        if (ans == 4) {
            System.out.println("CORRECT ANSWER");
            score++;
        } else {
            System.out.println("\n the correct answer is ALL OF THE ABOVE ");
        }
        System.out.println("-------------------------------");
        //Question 4
        System.out.println("4) a student assigns the value 33.5 to the variable x what must be  the data type of x");
        System.out.println("1->int\t2->char\t3->double\t4->float");
        System.out.print("ANSWER:");
        ans = sc.nextInt();
        if (ans == 3) {
            System.out.println("CORRECT ANSWER");
            score++;
        } else {
            System.out.println("the answer is double");
        }
        System.out.println("-------------------------------");
        //Question 5
        System.out.println("which of this iS frontend?");
        System.out.println("\n 1->HTML");
        System.out.println("\n 2->SQL");
        System.out.println("\n 3->JAVA");
        System.out.println("\n 4->PYTHON");
        System.out.print("ANSWER:");
        ans = sc.nextInt();
        if (ans == 1) {
            System.out.println("CORRECT ANSWER");
            score++;
        } else {
            System.out.println("THE correct answer is HTML");
        }
        //display
        System.out.println("------------DONE WITH THE QUIZ-------------------");
        System.out.println("your score is:" + score + "/5");
        if (score >= 3) {
            System.out.println("CONGRAGULATION'S!YOU  WON");
        } else {
            System.out.println("BETTER LUCK NEXT TIME" + score);
        }

    }
}

public class Quiz {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        Question qz = new Question();
        System.out.println("---------WELCOME TO QUIZ HAUNT---------");

        do {
            qz.startQuiz();
            System.err.println("restart");
            System.out.println("1->YES\t2->NO");
            System.out.println("ENTER YOUR CHOICE:");
            choice = scanner.nextInt();
        } while (choice == 1);
        System.out.println("========HOPE YOU HAD A GREAT TIME========");

    }
}
