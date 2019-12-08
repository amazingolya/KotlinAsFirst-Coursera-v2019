@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import java.lang.Integer.min
import java.lang.Math.pow
import kotlin.math.*

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var count = 1
    var divisionResult = n / 10
    if (divisionResult == 0) return count
    while (divisionResult > 0) {
        divisionResult /= 10
        count += 1
    }

    return count
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var fibNumber1 = 0
    var fibNumber2 = 1
    for (i in 1..n) {
        var fibNumber = fibNumber1 + fibNumber2
        fibNumber1 = fibNumber2
        fibNumber2 = fibNumber

    }
    return fibNumber1
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var max = max(m, n)
    var min = min(m, n)
    if (max % min == 0) return max
    var lcm = 0
    do {
        lcm += min
    } while (lcm % max != 0)

    return lcm
}

fun maxCommonDivider(m: Int, n: Int): Int {
    var a1 = max(m, n)
    var a2 = min(m, n)
    while (a2 > 0) {
        val temp = a1 % a2
        a1 = a2
        a2 = temp
    }
    return a1
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var divisor = 1
    if (n % 2 == 0) return divisor + 1
    do {
        divisor += 2
    } while (n % divisor != 0)
    return divisor
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var divisor = n - 2
    if (n % 2 == 0) return n / 2
    while (n % divisor != 0) {
        divisor -= 2
    }
    return divisor
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean = maxCommonDivider(m, n) == 1
/*  if (m % 2 == 0 && n % 2 == 0) return false
var count = 3
val max = max(m, n)
val min = min(m, n)
while (count <= min) {
if (max % count == 0 && min % count == 0) return false
count += 2
}
return true*/

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    for (i in m..n) {
        val sqrt = sqrt(i.toDouble())
        if (floor(sqrt) == ceil(sqrt)) return true
    }
    return false
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var count = 0
    var varX = x
    while (varX != 1) {
        if (varX % 2 == 0) varX /= 2
        else varX = 3 * varX + 1
        count++
    }
    return count
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {
    var a = x % (2 * PI)
    var n = 0
    var current = a
    var sin = current
    while (abs(current) >= eps) {
        n++
        current *= ((-1) * a * a) / ((2 * n + 1) * 2 * n)
        sin += current
    }
    return sin
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    var a = x % (2 * PI)
    var n = 1
    var current = 1.toDouble()
    var cos = 1.toDouble()
    while (abs(current) >= eps) {
        current *= (a * a * (-1) / (2 * n * (2 * n - 1)))
        cos += current
        n++
    }
    return cos
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var numberOfDigits = digitNumber(n) - 1
    var reversedNumber = 0
    var temp = n
    while (numberOfDigits >= 0) {
        /**reversedNumber += (temp % 10) * pow(10.toDouble(), numberOfDigits.toDouble()).toInt()**/
        reversedNumber = reversedNumber * 10 + temp % 10
        temp /= 10
        numberOfDigits--

    }
    return reversedNumber
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    val digitNumber = digitNumber(n)
    if (digitNumber == 1) return true
    for (i in 0 until digitNumber / 2) {
        if (digitAt(n, i) != digitAt(n, digitNumber - i - 1)) {
            return false
        }
    }
    return true
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    val digits = digitNumber(n) - 2
    for (i in 0..digits) {
        val d1 = digitAt(n, i)
        val d2 = digitAt(n, i + 1)
        if (d1 != d2) return true
    }
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */

fun digitAt(num: Int, position: Int): Int {
    return (num / pow(10.toDouble(), position.toDouble()).toInt()) % 10
}

fun squareSequenceDigit(n: Int): Int {

    var count = 0
    var i = 0
    while (count < n) {
        i++
        count += digitNumber(i * i)
    }
    val digit = digitAt(i * i, count - n)
    return digit
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var count = 0
    var previous = 0
    var current = 1
    var tests = ""
    while (count < n) {
        var next = current + previous
        previous = current
        current = next
        tests += previous
        count += digitNumber(previous)
    }
    return digitAt(previous, count - n)
}
