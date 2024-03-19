package javathuchanh;

import java.io.*;
import java.util.*;

class Student implements Comparable<Student> {
    String maSinhVien;
    String hoTen;
    String gioiTinh;
    double diemPython;
    double diemJava;
    double diemTrungBinh;
    String kqua;

    public Student(String maSinhVien, String hoTen, String gioiTinh, double diemPython, double diemJava) {
        this.maSinhVien = maSinhVien;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.diemPython = diemPython;
        this.diemJava = diemJava;
        this.diemTrungBinh = (diemPython + diemJava*2) / 3;
        if(this.diemTrungBinh>=5){
            this.kqua = "Đậu";
        }else if(this.gioiTinh.equals("nữ")) {
            this.kqua = "Đậu";
        }else {
            this.kqua = "Trượt";
        }
    }

    @Override
    public int compareTo(Student other) {
        return Double.compare(other.diemTrungBinh, this.diemTrungBinh);
    }

    @Override
    public String toString() {
        return String.format("Mã sinh viên: %s, Họ tên: %s, Giới tính: %s, Điểm python: %s, Điểm java: %s, Điểm trung bình: %.2f, Điểm tb: %s", maSinhVien, hoTen, gioiTinh, diemPython, diemJava, diemTrungBinh, kqua);
    }
}

class SortStudents {
//    public static void sort() {
//        String inputFile = "input.txt";
//        String outputFile = "output.txt";
//
//        List<Student> students = new ArrayList<>();
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
//             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] parts = line.split(",");
//                String maSinhVien = parts[0];
//                String hoTen = parts[1];
//                String gioiTinh = parts[2];
//                double diemPython = Double.parseDouble(parts[3]);
//                double diemJava = Double.parseDouble(parts[4]);
//
//                Student student = new Student(maSinhVien, hoTen, gioiTinh, diemPython, diemJava);
//                students.add(student);
//            }
//            for (Student student : students) {
//                System.out.println(student.toString());
//            }
//
//
//            System.out.println("Đã sắp xếp và lưu danh sách sinh viên vào file " + outputFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    public static List<Student> readStudentsFromFile(String filename) {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String maSinhVien = parts[0];
                String hoTen = parts[1];
                String gioiTinh = parts[2];
                double diemPython = Double.parseDouble(parts[3]);
                double diemJava = Double.parseDouble(parts[4]);

                Student student = new Student(maSinhVien, hoTen, gioiTinh, diemPython, diemJava);
                students.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }
    public static void printStudents(List<Student> students) {
        for (Student student : students) {
            System.out.println(student.toString());
        }
    }
    public static void printStudentsD(List<Student> students) {
        boolean check = true;
        for (Student student : students) {
            if (student.kqua.equals("Đậu")){
                System.out.println(student.toString());
                check = false;
            }
        }
        if (check){
            System.out.println("Không có sinh viên Đậu");
        }
    }
    public static void printStudentsT(List<Student> students) {
        boolean check = true;
        for (Student student : students) {
            if (student.kqua.equals("Trượt")){
                System.out.println(student.toString());
                check = false;
            }
        }
        if (check){
            System.out.println("Không có sinh viên Trượt");
        }
    }
    public static void printStudentsFile(List<Student> students) {
        String outputFile = "output.txt";
        Collections.sort(students);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (Student student : students) {
                writer.write(student.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<Student> searchStudentsByName(List<Student> students, String name) {
        boolean check = true;
        List<Student> foundStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.hoTen.equals(name)) {
                foundStudents.add(student);
                check = false;
            }
        }
        if(check){
            System.out.println("Không tìm thấy sinh viên");
        }else {
            System.out.println("\nĐã tìm thấy sinh viên '" + name + "':");
        }
        return foundStudents;
    }
    public static void main(String[] args) {
        String inputFile = "input.txt";
        List<Student> students = readStudentsFromFile(inputFile);
        printStudents(students);
        printStudentsFile(students);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập tên muốn tìm kiếm: ");
        String searchName = scanner.nextLine();
        List<Student> foundStudents = searchStudentsByName(students, searchName);
        printStudents(foundStudents);
        System.out.println("Danh sách học sinh đậu: \n");
        printStudentsD(students);
        System.out.println();
        System.out.println("Danh sách học sinh trượt: \n");
        printStudentsT(students);
    }
}