fun Bai1(){
    println("Bài 1: In các số từ 1 đến 10")
    for( i in 1..10){
        print("$i ");
    }
}
fun Bai2(){
    println("\n\nBài 2: Tính tổng 1 + 2 + 3 + ... + 100")
    var sum = 0
    for(i in 1..100){
        sum +=i
    }
    println("Tổng từ 1 đến 100 là $sum")
}
fun Bai3(){
    println("\n\nBài 3: In các số chẵn từ 1 đến 20")
    for( i in 2..20 step 2)
        print("$i ")
}
fun Bai3While(){
    println("\n\nBài 3: In các số chẵn từ 1 đến 20 dùng While")
    var i =2;
    while ( i <= 20 ){
        print("$i ")
        i +=2
    }
}

fun main() {
    Bai1()
    Bai2()
    Bai3()
    Bai3While()
}