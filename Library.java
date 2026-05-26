import java.util.ArrayList;

public class Library implements LibraryOperations {

    private ArrayList<Book> books;
    private ArrayList<Student> students;

    public Library() {
        books = new ArrayList<>();
        students = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully!");
    }

    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student added successfully!");
    }

    public void viewBooks() {

        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        for (Book b : books) {
            b.displayBook();
        }
    }

    public Student findStudent(int id) {

        for (Student s : students) {
            if (s.id == id) {
                return s;
            }
        }

        return null;
    }

    public Book findBook(int id) {

        for (Book b : books) {
            if (b.getBookId() == id) {
                return b;
            }
        }

        return null;
    }

    @Override
    public void issueBook(int studentId, int bookId) {

        try {

            Student student = findStudent(studentId);
            Book book = findBook(bookId);

            if (student == null) {
                throw new Exception("Invalid student ID");
            }

            if (book == null) {
                throw new Exception("Book not found");
            }

            if (!book.isAvailable()) {
                throw new Exception("Book already issued");
            }

            student.borrowBook(book);
            book.setAvailable(false);

            System.out.println("Book issued successfully!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void returnBook(int studentId, int bookId) {

        try {

            Student student = findStudent(studentId);
            Book book = findBook(bookId);

            if (student == null) {
                throw new Exception("Invalid student ID");
            }

            if (book == null) {
                throw new Exception("Book not found");
            }

            student.returnBook(book);
            book.setAvailable(true);

            System.out.println("Book returned successfully!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewStudent(int studentId) {

        Student student = findStudent(studentId);

        if (student == null) {
            System.out.println("Student not found");
            return;
        }

        student.displayDetails();
    }
}
