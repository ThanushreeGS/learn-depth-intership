
import java.util.ArrayList;
import java.util.Scanner;

class Library {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            ArrayList<String> books = new ArrayList<>();
            ArrayList<String> issuedBooks = new ArrayList<>();
            ArrayList<Integer> bookIds = new ArrayList<>();
            ArrayList<String> bookAuthors = new ArrayList<>();
            while (true) {
                System.out.println("---- Library Menu -----");
                System.out.println("1->add book\n2->view book\n3->issue book\n4->return book\n5->exit");
                System.out.print("enter your choice:");
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1 -> {
                        System.out.println("enter the book id:");
                        int bookId = sc.nextInt();
                        bookIds.add(bookId);
                        sc.nextLine();
                        System.out.print("enter the name of the book:");
                        String bookName = sc.nextLine();
                        books.add(bookName);
                        System.out.print("enter the author of the book:");
                        String bookAuthor = sc.nextLine();
                        bookAuthors.add(bookAuthor);
                        System.out.println("book added successfully\n");
                        break;
                    }
                    case 2 -> {
                        System.out.println("the available books in the library are:");
                        for (int i = 0; i < books.size(); i++) {
                            System.out.println("bookid:" + bookIds.get(i) + "\t" + "book name:" + books.get(i) + "\t\t" + "author:" + bookAuthors.get(i));
                            System.out.println();
                        }
                        break;
                    }
                    case 3 -> {
                        System.out.print("enter the book name to issue:");
                        String issue = sc.nextLine();
                        if (books.contains(issue)) {
                            books.remove(issue);
                            issuedBooks.add(issue);
                            bookAuthors.remove(issue);
                            System.out.println("book issued successfully\n");
                        } else {
                            System.out.println("book not found\n");
                        }
                        break;
                    }

                    case 4 -> {
                        System.out.print("enter the book name to return:");
                        String returnBook = sc.nextLine();
                        if (issuedBooks.contains(returnBook)) {
                            issuedBooks.remove(returnBook);
                            books.add(returnBook);
                            System.out.println("book returned successfully\n");
                        } else {
                            System.out.println("book not found\n");
                        }
                        break;
                    }
                    case 5 -> {
                        System.out.println("revisit again");
                        return;
                    }
                    default -> {
                        System.out.println("invalid choice\n");
                    }
                }
            }
        }

    }
}
