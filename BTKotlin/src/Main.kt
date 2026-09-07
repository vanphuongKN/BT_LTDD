
import java.util.Scanner
import kotlin.math.max

fun main() {
    val scanner = Scanner(System.`in`)
    val studentName = "Vo Van Phuong"
    val studentId = "2415053122230"
    println("--- NHẬP THÔNG TIN ĐIỂM ---")
    print("Nhập điểm Math: ")
    val math = scanner.nextDouble()
    print("Nhập điểm Programming: ")
    val programming = scanner.nextDouble()
    print("Nhập điểm Database: ")
    val database = scanner.nextDouble()
    val totalScore = math + programming + database
    val averageScore = totalScore / 3.0
    val highestScore = max(math, max(programming, database))
    val isPassed = averageScore >= 5.0
    println("\n--- KẾT QUẢ ĐÁNH GIÁ ---")
    println("Sinh viên: $studentName")
    println("MSSV: $studentId")
    println("------------------------")
    println("1. Tổng điểm: $totalScore")
    println("2. Điểm trung bình (GPA): String.format(\"%.2f\", averageScore)")
    println("3. Điểm cao nhất: $highestScore")
    print("4. Sinh viên có đạt không? ")
    if (isPassed) {
        println("Có (Đạt)")
    } else {
        println("Không (Chưa đạt)")
    }
}