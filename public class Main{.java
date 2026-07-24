

Page
1
of 3
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
public static void main(String[] args) {
Scanner scanner = new Scanner(System.in);
System.out.print("Enter the number of students: ");
int totalStudents = scanner.nextInt();
scanner.nextLine();
String[] studentNames = new String[totalStudents];
int[] finalScores = new int[totalStudents];
System.out.println("\n--- Enter Student Data ---");
for (int i = 0; i < totalStudents; i++) {
System.out.printf("Enter name for student %d: ", i + 1);
studentNames[i] = scanner.nextLine();
while (true) {
System.out.printf("Enter final score for %s (0-100): ",
studentNames[i]);
if (scanner.hasNextInt()) {
int score = scanner.nextInt();
if (score >= 0 && score <= 100) {
finalScores[i] = score;
break;
}
}
scanner.nextLine();
System.out.println("Invalid input. Please enter a number between 0
and 100.");
}
scanner.nextLine();
System.out.println();
}
scanner.close();
analyzeGrades(studentNames, finalScores);
}
public static void analyzeGrades(String[] names, int[] scores) {
int totalStudents = names.length;
int totalScoreSum = 0;
int passedCount = 0;
int failedCount = 0;
int highestScore = -1;
for (int i = 0; i < totalStudents; i++) {
int score = scores[i];
totalScoreSum += score;
if (score >= 60) {
passedCount++;
} else {
failedCount++;
}
if (score > highestScore) {
highestScore = score;
}
}
double classAverage = (double) totalScoreSum / totalStudents;
boolean averageBelow70 = classAverage < 70;
List<String> topStudents = new ArrayList<>();
for (int i = 0; i < totalStudents; i++) {
if (scores[i] == highestScore) {
topStudents.add(names[i]);
}
}
System.out.println("=========================================");
System.out.println(" GRADE ANALYSIS REPORT ");
System.out.println("=========================================");
System.out.println("\n--- Student Letter Grades ---");
for (int i = 0; i < totalStudents; i++) {
String letterGrade = getLetterGrade(scores[i]);
System.out.printf("%-15s | Score: %3d | Grade: %s%n", names[i],
scores[i], letterGrade);
}
System.out.println("\n--- Top Performing Student(s) ---");
System.out.println("Highest Score: " + highestScore);
System.out.println("Student(s) : " + String.join(", ", topStudents));
System.out.println("\n--- Class Statistics ---");
System.out.printf("Class Average (Mean) : %.2f%n", classAverage);
System.out.println("Students Passed (>= 60) : " + passedCount);
System.out.println("Students Failed (< 60) : " + failedCount);
System.out.println("Class Average Below 70? : " + (averageBelow70 ? "YES"
: "NO"));
System.out.println("=========================================");
}
private static String getLetterGrade(int score) {
if (score >= 98 && score <= 100) return "A+";
if (score >= 92 && score <= 97) return "A";
if (score >= 87 && score <= 91) return "B+";
if (score >= 81 && score <= 86) return "B";
if (score >= 77 && score <= 80) return "C+";
if (score >= 71 && score <= 76) return "C";
if (score >= 60 && score <= 70) return "D";
return "F";
}
}
