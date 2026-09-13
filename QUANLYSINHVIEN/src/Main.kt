data class Student(val studentId: String, val fullName: String, val age: Int,val major: String, val gpa: Double ){
    override fun toString(): String {
        return "ID: $studentId, FullName: $fullName, Age: $age, major: $major, GPA: $gpa"
    }
}
class StudentManager {
    private val students = mutableListOf<Student>()
    init{
        students.add(Student("SV001", "Võ Văn Phuong",20, "Information Technolohy", 8.5))
        students.add(Student("SV002", "Võ Thị Mai",21, "Information Technolohy", 9.5))
        students.add(Student("SV003", "Nguyễn Bão Long",19, "Information Technolohy", 6.5))
        students.add(Student("SV004", "Nguyễn Hoàng Anh ",22, "Information Technolohy", 7.5))
        students.add(Student("SV005", "Võ Quốc Khánh",23, "Information Technolohy", 4.5))
    }
    fun addStudent(){
        println("Thêm Sinh Viên Mới ")
        print("Nhập Student ID: ")
        val id = readlnOrNull() ?: ""
        print("Nhập Full Name : ")
        val name = readlnOrNull() ?: ""
        var age = 0
        while (true){
            print("Nhập Age: ")
            age = readlnOrNull()?.toInt() ?: -1
            if(age >0) break
            println("Tuổi không hợp lệ.")
        }
        print("Nhập Major: ")
        val major = readlnOrNull() ?: ""
        var gpa = 0.0
        while (true){
            print("Nhập GPA: ")
            gpa = readlnOrNull()?.toDouble() ?: -1.0
            if(gpa in 0.0..10.0) break
            println("GPA không hợp lệ ")
        }
        students.add(Student( id, name, age, major, gpa))
        println("Đã thêm sinh viên thành công")

    }
    private fun printList(list: List<Student>){
        if(list.isEmpty()){
            println("Không có sinh viên nào thoả mãn ")
            return
        }
        list.forEach {println(it)}
    }
    fun displayStudentsOption(){

        println(" Hiện Thị Danh Sánh ")
        println("1. Sắp xếp theo GPA giảm dần ")
        println("2. Hện thị 3 sinh viên có GPA cao nhất")
        println("3. Sắp xếp theo tuổi ")
        println("4. Sắp xếp theo tên ")
        print("Chọn cách hiện thị: ")
        when (readlnOrNull()){
            "1" ->printList(students.sortedByDescending { it.gpa })
            "2" -> printList(students.sortedByDescending { it.gpa }.take(3))
            "3" -> printList(students.sortedBy { it.age })
            "4" -> printList(students.sortedBy { it.fullName })
            else -> printList(students)

        }

    }
    fun searchStudents(){
        println("1. Tìm sinh viên theo một phần tên ")
        println("2. Tìm tất cả sinh viên thuộc một ngành ")
        println("3. Tìm sinh viên có GPA nằm trong khoảng 7.0 -> 8.5")
        println("4. Tìm sinh viên lớn tuổi nhất ")
        print("Chọn cách hiển thị:  ")

        when (readlnOrNull()){
            "1" ->{
                print("Nhập tên cần tìm: ")
                val keyword = readlnOrNull() ?: ""
                printList(students.filter { it.fullName.contains(keyword, true) })
            }
            "2" ->{
                print("Nhập ngành cần tìm: ")
                val major = readlnOrNull() ?: ""
                printList(students.filter { it.major.equals(major, true) })

            }
            "3" ->{
                printList(students.filter { it.gpa in 7.0..8.5 })
            }
            "4" ->{
                val oldest = students.maxByOrNull { it.age }
                if(oldest != null)
                    println(oldest)
                else
                    println("Danh sách trống ")

            }
            else ->  println("Lựa chọn không hợp lệ ")

        }
    }
    fun calculateGPA(){
        println("1. Tính GPA trung bình của sinh viên ngành được giao")
        println("2. Đếm số sinh viên có GPA ≥ 8.0")
        println("3. Đếm số sinh viên có GPA < 5.0 ")
        print("Chọn cách hiển thị: ")

        when (readlnOrNull()){
            "1" ->{
                print("Nhập tên ngành cần tính GPA: ")
                val major = readlnOrNull() ?: ""
                val filtered = students.filter { it.major.equals(major, ignoreCase = true) }
                val avg = if (filtered.isEmpty()) 0.0 else filtered.map { it.gpa }.average()
                println("=> Trung bình GPA ngành $major: $avg")

            }
            "2" -> println("Số lượng sinh viên có GPA >= 8.0: ${students.count{it.gpa >=8}}")
            "3" -> println("Số lượng sinh viên có GPA < 5.0: ${students.count{it.gpa < 5.0}} ")
            else -> println("Lựa chọn không hợp lệ")
        }
    }
    fun findHighestGPA(){
        val best = students.maxByOrNull { it.age }
        if (best != null){
            println("Sinh viên có GPA cao nhất:\n $best")
        }else{
            println("Danh sách trống ")
        }
    }
    fun removeStudent(){
        print("Nhập Student ID cần xoá: ")
        val id = readlnOrNull() ?: ""
        val removed = students.removeIf{it.studentId == id}
        if(removed)
            println("Xoá thành công ")
    }



}
fun main(){
    val manager = StudentManager()
    while (true){
        println(" QUAN LÝ SINH VIÊN ")
        println("1. Add student ")
        println("2. Display all Student ")
        println("3. Search student ")
        println("4. Calculate average GPA ")
        println("5. Find student with highest GPA")
        println("6. Remove student ")
        println("0. Exit")
        print("Chọn lựa chọn : ")

        when (readlnOrNull()){
            "1" -> manager.addStudent()
            "2" -> manager.displayStudentsOption()
            "3" -> manager.searchStudents()
            "4" -> manager.calculateGPA()
            "5" -> manager.findHighestGPA()
            "6" -> manager.removeStudent()
            "0" -> return

        }
    }
}